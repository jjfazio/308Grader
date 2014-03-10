package controller.graph;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import model.assignments_categories.Assignment;
import model.assignments_categories.Category;
import model.exception.BadDataException;
import model.gradebook.Gradebook;
import model.graph.Graph;
import model.spreadsheet.SpreadsheetCourse;
import model.users.Student;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.ViewUtility;

/**
 * Controller class which manages the GraphAndAdjustCurve view.
 * 
 * @author Erik Owen
 *
 */
public class GraphAndAdjustCurveController {
	/**Root anchorpane which all of the children nodes are in*/
	@FXML
	private AnchorPane root;
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
    /**Title of Page*/
    @FXML
    private Label graphAndAdjustCurveTitle;
    /**An instance of the graph model class*/
    private Graph graph;
    /**The current assignment being viewed*/
    private Assignment ass;
    /**The current category being viewed*/
    private Category cat;
    /**The current spreadsheet course open*/
    private SpreadsheetCourse course;
    
    /**
     * Creates a new instance of GraphAndAdjstCurveController
     * and initializes a new graph class.
     */
    public GraphAndAdjustCurveController() {
    	this.graph = new Graph();
    }
    
    /**
     * Initializes the scene
     */
    @FXML
    private void initialize() {
        this.addPercentCurveTextChangedListener();
    }
    
    /**
     * Setter method which passes category scores
     * to the graph model instance.
     * 
     * @param cat the current category being viewed on the graphs
     */
    public void setCategory(Category cat, List<Student> students, String granularity) {
    	this.cat = cat;
    	this.ass = null;
    	this.graphAndAdjustCurveTitle.setText(cat.getName() + " Graph & Curve Adjustment");
    	this.graph.setCategory(cat, students);
    	
    	setBarChart(granularity);
    	setPieChart();
    }
    
    /**
     * Setter method which passes the assignment scores
     * to the graph model instance.
     * 
     * @param ass the current assignment being viewed on the graphs
     */
    public void setAssignment(Assignment assignment, List<Student> students, String granularity) {
    	this.ass = assignment;
    	this.cat = null;
    	this.graphAndAdjustCurveTitle.setText(ass.getName() + " Graph & Curve Adjustment");
    	this.graph.setAssignment(assignment, students);
    	setBarChart(granularity);
    	setPieChart();
    }
    
    /**
     * Sets the current course that is being accessed to view grades
     * 
     * @param course the current spreadsheet course open
     */
    public void setCourse(SpreadsheetCourse course) {
    	this.course = course;
    	this.graph.setCourse(course);
    }
    
    /**
     * Sets the bar chart's data for the chosen assignment
     * 
     * @param granularity either 10% or 1% interval granularity
     */
    private void setBarChart(String granularity) {
    	Map<String, Integer> scoreMap;
    	
    	if(this.cat == null) {
    		scoreMap = graph.getAssignmentBarChartData(granularity);
    		this.barChart.setTitle(ass.getName() + " Grade Distribution Bar Chart");
    	}
    	else {
    		scoreMap = graph.getCategoryBarChartData(granularity);
    		this.barChart.setTitle(cat.getName() + " Grade Distribution Bar Chart");
    	}
    	
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        
        xAxis.setLabel("Number of Students");
        List<String> categories = Arrays.asList(scoreMap.keySet().toArray(new String[0]));
        Collections.sort(categories);
        xAxis.setCategories((FXCollections.<String>observableArrayList(categories)));
        //Series<String, Integer> series1 = new XYChart.Series<String, Integer>();
        Series series1 = new XYChart.Series();
        
        if(granularity.equals("1%")) {
        	for(int ndx = 0; ndx < scoreMap.size(); ndx++) {
        		String curPercentString = new Integer(ndx).toString();
        		if(scoreMap.get(curPercentString) > 0) {
        			series1.getData().add(new XYChart.Data<String, 
        				Integer>(curPercentString, scoreMap.get(curPercentString)));
        		}
        	}
        }
        else {
        	for(int ndx = 0; ndx < scoreMap.size(); ndx++) {
        		String curPercentString = new Integer(ndx * 10).toString();
        		if(scoreMap.containsKey(curPercentString)) {
        			series1.getData().add(new XYChart.Data<String, 
        				Integer>(curPercentString, scoreMap.get(curPercentString)));
        		}
        	}
        }
        
        this.barChart.setLegendVisible(false);
        this.barChart.getYAxis().setLabel("Number of Students");
        this.barChart.getXAxis().setLabel("Grade (%)");
  
        this.barChart.getData().clear();
        this.barChart.getData().add(series1);
        this.barChart.getXAxis().setAutoRanging(true);
        this.barChart.getYAxis().setAutoRanging(true);
    }
    
    /**
     * Sets the pie chart to have data for the chosen assignment
     */
    private void setPieChart() {
    	Map<String, Integer> scoreMap;
    	
    	if(this.cat == null) {
    		scoreMap = graph.getAssignmentPieChartData();
    		this.pieChart.setTitle(ass.getName() + " Grade Distribution Pie Chart");
    	}
    	else {
    		scoreMap = graph.getCategoryPieChartData();
    		this.pieChart.setTitle(cat.getName() + " Grade Distribution Pie Chart");
    	}
    	
    	for(String gradeStr : scoreMap.keySet()) {
    		if(scoreMap.get(gradeStr) > 0) {
    			PieChart.Data data = new PieChart.Data(gradeStr, scoreMap.get(gradeStr));
        		this.pieChart.getData().add(data);
    		}
    	}
        
        this.pieChart.setLegendVisible(false);
        this.pieChart.setVisible(true);
        this.pieChart.setLabelsVisible(true);
    }
    
    /**
     * Called when the user checks the bar chart button
     */
    @FXML
    private void handleBarChartChecked() {
    	boolean isVisible = this.barChart.isVisible();
    	this.barChart.setVisible(!isVisible);
        System.out.println("Bar chart check box pressed");
    }
    
    /**
     * Called when the user checks the pie chart button
     */
    @FXML
    private void handlePieChartChecked() {
    	boolean isVisible = this.pieChart.isVisible();
    	this.pieChart.setVisible(!isVisible);
        System.out.println("Pie chart check box pressed");
    }
    
    /**
     * Called when the user clicks the one percent button
     */
    @FXML
    private void handleOnePercentButtonPressed() {
        System.out.println("One percent button pressed");
        this.onePercentGranularity.setSelected(true);
        this.tenPercentGranularity.setSelected(false);
        
        setBarChart("1%");
    }
    
    /**
     * Called when the user clicks the ten percent button
     */
    @FXML
    private void handleTenPercentButtonPressed() {
        System.out.println("Ten percent buton pressed");
        this.tenPercentGranularity.setSelected(true);
        this.onePercentGranularity.setSelected(false);
        
        this.setBarChart("10%");
    }
    
    /**
     * Called when the user clicks the add custom curve button
     */
    @FXML
    private void handleAddCustomCurveButton() {
        System.out.println("Add custom curve button pressed");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/graph/CustomCurveAdjuster.fxml"));
        //ViewUtility.loadAndShowPage(loader, AnchorPane.class, "Custom Curve Adjuster");

    	AnchorPane pane = (AnchorPane)ViewUtility.loadView(loader);
    	CustomCurveAdjusterController controller = (CustomCurveAdjusterController)loader.getController();
    	Map<String, Integer> scoreMap = graph.getAssignmentBarChartData("1%");
    	controller.setBarChart(ass, scoreMap, "1%");
        ViewUtility.showPage(pane, "Custom Curve Adjustment");
    }
    
    /**
     * Called when the user clicks the saved curved grades button
     */
    @FXML
    private void handleSaveCurvedGradesButton() {
        System.out.println("Add percent curve button pressed");
        System.out.println(this.percentCurve.getText() + " entered.");
        try {
        	this.graph.applyStandardCurve(percentCurve.getText().toString());
        	this.pieChart.getData().clear();
        	this.barChart.getData().clear();
        	setBarChart("10%");
        	setPieChart();
        	Gradebook.getInstance().saveGradebook();
        }
        catch(BadDataException e) {
        	System.out.println(e.getMessage());
        	Dialogs.showErrorDialog(getStage(), "Invalid input: enter an integer."
        	    , "Error", "Adjust Curve");
        }

    }
    
    /**
     * Called when the text is changed in the add percent curve text field
     */
    private void addPercentCurveTextChangedListener() {
    	this.percentCurve.textProperty().addListener(new ChangeListener<String>() {
    		@Override
    		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			System.out.println("Percent curve text changed.");
    			
                if(newValue.length() > 2) {
                    percentCurve.setText(oldValue);
                }
                
    			if(percentCurve.getText().toString().isEmpty()) {
    				saveCurvedGradesButton.setDisable(true);
    			}
    			else {
    				saveCurvedGradesButton.setDisable(false);
    				
    			}
    		}
    	});
    }
    
    /**
     * Get the stage of this view
     * @return the stage of this view
     */
    private Stage getStage() {
        return (Stage) root.getScene().getWindow();
    }
    
}