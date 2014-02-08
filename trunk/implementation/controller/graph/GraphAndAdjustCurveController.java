package controller.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import model.assignments_categories.Assignment;
import model.assignments_categories.Category;
import model.graph.Graph;
import model.graph.Range;
import model.users.Student;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
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
    private BarChart<String, Integer> barChart;
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
    public void setCategory(Category cat, List<Student> students) {
    	this.graph.setCategory(cat, students);
    }
    
    /**
     * Setter method which passes the assignment scores
     * to the graph model instance.
     * 
     * @param ass the current assignment being viewed on the graphs
     */
    public void setAssignment(Assignment ass, List<Student> students) {
    	this.graph.setAssignment(ass, students);
    	HashMap<Range, Integer> scoreMap = graph.getAssignmentData();
    	final String[] grade = {"0 - 10", "10 - 20", "20 - 30", "30 - 40",
    		"40 - 50", "50 - 60", "60 - 70", "70 - 80", "80 - 90",
    		"90 - 100", "100+"};
        final CategoryAxis yAxis = new CategoryAxis();
        final NumberAxis xAxis = new NumberAxis();
        //this.barChart = new BarChart<Number, String>(xAxis, yAxis);
        //final BarChart<Number,String> bc = new BarChart<Number,String>(xAxis,yAxis);
        this.barChart.getXAxis().setAutoRanging(true);
        this.barChart.getYAxis().setAutoRanging(true);
        this.barChart.setTitle(ass.getName() + " Grade Distribution BarChart");
        yAxis.setLabel("Grade (%)");
        yAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(grade)));
        Series<String, Integer> series1 = new XYChart.Series<String, Integer>();
        series1.setName("Number of Students");
        Range[] ranges = Range.values();
        
        for(int ndx = 0; ndx < scoreMap.size(); ndx ++) {
        	series1.getData().add(new XYChart.Data<String, 
        		Integer>(grade[ndx], scoreMap.get(ranges[ndx])));
        }
        
        this.barChart.getData().add(series1);
        
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
        ViewUtility.loadAndShowPage(loader, AnchorPane.class, "Custom Curve Adjuster");

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