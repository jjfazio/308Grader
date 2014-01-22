package controller.graph;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import view.ViewUtility;

/**
 * 
 * @author Erik Owen
 *
 */
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
        System.out.println("Cancel button pressed");
    }
    
    /**
     * Called when the user clicks the apply curve button
     */
    @FXML
    private void handleApplyCurveButtonPressed() {
        System.out.println("Apply curve button pressed");
    }
}