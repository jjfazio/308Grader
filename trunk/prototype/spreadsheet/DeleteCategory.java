package spreadsheet;//package users;

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

/**
 *
 * @author Jirbert
 */
public class DeleteCategory extends Application {

    public DeleteCategory(){
        start(new Stage());
    }
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Delete Category");
        VBox mainBox = new VBox(15);

        HBox hbox1 = new HBox(40);
        VBox vbox1 = new VBox(15);
        vbox1.setPrefWidth(400.0);

        //Set padding for the right and left panels to leave space around the
        // dialogs border
        vbox1.setPadding(new Insets(15, 15, 15, 15));

        Label categoriesNameLab = new Label("Categories: ");

        ListView<String> categoryList = new ListView<String>();
        ObservableList<String> items =FXCollections.observableArrayList (
                /*"Single", "Double", "Suite", "Family App"*/);
        categoryList.setItems(items);

        categoryList.setMaxSize(300, 280);
        vbox1.getChildren().addAll(categoriesNameLab, categoryList);
        mainBox.getChildren().add(vbox1);



        //Create the actual Confirm Add and Cancel buttons
        Button confirmAddBtn = new Button();
        Button cancelBtn = new Button();
        confirmAddBtn.setText("Delete");
        cancelBtn.setText("Cancel");

        //Close dialog with click of the "Cancel" button
        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //primaryStage.close();
            }
        });

        //Close dialog with confirm add button click.
        //this is temporary
        confirmAddBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });


        HBox hButtonBox = new HBox();
        hButtonBox.getChildren().addAll(confirmAddBtn, cancelBtn);
        hButtonBox.setAlignment(Pos.CENTER);
        hButtonBox.setSpacing(50);


        mainBox.getChildren().add(hButtonBox);
        Scene scene = new Scene(mainBox, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
