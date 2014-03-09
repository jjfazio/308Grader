package controller.spreadsheet;

import java.util.Observable;
import java.util.Observer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import model.gradebook.Gradebook;
import model.spreadsheet.SpreadsheetCourse;
import view.ViewUtility;

/**
 * This class controls any actions done to the Tabs.
 * @author jamesfazio
 */
public class TabsController implements Observer {
   private Gradebook gradebook;
   
   @FXML
   private TabPane tabs;
   

   /**
    * This method is called when the view is loaded.
    * Goes through the list of classes taught by the current instructor and
    * adds the classes to tabs in the tab pane. Sets the current tab
    * to the current class in the gradebook. 
    */
   @FXML
   private void initialize() {
      gradebook = Gradebook.getInstance();
      tabs.getSelectionModel().selectedItemProperty().addListener(
              new TabListener());
      loadTabs();
   }
   
   
   private void loadTabs() 
   {
       Tab tab;
       SpreadsheetController controller;
       AnchorPane content;
       FXMLLoader loader;
       tabs.getTabs().clear();
       
       if (gradebook.getCourses() != null)
       {
           for (SpreadsheetCourse course : gradebook.getCourses()) {
               tab =
                       new Tab(course.getCourseInfo().getNumber() + "-"
                               + course.getCourseInfo().getSection());


               loader = new FXMLLoader(getClass().getResource(
                       "/view/spreadsheet/GradeSheet.fxml"));
               content = (AnchorPane) ViewUtility.loadView(loader);
               tab.setContent(content);
               tab.setUserData(course);
               controller = loader.getController();
               course.addObserver(controller);
               course.getCategoryContainer().addObserver(controller);
               controller.setSpreadsheet(course);

               tabs.getTabs().add(tab);
           }
       }
   }
   
   /**
    * Listens for a tab change. When a tab is clicked the
    * current SpreadsheetCourse is set in the Gradebook.
    * @author jamesfazio
    *
    */
   private class TabListener implements ChangeListener<Tab> {
      @Override
      public void changed(ObservableValue<? extends Tab> arg0, Tab oldTab,
         Tab newTab) {
          if (newTab != null) {
              System.out.println(newTab.getText() + " tab selected!");
              
              gradebook.setCurrentCourse((SpreadsheetCourse) newTab.getUserData()); 
              
          }
      }
   }

   @Override
   /**
    * Called when a new class gets added to the gradebook.
    */
   public void update(Observable o, Object arg)
   {
       loadTabs();
   }

}
