package controller.spreadsheet;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.spreadsheet.CourseInfo;
import model.spreadsheet.GradingScheme;
import model.spreadsheet.LatePolicy;
import model.users.TeacherAssistant;
import view.ViewUtility;

/**
 * This class represents the interaction between the add class dialog
 * and the various methods needed in the model package
 *
 * @author Kevin Backers      
 */

public class AddClassController {
    @FXML
    private String Name;
    @FXML
    private String Section;
    @FXML
    private String Term;
    @FXML
    private GradingScheme gradingScheme;
    @FXML
    private TeacherAssistant ta;
    @FXML
    private LatePolicy latePolicy;

    private Stage primaryStage;

    public AddClassController() {
    }

    /**
     * Called when the user clicks on the confirm add button
     * This will eventually check and validate all input requirements
     */
    @FXML
    private void handleCreateButton() {
        /* call addStudent function in spreadsheetcourse.java
         * in the model package
         */
    	CourseInfo sampleCourseInfo = new CourseInfo(Name, Term, Name, Name);
    }

    /**
     * Called when the user clicks on the "Create New" button for grading schemes
     * Opens the Create New Grading Scheme dialog
     */
    @FXML
    private void handleCreateNewGradingSchemeButton() {
       FXMLLoader loader = new FXMLLoader(getClass().getResource(
          "/view/spreadsheet/AddGradingScheme.fxml"));
       ViewUtility.showPage(loader, AnchorPane.class, "Create Grading Scheme");
    }

    /**
     * Called when the user clicks on the "Create New" button for TA's
     * Opens the Create New Teacher Assistant dialog
     */
    @FXML
    private void handleCreateNewTeacherAssistantButton() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/view/users/AddTeacherAssistant.fxml"));
        ViewUtility.showPage(loader, AnchorPane.class, "Create Teacher Assistant");
    }
}
