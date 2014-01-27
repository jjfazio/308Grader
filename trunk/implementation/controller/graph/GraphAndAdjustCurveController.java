package controller.graph;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import view.ViewUtility;

/**
 * 
 * @author Erik Owen
 *
 */
public class GraphAndAdjustCurveController {
    @FXML
    private CheckBox barChartCheckBox;
    @FXML
    private CheckBox pieChartCheckBox;
    @FXML
    private RadioButton onePercentGranularity;
    @FXML
    private RadioButton tenPercentGranularity;
    @FXML
    private TextField percentCurve;
    @FXML
    private Button addCustomCurveButton;
    @FXML
    private Button saveCurvedGradesButton;
    @FXML
    private PieChart pieChart;
    @FXML
    private BarChart barChart;
    
    public GraphAndAdjustCurveController() {
    	
    }
    
    /**
     * Called when the user checks the bar chart button
     */
    @FXML
    private void handleBarChartChecked() {
        System.out.println("Bar chart check box pressed");
    }
    
    /**
     * Called when the user checks the pie chart button
     */
    @FXML
    private void handlePieChartChecked() {
        System.out.println("Pie chart check box pressed");
    }
    
    /**
     * Called when the user clicks the one percent button
     */
    @FXML
    private void handleOnePercentButtonPressed() {
        System.out.println("One percent button pressed");
    }
    
    /**
     * Called when the user clicks the ten percent button
     */
    @FXML
    private void handleTenPercentButtonPressed() {
        System.out.println("Ten percent buton pressed");
    }
    
    /**
     * Called when the user clicks the add custom curve button
     */
    @FXML
    private void handleAddCustomCurveButton() {
        System.out.println("Add custom curve button pressed");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/graph/CustomCurveAdjuster.fxml"));
        ViewUtility.showPage(loader, AnchorPane.class, "Custom Curve Adjuster");

    }
    
    /**
     * Called when the user clicks the saved curved grades button
     */
    @FXML
    private void handleSaveCurvedGradesButton() {
        System.out.println("Add custom curve button pressed");

    }
    
}