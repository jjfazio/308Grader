package controller.assignments_categories;

import javafx.fxml.FXML;
import model.assignments_categories.Category;

/**
 * @author Jirbert Dilanchian
 */
public class EditCategoryController {

    @FXML
    private Category editCateg;

    @FXML
    public void handleEditCategoryEdit() {
        System.out.println("Edit button Clicked!");

    }

    @FXML
    public void handleEditCategoryCancel() {
        System.out.println("Cancel button Clicked!");

    }
}
