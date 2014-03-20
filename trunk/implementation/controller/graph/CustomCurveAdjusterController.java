package controller.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.assignments_categories.Assignment;
import model.assignments_categories.Category;
import model.graph.AdjustableGradeRange;
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
    private TableView<AdjustableGradeRange> gradeRangeTable;
    @FXML
    private TableColumn<AdjustableGradeRange,String> colSymbols;
    @FXML
    private TableColumn<AdjustableGradeRange,String> colStudsInRange;
    @FXML
    private TableColumn<AdjustableGradeRange,String> colLowPercent;
    @FXML
    private TableColumn<AdjustableGradeRange,String> colHighPercent;
    
    private SpreadsheetCourse course;
    private ObservableList<AdjustableGradeRange> obsGradeRangeList;
    private List<AdjustableGradeRange> gradeRangeList;
    private Graph graph;
    private Map<String, Integer> rangeStudMap;
    
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
        gradeRangeList = new ArrayList<AdjustableGradeRange>();
        
        gradeRangeTable.setItems(obsGradeRangeList);
        
        colSymbols.setCellValueFactory(
                new PropertyValueFactory<AdjustableGradeRange,String>("letterGrade")
        );
        
        colStudsInRange.setCellValueFactory(
                new PropertyValueFactory<AdjustableGradeRange,String>("studsInRange")
        );
        
        colLowPercent.setCellValueFactory(
                new PropertyValueFactory<AdjustableGradeRange,String>("low")
        );
        
        colHighPercent.setCellValueFactory(
                new PropertyValueFactory<AdjustableGradeRange,String>("high")
        );
        
        gradeRangeTable.setItems(obsGradeRangeList);
        
        for(GradeRange range : course.getGradingDistribution().getGradeRanges())
        {
        	AdjustableGradeRange temp = new AdjustableGradeRange(range, rangeStudMap);
        	obsGradeRangeList.add(temp);
        }
    }
    
    public void setCourse(SpreadsheetCourse course) {
    	this.course = course;
    }
    
    /**
     * Sets the bar chart with the appropriate data if assignment is being viewed
     */
    public void setAssignmentPieChart(Assignment ass, Graph graph) {
    	this.graph = graph;
    	rangeStudMap = graph.getAssignmentPieChartData();
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
    	this.graph = graph;
    	rangeStudMap = graph.getCategoryPieChartData();
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