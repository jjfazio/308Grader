package controller.spreadsheet;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import model.assignments_categories.Assignment;
import model.assignments_categories.Category;
import model.assignments_categories.CategoryContainer;
import model.assignments_categories.Grade;
import model.spreadsheet.GradingScheme;
import model.spreadsheet.LatePolicy;
import model.spreadsheet.SpreadsheetCourse;
import model.users.Student;

/**
 * This class controls the Spreadsheet actions. Any action
 * done to a spreadsheet will happen here
 * @author jamesfazio
 */
public class SpreadsheetController implements Observer {
    
    @FXML
    /** Table that represents the Spreadsheet */
    private TableView<Student> table;

    @FXML
    /** First name column in the table */
    private TableColumn<Student, String> firstNameColumn;
    
    @FXML
    /** Last name column in the table */
    private TableColumn<Student, String> lastNameColumn;

    @FXML
    /** User name column in the table */
    private TableColumn<Student, String> usernameColumn;

    @FXML
    /** User ID column in the table */
    private TableColumn<Student, String> userIDColumn;
    
    @FXML
    /** Year column in the table */
    private TableColumn<Student, String> yearColumn;
    
    private TableColumn<Student, String> totalCategoryCol;
    
    @FXML
    /** Major column in the table */
    private TableColumn<Student, String> majorColumn;
    
    /** Course represented by this spreadsheet */
    private SpreadsheetCourse course;
    
    /** List of student in the spreadsheet */
    private ObservableList<Student> studentList;
    
    
    @FXML
    /**
     * Called by FXML when the view is loaded, initializes the default
     * columns.
     */
    private void initialize() {
        studentList = FXCollections.observableArrayList();
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("userName"));
        userIDColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("gradeLevel"));
        majorColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("major"));
        
        table.setEditable(true);
    }


   /**
    * Must be called before displaying the view. Sets the course
    * to be used for this spreadsheet.
    * @param course - The SpreadsheetCourse for this Spreadsheet
    */
   public void setSpreadsheet(SpreadsheetCourse course) {
      this.course = course;
      
      totalCategoryCol = new TableColumn<Student, String>(
              course.getCategoryContainer().getRoot().getName());
      totalCategoryCol.setUserData(course.getCategoryContainer().getRoot());
      
      loadStudentContent(course.getStudentRoster());
      loadGradeColumns();
      
      System.out.println("Set up spreadsheet for " + course.getCourseInfo().getCourseName());
   }


   @Override
   /**
    * Called when a change is made to the model
    */
   public void update(Observable o, Object arg) {
       // If new students got added to the spreadsheet
       if (course.isStudentAdded())
           loadStudentContent(course.getAddedStudents());
       else if (course.isStudentDeleted())  {
           removeStudentContent(course.getStudentToDelete());
       }
       else if (o instanceof CategoryContainer) {
           totalCategoryCol.getColumns().clear();
           table.getColumns().remove(totalCategoryCol);
           loadGradeColumns();
       }
   }
   
   // Load all of the category/assignment columns 
   private void loadGradeColumns()
   {
       addCols(totalCategoryCol, course.getCategoryContainer().getRoot());
       table.getColumns().add(totalCategoryCol);
       
   }
   
   /**
    * Recursively adds columns to the Spreadsheet.
    * If there are assignments under a category, each assignment gets added
    * as a sub column. If there are sub categories underneath a category
    * the sub categories also get added as sub columns.
    * @param topCol The parent column
    * @param category The parent category
    */
   private void addCols(
        TableColumn<Student, String> topCol,
        Category category) {
       TableColumn<Student, String> assignmentCol;
       TableColumn<Student, String> subCatCol;
       
       for (Assignment assignment : category.getAssignments()) {
           assignmentCol = new TableColumn<Student, String>(assignment.getName());
           assignmentCol.setUserData(assignment);
           assignmentCol.setCellFactory(TextFieldTableCell.<Student>forTableColumn());
           assignmentCol.setCellValueFactory(new MyCallBack());
           assignmentCol.setOnEditCommit(new EditHandler());
           topCol.getColumns().add(assignmentCol);
       }
       
       if (category.getSubCategories() != null && 
               !category.getSubCategories().isEmpty()) {
           for (Category subCategory : category.getSubCategories()) {
               subCatCol = new TableColumn<Student, String>(subCategory.getName());
               subCatCol.setUserData(subCategory);
               topCol.getColumns().add(subCatCol);
               addCols(subCatCol, subCategory);
           }
       }
   }
   
   /**
    * CallBack for the assignment columns. For each assignment column
    * in the spreadsheet the associated grade of each student is displayed.
    * @author jamesfazio
    *
    */
   private class MyCallBack implements Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>> {
       @Override
       public ObservableValue<String> call(CellDataFeatures<Student, String> param)
       {
           HashMap<Assignment, Grade> grades = param.getValue().getGrades();
           Assignment assign = (Assignment) param.getTableColumn().getUserData();

           return grades.containsKey(assign) ? 
                   new SimpleStringProperty(String.format("%.2f",grades.get(assign).getScore()))
           :  new SimpleStringProperty(" - "); 
       }

   }
   
   private class EditHandler implements EventHandler<CellEditEvent<Student, String>> {
       @Override
       public void handle(CellEditEvent<Student, String> studentCell)
       {
           Grade grade;
           Assignment assign;
           Date turnedIn;
           Double score;
           //change 
           String letterGrade;
           Student student;
           
           score = Double.parseDouble(studentCell.getNewValue());
           letterGrade = "A";
           turnedIn = new Date();
           student = studentCell.getRowValue();
           
           assign = (Assignment) studentCell.getTableColumn().getUserData();
           grade = new Grade(turnedIn, score, letterGrade);
           
           student.addGrade(assign, grade);
       }
   }
   

   /**
    * Loads the given list of students into the Spreadsheet
    * @param studentToRemove
    */
   private void removeStudentContent(Student studentToRemove) {
       studentList.remove(studentToRemove);
   }


   /**
    * Loads the given list of students into the Spreadsheet
    * @param students
    */
   private void loadStudentContent(List<Student> students) {
       studentList.addAll(students);
       table.setItems(studentList);
   }
}
