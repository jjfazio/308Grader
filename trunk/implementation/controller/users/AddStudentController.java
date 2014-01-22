package controller.users;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import view.ViewUtility;

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

    }

    /**
     * Called when the user clicks on the add course button
     */
    @FXML
    private void handleAddCourseButton() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./../view/AddStudentCourse.fxml"));
        ViewUtility.loadView(loader);
        //open the add course dialog
    }

    /**
     * Called when the user clicks on the delete course button
     */
    @FXML
    private void handleDeleteCourseButton() {
        //open the add course dialog
    }
}