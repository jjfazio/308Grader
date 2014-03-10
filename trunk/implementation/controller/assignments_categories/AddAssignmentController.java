package controller.assignments_categories;

import java.util.ArrayList;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.assignments_categories.Assignment;
import model.assignments_categories.Category;
import model.exception.BadDataException;
import model.gradebook.Gradebook;
import model.spreadsheet.GradingScheme;
import model.spreadsheet.LatePolicy;
import model.spreadsheet.SpreadsheetCourse;
import view.assignments_categories.AssignmentTree;
import view.assignments_categories.CategoryTree;
import model.gradebook.Gradebook;

/**
 * @author Jirbert Dilanchian
 */
public class AddAssignmentController {
    
    private SpreadsheetCourse currentCourse;

    @FXML
    private TextField addAssignmentName;

//    @FXML
//    private ComboBox addAssignmentCategory;

    @FXML
    private TextField addAssignmentDueDate;

    @FXML
    private TextField addAssignmentWeight;

    @FXML
    private TreeView<String> treeView;

    @FXML
    private TextField addAssignmentPoints;

    @FXML
    private RadioButton addAssignmentDefaultLatePolicy;
    @FXML
    private RadioButton addAssignmentSetLatePolicy;
    @FXML
    private RadioButton addAssignmentSetGradingScheme;
    @FXML
    private RadioButton addAssignmentDefaultGradingScheme;


    private SpreadsheetCourse course;

    private AssignmentTree assignmentTree;

//    public AddAssignmentController() {
//    }

    /**
     * Called by FXML when view is loaded. Reloads all of the
     * categories.
     */
    @FXML
    private void initialize() {
//        currentCourse = Gradebook.getInstance().getCurrentCourse();
//        addAssignmentCategory.getItems().clear();
//        fillComboCategoryNames(currentCourse.getCategoryContainer().getRoot());

        course = Gradebook.getInstance().getCurrentCourse();
        assignmentTree = new AssignmentTree(course.getCategoryContainer());
        loadTreeView();
    }

    /**
     * Sets up the TreeView with courses in SIS
     */
    private void loadTreeView()
    {
        TreeItem<String> rootItem = assignmentTree.getRoot();
        rootItem.setExpanded(true);
        treeView.setRoot(rootItem);
        treeView.setShowRoot(true);
    }

    /**
     * Filles the comboBox on add Category dialogue.
     * @param theCat The category which name is going to be added to the list
     */
//    @FXML
//    private void fillComboCategoryNames(Category theCat) {
//        addAssignmentCategory.getItems().add(theCat.getName());
//        if((ArrayList<Category>)theCat.getSubCategories() != null){
//            for(Category x : (ArrayList<Category>)theCat.getSubCategories()) {
//                fillComboCategoryNames(x);
//            }
//        }
//    }
    /**
     * Adds an assignment to the collection of assignment of the parent Category.
     */
    @FXML
    private void handleAddAssignmentSave() {
        boolean success = true;

        Stage stage = (Stage) treeView.getScene().getWindow();
        try {

            String selectedCategory = treeView.getSelectionModel()
                    .getSelectedItem().getValue();
            String parentName = selectedCategory.substring(0,
                    selectedCategory.indexOf("(")).trim();
//            Category temp = assignmentTree.getCategory(parentName);

            try{
            course.getCategoryContainer().addAssignment(assignmentTree.getCategory(parentName),
                    addAssignmentName.getText(),
                    addAssignmentWeight.getText(),
                    addAssignmentPoints.getText(),
                    new Date(), new GradingScheme(), new LatePolicy(), false);
            } catch (BadDataException e) {
                success = false;
                Dialogs.showErrorDialog(stage, e.getMessage(), "Please resolve the following issues.", "Invalid input");
            }
        }catch (Exception err){
            success = false;
            Dialogs.showErrorDialog(stage, "Parent category not selected or An assignment is chosen instead of parent " +
                    "category",
                    "Please resolve the following issues.", "Invalid input");
        }
        if(success){
            stage.close();
        }


/*        System.out.println("Save button Clicked!");
//        addAssignment(addAssignmentCategory.getValue().toString(),
//                currentCourse.getCategoryContainer().getRoot());
        Stage stage = (Stage) treeView.getScene().getWindow();

        try {
            String selectedCategory = treeView.getSelectionModel()
                    .getSelectedItem().getValue();
            String parentName = selectedCategory.substring(0,
                    selectedCategory.indexOf("(")).trim();
//            addAssignment(parentName, currentCourse.getCategoryContainer().getRoot());
            try {
                Assignment newAssignment = new Assignment(assignmentTree.getCategory(parentName), addAssignmentName.getText(),
                        Double.parseDouble(addAssignmentWeight.getText()),
                        Integer.parseInt(addAssignmentPoints.getText()),
                        new Date(), new GradingScheme(), new LatePolicy(), false);
                currentCourse.getCategoryContainer().addAssignment(assignmentTree.getCategory(parentName),
                        newAssignment);
            }catch (BadDataException e)
            {
                Dialogs.showErrorDialog(stage, e.getMessage(), "Please resolve the following issues.", "Invalid input");
            }
        }
        catch (Exception e)
        {
            Dialogs.showErrorDialog(stage, "You need to specify a parent category", "Please resolve the following issues.", "Invalid input");
        }

        stage.close();*/
    }

    /**
     * Locates the parent category in the arraylist of subCategories and adds the new category to it.
     * @param name The name of the parent category.
     * @param cat The new category to be added to parent category
     */
    private void addAssignment(String name, Category cat) {

//        Category catLookingFor = null;
//        if(cat.getName().equals(name)) {
//            catLookingFor =  cat;
//                Assignment newAssignment = new Assignment(cat, addAssignmentName.getText(),
//                                                          Double.parseDouble(addAssignmentWeight.getText()),
//                                                          Integer.parseInt(addAssignmentPoints.getText()),
//                                                          new Date(), new GradingScheme(), new LatePolicy(), false);
//                currentCourse.getCategoryContainer().addAssignment(cat,
//                        newAssignment);
//        }
//        else if((ArrayList<Category>)cat.getSubCategories() != null && catLookingFor == null){
//            for(Category x : (ArrayList<Category>)cat.getSubCategories()) {
//                if(catLookingFor == null) {
//                    addAssignment(name, x);
//                }
//            }
//        }
    }

    /**
     * Closes the Add Assignment page without saving anything.
     */
    @FXML
    private void handleAddAssignmentCancel() {
        System.out.println("Cancel button Clicked!");
        Stage stage = (Stage) addAssignmentName.getScene().getWindow();
        stage.close();
    }
}
