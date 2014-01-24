package controller.assignments_categories;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import model.assignments_categories.Assignment;
import model.assignments_categories.Category;
import view.ViewUtility;

import java.util.Date;

/**
 * @author Jirbert Dilanchian
 */
public class AddAssignmentController {
    @FXML
    private String name;
    @FXML
    private String categoryName;
    @FXML
    private Date dueDate;
    @FXML
    private int weight;
    @FXML
    private int points;

    public AddAssignmentController() {
    }

    @FXML
    private void handleAddAssignmentSave() {
        System.out.println("Save button Clicked!");
        Assignment newAssignment = new Assignment();
        Category parCategory = new Category();
        parCategory.addAssignment(newAssignment);

    }

    @FXML
    private void handleAddAssignmentCancel() {
        System.out.println("Cancel button Clicked!");

    }
}
