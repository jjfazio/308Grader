package controller.users;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.users.Student;
import model.spreadsheet.SpreadsheetCourse;

/**
 * This class represents the interaction between the 
 * Edit Student list dialog and the list of students
 * contained in a SpreadsheetCourse
 *
 * @author Kevin Feutz
 */

public class EditStudentListController {

    /**
     * The entire table of students from the current spreadsheet to edit
     */
    @FXML
    private TableView<Student> studentTable;

    /**
     * First column of the table, the students' names
     */
    @FXML
    private TableColumn<Student, String>  studentNameColumn;

    /**
     * Second column of the table, the students' usernames
     */
    @FXML
    private TableColumn<Student, String>  studentUsernameColumn;

    /**
     * Third column of the table, the students' enrolled courses
     */
    @FXML
    private TableColumn<Student, String>  enrolledCourseColumn;

    /**
     * Fourth column of the table, contains check boxes
     */
    @FXML
    private TableColumn<Student, CheckBox> checkBoxColumn;

    public EditStudentListController() {
    }

    @FXML
    private void initialize() {
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        studentUsernameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("username"));
        enrolledCourseColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("courseEnrolled"));
    }

    /**
     * Method ran when the Edit Selected button is selected
     * This will call methods to manipulate the data of a
     * Student object
     */
    @FXML
    private void handleEditSelectedButton() {
        // call editstudent method in spreadsheetcourse model class
        Student tempStudent = new Student();
        tempStudent.editStudentInfo();
    }
}
