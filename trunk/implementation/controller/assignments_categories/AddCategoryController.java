package controller.assignments_categories;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.assignments_categories.Category;

import javax.sql.rowset.CachedRowSet;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Jirbert Dilanchian
 */
public class AddCategoryController {

    @FXML
    private TextField addCategoryName;

    @FXML
    private TextField addCategoryWeight;

    @FXML
    private ComboBox addCategoryParentName;

    /**
     * Adds a new category to the collection of categories of the parent category
     */
    @FXML

    private int flag = 0;

    public void handleAddCategorySave() {
        System.out.println("Save button Clicked!");
//        addCategoryParentName.setValue("Hello");
//        addCategoryParentName.setValue("Stupid");
//        addCategoryParentName.setValue("World");
        if (this.flag == 0) {
            addCategoryParentName.getItems().clear();

        }
        ArrayList<String> = new ArrayList<String>();
        addCategoryParentName.

    //    System.out.println(addCategoryName.getText() + "  " + addCategoryWeight.getText() + addCategoryParentName.getValue());
    //    Category cat = new Category();
    //    Category newCategory = new Category(cat, Double.parseDouble(addCategoryWeight.getText()), addCategoryName.getText());
//        topCategory.addSubCategory(newCategory);
    }

    /**
     * Closes the Add Category page without doing any changes.
     */
    @FXML
    public void handleAddCategoryCancel() {
        System.out.println("Cancel button Clicked!");
    }
}
