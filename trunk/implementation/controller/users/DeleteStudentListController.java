package controller.users;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.users.Student;


public class DeleteStudentListController {

    /*
     * The entire table of students from the current spreadsheet to edit
     */
    @FXML
    private TableView<Student> studentTable;

    /*
     * First column of the table, the students' names
     */
    @FXML
    private TableColumn<Student, String>  studentNameColumn;

    /*
     * Second column of the table, the students' usernames
     */
    @FXML
    private TableColumn<Student, String>  studentUsernameColumn;

    /*
     * Third column of the table, the students' enrolled courses
     */
    @FXML
    private TableColumn<Student, String>  enrolledCourseColumn;

    /*
     * Fourth column of the table, contains check boxes
     */
    @FXML
    private TableColumn<Student, CheckBox> checkBoxColumn;

    public DeleteStudentListController() {
    }

    @FXML
    private void initialize() {
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        studentUsernameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("username"));
        enrolledCourseColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("courseEnrolled"));
    }

    /*
     * Method when the Edit Selected button is selected
     */
    @FXML
    private void handleDeleteSelectedButton() {
        // call deletestudent method in spreadsheetcourse model class
    }
}