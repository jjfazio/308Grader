package spreadsheet;

import java.awt.Checkbox;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShowHideSpreadsheet extends Application {
   
   private final String[] builtInColumns = {"Username", 
         "First Name",
         "Last Name",
         "Major",
         "Grade",
         "Student ID"};
   
   private final String[] builtInRows = {"Mean", 
         "Standard Deviation",
         "Students Graded"};
   
   private final String[] builtInOther = {"Show Total Assignment Points"};
   
   public ShowHideSpreadsheet() {
      try {
         start(new Stage());
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   @Override
   public void start(Stage primaryStage) throws Exception {
      VBox vbox = new VBox();
      TreeView tree;
      TreeItem columnItems[] = new TreeItem[builtInColumns.length];
      TreeItem rowItems[] = new TreeItem[builtInRows.length];
      TreeItem otherItems[] = new TreeItem[builtInOther.length];
      TreeItem<String> root = new TreeItem<String>("Show/Hide Spreadsheet");
      TreeItem<String> columns = new TreeItem<String>("Columns");
      TreeItem<String> rows = new TreeItem<String>("Rows");
      TreeItem<String> other = new TreeItem<String>("Other");
      
      for (int i = 0; i < builtInColumns.length; i++) {
         columnItems[i] = new TreeItem<CheckBox>(new CheckBox(builtInColumns[i]));
      }
      
      for (int i = 0; i < builtInRows.length; i++) {
         rowItems[i] = new TreeItem<CheckBox>(new CheckBox(builtInRows[i]));
      }
      
      for (int i = 0; i < builtInOther.length; i++) {
         otherItems[i] = new TreeItem<CheckBox>(new CheckBox(builtInOther[i]));
      }
      
      columns.setExpanded(true);
      rows.setExpanded(true);
      other.setExpanded(true);
      
      columns.getChildren().addAll(columnItems);
      rows.getChildren().addAll(rowItems);
      other.getChildren().addAll(otherItems);
      
      root.getChildren().addAll(columns, rows, other);
      root.setExpanded(true);
      tree = new TreeView(root);
      tree.setShowRoot(true);
      
      vbox.getChildren().addAll(tree);
      
      primaryStage.setScene(new Scene(vbox));
      primaryStage.show();
   }
   
   public static void main(String[] args) { launch(args); }

}
