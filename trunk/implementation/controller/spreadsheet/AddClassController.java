package controller.spreadsheet;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.gradebook.Gradebook;
import model.spreadsheet.CourseDB;
import model.spreadsheet.CourseInfo;
import model.spreadsheet.GradingScheme;
import model.spreadsheet.LatePolicy;
import model.spreadsheet.SpreadsheetCourse;
import model.users.StudentDB;
import model.users.TeacherAssistant;
import view.ViewUtility;

/**
 * This class represents the interaction between the add class dialog
 * and the various methods needed in the model package
 *
 * @author Kevin Backers      
 */

public class AddClassController  {
    @FXML
    private TextField courseName;
    @FXML
    private TextField courseDepartment;
    @FXML
    private TextField courseNumber;
    @FXML
    private TextField courseSection;
    @FXML
    private TextField courseQuarter;
    @FXML
    private TextField courseYear;
    @FXML
    private GradingScheme gradingScheme;
    @FXML
    private ComboBox<GradingScheme> gradingSchemes;
    @FXML
    private TeacherAssistant ta;
    @FXML
    private ComboBox<TeacherAssistant> teacherAssistants;
    @FXML
    private LatePolicy latePolicy;
    
    private Gradebook gradebook;

    private Stage primaryStage;
    
    private ObservableList<GradingScheme> schemesObs;

    public AddClassController() {
        gradebook = Gradebook.getInstance();
       
    }
    
    @FXML
    /**
     * Called by FXML when view is loaded. Sets
     * the default grading scheme and TA.
     */
    private void initialize()
    {
        schemesObs = FXCollections.observableArrayList();
        GradingScheme defaultGS = new GradingScheme();
        schemesObs.add(defaultGS);
        gradingSchemes.getItems().clear();
        gradingSchemes.setItems(schemesObs);
    }

    /**
     * Called when the user clicks on the confirm add button
     * This will eventually check and validate all input requirements
     */
    @FXML
    private void handleCreateButton() {
        /* call addStudent function in spreadsheetcourse.java
         * in the model package
         */
        
        int courseYearInt = 0;
        if(!courseYear.getText().equals(""))
        {
            courseYearInt = Integer.parseInt(courseYear.getText());
        }
    	CourseInfo courseInfo = new CourseInfo(courseName.getText(),
    	        courseQuarter.getText(), courseSection.getText(), 
    	        courseNumber.getText(), courseDepartment.getText(), courseYearInt);
    	SpreadsheetCourse course = new SpreadsheetCourse(courseInfo,
    	        gradingSchemes.getValue(), new LatePolicy());
    	
    	gradebook.addSpreadsheetCourse(course);
    }

    /**
     * Called when the user clicks on the "Create New" button for grading schemes
     * Opens the Create New Grading Scheme dialog
     */
    @FXML
    private void handleCreateNewGradingSchemeButton() {
       FXMLLoader loader = new FXMLLoader(getClass().getResource(
          "/view/spreadsheet/AddGradingScheme.fxml"));
       ViewUtility.loadAndShowPage(loader, AnchorPane.class, "Create Grading Scheme");
    }

    /**
     * Called when the user clicks on the "Create New" button for TA's
     * Opens the Create New Teacher Assistant dialog
     */
    @FXML
    private void handleCreateNewTeacherAssistantButton() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/view/users/AddTeacherAssistant.fxml"));
        ViewUtility.loadAndShowPage(loader, AnchorPane.class, "Create Teacher Assistant");
    }
    
    /**
     * Called when the user clicks on the "Cancel" button
     * Closes the Create Class dialog
     */
    @FXML
    private void handleCancelButton() {
        primaryStage = (Stage) courseName.getScene().getWindow();
        primaryStage.close();
    }
}
