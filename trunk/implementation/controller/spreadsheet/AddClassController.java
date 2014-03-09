package controller.spreadsheet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.exception.BadDataException;
import model.exception.CourseDataException;
import model.gradebook.Gradebook;
import model.spreadsheet.CourseInfo;
import model.spreadsheet.GradingScheme;
import model.spreadsheet.LatePolicy;
import model.spreadsheet.SpreadsheetCourse;
import model.users.TeacherAssistant;
import view.ViewUtility;

import java.util.Observable;
import java.util.Observer;

/**
 * This class represents the interaction between the add class dialog
 * and the various methods needed in the model package
 *
 * @author Kevin Backers      
 */

public class AddClassController implements Observer {
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
    private ComboBox<GradingScheme> gradingSchemes;
    @FXML
    private ComboBox<TeacherAssistant> teacherAssistants;
    @FXML
    private LatePolicy latePolicy;
    @FXML
    private RadioButton decay;
    @FXML
    private TextField decayPercentage;
    @FXML
    private TextField decayRate;
    @FXML
    private RadioButton noLatePolicy;
    @FXML
    private RadioButton allotment;
    @FXML
    private RadioButton graceDays;
    @FXML
    private TextField numberOfGraceDays;
    
    private Gradebook gradebook;

    private Stage primaryStage;
    
    private ObservableList<GradingScheme> schemesObs;

    public AddClassController() {
        gradebook = Gradebook.getInstance();
       
    }
    
    @FXML
    /**
     * Called by FXML when view is loaded. Sets
     * the default grading scheme and late policy.
     */
    private void initialize()
    {
        gradebook.addObserver(this);
        schemesObs = FXCollections.observableArrayList();
        GradingScheme defaultGS = new GradingScheme();
        schemesObs.add(defaultGS);
        gradingSchemes.getItems().clear();
        
        gradingSchemes.setItems(schemesObs);
        
        noLatePolicy.setSelected(true);
        graceDays.setSelected(false);
        decay.setSelected(false);
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
        
    	CourseInfo courseInfo;
        try {
            courseInfo = new CourseInfo(courseName.getText(),
                     courseQuarter.getText(), courseSection.getText(), 
                     courseNumber.getText(), courseDepartment.getText(), courseYearInt);
            
        	/** Make the late policy */
        	LatePolicy lp = new LatePolicy();
        	
        	if(decay.isSelected())
        	{
        	   lp.setDecayRate(Integer.parseInt(decayRate.getText()));
        	   lp.setDecayPercentage(Integer.parseInt(decayPercentage.getText()) * 1.0);
        	}
        	else if(graceDays.isSelected())
        	{
        	    lp.setGraceDaysEnabled(true);
        	    lp.setGraceDays(Integer.parseInt(numberOfGraceDays.getText()));
        	}
        	SpreadsheetCourse course = new SpreadsheetCourse(courseInfo,
        	        gradingSchemes.getValue(), lp);
        	
        	gradebook.addSpreadsheetCourse(course);
        	close();
        	/*
        	System.out.println("Course " + course.getCourseInfo().getCourseName() + " added with a late policy of " 
        	        + course.getLatePolicy().getGraceDays() + " grace days and penalty of " + course.getLatePolicy().getDecayPercentage() 
        	        + "% per " + course.getLatePolicy().getDecayRate() + " day(s) and a gradings scheme of "
        	        + course.getGradingDistribution().toString());
        	*/
        } 
        catch (CourseDataException e) 
        {
            Dialogs.showErrorDialog(getStage(), e.getMessage(), "Input Data Error", "Course Error");
        }
        catch (BadDataException e)
        {
            e.printStackTrace();
        }
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
        close();
    }
    
    private void close() {
        primaryStage = (Stage) courseName.getScene().getWindow();
        primaryStage.close();
    }
    
    @Override
    public void update(Observable o, Object arg) {
        schemesObs.addAll(gradebook.getGradingSchemes());
        gradingSchemes.setItems(schemesObs);
        System.out.println("gs update called");
    }
    
    /**
     * Get the stage of this view
     * @return the stage of this view
     */
    private Stage getStage() {
        return (Stage) courseName.getScene().getWindow();
    }
}
