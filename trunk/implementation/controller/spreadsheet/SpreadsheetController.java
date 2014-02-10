package controller.spreadsheet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.assignments_categories.Assignment;
import model.assignments_categories.Category;
import model.assignments_categories.Grade;
import model.spreadsheet.SpreadsheetCourse;
import model.users.Student;

/**
 * This class controls the Spreadsheet actions. Any action
 * done to a spreadsheet will happen here
 * @author jamesfazio
 */
public class SpreadsheetController implements Observer {
    @FXML
    private TableView<Student> table;

    @FXML
    private TableColumn<Student, String> firstNameColumn;
    
    @FXML
    private TableColumn<Student, String> lastNameColumn;

    @FXML
    private TableColumn<Student, String> usernameColumn;

    @FXML
    private TableColumn<Student, String> userIDColumn;
    
    @FXML
    private TableColumn<Student, String> yearColumn;
    
    @FXML
    private TableColumn<Student, String> majorColumn;
    
    private LinkedList<Category> categories;
    
    private SpreadsheetCourse course;
    
    private ObservableList<Student> studentList;
    
    @FXML
    private void initialize() {
        studentList = FXCollections.observableArrayList();
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("userName"));
        userIDColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("gradeLevel"));
        majorColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("major"));
        categories = new LinkedList<Category>();
        
    }


   /**
    * Must be called before displaying the view. Sets the course
    * to be used for this spreadsheet.
    * @param course - The SpreadsheetCourse for this Spreadsheet
    */
   public void setSpreadsheet(SpreadsheetCourse course) {
      this.course = course;
      loadStudentContent(course.getStudentRoster());
      loadGradeColumns();
      
      System.out.println("Set up spreadsheet for " + course.getCourseInfo().getCourseName());
   }


   @Override
   public void update(Observable o, Object arg) {
       if (course.isStudentAdded())
           loadStudentContent(course.getAddedStudents());
   }
   
   // currently won't work if total has no subcategories
   private void loadGradeColumns()
   {
       TableColumn<Student, String> catCol;
       TableColumn<Student, String> subCatCol;
       TableColumn<Student, String> assignmentCol;
       LinkedList<Category> categories = new LinkedList<Category>();
       Category category;
       
       Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>> callBack = 
               new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> param) {
               HashMap<Assignment, Grade> grades = param.getValue().getGrades();
               Assignment assign = (Assignment) param.getTableColumn().getUserData();
               
               return grades.containsKey(assign) ? 
                       new SimpleStringProperty(String.format("%.1f",100d * grades.get(assign).getScore()))
                       :  new SimpleStringProperty(""); 
           }
       };
       
       categories.push(course.getTopCategory());
       
       while (!categories.isEmpty()) {
           category = categories.poll();
           catCol = new TableColumn<Student, String>(category.getName());
           
           if (category.getSubCategories() != null && 
                   !category.getSubCategories().isEmpty()) {
               for (Category subCategory : category.getSubCategories()) {
                   subCatCol = new TableColumn<Student, String>(subCategory.getName());
                   catCol.getColumns().add(subCatCol);
                   
                   for (Assignment assignment : subCategory.getAssignments()) {
                       assignmentCol = new TableColumn<Student, String>(assignment.getName());
                       assignmentCol.setUserData(assignment);
                       assignmentCol.setCellValueFactory(callBack);
                       subCatCol.getColumns().add(assignmentCol);
                   }
                   
                   categories.push(subCategory);
               }
               table.getColumns().add(catCol);
           }
           
       }
       
   }
   
   private void loadStudentContent(List<Student> students) {
      // studentList.clear();
       studentList.addAll(students);
       table.setItems(studentList);
   }
}
