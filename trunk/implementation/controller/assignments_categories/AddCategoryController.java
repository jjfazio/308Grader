package controller.assignments_categories;

import java.util.HashMap;
import java.util.Map;

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
    
    private Map<String, Category> categoryMap;

    /**
     * Called by FXML when view is loaded. Reloads all of the
     * categories.
     */
    @FXML
    private void initialize() {
        categoryMap = new HashMap<String, Category>();
        course = Gradebook.getInstance().getCurrentCourse();
        loadTreeView();
    }

    /**
     * Sets up the TreeView with courses in SIS
     */
    private void loadTreeView()
    {
        Category parent = course.getCategoryContainer().getRoot();
        TreeItem<String> rootItem = new TreeItem<String>(parent.getName()
                + " ( " + parent.getPercentofparent() + " )");
        
        addToTree(parent, rootItem);

        
        rootItem.setExpanded(true);
        treeView.setRoot(rootItem);
        treeView.setShowRoot(true);
        
    }
    
    private void addToTree(Category parent, TreeItem<String> rootItem)
    {
        TreeItem<String> curItem;
        
        categoryMap.put(parent.getName(), parent);
        
        if (parent.getSubCategories() != null && 
                !parent.getSubCategories().isEmpty()) {
            for (Category subCategory : parent.getSubCategories()) {
                curItem = new TreeItem<String>(subCategory.getName()
                        + " ( " + subCategory.getPercentofparent() + " %)");
                rootItem.getChildren().add(curItem);
                addToTree(subCategory, curItem);
            }
        }
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
        Double weight = Double.parseDouble(addCategoryWeight.getText());
        String categoryName = addCategoryName.getText();

        try {
        course.getCategoryContainer().addCategory(categoryMap.get(parentName),
                weight, categoryName);
        } catch (BadDataException e) {
            System.out.println("in the catch");
            Dialogs.showErrorDialog(stage, e.getMessage(), "Found Error", "4354");
            System.out.println("in the catch3");
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
