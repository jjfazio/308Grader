package controller.spreadsheet;

import java.util.Observable;
import java.util.Observer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.gradebook.Gradebook;
import model.spreadsheet.GradingScheme;
import model.spreadsheet.SpreadsheetCourse;
import model.spreadsheet.GradeRange;
import model.users.Student;

/**
 * This class represents the interaction between the add grading scheme dialog
 * and the various methods needed in the model package
 *
 * @author Kevin Backers      
 */

public class AddGradingSchemeController implements Observer {
    @FXML
    private TextField schemeName;
    @FXML
    private TableView<GradeRange> rangesTable;
    @FXML
    private TableColumn<GradeRange,String> colSymbols;
    @FXML
    private TableColumn<GradeRange,String> colLowPercent;
    @FXML
    private TableColumn<GradeRange,String> colHighPercent; 
    @FXML
    private TextField newSymbol;
    @FXML
    private TextField newLowPercent;
    @FXML
    private TextField newHighPercent;
    
    private ObservableList<GradeRange> gradeRangeList;
    
    private Stage primaryStage;

    private Gradebook gradebook;
    
    private SpreadsheetCourse course;
    
    @FXML
    private void initialize() {
        gradeRangeList = FXCollections.observableArrayList();
        gradeRangeList.add(new GradeRange("A", 90.0, 100.0));
        gradeRangeList.add(new GradeRange("B", 80.0, 89.9));
        gradeRangeList.add(new GradeRange("C", 70.0, 79.9));
        gradeRangeList.add(new GradeRange("D", 60.0, 69.9));
        gradeRangeList.add(new GradeRange("F", 0.0, 59.9));
        
        
        
        //rangesTable.setItems(gradeRangeList);
        
        
        //colSymbols = new TableColumn<GradeRange,String>("Symbol");
        
        colSymbols.setCellValueFactory(
                new PropertyValueFactory<GradeRange,String>("letterGrade")
        );
        
        //colLowPercent = new TableColumn<GradeRange,String>("lowPercent");
        colLowPercent.setCellValueFactory(
                new PropertyValueFactory<GradeRange,String>("low")
        );
        
        //colHighPercent = new TableColumn<GradeRange,String>("highPercent");
        colHighPercent.setCellValueFactory(
                new PropertyValueFactory<GradeRange,String>("high")
        );
        
        rangesTable.setItems(gradeRangeList);
        //rangesTable.getColumns().addAll(colSymbols, colLowPercent, colHighPercent); */
    }
    
    
    public AddGradingSchemeController() {
        gradebook = Gradebook.getInstance();
        //course = gradebook.getCurrentCourse();
    }

    /**
     * Called when the user clicks on the create button
     * This will eventually check and validate all input requirements
     */
    @FXML
    private void handleCreateButton() {
        
        // TODO: get list of graderanges from table in GUI
        // for now, just create a named scheme
    	GradingScheme tempGradingScheme = new GradingScheme(schemeName.getText());
    	System.out.println("scheme name: " + schemeName.getText());
    	
    	// TODO: get the grading scheme into the combobox ? ?
    	//course.setGradingDistribution(tempGradingScheme);
    	gradebook.addGradingScheme(tempGradingScheme);
    	close();
    	System.out.println("added grading scheme: " + schemeName.getText() + " to the gradebook"); 
    }

    /**
     * Called when the user clicks on the "+" button under the table
     * Adds a new empty row to the table
     */
    @FXML
    private void handlePlusButton() {
        
       // get data from 3 TextFields
       String symbol = newSymbol.getText();
       Double lowPercent = 0.0;
       if(!newLowPercent.getText().equals("")) {
           lowPercent = (double) Integer.parseInt(newLowPercent.getText());
       }
       Double highPercent = 100.0;
       if(!newHighPercent.getText().equals("")) {
           highPercent = (double) Integer.parseInt(newHighPercent.getText());
       }
       
       // create new GradeRange with this data
       GradeRange gr = new GradeRange(symbol, lowPercent, highPercent);
       
       // insert grade ranges into the table
       gradeRangeList.add(gr);
       rangesTable.setItems(gradeRangeList);
       
       newSymbol.clear();
       newLowPercent.clear();
       newHighPercent.clear();
        
       //update();
       System.out.println("Plus button pressed for new row");
    }
    
    /**
     * Called when the user clicks on the cancel button
     */
    @FXML
    private void handleCancelButton() {
        close();
    }
    
    private void close() {
        primaryStage = (Stage) rangesTable.getScene().getWindow();
        primaryStage.close();
    }
    
    
    
    @Override
    public void update(Observable o, Object arg) {
        
    }
}
