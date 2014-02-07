package controller.file;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import model.assignments_categories.Assignment;
import model.assignments_categories.Grade;
import model.gradebook.Gradebook;
import model.spreadsheet.GradingScheme;
import model.spreadsheet.LatePolicy;
import model.users.Student;
import view.ViewUtility;
import controller.graph.GraphAndAdjustCurveController;

/**
 * The MenuBar controller controls menu bar actions. Any time
 * the menu bar is clicked the action is performed here. Most
 * menu items simply bring up another view, others perform some
 * logic
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
    public void viewGraphs() {
    	System.out.println("View Graphs clicked!");
    	FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/graph/GraphAndAdjustCurve.fxml"));
    	AnchorPane pane = (AnchorPane)ViewUtility.loadView(loader);
    	GraphAndAdjustCurveController controller = (GraphAndAdjustCurveController)loader.getController();
    	
    	Assignment ass = new Assignment();
    	ass.setName("HW09");
    	List<Student> studentList = new ArrayList<Student>();
    	
    	Student erik = new Student("ejowen", "erik", "owen", "10370", "SE", "Junior");
    	erik.addGrade(ass, new Grade(ass, new Date(), 95.0, "A"));
    	
    	Student james = new Student("jfazio", "james", "fazio", "32456", "SE", "Junior");
    	erik.addGrade(ass, new Grade(ass, new Date(), 99.0, "A"));
    	
    	Student kevin = new Student("kfeutz", "kevin", "feutz", "84145", "SE", "Junior");
    	erik.addGrade(ass, new Grade(ass, new Date(), 77.0, "C"));
    	
    	Student kevin2 = new Student("kbackers", "kevin", "backers", "1232465", "SE", "Senior");
    	erik.addGrade(ass, new Grade(ass, new Date(), 88.0, "B"));
    	
    	Student jirbert = new Student("jdilanch", "Jirbert", "Dilanchian", "25642", "SE", "Junior");
    	erik.addGrade(ass, new Grade(ass, new Date(), 82.0, "A"));
    	
    	studentList.add(erik);
    	studentList.add(james);
    	studentList.add(kevin);
    	studentList.add(kevin2);
    	studentList.add(jirbert);
    	
    	controller.setAssignment(ass, studentList);
    	
        ViewUtility.showPage(pane, "Graphs");
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
    public void viewDownloadRoster() {
        System.out.println("Download Roster Clicked");
        
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/gradebook/DownloadRoster.fxml"));
        
        ViewUtility.loadAndShowPage(loader, AnchorPane.class, "Download Roster from SIS");
    }
}
