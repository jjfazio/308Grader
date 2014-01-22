package controller.file;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

<<<<<<< HEAD
=======
/**
 * This class controls the Show/Hide view. Any action done on the page
 * will be handled here
 */
>>>>>>> 9fe84d803cfd6e1e86687215486d9201a83a1161
public class ShowHideController {
   @FXML
   private TreeView<String> treeView;

   private final String[] builtInColumns = { "Username", "First Name",
      "Last Name", "Major", "Grade", "Student ID" };

   private final String[] builtInRows = { "Mean", "Standard Deviation",
      "Students Graded" };

   private final String[] builtInOther = { "Show Total Assignment Points" };

<<<<<<< HEAD
=======
   /**
    * Called when the view is constructed. Creates the TreeView
    * that displays the show/hide options.
    */
   @FXML
>>>>>>> 9fe84d803cfd6e1e86687215486d9201a83a1161
   public void initialize() {
      System.out.println("Displaying Show/Hide");
      loadTreeItems();
   }
   
<<<<<<< HEAD
   public void loadTreeItems() {
=======
   private void loadTreeItems() {
>>>>>>> 9fe84d803cfd6e1e86687215486d9201a83a1161
      TreeItem<String> root = new TreeItem<String>("Show/Hide Spreadsheet");
      TreeItem<String> columns = new TreeItem<String>("Columns");
      TreeItem<String> rows = new TreeItem<String>("Rows");
      TreeItem<String> other = new TreeItem<String>("Other");
      TreeItem columnItems[] = new TreeItem[builtInColumns.length];
      TreeItem rowItems[] = new TreeItem[builtInRows.length];
      TreeItem otherItems[] = new TreeItem[builtInOther.length];
      
      
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
      treeView.setRoot(root);
      treeView.setShowRoot(true);
      
      treeView = new TreeView(root);
      
      
      
   }
}
