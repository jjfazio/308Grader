package controller.users;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.exception.StudentDataException;
import model.spreadsheet.CourseInfo;
import model.spreadsheet.SpreadsheetCourse;
import model.users.Student;
import view.ViewUtility;

import java.util.ArrayList;

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

    /** The root of this scene */
    @FXML
    private Parent root;

    /** Contains the first name of the student */
    @FXML
    private TextField firstName;

    /** Controls an asteric for first name if the input was incorrect */
    @FXML
    private Label firstNameWarning;

    /** Contains the middle name of the student */
    @FXML
    private TextField middleName;

    /** Contains the last name of the student */
    @FXML
    private TextField lastName;

    /** Controls an asteric for last name if the input was incorrect */
    @FXML
    private Label lastNameWarning;

    /** Contains the username of the student */
    @FXML
    private TextField username;

    /** Contains the student id as a string */
    @FXML
    private TextField studentId;

    /** Controls an asteric for studentId if the input was incorrect */
    @FXML
    private Label studentIdWarning;

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

    /** Controls an asteric for courses enrolled if the input was incorrect */
    @FXML
    private Label coursesEnrolledWarning;

    /** Holds the list of courses to hold in the course list */
    private static ObservableList<String> courseData = FXCollections.observableArrayList();

    private static ArrayList<SpreadsheetCourse> courseList;

    @FXML
    private void initialize() {
        courseData.clear();
        courseList = new ArrayList<SpreadsheetCourse>();
        if(courseList != null)
        {
            courseList.clear();
        }
        viewCourseList.setItems(courseData);
    }

    protected static void addCourseToDialog(CourseInfo courseToAdd)
    {
        if(!courseList.contains(AddStudentCourseController.getCourseSelected()))
        {
            courseData.add(courseToAdd.getCourseName() + "-" + courseToAdd.getNumber());
            courseList.add(AddStudentCourseController.getCourseSelected());
        }
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
        String errorMessage = "";
        if(courseList.size() == 0)
        {
            errorMessage += "* You must select at least one course to which the student will be added\n\n";
            coursesEnrolledWarning.setText("*");
        }
        else
        {
            coursesEnrolledWarning.setText("");
        }
        try
        {
            Student studentToAdd = new Student(username.getText(),
                    firstName.getText(), lastName.getText(),
                    studentId.getText(), major.getText(), gradeLevel.getText());
            firstNameWarning.setText("");
            lastNameWarning.setText("");
            studentIdWarning.setText("");
            for(SpreadsheetCourse currentCourses: courseList)
            {
                if(!currentCourses.getStudentRoster().contains(studentToAdd))
                {
                    currentCourses.addStudent(studentToAdd);
                    studentToAdd.addCourse(currentCourses);
                }
            }
            Stage stage = getStage();
            stage.close();
        }
        catch(StudentDataException exc)
        {
            if(exc.isBadFirstName())
            {
                firstNameWarning.setText("*");
            }
            else
            {
                firstNameWarning.setText("");
            }
            if(exc.isBadLastName())
            {
                lastNameWarning.setText("*");
            }
            else
            {
                lastNameWarning.setText("");
            }
            if(exc.isBadId())
            {
                studentIdWarning.setText("*");
            }
            else
            {
                studentIdWarning.setText("");
            }
            Dialogs.showWarningDialog(getStage(), exc.getMessage() + errorMessage,
                    "Invalid Student Input",
                    "Invalid Student Input");
        }
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
        int indexOfClick = viewCourseList.getSelectionModel().getSelectedIndex();
        if(indexOfClick >= 0 && indexOfClick < courseData.size())
        {
            courseData.remove(indexOfClick);
            courseList.remove(indexOfClick);
        }
    }

    /**
     * This method gets the root stage of the view
     *
     * @return  Stage   The root stage of the view
     */
    private Stage getStage() {
        return (Stage) root.getScene().getWindow();
    }
}
