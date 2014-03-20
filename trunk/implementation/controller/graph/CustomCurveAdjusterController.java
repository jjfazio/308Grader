package controller.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.assignments_categories.Assignment;
import model.assignments_categories.Category;
import model.exception.BadDataException;
import model.exception.GradingSchemeDataException;
import model.exception.OverlappingRangesException;
import model.graph.AdjustableGradeRange;
import model.graph.Graph;
import model.spreadsheet.GradeRange;
import model.spreadsheet.GradingScheme;
import model.spreadsheet.SpreadsheetCourse;

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
    @FXML
    private Button refreshGraphButton;
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
    @FXML
    private AnchorPane root;
    private SpreadsheetCourse course;
    private ObservableList<AdjustableGradeRange> obsGradeRangeList;
    private List<AdjustableGradeRange> gradeRangeList;
    private Graph graph;
    private Map<String, Integer> rangeStudMap;
    private Category cat;
    private Assignment ass;
    
    /**
     * Creates a new CustomCurveAdjusterController
     */
    public CustomCurveAdjusterController() {
    	
    }
    
    @FXML
    private void initialize() {
        gradeRangeTable.setEditable(true);
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
        colLowPercent.setCellFactory(TextFieldTableCell.<AdjustableGradeRange>forTableColumn());
        colLowPercent.setCellValueFactory(new EditableCallBack(false));
        colLowPercent.setOnEditCommit(new EditHandler());
        
        colHighPercent.setCellValueFactory(
                new PropertyValueFactory<AdjustableGradeRange,String>("high")
        );
        colHighPercent.setCellFactory(TextFieldTableCell.<AdjustableGradeRange>forTableColumn());
        colHighPercent.setCellValueFactory(new EditableCallBack(true));
        colHighPercent.setOnEditCommit(new EditHandler());
        
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
    
    public void setCategory(Category category) {
    	this.cat = category;
    	this.ass = null;
    }
    
    public void setAssignment(Assignment assignment) {
    	this.ass = assignment;
    	this.cat = null;
    }
    
    /**
     * Sets the bar chart with the appropriate data if assignment is being viewed
     */
    public void setAssignmentPieChart(Assignment ass, Graph graph, GradingScheme scheme) {
    	this.graph = graph;
    	rangeStudMap = graph.getAssignmentPieChartData(scheme);
    	this.pieChart.setTitle(ass.getName() + " Grade Distribution Pie Chart");
    	Map<String, Integer> scoreMap = graph.getAssignmentPieChartData(scheme);
    	
    	this.pieChart.getData().clear();
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
    public void setCategoryPieChart(Category cat, Graph graph, GradingScheme scheme) {
    	this.graph = graph;
    	rangeStudMap = graph.getCategoryPieChartData(scheme);
    	this.pieChart.setTitle(cat.getName() + " Grade Distribution Pie Chart");
    	Map<String, Integer> scoreMap = graph.getCategoryPieChartData(scheme);
    	
    	this.pieChart.getData().clear();
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
     * Called when the user clicks the refresh button
     */
    @FXML
    private void handleRefreshGraphButtonPressed() {
    	for(AdjustableGradeRange curRange : this.obsGradeRangeList) {
    		System.out.println(curRange.getLetterGrade() + ": " + curRange.getLow() + ", " + curRange.getHigh());
    	}
    	
    	try {
			AdjustableGradeRange.checkForOverlappingRanges(obsGradeRangeList);
			List<GradeRange> tempRangeList = new ArrayList<GradeRange>();
			
			for(AdjustableGradeRange curRange : obsGradeRangeList) {
				tempRangeList.add(curRange.getGradeRangeVersion());
			}
			
			GradingScheme scheme = new GradingScheme(tempRangeList, course.getGradingDistribution().getSchemeName());
			
			if(ass != null) {
				setAssignmentPieChart(ass, graph, scheme);
			}
			else {
				setCategoryPieChart(cat, graph, scheme);
			}
			
		}
    	catch (OverlappingRangesException e) {
    		String message = "There are overlapping grade ranges:\n" + e.getFirstRange().getLetterGrade()
    			+ " and " + e.getSecondRange().getLetterGrade() + " overlap.";
    		Dialogs.showErrorDialog(getStage(), message, "Error", "Custom Curve Adjustment");
		} catch (GradingSchemeDataException e) {
			
		}
    }
    
    /**
     * Called when the user clicks the apply curve button
     */
    @FXML
    private void handleApplyCurveButtonPressed() {
        System.out.println("Apply curve button pressed");
    }
    
    private class EditHandler implements EventHandler<CellEditEvent<AdjustableGradeRange, String>> {
		@Override
		public void handle(CellEditEvent<AdjustableGradeRange, String> cellText) {
			AdjustableGradeRange curRange = cellText.getRowValue();
			
			try {
				if(cellText.getTableColumn().equals(colLowPercent)) {
					System.out.println("Adjusted Low");
					curRange.setLow(cellText.getNewValue());
				}
				else if(cellText.getTableColumn().equals(colHighPercent)) {
					System.out.println("Adjusted High");
					curRange.setHigh(cellText.getNewValue());
				}
			}
			catch(BadDataException bde) {
				Dialogs.showErrorDialog(getStage(), "Invalid input: you must enter a number."
						, "Error", "Custom Curve Adjustment");
			}
		}
    }
    
    private class EditableCallBack implements Callback<TableColumn.CellDataFeatures<AdjustableGradeRange, String>, ObservableValue<String>> {
    	private boolean high;
    	
    	public EditableCallBack(Boolean high)
    	{
    		this.high = high;
    	}
        @Override
        public ObservableValue<String> call(CellDataFeatures<AdjustableGradeRange, String> param)
        {
        	if (high)
        		return new SimpleStringProperty(param.getValue().getHigh().toString());
        	else
        		return new SimpleStringProperty(param.getValue().getLow().toString());
        }
    }
    
    /**
     * Get the stage of this view
     * @return the stage of this view
     */
    private Stage getStage() {
        return (Stage) root.getScene().getWindow();
    }
}