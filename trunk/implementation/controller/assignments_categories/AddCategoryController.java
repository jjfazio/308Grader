package controller.assignments_categories;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.assignments_categories.Category;
import model.gradebook.Gradebook;
import model.spreadsheet.SpreadsheetCourse;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogResponse;

import javax.sql.rowset.CachedRowSet;
import java.awt.*;
import java.util.ArrayList;




import java.util.ArrayList;
import java.util.List;

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

    /**
     * Filles the comboBox on add Category dialogue.
     * @param theCat The category which name is going to be added to the list
     */
    @FXML
    private void fillCombo(Category theCat) {
        addCategoryParentName.getItems().add(theCat.getName());
        if((ArrayList<Category>)theCat.getSubCategories() != null){
            for(Category x : (ArrayList<Category>)theCat.getSubCategories()) {
                fillCombo(x);
            }
        }
    }


    /**
     * Locates the parent category in the arraylist of subCategories and adds the new category to it.
     * @param name The name of the parent category.
     * @param cat The new category to be added to parent category
     */
    private void findCategory(String name, Category cat) {
        Category catLookingFor = null;
        if(cat.getName().equals(name)) {
            catLookingFor =  cat;
            Category newCategory = new Category(catLookingFor,
                    Double.parseDouble(addCategoryWeight.getText()), addCategoryName.getText());
        }
        if((ArrayList<Category>)cat.getSubCategories() != null && catLookingFor == null){
            for(Category x : (ArrayList<Category>)cat.getSubCategories()) {
                if(catLookingFor == null) {
                    findCategory(name, x);
                }
            }
        }
    }


    /**
     * Adds a new category to the collection of categories of the parent category
     */
    @FXML
    public void handleAddCategorySave() {
        System.out.println("Save button Clicked!");
        findCategory(addCategoryParentName.getValue().toString().trim(),
                Gradebook.getInstance().getCurrentCourse().getTopCategory());
        Stage stage = (Stage) addCategoryName.getScene().getWindow();
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
