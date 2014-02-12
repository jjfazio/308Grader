package controller.users;


import java.util.ArrayList;

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
import model.spreadsheet.CourseInfo;
import model.spreadsheet.SpreadsheetCourse;
import model.users.Student;
import view.ViewUtility;

/****
 *
 * Class EditStudentController represents the interaction
 * between the edit student dialog and the various methods
 * needed in the model package.  Class EditStudentController
 * contains all fields similar to the Student class, each of
 * which correspond to an entry field in the view package
 *
 * @author      Kevin Feutz     (kfeutz@calpoly.edu)
 *
 */

public class EditStudentController {

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

    /** Contains the view of the courses for the display */
    @FXML
    private ListView<String> viewCourseList;

    /** Controls an asteric for courses enrolled if the input was incorrect */
    @FXML
    private Label coursesEnrolledWarning;

    /** Holds the list of courses to hold in the course list */
    private static ObservableList<String> courseData = FXCollections.observableArrayList();

    /** Holds the list of spreadsheet courses */
    private static ArrayList<SpreadsheetCourse> courseList;

    /** Holds the original list of spreadsheet courses */
    private ArrayList<SpreadsheetCourse> oldCourseList;

    /** Holds the reference for the student we wish to edit */
    Student studentToEdit;

    /**
     * Initializes the edit student screen.
     */
    @FXML
    protected void initialize() {
        courseData.clear();
        if(courseList != null)
        {
            courseList.clear();
        }
        viewCourseList.setItems(courseData);
    }

    /**
     * This method takes in a student and assigns all of the
     * text fields and other displays to the passed student's
     * fields.
     *
     * @param studToEdit    The student that is being editted.
     */
    protected void initData(Student studToEdit)
    {
        studentToEdit = studToEdit;
        firstName.setText(studentToEdit.getFirstName());
        middleName.setText(studentToEdit.getMiddleName());
        lastName.setText(studentToEdit.getLastName());
        username.setText(studentToEdit.getUserName());
        studentId.setText(studentToEdit.getId());
        major.setText(studentToEdit.getMajor());
        email.setText(studentToEdit.getEmail());

        oldCourseList = new ArrayList<SpreadsheetCourse>();
        ArrayList<SpreadsheetCourse> coursesEnrolled = studentToEdit.getCoursesEnrolled();
        courseList = new ArrayList<SpreadsheetCourse>();
        for(SpreadsheetCourse currentCourse : coursesEnrolled)
        {
            oldCourseList.add(currentCourse);
            addCourseToDialog(currentCourse);
        }
        phoneNumber.setText(studentToEdit.getPhoneNumber());
        gradeLevel.setText(studentToEdit.getGradeLevel());
    }

    /**
     * This method adds a course to the dialog list of courses
     *
     * @param courseToAdd   The course to add to the dialog list
     */
    protected static void addCourseToDialog(SpreadsheetCourse courseToAdd)
    {
        if(!courseList.contains(courseToAdd))
        {
            courseData.add(courseToAdd.getCourseInfo().getCourseName()
                + "-" + courseToAdd.getCourseInfo().getNumber());
            courseList.add(courseToAdd);
        }
    }
    /**
     * Contructor for this class
     */
    public EditStudentController() {
    }

    /**
     * Called when the user clicks on the confirm add button
     * This will check and validate all input requirements for
     * the Student class
     */
    @FXML
    private void handleConfirmAddButton() {
        /** Will append the error message if one occurs */
        String errorMessage = "";
        /*
         * call addStudent function in SpreadsheetCourse.java
         * in the model package
         */
        if(!firstName.getText().matches("[a-zA-Z]*"))
        {
            errorMessage += "* First Name must contain only alphabetic characters\n\n";
            firstNameWarning.setText("*");
        }
        else
        {
            firstNameWarning.setText("");
        }
        if(!lastName.getText().matches("[a-zA-Z]*"))
        {
            errorMessage += "* Last Name must contain only alphabetic characters\n\n";
            lastNameWarning.setText("*");
        }
        else
        {
            lastNameWarning.setText("");
        }
        if(studentId.getText().length() == 0)
        {
            errorMessage += "* Student ID is a required text entry field\n\n";
            studentIdWarning.setText("*");
        }
        else
        {
            studentIdWarning.setText("");
        }
        if(courseList.size() == 0)
        {
            errorMessage += "* You must select at least one course to which the student will be added\n\n";
            coursesEnrolledWarning.setText("*");
        }
        else
        {
            coursesEnrolledWarning.setText("");
        }
        if(errorMessage.length() <= 0)
        {
            studentToEdit.setFirstName(firstName.getText());
            studentToEdit.setMiddleName(middleName.getText());
            studentToEdit.setLastName(lastName.getText());
            studentToEdit.setUserName(username.getText());
            studentToEdit.setId(studentId.getText());
            studentToEdit.setEmail(email.getText());
            studentToEdit.setMajor(major.getText());
            studentToEdit.setGradeLevel(gradeLevel.getText());
            studentToEdit.setPhoneNumber(phoneNumber.getText());
            /** Checks to see which courses are new courses to the student */
            for(SpreadsheetCourse currentCourses: courseList)
            {
                if(!oldCourseList.contains(currentCourses))
                {
                    currentCourses.addStudent(studentToEdit);
                    studentToEdit.addCourse(currentCourses);
                }
                else
                {
                    currentCourses.editStudent();
                }
            }
            /** Checks to see which courses are no longer in the students course list */
            for(SpreadsheetCourse oldCourses: oldCourseList)
            {
                if(!courseList.contains(oldCourses))
                {
                    oldCourses.deleteStudent(studentToEdit);
                    studentToEdit.removeCourse(oldCourses);
                }
            }
            Stage stage = getStage();
            stage.close();
        }
        else
        {
            Dialogs.showWarningDialog(getStage(), errorMessage, "Invalid Student Input",
                    "Invalid Student Input");
        }
    }

    /**
     * Closes the stage when the cancel button is selected
     */
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
                "/view/users/EditStudentCourse.fxml"));
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
