package controller.assignments_categories;

import java.util.HashMap;
import java.util.Map;

import view.assignments_categories.CategoryTree;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import model.assignments_categories.Category;
import model.exception.BadDataException;
import model.gradebook.Gradebook;
import javafx.scene.control.Dialogs;
import model.spreadsheet.SpreadsheetCourse;

/**
 * @author Jirbert Dilanchian
 */
public class AddCategoryController {

    @FXML
    private TextField addCategoryName;

    @FXML
    private TextField addCategoryWeight;

    @FXML
    private TreeView<String> treeView;
    
    private SpreadsheetCourse course;
    
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
     * Adds a new category to the collection of categories of the parent category
     */
    @FXML
    public void handleAddCategorySave() {
        Stage stage = (Stage) addCategoryName.getScene().getWindow();
        String selectedCategory = treeView.getSelectionModel()
                .getSelectedItem().getValue();
        String parentName = selectedCategory.substring(0,
                selectedCategory.indexOf("(")).trim();
       // Double weight = Double.parseDouble(addCategoryWeight.getText());
        String weight = addCategoryWeight.getText();
        String categoryName = addCategoryName.getText();

        try {
            course.getCategoryContainer().addCategory(categoryTree.getCategory(parentName),
                    weight, categoryName);
        } catch (BadDataException e) {
            Dialogs.showErrorDialog(stage, e.getMessage(), "Found Error", "4354");
        }

        stage.close();
    }

    /**
     * Closes the Add Category page without doing any changes.
     */
    @FXML
    public void handleAddCategoryCancel() {
        System.out.println("Cancel button Clicked!");
        Stage stage = (Stage) addCategoryName.getScene().getWindow();
        stage.close();
    }
}
