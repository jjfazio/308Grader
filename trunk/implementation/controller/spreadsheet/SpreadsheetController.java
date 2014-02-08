package controller.spreadsheet;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.assignments_categories.Assignment;
import model.assignments_categories.Category;
import model.gradebook.Gradebook;
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
      loadStudentContent(course.getStudentRoster());
      
      System.out.println("Set up spreadsheet for " + course.getCourseInfo().getCourseName());
   }


   @Override
   public void update(Observable o, Object arg) {
       if (course.isStudentAdded())
           loadStudentContent(course.getAddedStudents());
   }
   
   //coming soon to a theater near you
//   private void loadGradeColumns()
//   {
//       Category top = course.getTopCategory();
//       List<Assignment> assigns = top.getAssignments();
//       TableColumn<Student, String> assignCol;
//       
//       for (Assignment assign : assigns)
//       {
//           assignCol = new TableColumn<Student, String>();
//           assignCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
//
//               @Override
//               public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> student) {
//                   
//                   return new SimpleStringProperty("");
//               }
//           });
//       }
//       
//   }
   
   private void loadStudentContent(List<Student> students) {
      // studentList.clear();
       studentList.addAll(students);
       table.setItems(studentList);
   }
}
