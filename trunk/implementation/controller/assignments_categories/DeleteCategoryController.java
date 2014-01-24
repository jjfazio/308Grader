package controller.assignments_categories;

import javafx.fxml.FXML;
import model.assignments_categories.Category;

/**
 * @author Jirbert Dilanchian
 */
public class DeleteCategoryController {

    @FXML
    private Category delCategory;

    @FXML
    public void handleDeleteCategoryDelete() {
        System.out.println("Delete button Clicked!");
        Category parCategory = new Category();
        Category childCategory = new Category();
        parCategory.addSubCategory(childCategory);
        parCategory.removeCategory(childCategory);

    }

    @FXML
    public void handleDeleteCategoryCancel() {
        System.out.println("Cancel button Clicked!");

    }
}


