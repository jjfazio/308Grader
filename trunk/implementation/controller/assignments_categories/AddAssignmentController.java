package controller.assignments_categories;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import view.ViewUtility;

import java.util.Date;

/**
 * Created by jib on 1/23/14.
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

    }

    @FXML
    private void handleAddAssignmentCancel() {

    }
}
