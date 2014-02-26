package controller.assignments_categories;

//import javax.swing.text.html.ListView;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Dialogs;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import model.assignments_categories.Category;
import model.exception.BadDataException;
import model.gradebook.Gradebook;
import model.spreadsheet.SpreadsheetCourse;
import view.assignments_categories.CategoryTree;


/**
 * @author Jirbert Dilanchian
 */
public class DeleteCategoryController {

    @FXML
    private Parent root;
    @FXML
    private Category delCategory;
    @FXML
    private TreeView<String> treeView;

    private SpreadsheetCourse course;

    private CategoryTree categoryTree;
//    @FXML
//    private ListView<String> deleteCategoryList;


    /**
     * Called by FXML when view is loaded. Reloads all of the
     * categories.
     */
    @FXML
    private void initialize() {
        course = Gradebook.getInstance().getCurrentCourse();
        categoryTree = new CategoryTree(course.getCategoryContainer());
        loadTreeView();
//        ArrayList<String> categoryNames = new ArrayList<String>();
//        fillList(Gradebook.getInstance().getCurrentCourse().
//                getCategoryContainer().getRoot(), categoryNames);
//        deleteCategoryList.getItems().setAll(categoryNames);
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

    /**
     * Fills the viewList of delete Category page
     * @param theCat name of the list which we get the name.
     * @param categoryNames List of the names of categories
     */
    private void fillList(Category theCat, ArrayList<String> categoryNames) {
        //addCategoryParentName.getItems().add(theCat.getName());
        if (!theCat.getName().trim().equals("Total"))
        {
            categoryNames.add(theCat.getName());
        }
        if((ArrayList<Category>)theCat.getSubCategories() != null){
            for(Category x : (ArrayList<Category>)theCat.getSubCategories()) {
                fillList(x, categoryNames);
            }
        }
    }

    /**
     * Finds the parent category of current category
     * @param name Name of the child category
     * @param cat The category that we check if it's our target category
     */
    public void findParentCategory(String name, Category cat)
    {
       // Category temp = null;
        if(cat.getSubCategories() != null ){
            for(Category x : cat.getSubCategories()) {
                if(x.getName().trim().equals(name)){
                    setTempCategory(cat);
                }
                else {
                    findParentCategory(name, x);
                }
            }
        }
    }

    // temporary Category variable used to store child and parent categories
    private Category tempCategory;

    /**
     * Setter method to set tempCategory;
     * @param x The category to be assigned to temp category
     */
    @FXML
    public void setTempCategory(Category x){
        tempCategory = x;
    }


    /**
     * Finds the category selected from the list.
     * @param name Name of the category to be found
     * @param cat  The category that we check if it's our target category
     */
    public void findChildCategory(String name, Category cat) {
        if(cat.getName().equals(name)){
            tempCategory = cat;
        }

        if(cat.getSubCategories() != null ){
            for(Category x : cat.getSubCategories()) {
                 findChildCategory(name, x);
            }
        }
    }


    /**
     * Removes a category from the collection of categories of the parent Category.
     */
    @FXML
    public void handleDeleteCategoryDelete() {
        try {
            String origSelectedCategory = treeView.getSelectionModel()
                    .getSelectedItem().getValue();
            String selectName = origSelectedCategory.substring(0,
                    origSelectedCategory.indexOf("(")).trim();
            String origParentCategory = treeView.getSelectionModel()
                    .getSelectedItem().getParent().getValue();
            String parentName = origParentCategory.substring(0,
                    origParentCategory.indexOf("(")).trim();

            course.getCategoryContainer().removeCategory(categoryTree.getCategory(parentName), categoryTree.getCategory(selectName));
            getStage().close();
        } catch (Exception e) {
            Dialogs.showErrorDialog(getStage(), "Total category cannot be removed", "Please resolve the following issues.", "Invalid input");
        }
//        System.out.println("Delete button Clicked!");
//        String name = "";
//        Category parentCategory;
//        Category childCategory = null;
//        name = deleteCategoryList.getSelectionModel().getSelectedItem();
//        findParentCategory(name, Gradebook.getInstance().getCurrentCourse().
//                getCategoryContainer().getRoot());
//        parentCategory = tempCategory;
//        findChildCategory(name, Gradebook.getInstance().getCurrentCourse().
//                getCategoryContainer().getRoot());
//        childCategory = tempCategory;
//        //parentCategory.removeCategory(childCategory);
//        Gradebook.getInstance().getCurrentCourse().getCategoryContainer().removeCategory(parentCategory, childCategory);
//
//
       // Stage stage = (Stage) deleteCategoryList.getScene().getWindow();

    }

    /**
     * Closes the Delete Category page without doing any changes.
     */
    @FXML
    public void handleDeleteCategoryCancel() {
        System.out.println("Cancel button Clicked!");
        getStage().close();
    }

    public Stage getStage() {
        return (Stage) root.getScene().getWindow();
    }
}


