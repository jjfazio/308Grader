package controller.users;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.users.TeacherAssistant;

/**
 * This class represents the interaction between the add teacher assistant dialog
 * and the various methods needed in the model package
 *
 * @author Kevin Backers      
 */

public class AddTeacherAssistantController {
	
    @FXML
    private String FisrtName;
    @FXML
    private String LastName;
    @FXML
    private String ID;

    private Stage primaryStage;

    public AddTeacherAssistantController() {
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
    	TeacherAssistant tempTA = new TeacherAssistant();
    }


}
