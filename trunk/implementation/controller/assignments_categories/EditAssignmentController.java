package controller.assignments_categories;

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

import java.util.GregorianCalendar;

/**
 * @author Jirbert Dilanchian
 */
public class EditAssignmentController {

    @FXML
    private Assignment editAssign;

    /**Name of the Assignment*/
    @FXML
    public TextField editAssignmentName;

    /**Points of the Assignment*/
    @FXML
    public TextField editAssignmentPoints;

    /**Points of the Assignment*/
    @FXML
    public TextField editAssignmentDueDate;

    /**Weight of Category*/
    @FXML
    private TextField  editAssignmentPercent;

    /**TreeView list of all of the assignment and categories*/
    @FXML
    private TreeView<String> treeView;

    @FXML
    private Button editAssignmentSaveBtn;

    @FXML
    private javafx.scene.control.Label topLbl;

    private SpreadsheetCourse course;

    private CategoryTree categoryTree;

    private AssignmentTree assignmentTree;

    private boolean isChosen;

    private Category curParentCategory;

    private String origSelectedAssignment;

    private String origSelectName;

    private String origParentName;

    private String origParentCategory;

    /**
     * Called by FXML when view is loaded. Reloads all of the
     * categories.
     */
    @FXML
    private void initialize() {
        course = Gradebook.getInstance().getCurrentCourse();
        assignmentTree = new AssignmentTree(course.getCategoryContainer());
        categoryTree = new CategoryTree(course.getCategoryContainer());
        editAssignmentName.setDisable(true);
        editAssignmentPercent.setDisable(true);
        editAssignmentDueDate.setDisable(true);
        editAssignmentPoints.setDisable(true);
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
     * Opens the New Assignment page and fills up the fields with current assignment's properties.
     * The user can change the properties of the assignment.
     */
    @FXML
    private void handleEditAssignmentEdit() {
        System.out.println("Edit button Clicked!");
        Stage stage = (Stage) treeView.getScene().getWindow();
        System.out.println("Edit button Clicked!");
        if(editAssignmentSaveBtn.getText().equals("Edit")) {

            String origSelectedAssignmentTemp = treeView.getSelectionModel().getSelectedItem().getValue();
            if(origSelectedAssignmentTemp.indexOf("<") != -1) {
                origSelectedAssignment = origSelectedAssignmentTemp.substring(0, origSelectedAssignmentTemp.indexOf("<")).trim();
                String origParentCategoryTemp = treeView.getSelectionModel()
                        .getSelectedItem().getParent().getValue();

                origParentCategory = origParentCategoryTemp.substring(0, origParentCategoryTemp.indexOf("(")).trim();
                treeView.getSelectionModel().select(null);
                editAssignmentSaveBtn.setText("Save");
                topLbl.setText("Select the new parent category");
                editAssignmentName.setDisable(false);
                editAssignmentPercent.setDisable(false);
                editAssignmentPoints.setDisable(false);
                editAssignmentDueDate.setDisable(false);

                System.out.println("Assignment name " + origSelectedAssignment + "  parent Name " + origParentCategory);
            }
            else {
                Dialogs.showErrorDialog(stage, "You need to choose an Assignment to Edit",
                        "Please resolve the following issues.", "Invalid input");
            }
        }
        else {
            String newParentName;
            if(treeView.getSelectionModel().getSelectedItem() != null) {
                String newParentNameTemp = treeView.getSelectionModel().getSelectedItem().getValue();
                newParentName = newParentNameTemp.substring(0, newParentNameTemp.indexOf("(")).trim();
                try {
                    course.getCategoryContainer().editAssignment(
                            //  cur parent Category
                            categoryTree.getCategory(origParentCategory),
                            // new parent Category
                            categoryTree.getCategory(newParentName),
                            /*tempParentname.substring(0, tempParentname.indexOf("(")).trim()*/
                            // The Category to be edited
                            assignmentTree.getAssignment(origSelectedAssignment),
                            //      categoryTree.getCategory(origSelectedAssignment /*treeView.getSelectionModel().getSelectedItem().getValue()*/),
                            // new Name
                            editAssignmentName.getText(),
                            // new weight
                            editAssignmentPercent.getText(),
                            editAssignmentDueDate.getText(),
                            editAssignmentPoints.getText()
                    );
                } catch (BadDataException e) {
                    Dialogs.showErrorDialog(stage, e.getMessage(), "Please resolve the following issues.", "Invalid input");
                }
                stage.close();
            }
            else {
                Dialogs.showErrorDialog(stage, "You need to select new parent category ",
                        "Please resolve the following issues.", "Invalid input");
            }

        }


    }

    /**
     * Closes the Edit Assignment page without making any changes.
     */
    @FXML
    private void handleEditAssignmentCancel() {
        System.out.println("Cancel button Clicked!");
        Stage stage = (Stage) treeView.getScene().getWindow();
        stage.close();
    }
}
