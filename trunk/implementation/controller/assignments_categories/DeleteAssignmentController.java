package controller.assignments_categories;

import javafx.fxml.FXML;
import model.assignments_categories.Assignment;
import model.assignments_categories.Category;

/**
 * @author Jirbert Dilanchian
 */
public class DeleteAssignmentController {

    @FXML
    private Assignment delAssignment;

    public DeleteAssignmentController() {

    }

    @FXML
    private void handleDeleteAssignmentDelete() {
        System.out.println("Delete button Clicked!");
        Category parCategory = new Category();
        Assignment dAssignment = new Assignment();
        parCategory.addAssignment(dAssignment);
        parCategory.removeAssignment(dAssignment);
    }

    @FXML
    private void handleDeleteAssignmentCancel() {
        System.out.println("Cancel button Clicked!");

    }
}
