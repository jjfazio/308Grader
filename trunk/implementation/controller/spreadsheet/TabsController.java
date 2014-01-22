package controller.spreadsheet;

<<<<<<< HEAD
import view.ViewUtility;
=======
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
>>>>>>> 9fe84d803cfd6e1e86687215486d9201a83a1161
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import model.gradebook.Gradebook;
import model.spreadsheet.SpreadsheetCourse;
<<<<<<< HEAD

=======
import view.ViewUtility;

/**
 * This class controls any actions done to the Tabs.
 */
>>>>>>> 9fe84d803cfd6e1e86687215486d9201a83a1161
public class TabsController {
   private Gradebook gradebook;

   @FXML
<<<<<<< HEAD
   private TabPane Tabs;

=======
   private TabPane tabs;

   /**
    * This method is called when the view is loaded.
    * Goes through the list of classes taught by the current instructor and
    * adds the classes to tabs in the tab pane. Sets the current tab
    * to the current class in the gradebook. 
    */
>>>>>>> 9fe84d803cfd6e1e86687215486d9201a83a1161
   @FXML
   private void initialize() {
      Tab tab;
      SpreadsheetController controller;
      AnchorPane content;
      FXMLLoader loader;
<<<<<<< HEAD
      SingleSelectionModel<Tab> selectionModel = Tabs.getSelectionModel();
=======
      SingleSelectionModel<Tab> selectionModel = tabs.getSelectionModel();
>>>>>>> 9fe84d803cfd6e1e86687215486d9201a83a1161
      gradebook = Gradebook.getInstance();

      for (SpreadsheetCourse course : gradebook.getCourses()) {
         tab =
            new Tab(course.getCourseInfo().getCourseName() + "-"
               + course.getCourseInfo().getNumber());

         if (gradebook.getCurrentCourse().equals(course)) {
            selectionModel.select(tab);
         }

<<<<<<< HEAD
         loader = new FXMLLoader(getClass().getResource("./../../view/spreadsheet/GradeSheet.fxml"));
         content = (AnchorPane) ViewUtility.loadView(loader);
         tab.setContent(content);
=======
         loader = new FXMLLoader(getClass().getResource(
            "./../../view/spreadsheet/GradeSheet.fxml"));
         content = (AnchorPane) ViewUtility.loadView(loader);
         tab.setContent(content);
         tab.setUserData(course);
>>>>>>> 9fe84d803cfd6e1e86687215486d9201a83a1161
         
         controller = loader.getController();
         controller.setSpreadsheet(course);

<<<<<<< HEAD
         Tabs.getTabs().add(tab);
      }
=======
         tabs.getTabs().add(tab);
      }
      
      tabs.getSelectionModel().selectedItemProperty().addListener(
         new TabListener());
   }
   
   
   private class TabListener implements ChangeListener<Tab> {
      @Override
      public void changed(ObservableValue<? extends Tab> arg0, Tab oldTab,
         Tab newTab) {
         System.out.println(newTab.getText() + " tab selected!");
         
         gradebook.setCurrentCourse((SpreadsheetCourse) newTab.getUserData()); 
      }
      
>>>>>>> 9fe84d803cfd6e1e86687215486d9201a83a1161
   }

}
