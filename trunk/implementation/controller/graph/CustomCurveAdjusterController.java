package controller.graph;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import view.ViewUtility;

public class CustomCurveAdjusterController {
	@FXML
    private Button cancelButton;
    @FXML
    private Button applyCurveButton;
    @FXML
    private BarChart barChart;
    
    public CustomCurveAdjusterController() {
    	
    }
    
    /**
     * Called when the user clicks the cancel button
     */
    @FXML
    private void handleCancelButtonPressed() {
        System.out.println("One percent button pressed");
    }
}