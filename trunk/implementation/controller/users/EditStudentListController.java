package controller.users;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.gradebook.Gradebook;
import model.spreadsheet.SpreadsheetCourse;
import model.users.Student;

import java.io.IOException;
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

    /** First column of the table, the students' last name */
    @FXML
    private TableColumn<Student, String>  studentLastNameColumn;

    /** Second column of the table, the students' usernames */
    @FXML
    private TableColumn<Student, String>  studentUsernameColumn;

    /** Third column of the table, the students' enrolled courses */
    @FXML
    private TableColumn<Student, String>  enrolledCourseColumn;


    /** Contains the observable list of students */
    private static ObservableList<Student> allStudents = FXCollections.observableArrayList();

    /** Contants the courses taught by the instructor */
    private ArrayList<SpreadsheetCourse> allCourses;

    /** Holds the instance of the gradebook */
    private Gradebook gradeBook;

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
        studentLastNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        studentUsernameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("userName"));
        enrolledCourseColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("formattedCourseList"));

        gradeBook = Gradebook.getInstance();
        allStudents.clear();
        ArrayList<SpreadsheetCourse> allCourses = gradeBook.getCourses();
        ArrayList<Student> students = new ArrayList<Student>();
        for(SpreadsheetCourse currentCourse : allCourses)
        {
            students.clear();
            students.addAll(currentCourse.getStudentRoster());
            for(Student currentStudent : students)
            {
                if(!allStudents.contains(currentStudent))
                {
                    allStudents.add(currentStudent);
                }
            }
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
    private void handleEditSelectedButton() throws IOException {
        /*
         * Call editStudentInfo method in Student model class
         * to make the data change.
         */
        int indexSelected = studentTable.getSelectionModel().getSelectedIndex();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/view/users/EditStudent.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene((Pane) loader.load()));
        EditStudentController editStudentController =
                loader.getController();
        editStudentController.initData(allStudents.get(indexSelected));
        /*
         * Shows the Add Course dialog box
         */
        stage.show();
        stage = (Stage) studentTable.getScene().getWindow();
        stage.close();
    }

    /**
     * This method closes the dialog box when the cancel
     * button is selected
     */
    @FXML
    public void handleCancelButton() {
        Stage stage = (Stage) studentTable.getScene().getWindow();
        stage.close();
    }
}
