package controller.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.assignments_categories.Assignment;
import model.assignments_categories.Category;
import model.graph.Graph;
import model.spreadsheet.GradeRange;
import model.spreadsheet.SpreadsheetCourse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private PieChart pieChart;
    @FXML
    private TableView<GradeRange> gradeRangeTable;
    @FXML
    private TableColumn<GradeRange,String> colSymbols;
    @FXML
    private TableColumn<GradeRange,String> colStudsInRange;
    @FXML
    private TableColumn<GradeRange,String> colLowPercent;
    @FXML
    private TableColumn<GradeRange,String> colHighPercent;
    
    private SpreadsheetCourse course;
    private ObservableList<GradeRange> obsGradeRangeList;
    private List<GradeRange> gradeRangeList;
    
    /**
     * Creates a new CustomCurveAdjusterController
     */
    public CustomCurveAdjusterController() {
    	
    }
    
    @FXML
    private void initialize() {
        
    }
    
    public void setTable() {
        obsGradeRangeList = FXCollections.observableArrayList();
        gradeRangeList = new ArrayList<GradeRange>();
        
        gradeRangeTable.setItems(obsGradeRangeList);
        
        colSymbols.setCellValueFactory(
                new PropertyValueFactory<GradeRange,String>("letterGrade")
        );
        
        colLowPercent.setCellValueFactory(
                new PropertyValueFactory<GradeRange,String>("low")
        );
        
        colHighPercent.setCellValueFactory(
                new PropertyValueFactory<GradeRange,String>("high")
        );
        
        gradeRangeTable.setItems(obsGradeRangeList);
        
        for(GradeRange range : course.getGradingDistribution().getGradeRanges())
        {
        	obsGradeRangeList.add(range);
        }
    }
    
    public void setCourse(SpreadsheetCourse course) {
    	this.course = course;
    }
    
    /**
     * Sets the bar chart with the appropriate data if assignment is being viewed
     */
    public void setAssignmentPieChart(Assignment ass, Graph graph) {
    	this.pieChart.setTitle(ass.getName() + " Grade Distribution Pie Chart");
    	Map<String, Integer> scoreMap = graph.getAssignmentPieChartData();
    	
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
     * Sets the bar chart with the appropriate data if assignment is being viewed
     */
    public void setCategoryPieChart(Category cat, Graph graph) {
    	this.pieChart.setTitle(cat.getName() + " Grade Distribution Pie Chart");
    	Map<String, Integer> scoreMap = graph.getCategoryPieChartData();
    	
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