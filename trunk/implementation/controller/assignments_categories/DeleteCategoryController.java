package controller.assignments_categories;

import javafx.fxml.FXML;
import model.assignments_categories.Category;
import model.gradebook.Gradebook;
import javafx.scene.control.*;

//import javax.swing.text.html.ListView;
import java.util.ArrayList;

/**
 * @author Jirbert Dilanchian
 */
public class DeleteCategoryController {

    @FXML
    private Category delCategory;
    @FXML
    private ListView<String> deleteCategoryList;

    @FXML
    private void initialize() {
        ArrayList<String> categoryNames = new ArrayList<String>();
        fillList(Gradebook.getInstance().getCurrentCourse().getTopCategory(), categoryNames);
        deleteCategoryList.getItems().setAll(categoryNames);
    }

    @FXML
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

    @FXML
    private Category findParentCategory(String name, Category cat) {
        Category parentCatLookingFor = null;
//        if(cat.getName().equals(name)) {
//            catLookingFor =  cat;
//        }
        if((ArrayList<Category>)cat.getSubCategories() != null && parentCatLookingFor == null){
            for(Category x : (ArrayList<Category>)cat.getSubCategories()) {
                if(x.getName().equals(name))
                {
                    parentCatLookingFor = x;
                }
                else if(parentCatLookingFor == null) {
                    findParentCategory(name, x);
                }
            }
        }
        return parentCatLookingFor;
    }


    @FXML
    private void findCategory(String name, Category cat, Category catLookingFor) {
     //   Category catLookingFor = null;
        if(cat.getName().equals(name)) {
            catLookingFor =  cat;
        }
        if((ArrayList<Category>)cat.getSubCategories() != null && catLookingFor == null){
            for(Category x : (ArrayList<Category>)cat.getSubCategories()) {
                if(catLookingFor == null) {
                    findCategory(name, x, catLookingFor);
                }
            }
        }
     //   return catLookingFor;
    }


    /**
     * Removes a category from the collection of categories of the parent Category.
     */
    @FXML
    public void handleDeleteCategoryDelete() {
        System.out.println("Delete button Clicked!");
        String name = "";
        Category parentCategory;
        Category deleteCategory = null;
        name = deleteCategoryList.getSelectionModel().selectedItemProperty().getName().trim();
        findCategory(name, Gradebook.getInstance().getCurrentCourse().getTopCategory(), deleteCategory);
        parentCategory = findParentCategory(name, Gradebook.getInstance().getCurrentCourse().getTopCategory());
//        parentCategory.removeCategory(deleteCategory);
        //parentCategory.getSubCategories().remove(deleteCategory);
        if(deleteCategory != null )
        {
            System.out.println("delete category is not null");
        }
        if( parentCategory != null)
        {
            System.out.println("parent category is null");
        }
           // System.out.println(deleteCategory.getName() + "  parent " + parentCategory.getName());

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


