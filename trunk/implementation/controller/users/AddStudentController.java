package controller.users;


import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.gradebook.Gradebook;
import model.spreadsheet.CourseInfo;
import model.spreadsheet.SpreadsheetCourse;
import model.users.Student;
import view.ViewUtility;

/****
 *
 * Class AddStudentController represents the interaction
 * between the add student dialog and the various methods
 * needed in the model package.  Class AddStudentController
 * contains all fields similar to the Student class, each of
 * which correspond to an entry field in the view package
 *
 * @author      Kevin Feutz     (kfeutz@calpoly.edu)
 *
 */

public class AddStudentController {
    /** Contains the first name of the student */
    @FXML
    private TextField firstName;

    /** Contains the middle name of the student */
    @FXML
    private TextField middleName;

    /** Contains the last name of the student */
    @FXML
    private TextField lastName;

    /** Contains the username of the student */
    @FXML
    private TextField username;

    /** Contains the student id as a string */
    @FXML
    private TextField studentId;

    /** Contains the student's current major */
    @FXML
    private TextField major;

    /** Contains the student's email address */
    @FXML
    private TextField email;

    /** Contains the student's phone number */
    @FXML
    private TextField phoneNumber;

    /** Contains the student's current grade level */
    @FXML
    private TextField gradeLevel;

    @FXML
    private ListView<String> viewCourseList;

    /** Holds the list of courses to hold in the course list */
    private static ObservableList<String> courseData = FXCollections.observableArrayList();

    private ArrayList<SpreadsheetCourse> courseList;

    @FXML
    private void initialize() {
        courseData.removeAll();
        viewCourseList.setItems(courseData);
    }

    protected static void addCourseToDialog(CourseInfo courseToAdd)
    {
        courseData.add(courseToAdd.getCourseName() + "-" + courseToAdd.getNumber());
    }
    /**
     * Contructor for this class
     */
    public AddStudentController() {
    }

    /**
     * Called when the user clicks on the confirm add button
     * This will check and validate all input requirements for
     * the Student class
     */
    @FXML
    private void handleConfirmAddButton() {
        /*
         * call addStudent function in SpreadsheetCourse.java
         * in the model package
         */
        courseList = AddStudentCourseController.getCourseList();
        for(SpreadsheetCourse currentCourses: courseList)
        {
           if(currentCourses != null)
               currentCourses.addStudent(new Student(username.getText(),
                   firstName.getText(), lastName.getText(),
                   studentId.getText(), major.getText(), gradeLevel.getText()));
        }
        Stage stage = (Stage) firstName.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancelButton() {
        Stage stage = (Stage) firstName.getScene().getWindow();
        stage.close();
    }

    /**
     * Called when the user clicks on the add course button
     * Opens the AddStudentCourse dialog
     */
    @FXML
    private void handleAddCourseButton() {
       FXMLLoader loader = new FXMLLoader(getClass().getResource(
          "/view/users/AddStudentCourse.fxml"));
        /*
         * Shows the Add Course dialog box
         */
       ViewUtility.loadAndShowPage(loader, AnchorPane.class, "Add Course");
    }



    /**
     * Called when the user clicks on the delete course button in the
     * add student dialog.  This will remove a course from this Student's
     * collection of courses enrolled
     */
    @FXML
    private void handleDeleteCourseButton() {
//        Student tempStudent = new Student("","","","","","");
//        tempStudent.removeCourse(new SpreadsheetCourse());
    }
}
