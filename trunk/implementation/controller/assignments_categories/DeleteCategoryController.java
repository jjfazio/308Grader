package controller.assignments_categories;

import javafx.fxml.FXML;
import model.assignments_categories.Category;

/**
 * @author Jirbert Dilanchian
 */
public class DeleteCategoryController {

    @FXML
    private Category delCategory;

    /**
     * Removes a category from the collection of categories of the parent Category.
     */
    @FXML
    public void handleDeleteCategoryDelete() {
        System.out.println("Delete button Clicked!");
        Category parCategory = new Category();
        Category childCategory = new Category();
        parCategory.addSubCategory(childCategory);
        parCategory.removeCategory(childCategory);
    }

    /**
     * Closes the Delete Category page without doing any changes.
     */
    @FXML
    public void handleDeleteCategoryCancel() {
        System.out.println("Cancel button Clicked!");
        /*
         * cboVet.getSelectionModel().clearSelection();
cboVet.getItems.clear();
do something like this...

parentNode.getChildren().remove(cboVet);
cboVet = new ComboBox();  // do whatever else you need to format your ComboBox
parentNode.add(cboVet);
         */

    }
}


