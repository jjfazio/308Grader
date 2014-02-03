package controller.spreadsheet;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.gradebook.Gradebook;
import model.spreadsheet.SpreadsheetCourse;
import model.users.Student;

/**
 * This class controls the Spreadsheet actions. Any action
 * done to a spreadsheet will happen here
 * @author jamesfazio
 */
public class SpreadsheetController {
    @FXML
    private TableView<Student> studentTable;

    @FXML
    private TableColumn<Student, String> studentNameColumn;

    @FXML
    private TableColumn<Student, String> usernameColumn;

    @FXML
    private TableColumn<Student, String> userIDColumn;

    @FXML
    private void initialize() {
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName" + ", " + "firstName"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("username"));
        userIDColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));
        studentTable.setItems(Gradebook.getInstance().getCourses().get(0).getStudentList());
    }

   private SpreadsheetCourse course;


   /**
    * Must be called before displaying the view. Sets the course
    * to be used for this spreadsheet.
    * @param course - The SpreadsheetCourse for this Spreadsheet
    */
   public void setSpreadsheet(SpreadsheetCourse course) {
      this.course = course;
      System.out.println("Set up spreadsheet for " + course.getCourseInfo().getCourseName());
   }
}
