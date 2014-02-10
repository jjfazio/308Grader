package controller.assignments_categories;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import model.assignments_categories.Assignment;
import model.assignments_categories.Category;
import model.gradebook.Gradebook;
import view.ViewUtility;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Jirbert Dilanchian
 */
public class AddAssignmentController {
    @FXML
    private String addAssignmentName;
    @FXML
    private ComboBox addAssignmentCategory;
    @FXML
    private Date addAssignmentDueDate;
    @FXML
    private int addAssignmentWeight;
    @FXML
    private int addAssignmentPoints;

    @FXML
    private boolean addAssignmentDefaultLatePolicy;
    @FXML
    private boolean addAssignmentSetLatePolicy;
    @FXML
    private boolean addAssignmentSetGradingScheme;
    @FXML
    private boolean addAssignmentDefaultGradingScheme;

//    public AddAssignmentController() {
//    }

  /*  @FXML
    private Category findCategory(String name, Category cat) {
        Category catLookingFor = null;
        if(cat.getName().equals(name)) {
            catLookingFor =  cat;
        }
        if((ArrayList<Category>)cat.getSubCategories() != null && catLookingFor == null){
            for(Category x : (ArrayList<Category>)cat.getSubCategories()) {
                if(catLookingFor == null) {
                    findCategory(name, x);
                }
            }
        }
        return catLookingFor;
    }          */

    /**
     * Adds an assignment to the collection of assignment of the parent Category.
     */
    @FXML
    private void handleAddAssignmentSave() {
        System.out.println("Save button Clicked!");
        //       Category parCategory = findCategory(addAssignmentCategory.getValue().toString().trim(),
        //               Gradebook.getInstance().getCurrentCourse().getTopCategory());
//        System.out.println(addAssignmentSetLatePolicy);
//        if(addAssignmentSetLatePolicy.get)
//        Assignment newAssignment = new Assignment(addAssignmentName, addAssignmentWeight, addAssignmentPoints,
//                addAssignmentDueDate, (grading scheme), (LatePolicy),  false);
        //      parCategory.addAssignment(newAssignment);

    }

    /**
     * Closes the Add Assignment page without saving anything.
     */

    @FXML
    private void handleAddAssignmentCancel() {
        System.out.println("Cancel button Clicked!");
    }
}
