package controller.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.assignments_categories.Assignment;
import model.assignments_categories.Category;
import model.assignments_categories.Grade;
import model.graph.AdjustableGradeRange;
import model.graph.Graph;
import model.spreadsheet.AssignView;
import model.spreadsheet.GradeRange;
import model.spreadsheet.SpreadsheetCourse;
import model.users.Student;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

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
    
    private class EditHandler implements EventHandler<CellEditEvent<AdjustableGradeRange, String>> {
		@Override
		public void handle(CellEditEvent<AdjustableGradeRange, String> arg0) {
			System.out.println("Cell changed!");
			
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
    
//    private class EditCallBack implements Callback<TableColumn<AdjustableGradeRange, String>, TableCell<AdjustableGradeRange, String>> {
//
//        @Override
//        public TableCell<AdjustableGradeRange, String> call(TableColumn<AdjustableGradeRange, String> arg)
//        {
//            //Assignment assign = (Assignment) arg.getUserData();
//            return new EditingCell();
//        }
//           
//       }
//    
//    class EditingCell extends TableCell<AdjustableGradeRange, String> {
//
//        private TextField textField;
//
//        @Override
//        public void startEdit() {
//            if (!isEmpty()) {
//                super.startEdit();
//                createTextField();
//                setText(null);
//                setGraphic(textField);
//                textField.selectAll();
//                
//                //Hack to get double click to work
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        textField.requestFocus();
//                    }
//               });
//
//            }
//        }
//
//        @Override
//        public void cancelEdit() {
//            super.cancelEdit();
//
//            setText((String) getItem());
//            setGraphic(null);
//        }
//
//        @Override
//        public void updateItem(String item, boolean empty) {
//            super.updateItem(item, empty);
//
//            if (empty) {
//                setText(null);
//                setGraphic(null);
//            } else {
//                if (isEditing()) {
//                    if (textField != null) {
//                        textField.setText(getString());
//                    }
//                    setText(null);
//                    setGraphic(textField);
//                } else {
//                    setText(getString());
//                    setGraphic(null);
//                }
//            }
//        }
//
//        private void createTextField() {
//            textField = new TextField(getString());
//            
//            textField.setFocusTraversable(true);
//            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
//            textField.focusedProperty().addListener(new ChangeListener<Boolean>(){
//                @Override
//                public void changed(ObservableValue<? extends Boolean> arg0, 
//                    Boolean arg1, Boolean arg2) {
//                        if (!arg2) {
//                            commitEdit(textField.getText());
//                        }
//                }
//            });
//            
//            // Allows for tabbing and entering in assignment cells
//            textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
//                @Override
//                public void handle(KeyEvent t) {
//                    if (t.getCode() == KeyCode.ENTER) {
//                        commitEdit(textField.getText());
//                    } else if (t.getCode() == KeyCode.ESCAPE) {
//                        cancelEdit();
//                    } else if (t.getCode() == KeyCode.TAB) {
//                        commitEdit(textField.getText());
//                        getTableView().getSelectionModel().selectBelowCell();
//                        }
//                    }
//                });
//        }
//
//        private String getString() {
//            return getItem() == null ? "" : getItem().toString();
//        }
//    }
    
    
}