package controller.assignments_categories;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import model.assignments_categories.Assignment;
import model.assignments_categories.Category;
import model.gradebook.Gradebook;
import model.spreadsheet.GradingScheme;
import model.spreadsheet.LatePolicy;
import view.ViewUtility;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.assignments_categories.Category;
import model.gradebook.Gradebook;
import model.spreadsheet.SpreadsheetCourse;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogResponse;

import javax.sql.rowset.CachedRowSet;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Jirbert Dilanchian
 */
public class AddAssignmentController {

    @FXML
    private TextField addAssignmentName;

    @FXML
    private ComboBox addAssignmentCategory;

    @FXML
    private TextField addAssignmentDueDate;

    @FXML
    private TextField addAssignmentWeight;

    @FXML
    private TextField addAssignmentPoints;

    @FXML
    private RadioButton addAssignmentDefaultLatePolicy;
    @FXML
    private RadioButton addAssignmentSetLatePolicy;
    @FXML
    private RadioButton addAssignmentSetGradingScheme;
    @FXML
    private RadioButton addAssignmentDefaultGradingScheme;

//    public AddAssignmentController() {
//    }

    /**
     * Called by FXML when view is loaded. Reloads all of the
     * categories.
     */
    @FXML
    private void initialize() {
        addAssignmentCategory.getItems().clear();
        fillComboCategoryNames(Gradebook.getInstance().getCurrentCourse().getTopCategory());
    }

    /**
     * Filles the comboBox on add Category dialogue.
     * @param theCat The category which name is going to be added to the list
     */
    @FXML
    private void fillComboCategoryNames(Category theCat) {
        addAssignmentCategory.getItems().add(theCat.getName());
        if((ArrayList<Category>)theCat.getSubCategories() != null){
            for(Category x : (ArrayList<Category>)theCat.getSubCategories()) {
                fillComboCategoryNames(x);
            }
        }
    }
    /**
     * Adds an assignment to the collection of assignment of the parent Category.
     */
    @FXML
    private void handleAddAssignmentSave() {
        System.out.println("Save button Clicked!");
        addAssignment(addAssignmentCategory.getValue().toString(),
                Gradebook.getInstance().getCurrentCourse().getTopCategory());
    }

    /**
     * Locates the parent category in the arraylist of subCategories and adds the new category to it.
     * @param name The name of the parent category.
     * @param cat The new category to be added to parent category
     */
    private void addAssignment(String name, Category cat) {
        Category catLookingFor = null;
        if(cat.getName().equals(name)) {
            catLookingFor =  cat;
//            if(addAssignmentSetLatePolicy.isSelected() == false ){//&& addAssignmentSetGradingScheme.isSelected() == false) {
                Assignment newAssignment = new Assignment(addAssignmentName.getText(),
                                                          Double.parseDouble(addAssignmentWeight.getText()),
                                                          Integer.parseInt(addAssignmentPoints.getText()),
                                                          new Date(), new GradingScheme(), new LatePolicy(), false);


                cat.addAssignment(newAssignment);
//            }
        }
        if((ArrayList<Category>)cat.getSubCategories() != null && catLookingFor == null){
            for(Category x : (ArrayList<Category>)cat.getSubCategories()) {
                if(catLookingFor == null) {
                    addAssignment(name, x);
                }
            }
        }
    }
//    public Assignment(String name, Double percentOfCategory, Integer maxPoints,
//Date dueDate, GradingScheme gScheme, LatePolicy latePolicy,
//    Boolean hasElectronicTurnin)



    /**
     * Closes the Add Assignment page without saving anything.
     */
    @FXML
    private void handleAddAssignmentCancel() {
        System.out.println("Cancel button Clicked!");
    }
}
