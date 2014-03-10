package controller.gradebook;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.exception.StudentDataException;
import model.gradebook.Gradebook;
import model.spreadsheet.SpreadsheetCourse;
import model.users.Student;
import view.ViewUtility;

import java.util.ArrayList;

/****
 *
 * 
 *
 * @author      Kevin Backers     (kbackers@calpoly.edu)
 *
 */

public class EditClassController {

    /** The root of this scene */
    @FXML
    private Parent root;

    /** Contains the name of the course */
    @FXML
    private TextField name;

    /** Contains the department of the course */
    @FXML
    private TextField department;

    /** Contains the number of the course */
    @FXML
    private TextField number;
    
    /** Contains the section of the course */
    @FXML
    private TextField section;

    /** Contains the quarter of the course */
    @FXML
    private TextField quarter;
    
    /** Contains the year of the course */
    @FXML
    private TextField year;

    /** Holds the reference for the class we wish to edit */
    SpreadsheetCourse courseToEdit;
    
    Gradebook gradebook;

    /**
     * Initializes the edit student screen.
     */
    @FXML
    protected void initialize() {
        
        this.courseToEdit = gradebook.getCurrentCourse();
        
        name.setText(courseToEdit.getCourseName());
        department.setText(courseToEdit.getCourseInfo().getDept());
        number.setText(courseToEdit.getCourseInfo().getNumber());
        section.setText(courseToEdit.getCourseSection());
        quarter.setText(courseToEdit.getCourseInfo().getQuarter());
        year.setText(courseToEdit.getCourseInfo().getYear()); 
    }

    /**
     * Contructor for this class
     */
    public EditClassController() {
        
        this.gradebook = Gradebook.getInstance();
    }

    /**
     * Called when the user clicks on the confirm edit button
     * This edit the SpreadsheetCourse class
     */
    @FXML
    private void handleConfirmEditButton() {
        
        gradebook.getCurrentCourse().editCourse(
                name.getText(), 
                department.getText(), 
                number.getText(), 
                section.getText(), 
                quarter.getText(),
                year.getText());
        
        gradebook.updatedCourse();
        getStage().close();
        
    }

    /**
     * Closes the stage when the cancel button is selected
     */
    @FXML
    private void handleCancelButton() {
        getStage().close();
    }

    /**
     * Gets the root stage of the view
     * @return  Stage   The root stage of the view
     */
    private Stage getStage() {
        return (Stage) root.getScene().getWindow();
    }
}
