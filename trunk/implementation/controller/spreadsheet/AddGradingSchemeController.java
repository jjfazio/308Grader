package controller.spreadsheet;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.spreadsheet.GradingScheme;

/**
 * This class represents the interaction between the add grading scheme dialog
 * and the various methods needed in the model package
 *
 * @author Kevin Backers      
 */

public class AddGradingSchemeController {
    @FXML
    private String SchemeName;
    @FXML
    private String Section;
    @FXML

    private Stage primaryStage;

    public AddGradingSchemeController() {
    }

    /**
     * Called when the user clicks on the create button
     * This will eventually check and validate all input requirements
     */
    @FXML
    private void handleCreateButton() {
        /* call addStudent function in spreadsheetcourse.java
         * in the model package
         */
    	GradingScheme tempGradingScheme = new GradingScheme();
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
