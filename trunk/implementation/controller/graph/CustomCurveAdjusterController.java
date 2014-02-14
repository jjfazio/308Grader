package controller.graph;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import model.assignments_categories.Assignment;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
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
     * Sets the bar chart with the appropriate data
     */
    public void setBarChart(Assignment ass, Map<String, Integer>scoreMap, String granularity) {
    	CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        
        this.barChart.setTitle(ass.getName() + " Grade Distribution Curve Adjuster");
        xAxis.setLabel("Number of Students");
        List<String> categories = Arrays.asList(scoreMap.keySet().toArray(new String[0]));
        Collections.sort(categories);
        xAxis.setCategories((FXCollections.<String>observableArrayList(categories)));
        //Series<String, Integer> series1 = new XYChart.Series<String, Integer>();
        Series series1 = new XYChart.Series();
        
        for(int ndx = 0; ndx < scoreMap.size(); ndx++) {
        	String curPercentString = new Integer(ndx).toString();
        	//if(scoreMap.get(curPercentString) > 0) {
        	series1.getData().add(new XYChart.Data<String, 
        		Integer>(curPercentString, scoreMap.get(curPercentString)));
        	//}
        }
        
        this.barChart.getXAxis().setMinWidth(200);
        this.barChart.setLegendVisible(false);
        this.barChart.getYAxis().setLabel("Number of Students");
        this.barChart.getXAxis().setLabel("Grade (%)");
  
        this.barChart.getData().clear();
        this.barChart.getData().add(series1);
        this.barChart.getXAxis().setAutoRanging(true);
        this.barChart.getYAxis().setAutoRanging(true);
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