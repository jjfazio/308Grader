package spreadsheet;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import spreadsheet.AddCategory;
import spreadsheet.AddClass;
import spreadsheet.DeleteCategory;
import spreadsheet.EditCategory;
import spreadsheet.ShowHideSpreadsheet;
import users.AddStudent;
import users.DeleteStudentList;
import users.EditStudentList;

public class MyMenuBar {
   private MenuBar menubar;
   
   public MyMenuBar() {
      init();
   }
   
   private void init() {
      Menu file = new Menu("File");
      Menu edit = new Menu("Edit");
      Menu classes  = new Menu("Classes");
      Menu students = new Menu("Students");
      Menu assignments = new Menu("Assignments");
      menubar = new MenuBar();
      
      addFileMenuItems(file);
      addEditMenuItems(edit);
      addClassMenuItems(classes);
      addStudentMenuItems(students);
      addAssignmentMenuItems(assignments);
      
      menubar.getMenus().addAll(file, edit, classes, students, assignments);
   }
   
   private void addFileMenuItems(Menu file) {
      MenuItem quit = new MenuItem("Quit");
      quit.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            new QuitNotification();
        }
      });
       
       file.getItems().addAll(new MenuItem("New Window"), new MenuItem("Print"),
            quit);
      
   }
   
   private void addEditMenuItems(Menu edit) {
      edit.getItems().addAll(new MenuItem("Undo"), new MenuItem("Cut"), 
            new MenuItem("Copy"), new MenuItem("Paste"), new MenuItem("Delete"),
            new MenuItem("Select"), new MenuItem("Select All"));
      
      MenuItem showHide = new MenuItem("Show/Hide");
      
      showHide.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent event) {
            new ShowHideSpreadsheet();
         }
      });
      
      edit.getItems().add(showHide);
   }
   
   private void addClassMenuItems(Menu classes) {
      MenuItem addClass = new MenuItem("Add Class");
      MenuItem downloadRoster = new MenuItem("Download Roster");
      MenuItem editClass = new MenuItem("Edit Class");
      MenuItem openClass = new MenuItem("Open Class");
      MenuItem removeClass = new MenuItem("Remove Class");
      
      addClass.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent event) {
            new AddClass();
         }
      });
      
      openClass.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent event) {
            new OpenClass();
         }
      });
      
      downloadRoster.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent event) {
            new DownloadRoster();
         }
      });
      
      editClass.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent event) {
            new EditClass();
         }
      });
      
      removeClass.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent event) {
            new RemoveClass();
         }
      });
      
      classes.getItems().addAll(addClass, downloadRoster, editClass,
            openClass, removeClass);
      
   }
   
   private void addStudentMenuItems(Menu students) {
      MenuItem addStudent = new MenuItem("Add Student");
      addStudent.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent event) {
            new AddStudent();
         }
      });
      
      MenuItem modifyStudent = new MenuItem("Modify Student");
      modifyStudent.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent event) {
            new EditStudentList();
         }
      });
      MenuItem deleteStudent = new MenuItem("Delete Student");
      deleteStudent.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              new DeleteStudentList();
          }
      });
      
      students.getItems().addAll(addStudent, modifyStudent, deleteStudent);
   }
   
   private void addAssignmentMenuItems(Menu assignments) {      
      MenuItem addCategory = new MenuItem("Add Category");
      addCategory.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent event) {
            new AddCategory();
         }
      });
      
      MenuItem editCategory = new MenuItem("Edit Category");
      editCategory.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent event) {
            new EditCategory();
         }
      });
      
      MenuItem deleteCategory = new MenuItem("Delete Category");
      deleteCategory.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent event) {
            new DeleteCategory();
         }
      });

      MenuItem addAssignment = new MenuItem("Add Assignment");
      addAssignment.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              new AddAssignment();
          }
      });

      MenuItem editAssignment = new MenuItem("Edit Assignment");
      editAssignment.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               new EditAssignment();
           }
       });

      MenuItem deleteAssignment = new MenuItem("Delete Assignment");
      deleteAssignment.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               new DeleteAssignment();
           }
       });

      assignments.getItems().addAll(addCategory, editCategory, deleteCategory, addAssignment, editAssignment, deleteAssignment,
            new MenuItem("Import Assignment Results"), new MenuItem("View Assignments As"));
   }
   
   public MenuBar getMenuBar() {
      return menubar;
   }
   

}
