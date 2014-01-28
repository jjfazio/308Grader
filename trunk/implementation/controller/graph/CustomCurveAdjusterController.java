package controller.graph;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import view.ViewUtility;

/**
 * Controller class for the custom curve adjuster view.
 * 
 * @author Erik Owen
 *
 */
public class CustomCurveAdjusterController {
	/**cancel button which closes the window when clicked*/
	@FXML
    private Button cancelButton;
	/**apply curve button which submits the curved grades when clicked*/
    @FXML
    private Button applyCurveButton;
    /**bar chart object*/
    @FXML
    private BarChart barChart;
    
    /**
     * Creates a new CustomCurveAdjusterController
     */
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