package controller.assignments_categories;

import javafx.fxml.FXML;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import view.assignments_categories.CategoryTree;
import model.assignments_categories.Category;
import model.exception.BadDataException;
import model.gradebook.Gradebook;
import model.spreadsheet.SpreadsheetCourse;
import view.assignments_categories.CategoryTree;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.HashMap;
import java.util.Map;

import view.assignments_categories.CategoryTree;
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

import javax.xml.soap.Text;
import java.awt.*;

/**
 * @author Jirbert Dilanchian
 *
 * Opens the New Category page and fills up the fields with current Category's properties.
 * The user can change the properties of the category.
 */

public class EditCategoryController {

    @FXML
    private Category editCateg;

    @FXML
    public TextField  editCategoryName;

    @FXML
    private TextField  editCategoryPercent;

    @FXML
    private TreeView<String> treeView;

    @FXML
    private Button editCategorySaveBtn;

    @FXML
    private javafx.scene.control.Label topLbl;

    private SpreadsheetCourse course;

    private CategoryTree categoryTree;

    private boolean isChosen;

    private Category curParentCategory;

    private String origSelectedCategory;

    private String origSelectName;

    private String origParentName;

    private String origParentCategory;



    /**
     * Called by FXML when view is loaded. Reloads all of the
     * categories.
     */
    @FXML
    private void initialize() {
        course = Gradebook.getInstance().getCurrentCourse();
        categoryTree = new CategoryTree(course.getCategoryContainer());
        loadTreeView();
        isChosen = false;
        origParentName = "";
        origSelectedCategory = "";
        editCategoryName.setDisable(true);
        editCategoryPercent.setDisable(true);
    }

    /**
     * Sets up the TreeView with courses in SIS
     */
    private void loadTreeView()
    {
        TreeItem<String> rootItem = categoryTree.getRoot();
        rootItem.setExpanded(true);
        treeView.setRoot(rootItem);
        treeView.setShowRoot(true);
    }

    @FXML
    public void handleEditCategoryEdit() {
        Stage stage = (Stage) treeView.getScene().getWindow();
        System.out.println("Edit button Clicked!");
        if(origSelectedCategory.equals("")) {
            origSelectedCategory = treeView.getSelectionModel()
                    .getSelectedItem().getValue();
            origSelectName = origSelectedCategory.substring(0,
                    origSelectedCategory.indexOf("(")).trim();
            origParentCategory = treeView.getSelectionModel()
                    .getSelectedItem().getParent().getValue();
            origParentName = origParentCategory.substring(0,
                    origParentCategory.indexOf("(")).trim();

            treeView.getSelectionModel().select(null);
            editCategorySaveBtn.setText("Save");
            topLbl.setText("Select the new parent category");
            editCategoryName.setDisable(false);
            editCategoryPercent.setDisable(false);

            System.out.println("Cat name " + origSelectName + "  parent Name " + origParentName);
        }
        else {
            try {
                String tempParentname = treeView.getSelectionModel().getSelectedItem().getValue();
                course.getCategoryContainer().editCategory(
                        //  cur parent Category
                        categoryTree.getCategory(origParentName/*treeView.getSelectionModel().getSelectedItem().getParent().getValue()*/),
                        // new parent Category
                        categoryTree.getCategory(tempParentname.substring(0, tempParentname.indexOf("(")).trim()),
                        // The Category to be edited
                        categoryTree.getCategory(origSelectName),
                        // new Name
                        origSelectName,
                       // editCategoryName.getText(),
                        // new weight
                        editCategoryPercent.getText()
                        );
            } catch (BadDataException e) {
                Dialogs.showErrorDialog(stage, e.getMessage(), "Please resolve the following issues.", "Invalid input");
            }
            stage.close();
        }
//
//
//        try {
//            course.getCategoryContainer().editCategory(categoryTree.getCategory(parentName),
//                    weight, categoryName);
//        } catch (BadDataException e) {
//            Dialogs.showErrorDialog(stage, e.getMessage(), "Please resolve the following issues.", "Invalid input");
//        }

     //   stage.close();
    }

    @FXML
    public void onMouseClicked() {
        if (treeView.getSelectionModel().getSelectedItem() != null){
            isChosen = true;
            String selectedCategory = treeView.getSelectionModel()
                    .getSelectedItem().getValue();
            String curName = selectedCategory.substring(0,
                    selectedCategory.indexOf("(")).trim();
            String tempPercent = selectedCategory.substring(selectedCategory.indexOf("(") + 1,
                    selectedCategory.indexOf("%")).trim();
            String curPercent = tempPercent.substring(0, tempPercent.indexOf('.'));
//            String curPercent = selectedCategory.substring(selectedCategory.indexOf("(") + 1,
//                    selectedCategory.indexOf("%")).trim();
            //System.out.println(curName);
            editCategoryName.setText(curName);
            editCategoryPercent.setText(curPercent);
        }
    }

    @FXML
    public void handleEditCategoryCancel() {
        System.out.println("Cancel button Clicked!");

    }
}
