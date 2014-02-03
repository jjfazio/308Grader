package controller.spreadsheet;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.gradebook.Gradebook;
import model.spreadsheet.GradingScheme;
import model.spreadsheet.SpreadsheetCourse;

/**
 * This class represents the interaction between the add grading scheme dialog
 * and the various methods needed in the model package
 *
 * @author Kevin Backers      
 */

public class AddGradingSchemeController {
    @FXML
    private TextField SchemeName;

    private Stage primaryStage;

    private Gradebook gradebook;
    
    private SpreadsheetCourse course;
    
    public AddGradingSchemeController() {
        gradebook = Gradebook.getInstance();
        course = gradebook.getCurrentCourse();
    }

    /**
     * Called when the user clicks on the create button
     * This will eventually check and validate all input requirements
     */
    @FXML
    private void handleCreateButton() {
        
        // TODO: get list of graderanges from table in GUI
        // for now, just create a named schemes
    	GradingScheme tempGradingScheme = new GradingScheme(SchemeName.getText());
    	course.setGradingDistribution(tempGradingScheme);
    	System.out.println("added grading scheme: " + SchemeName.getText() + " to course: " + course.getCourseInfo().getCourseName());
    }

    /**
     * Called when the user clicks on the "+" button under the table
     * Adds a new empty row to the table
     */
    @FXML
    private void handlePlusButton() {
       System.out.println("Plus button pressed for new row");
    }
}
