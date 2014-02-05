package controller.assignments_categories;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.assignments_categories.Category;
import model.spreadsheet.SpreadsheetCourse;

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
        if (this.flag == 0) {
            addCategoryParentName.getItems().clear();
        }

        if(addCategoryParentName.getValue().equals("TopCategory")){
            Category newCategory = new Category(SpreadsheetCourse.getTopCategory(),
                Double.parseDouble(addCategoryWeight.getText()), addCategoryName.getText());
            SpreadsheetCourse.getTopCategory().addSubCategory(newCategory);
        }
        else
        {
//            findCategory(addCategoryParentName.getValue());
        }
    }




    /**
     * Closes the Add Category page without doing any changes.
     */
    @FXML
    public void handleAddCategoryCancel() {
        System.out.println("Cancel button Clicked!");
    }
}
