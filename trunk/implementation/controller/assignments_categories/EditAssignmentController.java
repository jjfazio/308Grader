package controller.assignments_categories;

import javafx.fxml.FXML;
import model.assignments_categories.Assignment;

/**
 * @author Jirbert Dilanchian
 */
public class EditAssignmentController {

    @FXML
    private Assignment editAssign;

    @FXML
    private void handleEditAssignmentEdit() {
        System.out.println("Edit button Clicked!");
    }

    @FXML
    private void handleEditAssignmentCancel() {
        System.out.println("Cancel button Clicked!");

    }
}
