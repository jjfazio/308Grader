package controller.assignments_categories;

//import javax.swing.text.html.ListView;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Dialogs;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import model.assignments_categories.Category;
import model.exception.BadDataException;
import model.gradebook.Gradebook;
import model.spreadsheet.SpreadsheetCourse;
import view.assignments_categories.CategoryTree;


/**
 * @author Jirbert Dilanchian
 */
public class DeleteCategoryController {

    /** The root of the layout */
    @FXML
    private Parent root;

    /**TreeView list of all of the categories*/
    @FXML
    private TreeView<String> treeView;

    /**Instance of current course */
    private SpreadsheetCourse course;

    /**The assignment tree that is being shown in the TreeView*/
    private CategoryTree categoryTree;

    /**
     * Called by FXML when view is loaded. Reloads all of the
     * categories.
     */
    @FXML
    private void initialize() {
        course = Gradebook.getInstance().getCurrentCourse();
        categoryTree = new CategoryTree(course.getCategoryContainer());
        loadTreeView();
    }

    /**
     * Sets up the TreeView with courses in SIS
     */
    private void loadTreeView()
    {
        TreeItem<String> rootItem = categoryTree.getRoot();
        rootItem.setExpanded(true);
        treeView.setRoot(rootItem);
        treeView.setShowRoot(true);
    }

    /**
     * It gets the name of the category selected by the user and its parent category. Then it
     * removes a category from the collection of categories of the parent Category by calling removeCategory in
     * course.getCategoryContainer().removeCategory().
     */
    @FXML
    public void handleDeleteCategoryDelete() {
        try {
            String origSelectedCategory = treeView.getSelectionModel()
                    .getSelectedItem().getValue();
            String selectName = origSelectedCategory.substring(0,
                    origSelectedCategory.indexOf("(")).trim();
            String origParentCategory = treeView.getSelectionModel()
                    .getSelectedItem().getParent().getValue();
            String parentName = origParentCategory.substring(0,
                    origParentCategory.indexOf("(")).trim();

            course.getCategoryContainer().removeCategory(categoryTree.getCategory(parentName), categoryTree.getCategory(selectName));
            getStage().close();
        } catch (Exception e) {
            Dialogs.showErrorDialog(getStage(), "Total category cannot be removed", "Please resolve the following issues.", "Invalid input");
        }
    }

    /**
     * Closes the Delete Category page without doing any changes.
     */
    @FXML
    public void handleDeleteCategoryCancel() {
        System.out.println("Cancel button Clicked!");
        getStage().close();
    }

    public Stage getStage() {
        return (Stage) root.getScene().getWindow();
    }
}


