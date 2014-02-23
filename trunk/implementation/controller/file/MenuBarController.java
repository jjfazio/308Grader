package controller.file;

import controller.graph.GraphAndAdjustCurveController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import model.assignments_categories.Assignment;
import model.assignments_categories.Grade;
<<<<<<< HEAD
import model.exception.BadDataException;
=======
import model.exception.StudentDataException;
>>>>>>> aecac2f7c2668efecb7227fe29c97eeaa4427a6d
import model.gradebook.Gradebook;
import model.users.Student;
import view.ViewUtility;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public void viewGraphs() throws BadDataException {
    	System.out.println("View Graphs clicked!");
    	FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/graph/GraphAndAdjustCurve.fxml"));
    	AnchorPane pane = (AnchorPane)ViewUtility.loadView(loader);
    	GraphAndAdjustCurveController controller = (GraphAndAdjustCurveController)loader.getController();
    	
    	Assignment ass = new Assignment();
    	ass.setName("HW09");
    	List<Student> studentList = new ArrayList<Student>();

        try
        {
    	    Student erik = new Student("ejowen", "erik", "owen", "10370", "SE", "Junior");
        	erik.addGrade(ass, new Grade(new Date(), 88.11, "B"));
    	
<<<<<<< HEAD
    	Student erik = new Student("ejowen", "erik", "owen", "10370", "SE", "Junior");
    	erik.addGrade(ass, new Grade(new Date(), "88.11"));
    	
    	Student james = new Student("jfazio", "james", "fazio", "32456", "SE", "Junior");
    	james.addGrade(ass, new Grade(new Date(), "99.0"));
    	
    	Student kevin = new Student("kfeutz", "kevin", "feutz", "84145", "SE", "Junior");
    	kevin.addGrade(ass, new Grade(new Date(), "77.0"));
    	
    	Student kevin2 = new Student("kbackers", "kevin", "backers", "1232465", "SE", "Senior");
    	kevin2.addGrade(ass, new Grade(new Date(), "88.0"));
    	
    	Student jirbert = new Student("jdilanch", "Jirbert", "Dilanchian", "25642", "SE", "Junior");
    	jirbert.addGrade(ass, new Grade(new Date(), "82.0"));
    	
    	Student sally = new Student("slou", "Sally", "Lou", "32463", "SE", "Sophmore");
    	sally.addGrade(ass, new Grade(new Date(), "85.0"));
    	
    	Student steven = new Student("stevenShyinayga", "Steven", "Shinyagan", "46765", "SE", "Senior");
    	steven.addGrade(ass, new Grade(new Date(), "85.0"));
    	
    	Student patrick = new Student("pweston", "Patrick", "Weston", "234523456", "ME", "Junior");
    	patrick.addGrade(ass, new Grade(new Date(), "95.2"));
    	
    	Student jamesC = new Student("jcornsih", "James", "Cornish", "54634", "ME", "Junior");
    	jamesC.addGrade(ass, new Grade(new Date(), "66.66"));
    	
    	Student jake = new Student("jcosmo", "jake", "cosmo", "7856", "ME", "Junior");
    	jake.addGrade(ass, new Grade(new Date(), "77.1"));
    	
    	Student ferguson = new Student("fAnderz", "Ferguson", "Anderz", "63245", "CPE", "Freshmen");
    	ferguson.addGrade(ass, new Grade(new Date(), "52.99"));
    	
    	Student tommy = new Student("ttall", "Tommy", "Tall", "34673", "CE", "Sophmore");
    	tommy.addGrade(ass, new Grade(new Date(), "85.88"));

    	studentList.add(erik);
    	studentList.add(james);
    	studentList.add(kevin);
    	studentList.add(kevin2);
    	studentList.add(jirbert);
    	studentList.add(sally);
    	studentList.add(steven);
    	studentList.add(patrick);
    	studentList.add(jamesC);
    	studentList.add(jake);
    	studentList.add(ferguson);
    	studentList.add(tommy);
        }
        catch(StudentDataException exc)
        {
            System.out.println(exc.getMessage());
        }

        System.out.println("Assignment being passed to Graph and Curve page: " + ass.getName());
        System.out.println("Students/scores passed to Graph and Curve page: ");
        for(Student stud : studentList) {
            System.out.println("Student: " + stud.getFirstName() + ", Score: " + stud.getGrades().get(ass).getScore());
        }
        System.out.println();

        controller.setAssignment(ass, studentList, "10%");
        ViewUtility.showPage(pane, "Graphs & Adjust Curves");
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
