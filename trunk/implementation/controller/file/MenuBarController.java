package controller.file;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import view.ViewUtility;

/**
 * This class controls the Menu Bar. Any action done on the menu bar
 * will be handled here.
 * @author jfazio erikowen jdilanchian kfeutz kbackers
 */
public class MenuBarController
{
   @FXML 
   private MenuBar menuBar;

   //Interesting idea in tutorial to block other GUI until this screen
   //is canceled, also return boolean returning whether an edit was made
   /**
    * Displays the show/hide dialog when the corresponding menu item 
    * is clicked.
    */
   @FXML
   public void viewShowHide() {
      FXMLLoader loader = new FXMLLoader(
         getClass().getResource("./../../view/file/ShowHide.fxml"));
      
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
         getClass().getResource("./../../view/gradebook/CreateClass.fxml"));
      
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
                getClass().getResource("./../../view/users/AddStudent.fxml"));

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
                getClass().getResource("./../../view/users/EditStudentList.fxml"));

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
                getClass().getResource("./../../view/users/DeleteStudentList.fxml"));

        ViewUtility.showPage(loader, AnchorPane.class, "Delete Student List");

        System.out.println("Delete Student clicked");
    }
    
    @FXML
    public void viewGraphs() {
    	System.out.println("View Graphs clicked!");
    	FXMLLoader loader = new FXMLLoader(
                getClass().getResource("./../../view/graph/GraphAndAdjustCurve.fxml"));

        ViewUtility.showPage(loader, AnchorPane.class, "Graphs");
    }

    @FXML
    public void viewAddCategory() {
        System.out.println("Add Category clicked!");
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("./../../view/assignments_categories/addCategory.fxml"));

        ViewUtility.showPage(loader, AnchorPane.class, "Add Category");
    }

}
