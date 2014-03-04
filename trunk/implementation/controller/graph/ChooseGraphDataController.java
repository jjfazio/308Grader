package controller.graph;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.assignments_categories.Assignment;
import model.gradebook.Gradebook;
import model.spreadsheet.SpreadsheetCourse;
import model.users.Student;
import view.ViewUtility;
import view.assignments_categories.AssignmentTree;
import view.assignments_categories.CategoryTree;

public class ChooseGraphDataController {

    @FXML
    private TreeView<String> graphDataChoserTree;
    
    private SpreadsheetCourse course;
    
    private AssignmentTree assignmentTree;

    /**
     * Called by FXML when view is loaded. Reloads all of the
     * categories.
     */
    @FXML
    private void initialize() {
        course = Gradebook.getInstance().getCurrentCourse();
        assignmentTree = new AssignmentTree(course.getCategoryContainer());
        loadTreeView();
        graphDataChoserTree.getSelectionModel().selectFirst();
        
    }

    /**
     * Sets up the TreeView with courses in SIS
     */
    private void loadTreeView()
    {
        TreeItem<String> rootItem = assignmentTree.getRoot();
        rootItem.setExpanded(true);
        graphDataChoserTree.setRoot(rootItem);
        graphDataChoserTree.setShowRoot(true);
    }
    
    /**
     * Closes the Add Category page without doing any changes.
     */
    @FXML
    public void handleChooseGraphDataCancel() {
        System.out.println("Cancel button Clicked!");
        Stage stage = (Stage) graphDataChoserTree.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Closes the Add Category page without doing any changes.
     */
    @FXML
    public void handleChooseGraphDataSelect() {
        System.out.println("Select button Clicked!");
        Stage stage = (Stage) graphDataChoserTree.getScene().getWindow();
        String selectedItem = graphDataChoserTree.getSelectionModel()
                .getSelectedItem().getValue();
        
        if(selectedItem.contains("(")) {
        	String name = selectedItem.substring(0, selectedItem.indexOf("(")).trim();
        	if(name.toLowerCase().equals("overall")) {
        		//go to graphs page and display overall grades
        	}
        	else {
        		Dialogs.showErrorDialog(stage, "Only the overall grade or assignments " +
        		"can be selected for graph viewing.", "Selection Error", "Invalid Selection");
        	}
        }
        else {
        	FXMLLoader loader = new FXMLLoader(
        			getClass().getResource("/view/graph/GraphAndAdjustCurve.fxml"));
        	AnchorPane pane = (AnchorPane)ViewUtility.loadView(loader);
        	GraphAndAdjustCurveController controller = (GraphAndAdjustCurveController)loader.getController();
        	Assignment ass = assignmentTree.getAssignment(selectedItem);
        	List<Student> studentList = course.getStudentRoster();
            controller.setAssignment(ass, studentList, "10%");
            ViewUtility.showPage(pane, "Graphs & Adjust Curves");
        }
        
        stage.close();
    }
}
