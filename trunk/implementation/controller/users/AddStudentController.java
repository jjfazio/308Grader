package controller.users;

<<<<<<< HEAD
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import view.ViewUtility;

public class AddStudentController {
   
=======
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.spreadsheet.SpreadsheetCourse;
import model.users.Student;
import view.ViewUtility;

public class AddStudentController {
>>>>>>> 9fe84d803cfd6e1e86687215486d9201a83a1161
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

<<<<<<< HEAD
=======
    private Stage primaryStage;

>>>>>>> 9fe84d803cfd6e1e86687215486d9201a83a1161
    public AddStudentController() {
    }

    /**
     * Called when the user clicks on the confirm add button
     */
    @FXML
    private void handleConfirmAddButton() {
        /* call addStudent function in spreadsheetcourse.java
         * in the model package
         */
<<<<<<< HEAD

=======
        SpreadsheetCourse sampleCourse = new SpreadsheetCourse();
        sampleCourse.addStudent(new Student());
>>>>>>> 9fe84d803cfd6e1e86687215486d9201a83a1161
    }

    /**
     * Called when the user clicks on the add course button
<<<<<<< HEAD
     */
    @FXML
    private void handleAddCourseButton() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./../view/AddStudentCourse.fxml"));
        ViewUtility.loadView(loader);
        //open the add course dialog
=======
     * Opens the AddStudentCourse dialog
     */
    @FXML
    private void handleAddCourseButton() {
       FXMLLoader loader = new FXMLLoader(getClass().getResource(
          "./../../view/users/AddStudentCourse.fxml"));
       ViewUtility.showPage(loader, AnchorPane.class, "Add Course");
       //open the add course dialog
>>>>>>> 9fe84d803cfd6e1e86687215486d9201a83a1161
    }

    /**
     * Called when the user clicks on the delete course button
     */
    @FXML
    private void handleDeleteCourseButton() {
        //open the add course dialog
    }
}