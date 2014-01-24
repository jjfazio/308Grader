package controller.assignments_categories;

import javafx.fxml.FXML;
import model.assignments_categories.Category;

import javax.sql.rowset.CachedRowSet;

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

    @FXML
    public void handleAddCategorySave() {
        System.out.println("Save button Clicked!");
        Category newCategory = new Category();
        Category topCategory = new Category();
        topCategory.addSubCategory(newCategory);

    }

    @FXML
    public void handleAddCategoryCancel() {
        System.out.println("Cancel button Clicked!");
    }
}
