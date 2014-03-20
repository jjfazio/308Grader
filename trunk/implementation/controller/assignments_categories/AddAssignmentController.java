package controller.assignments_categories;

import java.util.ArrayList;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.assignments_categories.Assignment;
import model.assignments_categories.Category;
import model.exception.BadDataException;
import model.gradebook.Gradebook;
import model.spreadsheet.GradingScheme;
import model.spreadsheet.LatePolicy;
import model.spreadsheet.SpreadsheetCourse;
import view.assignments_categories.AssignmentTree;
import view.assignments_categories.CategoryTree;
import model.gradebook.Gradebook;

/**
 * @author Jirbert Dilanchian
 */
public class AddAssignmentController {

    /**An instance of current course*/
    private SpreadsheetCourse currentCourse;

    /**Name of the new assignment */
    @FXML
    private TextField addAssignmentName;

    /**Due Date of the new assignment*/
    @FXML
    private TextField addAssignmentDueDate;

    /**Weight of the assignment*/
    @FXML
    private TextField addAssignmentWeight;

    /**TreeView list of all of the assignment and categories*/
    @FXML
    private TreeView<String> treeView;

    /**Points of assignments*/
    @FXML
    private TextField addAssignmentPoints;

    /**An instance of current course*/
    private SpreadsheetCourse course;

    /**The assignment tree that is being shown in the TreeView*/
    private AssignmentTree assignmentTree;


    /**
     * Called by FXML when view is loaded. Reloads all of the
     * categories.
     */
    @FXML
    private void initialize() {
        course = Gradebook.getInstance().getCurrentCourse();
        assignmentTree = new AssignmentTree(course.getCategoryContainer());
        loadTreeView();
    }

    /**
     * Sets up the TreeView with courses in SIS
     */
    private void loadTreeView()
    {
        TreeItem<String> rootItem = assignmentTree.getRoot();
        rootItem.setExpanded(true);
        treeView.setRoot(rootItem);
        treeView.setShowRoot(true);
    }

    /**
     * Adds an assignment to the collection of assignment of the parent Category. Throw exception if the user has not
     * defined the parent category, name, weight, due date, and points of category.
     * Calls course.getCategoryContainer().addAssignment() in order to add an assignment to the parent category chosen
     * by the user.
     */
    @FXML
    private void handleAddAssignmentSave() {
        boolean success = true;

        Stage stage = (Stage) treeView.getScene().getWindow();
        try {

            String selectedCategory = treeView.getSelectionModel()
                    .getSelectedItem().getValue();
            String parentName = selectedCategory.substring(0,
                    selectedCategory.indexOf("(")).trim();
            try{
            course.getCategoryContainer().addAssignment(assignmentTree.getCategory(parentName),
                    addAssignmentName.getText(),
                    addAssignmentWeight.getText(),
                    addAssignmentPoints.getText(),
                    new Date(), new GradingScheme(), new LatePolicy(), false);
            } catch (BadDataException e) {
                success = false;
                Dialogs.showErrorDialog(stage, e.getMessage(), "Please resolve the following issues.", "Invalid input");
            }
        }catch (Exception err){
            success = false;
            Dialogs.showErrorDialog(stage, "Parent category not selected or An assignment is chosen instead of parent " +
                    "category",
                    "Please resolve the following issues.", "Invalid input");
        }
        if(success){
            stage.close();
        }
    }
    /**
     * Closes the Add Assignment page without saving anything.
     */
    @FXML
    private void handleAddAssignmentCancel() {
        System.out.println("Cancel button Clicked!");
        Stage stage = (Stage) addAssignmentName.getScene().getWindow();
        stage.close();
    }
}
