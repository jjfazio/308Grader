package controller.file;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.exception.BadDataException;
import model.exception.StudentDataException;
import model.gradebook.Gradebook;
import view.ViewUtility;

/**
 * The MenuBar controller controls menu bar actions. Any time
 * the menu bar is clicked the action is performed here. Most
 * menu items simply bring up another view, others perform some
 * logic
 * @author jfazio erikowen jdilanchian kfeutz kbackers
 */
public class MenuBarController
{
   /** Instance of the Gradebook */
   Gradebook gradebook;
   
   @FXML 
   /** Menu Bar of the view */
   private MenuBar menuBar;
   
   @FXML
   /** Controller for saving a gradebook */
   public void saveGradebook() {
	   System.out.println("Pressed save!");
	   gradebook = Gradebook.getInstance();
	   gradebook.saveGradebook();
   }
   
   @FXML
   public void quitGradebook() {
       Platform.exit();
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
      
      ViewUtility.loadAndShowPage(loader, AnchorPane.class, "Show/Hide Spreadsheet");
      
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
      
      ViewUtility.loadAndShowPage(loader, AnchorPane.class, "Add Class");
      
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

        ViewUtility.loadAndShowPage(loader, AnchorPane.class, "Add Student");

        System.out.println("Add Student clicked");
    }

    /**
     * Displays the statistics table
     */
    @FXML
    public void viewStatisticsTable() {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/spreadsheet/StatsView.fxml"));

        ViewUtility.loadAndShowPage(loader, AnchorPane.class, "Stats View");
    }

    /**
     * Displays the edit student list dialog when the corresponding
     * menu item is clicked.
     */  
    @FXML
    public void viewEditStudentListDialog() {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/users/EditStudentList.fxml"));

        ViewUtility.loadAndShowPage(loader, AnchorPane.class, "Edit Student List");

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

        ViewUtility.loadAndShowPage(loader, AnchorPane.class, "Delete Student List");

        System.out.println("Delete Student clicked");
    }
    
    @FXML
    public void viewGraphs() throws BadDataException, StudentDataException {
    	System.out.println("View Graphs clicked!");
    	
    	FXMLLoader loader = new FXMLLoader(
           getClass().getResource("/view/graph/ChooseGraphData.fxml"));
        ViewUtility.loadAndShowPage(loader, VBox.class, "View Graphs");
    }

    @FXML
    public void viewAddCategory() {
        System.out.println("Add Category clicked!");
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/assignments_categories/addCategory.fxml"));

        ViewUtility.loadAndShowPage(loader, AnchorPane.class, "Add Category");
    }

    @FXML
    public void viewEditCategory() {
        System.out.println("Edit Category clicked!");
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/assignments_categories/editCategory.fxml"));

        ViewUtility.loadAndShowPage(loader, AnchorPane.class, "Edit Category");
    }

    @FXML
    public void viewDeleteCategory() {
        System.out.println("Delete Category clicked!");
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/assignments_categories/deleteCategory.fxml"));

        ViewUtility.loadAndShowPage(loader, AnchorPane.class, "Delete Category");
    }

    @FXML
    public void viewAddAssignment() {
        System.out.println("Add Assignment clicked!");
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/assignments_categories/addAssignment.fxml"));

        ViewUtility.loadAndShowPage(loader, AnchorPane.class, "Add Assignment");
    }

    @FXML
    public void viewEditAssignment() {
        System.out.println("Edit Assignment clicked!");
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/assignments_categories/editAssignment.fxml"));

        ViewUtility.loadAndShowPage(loader, AnchorPane.class, "Edit Assignment");
    }

    @FXML
    public void viewDeleteAssignment() {
        System.out.println("Delete Assignment clicked!");
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/assignments_categories/deleteAssignment.fxml"));
        
        ViewUtility.loadAndShowPage(loader, AnchorPane.class, "Delete Assignment");
    }
    
    @FXML
    /** Controller for download roster menu item */
    public void viewDownloadRoster() {
        System.out.println("Download Roster Clicked");
        
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/gradebook/DownloadRoster.fxml"));
        
        ViewUtility.loadAndShowPage(loader, AnchorPane.class, "Download Roster from SIS");
    }
}
