package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Utility class that assists in loading and displaying views
 * @author jamesfazio
 *
 */
public class ViewUtility
{
   
   /**
    * Loads a view and returns it. Thought this would be a good idea
    * since we can isolate the try/catch to one location.
    * @param loader - The loader being used
    * @return
    */
   public static Object loadView(FXMLLoader loader) {
      Object view = null;
      try {
         view = loader.load();
      } 
      catch (IOException e) {
         e.printStackTrace();
      }
      
      return view;
   }
   
   /**
    * Displays a new window to the screen.
    * @param loader - The loader that contains the view to be loaded
    * @param clazz - The class type of the view being loaded (example:
    * AnchorPane, BorderLayout, HBox.. )
    * @param title - The title of the new window
    */
   public static <T extends Parent> void loadAndShowPage(FXMLLoader loader,
      Class<T> clazz, String title) {
      T page;
      Stage stage;
      Scene scene;
      
      page = (T) ViewUtility.loadView(loader);
      stage = new Stage();
      stage.setTitle(title);
      scene = new Scene(page);
      stage.setScene(scene);
      stage.show();
      
   }
   
   public static <T extends Parent> void showPage(T parent, String title) {
       Scene scene;
       Stage stage = new Stage();
       
       stage.setTitle(title);
       scene = new Scene(parent);
       stage.setScene(scene);
       stage.show();
   }
}
