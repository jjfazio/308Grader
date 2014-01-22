package controller.file;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.ViewUtility;

public class MenuBarController
{
   @FXML 
   private MenuBar menuBar;

   //Interesting idea in tutorial to block other GUI until this screen
   //is canceled, also return boolean returning whether an edit was made
   @FXML
   public void viewShowHide() {
      Scene scene;
      AnchorPane page;
      Stage stage;
      FXMLLoader loader = new FXMLLoader(
         getClass().getResource("./../../view/file/ShowHide.fxml"));
      
      ViewUtility.showPage(loader, AnchorPane.class, "Show/Hide Spreadsheet");
      
//      page = (AnchorPane) ViewUtility.loadView(loader);
//      stage = new Stage();
//      stage.setTitle("Show/Hide Spreadsheet");
//      scene = new Scene(page);
//      stage.setScene(scene);

      //stage.showAndWait()
      
      System.out.println("Show/Hide clicked");
      
   }
}
