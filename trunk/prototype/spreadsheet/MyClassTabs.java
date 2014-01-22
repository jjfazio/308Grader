package spreadsheet;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MyClassTabs {
   private BorderPane borderPane;
   private TabPane tabPane;
   
   public MyClassTabs() {
      init();
   }
   
  // public MyClassTabs
   
   private void init() {
      MyGradingSpreadsheet spreadsheet = new MyGradingSpreadsheet();
      borderPane = new BorderPane();
      TabPane tabPane = new TabPane();
      VBox vbox = new VBox();
      
      vbox.getChildren().add(spreadsheet.getTableView());
      
      Tab tab365 = new Tab();
      tab365.setText("CPE365");
      tab365.setContent(vbox);
      
      Tab tab308 = new Tab();
      tab308.setText("CPE308");
      
      tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
      tabPane.getTabs().addAll(tab365, tab308);
      
      borderPane.setCenter(tabPane);
   }
   
   public void addTabs(Tab tab) {
      tabPane.getTabs().add(tab);
   }
   
   public BorderPane getBorderPane() {
      return this.borderPane;
   }
}
