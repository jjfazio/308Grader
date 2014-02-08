package controller.assignments_categories;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.assignments_categories.Category;
import model.gradebook.Gradebook;
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
     * Called by FXML when view is loaded. Reloads all of the
     * categories.
     */
    @FXML
    private void initialize() {
        addCategoryParentName.getItems().clear();
        fillCombo(Gradebook.getInstance().getCurrentCourse().getTopCategory());
    }

    @FXML
    private void fillCombo(Category theCat) {
        addCategoryParentName.getItems().add(theCat.getName());
        if((ArrayList<Category>)theCat.getSubCategories() != null){
            for(Category x : (ArrayList<Category>)theCat.getSubCategories()) {
                fillCombo(x);
            }
        }
    }

    @FXML
    private Category findCategory(String name, Category cat) {
        Category catLookingFor = null;
        if(cat.getName().equals(name)) {
            catLookingFor =  cat;
        }
        if((ArrayList<Category>)cat.getSubCategories() != null && catLookingFor == null){
            for(Category x : (ArrayList<Category>)cat.getSubCategories()) {
                if(catLookingFor == null) {
                    findCategory(name, x);
                }
            }
        }
        return catLookingFor;
    }


    /**
     * Adds a new category to the collection of categories of the parent category
     */
    @FXML
    public void handleAddCategorySave() {
        System.out.println("Save button Clicked!");
        Category parCategory = findCategory(addCategoryParentName.getValue().toString().trim(),
                Gradebook.getInstance().getCurrentCourse().getTopCategory());
        Category newCategory = new Category(parCategory,
                Double.parseDouble(addCategoryWeight.getText()), addCategoryName.getText());
//in mygrading spreadsheet
        // table.getcolums().get(0).getcolumns.add(e)
        //table column to tableview

    }

    /**
     * Closes the Add Category page without doing any changes.
     */
    @FXML
    public void handleAddCategoryCancel() {
        System.out.println("Cancel button Clicked!");
    }
}
