package controller.file;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
}
