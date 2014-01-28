package controller.graph;

import model.assignments_categories.Assignment;
import model.assignments_categories.Category;
import model.graph.Graph;
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
 * Controller class which manages the GraphAndAdjustCurve view.
 * 
 * @author Erik Owen
 *
 */
public class GraphAndAdjustCurveController {
	/**Checkbox indicating whether or not the bar chart is visible*/
    @FXML
    private CheckBox barChartCheckBox;
    /**Checkbox indicating whether or not the pie chart is visible*/
    @FXML
    private CheckBox pieChartCheckBox;
    /**Radio button making bar chart y-axis increment by one percent*/
    @FXML
    private RadioButton onePercentGranularity;
    /**Radio button making bar chart y-axis increment by ten percent*/
    @FXML
    private RadioButton tenPercentGranularity;
    /**TextField where user can enter overall percent curve*/
    @FXML
    private TextField percentCurve;
    /**Button which opens up the custom curve adjuster view when clicked*/
    @FXML
    private Button addCustomCurveButton;
    /**Button which sends all the grades back to the spreadsheet*/
    @FXML
    private Button saveCurvedGradesButton;
    /**Pie chart object*/
    @FXML
    private PieChart pieChart;
    /**Bar chart object*/
    @FXML
    private BarChart barChart;
    /**An instance of the graph model class*/
    private Graph graph;
    
    /**
     * Creates a new instance of GraphAndAdjstCurveController
     * and initializes a new graph class.
     */
    public GraphAndAdjustCurveController() {
    	this.graph = new Graph();
    }
    
    /**
     * Setter method which passes category scores
     * to the graph model instance.
     * 
     * @param cat the current category being viewed on the graphs
     */
    public void setCategory(Category cat) {
    	this.graph.setCategory(cat);
    }
    
    /**
     * Setter method which passes the assignment scores
     * to the graph model instance.
     * 
     * @param ass the current assignment being viewed on the graphs
     */
    public void setAssignment(Assignment ass) {
    	this.graph.setAssignment(ass);
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
        int percentCurve = Integer.parseInt(this.percentCurve.getText());
        this.graph.applyStandardCurve(percentCurve);

    }
    
}