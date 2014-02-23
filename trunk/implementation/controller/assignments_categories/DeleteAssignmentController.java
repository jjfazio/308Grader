package controller.assignments_categories;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
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
        fillList(Gradebook.getInstance().getCurrentCourse().getCategoryContainer().getRoot(), assignmentNames);
        deleteAssignmentList.getItems().setAll(assignmentNames);
    }

    /**
     * Removes an assignment from the collection of assignments of the parrent category.
     */
    @FXML
    private void handleDeleteAssignmentDelete() {
        System.out.println("Delete button Clicked!");
        String name = deleteAssignmentList.getSelectionModel().getSelectedItem();
        removeAssignment(name, Gradebook.getInstance().getCurrentCourse().getCategoryContainer().getRoot());

        Stage stage = (Stage) deleteAssignmentList.getScene().getWindow();
        stage.close();

    }

    /**
     * Closes the Delete Assignment page without doing any changes.
     */
    @FXML
    private void handleDeleteAssignmentCancel() {
        System.out.println("Cancel button Clicked!");
        Stage stage = (Stage) deleteAssignmentList.getScene().getWindow();
        stage.close();
    }


    Category tempCategory;
    private void setTempCategory(Category cat) {
        tempCategory = cat;
    }
    /**
     * Finds the parent category of current category
     * @param name Name of the child category
     * @param theCat The category that we check if it's our target category
     */
    @FXML
    public void removeAssignment(String name, Category theCat) {
        if(theCat.getAssignments() != null) {
            for(Assignment x : theCat.getAssignments()) {
                if(x.getName().equals(name)) {
//                    theCat.removeAssignment(x);
                    Gradebook.getInstance().getCurrentCourse().getCategoryContainer().deleteAssignment(theCat, x);
                    break;
                }
            }
        }
        if(theCat.getSubCategories() != null) {
            for(Category y : theCat.getSubCategories()) {
                removeAssignment(name, y);
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
        if(theCat.getAssignments() != null){
            for (Assignment x : theCat.getAssignments()) {
                assignmentNames.add(x.getName());
            }
        }
        if(theCat.getSubCategories() != null) {
            for (Category y : theCat.getSubCategories()) {
                fillList(y, assignmentNames);
            }
        }

    }
}
