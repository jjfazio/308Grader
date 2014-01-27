package controller.users;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.spreadsheet.SpreadsheetCourse;
import model.users.Student;
import view.ViewUtility;
/**
 * This class represents the interaction between the add student dialog
 * and the various methods needed in the model package
 *
 * @author Kevin Feutz      
 */

public class AddStudentController {
    @FXML
    private String firstName;
    @FXML
    private String middleName;
    @FXML
    private String lastName;
    @FXML
    private String username;
    @FXML
    private String studentId;
    @FXML
    private String major;
    @FXML
    private String email;
    @FXML
    private String phoneNumber;
    @FXML
    private String gradeLevel;

    private Stage primaryStage;

    public AddStudentController() {
    }

    /**
     * Called when the user clicks on the confirm add button
     * This will eventually check and validate all input requirements
     */
    @FXML
    private void handleConfirmAddButton() {
        /* call addStudent function in spreadsheetcourse.java
         * in the model package
         */
        SpreadsheetCourse sampleCourse = new SpreadsheetCourse();
        sampleCourse.addStudent(new Student());
    }

    /**
     * Called when the user clicks on the add course button
     * Opens the AddStudentCourse dialog
     */
    @FXML
    private void handleAddCourseButton() {
       FXMLLoader loader = new FXMLLoader(getClass().getResource(
          "/view/users/AddStudentCourse.fxml"));
       ViewUtility.showPage(loader, AnchorPane.class, "Add Course");
       //open the add course dialog
    }

    /**
     * Called when the user clicks on the delete course button
     */
    @FXML
    private void handleDeleteCourseButton() {
        //open the add course dialog
        Student tempStudent = new Student();
        tempStudent.removeCourse(new SpreadsheetCourse());
    }
}
