package controller.file;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
<<<<<<< HEAD
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.ViewUtility;

=======
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import view.ViewUtility;

/**
 * This class controls the Menu Bar. Any action done on the menu bar
 * will be handled here.
 */
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
=======
   /**
    * Displays the show/hide dialog when the corresponding menu item 
    * is clicked.
    */
   @FXML
   public void viewShowHide() {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("./../../view/file/ShowHide.fxml"));
      
      ViewUtility.showPage(loader, AnchorPane.class, "Show/Hide Spreadsheet");
      
      page = (AnchorPane) ViewUtility.loadView(loader);
      stage = new Stage();
      stage.setTitle("Show/Hide Spreadsheet");
      scene = new Scene(page);
      stage.setScene(scene);

      stage.showAndWait()
      
      System.out.println("Show/Hide clicked");
      
   }
}
