package controller.users;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.gradebook.Gradebook;
import model.users.Student;
import model.spreadsheet.SpreadsheetCourse;

import java.util.ArrayList;

/****
 *
 * This class represents the interaction between the 
 * Edit Student list dialog and the list of students
 * contained in a SpreadsheetCourse.  This class has the
 * ability to make changes to repective Student's and their
 * corresponding data
 *
 * @author  Kevin Feutz     (kfeutz@calpoly.edu)
 *
 */

public class EditStudentListController {

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

    private static ObservableList<Student> allStudents = FXCollections.observableArrayList();

    private ArrayList<SpreadsheetCourse> allCourses;

    /**
     * Contructor for this class
     */
    public EditStudentListController() {
    }

    /**
     * Initializes the table in the edit student list dialog, telling the
     * table which values to populate with.
     */
    @FXML
    private void initialize() {
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        studentUsernameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("userName"));
        enrolledCourseColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("courseEnrolled"));

        allCourses = Gradebook.getInstance().getCourses();
        for(SpreadsheetCourse currentCourse : allCourses)
        {
            allStudents.addAll(currentCourse.getStudentRoster());
        }
        studentTable.setItems(allStudents);
    }

    /**
     * Method ran when the Edit Selected button is selected
     * This will call methods to manipulate the data of a
     * Student object, and will error check for invalid edit
     * entries.
     */
    @FXML
    private void handleEditSelectedButton() {
        /*
         * Call editStudentInfo method in Student model class
         * to make the data change.
         */
        Student tempStudent = new Student("","","","","","");
        tempStudent.editStudentInfo(tempStudent);
    }
}
