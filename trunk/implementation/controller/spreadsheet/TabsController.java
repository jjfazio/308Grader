package controller.spreadsheet;

import view.ViewUtility;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import model.gradebook.Gradebook;
import model.spreadsheet.SpreadsheetCourse;

public class TabsController {
   private Gradebook gradebook;

   @FXML
   private TabPane Tabs;

   @FXML
   private void initialize() {
      Tab tab;
      SpreadsheetController controller;
      AnchorPane content;
      FXMLLoader loader;
      SingleSelectionModel<Tab> selectionModel = Tabs.getSelectionModel();
      gradebook = Gradebook.getInstance();

      for (SpreadsheetCourse course : gradebook.getCourses()) {
         tab =
            new Tab(course.getCourseInfo().getCourseName() + "-"
               + course.getCourseInfo().getNumber());

         if (gradebook.getCurrentCourse().equals(course)) {
            selectionModel.select(tab);
         }

         loader = new FXMLLoader(getClass().getResource("./../../view/spreadsheet/GradeSheet.fxml"));
         content = (AnchorPane) ViewUtility.loadView(loader);
         tab.setContent(content);
         
         controller = loader.getController();
         controller.setSpreadsheet(course);

         Tabs.getTabs().add(tab);
      }
   }

}
