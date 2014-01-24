package controller.assignments_categories;

import javafx.fxml.FXML;
import model.assignments_categories.Assignment;

/**
 * @author Jirbert Dilanchian
 */
public class EditAssignmentController {

    @FXML
    private Assignment editAssign;

    /**
     * Opens the New Assignment page and fills up the fields with current assignment's properties.
     * The user can change the properties of the assignment.
     */
    @FXML
    private void handleEditAssignmentEdit() {
        System.out.println("Edit button Clicked!");
    }

    /**
     * Closes the Edit Assignment page without making any changes.
     */
    @FXML
    private void handleEditAssignmentCancel() {
        System.out.println("Cancel button Clicked!");

    }
}
