package controller.file;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import model.assignments_categories.Assignment;
import model.assignments_categories.Category;
import model.gradebook.Gradebook;
import controller.graph.*;
import view.ViewUtility;

/**
 * This class controls the Menu Bar. Any action done on the menu bar
 * will be handled here. ooga booga
 * @author jfazio erikowen jdilanchian kfeutz kbackers
 */
public class MenuBarController
{
   Gradebook gradebook;
   
   @FXML 
   private MenuBar menuBar;
   
   @FXML
   public void saveGradebook() {
	   System.out.println("Pressed save!");
	   gradebook = Gradebook.getInstance();
	   gradebook.saveGradebook();
   }

   //Interesting idea in tutorial to block other GUI until this screen
   //is canceled, also return boolean returning whether an edit was made
   /**
    * Displays the show/hide dialog when the corresponding menu item 
    * is clicked.
    */
   @FXML
   public void viewShowHide() {
      FXMLLoader loader = new FXMLLoader(
         getClass().getResource("/view/file/ShowHide.fxml"));
      
      ViewUtility.showPage(loader, AnchorPane.class, "Show/Hide Spreadsheet");
      
      System.out.println("Show/Hide clicked");
      
   }
   
   /**
    * Displays the add class dialog when the corresponding menu item 
    * is clicked.
    */
   @FXML
   public void classAdd() {
      FXMLLoader loader = new FXMLLoader(
         getClass().getResource("/view/gradebook/CreateClass.fxml"));
      
      ViewUtility.showPage(loader, AnchorPane.class, "Add Class");
      
      System.out.println("Add Class clicked");
      
   }

   /**
    * Displays the add student dialog when the corresponding menu item
    * is clicked.
    */
    @FXML
    public void viewAddStudentDialog() {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/users/AddStudent.fxml"));

        ViewUtility.showPage(loader, AnchorPane.class, "Add Student");

        System.out.println("Add Student clicked");
    }

    /**
     * Displays the edit student list dialog when the corresponding
     * menu item is clicked.
     */  
    @FXML
    public void viewEditStudentListDialog() {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/users/EditStudentList.fxml"));

        ViewUtility.showPage(loader, AnchorPane.class, "Edit Student List");

        System.out.println("Edit Student clicked");
    }

    /**
     * Displays the delete student list dialog when the corresponding
     * menu item is clicked
     */   
    @FXML
    public void viewDeleteStudentListDialog() {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/users/DeleteStudentList.fxml"));

        ViewUtility.showPage(loader, AnchorPane.class, "Delete Student List");

        System.out.println("Delete Student clicked");
    }
    
    @FXML
    public void viewGraphs() {
    	System.out.println("View Graphs clicked!");
    	FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/graph/GraphAndAdjustCurve.fxml"));

    	GraphAndAdjustCurveController controller = (GraphAndAdjustCurveController)loader.getController();
    	
    	//If a category is selected, create a graph based on the current category
    	//controller.setCategory(new Category());
    	
    	//controller.toString();
    	
    	//If an assignment is selected, create a graph based on the current assignment
    	//controller.setAssignment(new Assignment());
    	
        ViewUtility.showPage(loader, AnchorPane.class, "Graphs");
    }

    @FXML
    public void viewAddCategory() {
        System.out.println("Add Category clicked!");
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/assignments_categories/addCategory.fxml"));

        ViewUtility.showPage(loader, AnchorPane.class, "Add Category");
    }

    @FXML
    public void viewEditCategory() {
        System.out.println("Edit Category clicked!");
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/assignments_categories/editCategory.fxml"));

        ViewUtility.showPage(loader, AnchorPane.class, "Edit Category");
    }

    @FXML
    public void viewDeleteCategory() {
        System.out.println("Delete Category clicked!");
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/assignments_categories/deleteCategory.fxml"));

        ViewUtility.showPage(loader, AnchorPane.class, "Delete Category");
    }

    @FXML
    public void viewAddAssignment() {
        System.out.println("Add Assignment clicked!");
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/assignments_categories/addAssignment.fxml"));

        ViewUtility.showPage(loader, AnchorPane.class, "Add Assignment");
    }

    @FXML
    public void viewEditAssignment() {
        System.out.println("Edit Assignment clicked!");
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/assignments_categories/editAssignment.fxml"));

        ViewUtility.showPage(loader, AnchorPane.class, "Edit Assignment");
    }

    @FXML
    public void viewDeleteAssignment() {
        System.out.println("Delete Assignment clicked!");
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/assignments_categories/deleteAssignment.fxml"));
        
        ViewUtility.showPage(loader, AnchorPane.class, "Delete Assignment");
    }
}
