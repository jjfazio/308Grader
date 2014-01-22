package spreadsheet;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RemoveClass extends Application {
   
   public RemoveClass() {
      start(new Stage());
   }

   @Override
   public void start(final Stage primaryStage) {
      primaryStage.setTitle("Remove Class");
      VBox mainBox = new VBox(15);

      VBox vbox1 = new VBox(15);
      vbox1.setPrefWidth(400.0);

      //Set padding for the right and left panels to leave space around the
      // dialogs border
      vbox1.setPadding(new Insets(15, 15, 15, 15));

      Label title = new Label("Classes: ");
      final ListView<String> categoryList = new ListView<String>();
      ObservableList<String> items = FXCollections.observableArrayList(
            "CPE365", "CPE308");
      categoryList.setItems(items);

      categoryList.setMaxSize(300, 280);
      vbox1.getChildren().addAll(title, categoryList);
      mainBox.getChildren().add(vbox1);

      //Create the actual Confirm Add and Cancel buttons
      Button removeBtn = new Button();
      Button cancelBtn = new Button();
      removeBtn.setText("Remove");
      cancelBtn.setText("Cancel");

      //Close dialog with click of the "Cancel" button
      cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              primaryStage.close();
          }
      });

      //Close dialog with confirm add button click.
      //this is temporary
      removeBtn.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
             String selected = categoryList.getSelectionModel().getSelectedItem();
             
             if (selected != null){
                System.out.println(selected);
             }
              primaryStage.close();
          }
      });


      HBox hButtonBox = new HBox();
      hButtonBox.getChildren().addAll(removeBtn, cancelBtn);
      hButtonBox.setAlignment(Pos.CENTER);
      hButtonBox.setSpacing(50);


      mainBox.getChildren().add(hButtonBox);
      Scene scene = new Scene(mainBox, 600, 400);

      primaryStage.setScene(scene);
      primaryStage.show();
  }

}
