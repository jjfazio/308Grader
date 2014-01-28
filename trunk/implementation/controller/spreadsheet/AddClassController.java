package controller.spreadsheet;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.gradebook.Gradebook;
import model.spreadsheet.CourseInfo;
import model.spreadsheet.GradingScheme;
import model.spreadsheet.LatePolicy;
import model.spreadsheet.SpreadsheetCourse;
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
    private TextField courseName;
    @FXML
    private TextField courseSection;
    @FXML
    private TextField courseTerm;
    @FXML
    private GradingScheme gradingScheme;
    @FXML
    private TeacherAssistant ta;
    @FXML
    private LatePolicy latePolicy;
    
    private Gradebook gradebook;

    private Stage primaryStage;

    public AddClassController() {
        gradebook = Gradebook.getInstance();
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
    	CourseInfo courseInfo = new CourseInfo(courseName.getText(),
    	        courseTerm.getText(), courseName.getText(), courseName.getText());
    	SpreadsheetCourse course = new SpreadsheetCourse(courseInfo,
    	        new GradingScheme(), new LatePolicy());
    	
    	gradebook.addSpreadsheetCourse(course);
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
