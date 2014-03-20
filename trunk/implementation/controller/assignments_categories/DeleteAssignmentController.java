package controller.assignments_categories;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import model.assignments_categories.Assignment;
import model.assignments_categories.Category;
import model.gradebook.Gradebook;
import model.spreadsheet.SpreadsheetCourse;
import model.users.Student;
import view.assignments_categories.AssignmentTree;

import java.util.ArrayList;

/**
 * @author Jirbert Dilanchian
 */
public class DeleteAssignmentController {

    /**TreeView list of all of the assignment and categories*/
    @FXML
    private TreeView<String> treeView;

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
     * Gets the name of the assignment and sends it
     */
    @FXML
    private void handleDeleteAssignmentDelete() {
        System.out.println("Delete button Clicked!");
        String selectedCategory = treeView.getSelectionModel()
                .getSelectedItem().getValue();
        String name = selectedCategory.substring(0,
                selectedCategory.indexOf("<")).trim();

        removeAssignment(name, Gradebook.getInstance().getCurrentCourse().getCategoryContainer().getRoot());
        Stage stage = (Stage) treeView.getScene().getWindow();
        stage.close();
    }

    /**
     * Closes the Delete Assignment page without doing any changes.
     */
    @FXML
    private void handleDeleteAssignmentCancel() {
        System.out.println("Cancel button Clicked!");
        Stage stage = (Stage) treeView.getScene().getWindow();
        stage.close();
    }


    Category tempCategory;
    private void setTempCategory(Category cat) {
        tempCategory = cat;
    }
    /**
     * Finds the parent category of current category
     * @param name Name of the child category
     * @param theCat The category that we check if it's our target category
     */
    @FXML
    public void removeAssignment(String name, Category theCat) {
        if(theCat.getAssignments() != null) {
            for(Assignment x : theCat.getAssignments()) {
                if(x.getName().equals(name)) {
                    Gradebook.getInstance().getCurrentCourse().getCategoryContainer().deleteAssignment(theCat, x, course);
                    break;
                }
            }
        }
        if(theCat.getSubCategories() != null) {
            for(Category y : theCat.getSubCategories()) {
                removeAssignment(name, y);
            }
        }
    }
}
