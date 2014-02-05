package controller.spreadsheet;

import java.util.Observable;
import java.util.Observer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    }


   /**
    * Must be called before displaying the view. Sets the course
    * to be used for this spreadsheet.
    * @param course - The SpreadsheetCourse for this Spreadsheet
    */
   public void setSpreadsheet(SpreadsheetCourse course) {
      this.course = course;
      loadContent();
      
      System.out.println("Set up spreadsheet for " + course.getCourseInfo().getCourseName());
   }


   @Override
   public void update(Observable o, Object arg) {
       loadContent();
   }
   
   private void loadContent() {
       studentList.clear();
       studentList.addAll(course.getStudentRoster());
       table.setItems(studentList);
   }

}
