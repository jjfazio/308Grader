package controller.assignments_categories;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.assignments_categories.Assignment;
import model.assignments_categories.Category;
import model.gradebook.Gradebook;

import java.util.ArrayList;

/**
 * @author Jirbert Dilanchian
 */
public class DeleteAssignmentController {

    @FXML
    private Assignment delAssignment;

    @FXML
    private ListView<String> deleteAssignmentList;

    public DeleteAssignmentController() {

    }

    /**
     * Called by FXML when view is loaded. Reloads all of the
     * categories.
     */
    @FXML
    private void initialize() {
        ArrayList<String> assignmentNames = new ArrayList<String>();
        fillList(Gradebook.getInstance().getCurrentCourse().getTopCategory(), assignmentNames);
        deleteAssignmentList.getItems().setAll(assignmentNames);
    }

    /**
     * Removes an assignment from the collection of assignments of the parrent category.
     */
    @FXML
    private void handleDeleteAssignmentDelete() {
        System.out.println("Delete button Clicked!");
//        tempCategory.removeAssignment();
    }

    /**
     * Closes the Delete Assignment page without doing any changes.
     */
    @FXML
    private void handleDeleteAssignmentCancel() {
        System.out.println("Cancel button Clicked!");
    }


    Category tempCategory;
    private void setTempCategory(Category cat) {
        tempCategory = cat;
    }
    /**
     * Finds the parent category of current category
     * @param name Name of the child category
     * @param cat The category that we check if it's our target category
     */
    @FXML
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



    /**
     * Fills the viewList of delete Category pagedeleteAssignmentList
     * @param theCat name of the list which we get the name.
     * @param assignmentNames List of the names of categories
     */
    @FXML
    private void fillList(Category theCat, ArrayList<String> assignmentNames) {
        if(theCat.getSubCategories() != null) {
            System.out.println("found CAtegory");
            for(Category cat : theCat.getSubCategories()) {
                if (cat.getAssignments() != null) {
                    System.out.println("first line found assignment");
                    for(Assignment as : cat.getAssignments()) {
                        assignmentNames.add(as.getName());


                    }
                }
                fillList(cat, assignmentNames);
            }
        }
    }
}
