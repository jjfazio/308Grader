package controller.spreadsheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.exception.GradingSchemeDataException;
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
    private TableColumn<GradeRange,String> colColor;
    @FXML
    private TextField newSymbol;
    @FXML
    private TextField newLowPercent;
    @FXML
    private TextField newHighPercent;
    @FXML
    private ColorPicker newColor;
    
    private List<GradeRange> gradeRangeList; // serializable list to be sent to the GS constructor
    
    private ObservableList<GradeRange> obsGradeRangeList; // not serializable but needed for the view methods
    
    private Stage primaryStage;

    private Gradebook gradebook;
    
    private SpreadsheetCourse course;
    
    @FXML
    private void initialize() {
        
        obsGradeRangeList = FXCollections.observableArrayList();
        gradeRangeList = new ArrayList<GradeRange>();
        
        rangesTable.setItems(obsGradeRangeList);
        
        colSymbols.setCellValueFactory(
                new PropertyValueFactory<GradeRange,String>("letterGrade")
        );
        
        colLowPercent.setCellValueFactory(
                new PropertyValueFactory<GradeRange,String>("low")
        );
        
        colHighPercent.setCellValueFactory(
                new PropertyValueFactory<GradeRange,String>("high")
        );
        
        colColor.setCellValueFactory(
                new PropertyValueFactory<GradeRange,String>("color")
        );
        
        newColor.setValue(Color.BLACK);
        rangesTable.setItems(obsGradeRangeList);
    }
    
    public AddGradingSchemeController() {
        gradebook = Gradebook.getInstance();
    }

    /**
     * Called when the user clicks on the create button
     * This will eventually check and validate all input requirements
     */
    @FXML
    private void handleCreateButton() {
        try {
        	GradingScheme tempGradingScheme = new GradingScheme(gradeRangeList, schemeName.getText());
        	gradebook.addGradingScheme(tempGradingScheme);
        	close();
        } catch (GradingSchemeDataException e) {
            Dialogs.showErrorDialog(getStage(), e.getMessage(), "Input Data Error", "Grading Scheme Error");
        }
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
           lowPercent = Double.parseDouble(newLowPercent.getText());
       }
       Double highPercent = 100.0;
       if(!newHighPercent.getText().equals("")) {
           highPercent = Double.parseDouble(newHighPercent.getText());
       }
       
       // create new GradeRange with this data
       GradeRange gr = new GradeRange(symbol, lowPercent, highPercent);
       
       
       Color c = newColor.getValue();
       int r = (int) (c.getRed() * 256) ;
       int g = (int) (c.getGreen() * 256) ;
       int b = (int) (c.getBlue() * 256) ;
       String toSet = String.format("rgb(%d, %d, %d)", r, g, b);
       
       gr.setColor(toSet);
       
       // insert grade ranges into the table
       gradeRangeList.add(gr);
       obsGradeRangeList.add(gr);
       rangesTable.setItems(obsGradeRangeList);
       
       newSymbol.clear();
       newLowPercent.clear();
       newHighPercent.clear();
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
    
    /**
     * Get the stage of this view
     * @return the stage of this view
     */
    private Stage getStage() {
        return (Stage) schemeName.getScene().getWindow();
    }
}
