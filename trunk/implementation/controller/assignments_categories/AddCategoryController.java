package controller.assignments_categories;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.assignments_categories.Category;

import javax.sql.rowset.CachedRowSet;
import java.awt.*;

/**
 * @author Jirbert Dilanchian
 */
public class AddCategoryController {

    @FXML
    private String name;

    @FXML
    private int weight;

    @FXML
    private Category parentCategory;

    /**
     * Adds a new category to the collection of categories of the parent category
     */
    @FXML
    public void handleAddCategorySave() {
        System.out.println("Save button Clicked!");
        Category newCategory = new Category();
        Category topCategory = new Category();
        topCategory.addSubCategory(newCategory);
    }

    /**
     * Closes the Add Category page without doing any changes.
     */
    @FXML
    public void handleAddCategoryCancel() {
        System.out.println("Cancel button Clicked!");
    }
}
