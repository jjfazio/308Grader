package controller.spreadsheet;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialogs;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.assignments_categories.Assignment;
import model.assignments_categories.Category;
import model.assignments_categories.CategoryContainer;
import model.assignments_categories.Grade;
import model.exception.BadDataException;
import model.spreadsheet.AssignView;
import model.spreadsheet.SpreadsheetCourse;
import model.users.Student;

/**
 * This class controls the Spreadsheet actions. Any action
 * done to a spreadsheet will happen here
 * @author jamesfazio
 */
public class SpreadsheetController implements Observer {
    
    @FXML
    private Parent root;
    
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
    
    @FXML
    /** Column that holds the hierarchical structure of categories
     *  and assignments
     */
    private TableColumn<Student, String> totalCategoryCol;
    
    @FXML
    /** Total Percentage grade column */
    private TableColumn<Student, String> totalGradeColumn;
    
    @FXML
    /** Total Grade Symbol grade column */
    private TableColumn<Student, String> totalLetterColumn;
    
    @FXML
    /** Major column in the table */
    private TableColumn<Student, String> majorColumn;
    
    /** Course represented by this spreadsheet */
    private SpreadsheetCourse course;
    
    /** List of student in the spreadsheet */
    private ObservableList<Student> studentList;
    
    private static final double MIN_COL_WIDTH = 125;
    
    
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
        totalGradeColumn.setCellValueFactory(new TotalGradeCallBack());
        totalGradeColumn.setText("Total Grade");
        totalLetterColumn.setCellValueFactory(new TotalLetterCallBack());
        totalLetterColumn.setText("Grade Symbol");
        totalLetterColumn.setCellFactory(new ColorCellFactory());
        
    }


   /**
    * Must be called before displaying the view. Sets the course
    * to be used for this spreadsheet.
    * @param course - The SpreadsheetCourse for this Spreadsheet
    */
   public void setSpreadsheet(SpreadsheetCourse course) {
      this.course = course;
      
      //Set the user data to be the top category object
      totalCategoryCol.setUserData(course.getCategoryContainer().getRoot());
      
      loadStudentContent(course.getStudentRoster());
      addCols(totalCategoryCol, course.getCategoryContainer().getRoot());
      
      if (course.getAssignView() == AssignView.POINTS)
          table.setEditable(true);
      else
          table.setEditable(false);
      
      addContextMenus();
   }


   @Override
   /**
    * Called when a change is made to the model
    * Make this code better
    */
   public void update(Observable o, Object arg) {
       String val = (String) arg;
       // If new students got added to the spreadsheet
       if (course.isStudentAdded())
           loadStudentContent(course.getAddedStudents());
       else if (course.isStudentDeleted())  {
           removeStudentContent(course.getStudentToDelete());
       }
       else
       {
    	   refreshTable();
       }
   }
   
   private void addContextMenus()
   {
       ContextMenu menu;
       MenuItem hide;
       MenuItem show;
       
       for (TableColumn<Student, ?> col : table.getColumns())
       {
           menu = new ContextMenu();
           
           hide = new MenuItem("Hide " + col.getText());
           hide.setUserData(col);
           
           hide.setOnAction(new EventHandler<ActionEvent>() {
               public void handle(ActionEvent e) {
                   MenuItem item = (MenuItem) e.getSource();
                   TableColumn<Student, ?> column = (TableColumn<Student, ?>)
                           item.getUserData();
                   column.setVisible(false);
               }
           });
           
           menu.getItems().add(hide);
           
           show = new MenuItem("Show all Columns");
           
           show.setOnAction(new EventHandler<ActionEvent>() {
               public void handle(ActionEvent e) {
                   for (TableColumn<Student, ?> col : table.getColumns())
                       col.setVisible(true);
               }
           });
           
           menu.getItems().add(show);
           
           col.setContextMenu(menu);
       }
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
       
       // Loop through all the assignments in this category 
       // and add them as children columns
       for (Assignment assignment : category.getAssignments()) {
           assignmentCol = new TableColumn<Student, String>(
                   assignment.getName() + " (" + assignment.getPercentOfCategory() + " %)");
           assignmentCol.setUserData(assignment);
           assignmentCol.setCellFactory(new EditCallBack());
           assignmentCol.setCellValueFactory(new AssignmentCallBack());
           assignmentCol.setOnEditCommit(new EditHandler());
           assignmentCol.setMinWidth(MIN_COL_WIDTH);
           topCol.getColumns().add(assignmentCol);
       }
       
       // Loop through all the categories in this category 
       // and add them as children columns, call this method 
       // on all sub categories
       if (category.getSubCategories() != null && 
               !category.getSubCategories().isEmpty()) {
           for (Category subCategory : category.getSubCategories()) {
               subCatCol = new TableColumn<Student, String>(subCategory.getName()
                       + " (" + subCategory.getPercentOfParent() + " %)");
               subCatCol.setUserData(subCategory);
               subCatCol.setMinWidth(MIN_COL_WIDTH);
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
   private class AssignmentCallBack implements Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>> {
       @Override
       public ObservableValue<String> call(CellDataFeatures<Student, String> param)
       {
           SimpleStringProperty val = new SimpleStringProperty("");
           HashMap<Integer, Grade> grades = param.getValue().getGrades();
           Assignment assign = (Assignment) param.getTableColumn().getUserData();
           
           if (grades.containsKey(assign.getID()))
           {
               if (course.getAssignView() == AssignView.POINTS)
                   val =  new SimpleStringProperty(String.format("%.1f",grades.get(assign.getID()).getScore()));
               if (course.getAssignView() == AssignView.PERCENTAGES)
                   val =  new SimpleStringProperty(String.format("%.1f",grades.get(assign.getID()).getScore() /
                           assign.getMaxPoints() * 100.0) + " %");
               if (course.getAssignView() == AssignView.SYMBOLS)
                   val =  new SimpleStringProperty(grades.get(assign.getID()).getLetterGrade());
           }
           
           return val;
       }

   }
   
   private class ColorCellFactory implements Callback<TableColumn<Student, String>, TableCell<Student, String>>
   {

    @Override
    public TableCell<Student, String> call(TableColumn<Student, String> student)
    {
        return new TableCell<Student, String>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (!isEmpty()) {
                    this.setStyle("-fx-background-color:red");
                    setText(item);
                }
            }
        };
    }
       
   }
   
   /**
    * CallBack for the total grade column. Gets the total grade for the student
    * and puts it in the table
    * @author jamesfazio
    *
    */
   private class TotalGradeCallBack implements Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>> {
       @Override
       public ObservableValue<String> call(CellDataFeatures<Student, String> param)
       {
           double totalGrade = param.getValue().getTotalGrade(course.getID());
           return new SimpleStringProperty(String.format("%.2f", totalGrade) + " %");
       }
   }
   
   /**
    * CallBack for the total letter grade column. Gets the total letter grade
    * or symbol for the student and puts it in the table
    * @author jamesfazio
    *
    */
   private class TotalLetterCallBack implements Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>> {
       @Override
       public ObservableValue<String> call(CellDataFeatures<Student, String> param)
       {
           String letter = param.getValue().getLetterGrade(course.getID());
           return new SimpleStringProperty(letter);
       }
   }
   
   /**
    * Call back for assignment cells. Assignment cells are editable and require
    * their own cell class.
    * @author jamesfazio
    *
    */
   private class EditCallBack implements Callback<TableColumn<Student, String>, TableCell<Student, String>> {

    @Override
    public TableCell<Student, String> call(TableColumn<Student, String> arg)
    {
        Assignment assign = (Assignment) arg.getUserData();
        return new EditingCell(assign);
    }
       
   }
   
   
   
   /**
    * Handler for editing grades in the spreadsheet. When a user double
    * clicks an assignment cell they can enter in a grade for the student.
    * If they enter an invalid grade, an error message is displayed.
    * @author jamesfazio
    *
    */
   private class EditHandler implements EventHandler<CellEditEvent<Student, String>> {
       @Override
       public void handle(CellEditEvent<Student, String> studentCell)
       {
           Grade grade = null;
           Assignment assign;
           Date turnedIn;
           String score;
           Student student;
           
           score = studentCell.getNewValue();
           
           turnedIn = new Date();
           student = studentCell.getRowValue();
           
           assign = (Assignment) studentCell.getTableColumn().getUserData();
           try
           {
               // If the user decides to delete the grade
               if (score.equals(""))
               {
                   student.removeGrade(course, assign);
               }
               else
               {
                   // If the student already has grade for 
                   // the cell being edited
                   if (student.getGrades().containsKey(assign.getID()))
                   {
                       grade = student.getGrades().get(assign.getID());
                       grade.setScore(score);
                   }
                   // The user is entering in a new grade for a student
                   else
                   {
                       grade = new Grade(turnedIn, score);
                   }
                   
                   student.addGrade(course, assign, grade);
               }
               
           }
           // If the user enters in bad data for the student's grade
           catch (BadDataException e)
           {
               // Don't show bad data
               System.out.println("Bad Data entered: " + e.getMessage());
           }
           finally
           {
               refreshColumns(studentCell.getTableColumn());
           }
       }
   }
   
   private void refreshTable()
   {
       totalCategoryCol.getColumns().clear();
       table.getColumns().remove(totalCategoryCol);
       addCols(totalCategoryCol, course.getCategoryContainer().getRoot());
       table.getColumns().add(table.getColumns().size() - 2, totalCategoryCol);

       // Only can edit table in points mode
       if (course.getAssignView() == AssignView.POINTS)
           table.setEditable(true);
       else
           table.setEditable(false);
   }
   
   /**
    * Called to refresh an assignment col along with the total grade and
    * total letter grade col.
    * @param column
    */
   private void refreshColumns(TableColumn<Student, String> column)
   {
       column.setVisible(false);
       column.setVisible(true);
       
       table.getColumns().get(table.getColumns().size() -1).setVisible(false);
       table.getColumns().get(table.getColumns().size() - 1).setVisible(true);
       
       table.getColumns().get(table.getColumns().size() - 2).setVisible(false);
       table.getColumns().get(table.getColumns().size() - 2).setVisible(true);
       
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
   
   /**
    * Get the stage of this view
    * @return the stage of this view
    */
   private Stage getStage() {
       return (Stage) root.getScene().getWindow();
   }
   
   /**
    * Assignment cell that handles the editing
    * @author jamesfazio
    *
    */
   class EditingCell extends TableCell<Student, String> {

       private TextField textField;

       public EditingCell(Assignment assign) {
           this.setTooltip(new Tooltip(assign.getMaxPoints() + " pts max"));
       }

       @Override
       public void startEdit() {
           if (!isEmpty()) {
               super.startEdit();
               createTextField();
               setText(null);
               setGraphic(textField);
               textField.selectAll();
               
               //Hack to get double click to work
               Platform.runLater(new Runnable() {
                   @Override
                   public void run() {
                       textField.requestFocus();
                   }
              });

           }
       }

       @Override
       public void cancelEdit() {
           super.cancelEdit();

           setText((String) getItem());
           setGraphic(null);
       }

       @Override
       public void updateItem(String item, boolean empty) {
           super.updateItem(item, empty);

           if (empty) {
               setText(null);
               setGraphic(null);
           } else {
               if (isEditing()) {
                   if (textField != null) {
                       textField.setText(getString());
                   }
                   setText(null);
                   setGraphic(textField);
               } else {
                   setText(getString());
                   setGraphic(null);
               }
           }
       }

       private void createTextField() {
           textField = new TextField(getString());
           
           textField.setFocusTraversable(true);
           textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
           textField.focusedProperty().addListener(new ChangeListener<Boolean>(){
               @Override
               public void changed(ObservableValue<? extends Boolean> arg0, 
                   Boolean arg1, Boolean arg2) {
                       if (!arg2) {
                           commitEdit(textField.getText());
                       }
               }
           });
           
           // Allows for tabbing and entering in assignment cells
           textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
               @Override
               public void handle(KeyEvent t) {
                   if (t.getCode() == KeyCode.ENTER) {
                       commitEdit(textField.getText());
                   } else if (t.getCode() == KeyCode.ESCAPE) {
                       cancelEdit();
                   } else if (t.getCode() == KeyCode.TAB) {
                       commitEdit(textField.getText());
                       getTableView().getSelectionModel().selectBelowCell();
                       }
                   }
               });
       }

       private String getString() {
           return getItem() == null ? "" : getItem().toString();
       }
   }
}
