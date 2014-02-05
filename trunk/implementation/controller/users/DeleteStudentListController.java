package controller.users;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.users.Student;
import model.spreadsheet.SpreadsheetCourse;

/****
 *
 * This class represents the interaction between the
 * delete student list dialog and the deletion of students
 * from a SpreadsheetCourse.  This class has the ability to
 * remove students from the SpreadsheetCourses
 *
 * @author  Kevin Feutz     (kfeutz@calpoly.edu)
 *
 */
public class DeleteStudentListController {

    /** The entire table of students from the current spreadsheet to edit */
    @FXML
    private TableView<Student> studentTable;

    /** First column of the table, the students' names */
    @FXML
    private TableColumn<Student, String>  studentNameColumn;

    /** Second column of the table, the students' usernames */
    @FXML
    private TableColumn<Student, String>  studentUsernameColumn;

    /** Third column of the table, the students' enrolled courses */
    @FXML
    private TableColumn<Student, String>  enrolledCourseColumn;

    /** Fourth column of the table, contains check boxes */
    @FXML
    private TableColumn<Student, CheckBox> checkBoxColumn;

    /**
     * Contructor for this class
     */
    public DeleteStudentListController() {
    }

    /**
     * Method called when the Edit Selected button is selected
     * on the delete student list dialog.  This method removes the
     * selected Student from the SpreadsheetCourse in which it belongs
     */
    @FXML
    private void handleDeleteSelectedButton() {
        /*
         * calls deletestudent method in spreadsheetcourse model class
         */
        SpreadsheetCourse tempSpreadsheet = new SpreadsheetCourse();
        tempSpreadsheet.deleteStudent(new Student("","","","","",""));
    }
}
