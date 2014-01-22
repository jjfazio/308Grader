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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Jirbert
 */


//package spreadsheet;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.control.cell.*;
import javafx.scene.control.TableColumn.*;
import javafx.collections.FXCollections;
import javafx.beans.value.*;


/**
 *
 * @author Jirbert
 */
public class AddCategory extends Application {

    Stage primaryStage;

    public AddCategory(){
        start(new Stage());
    }

    @Override
    public void start(final Stage stage) {
        primaryStage = stage;
        Button addBtn = new Button("Save");
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setMinSize(100, 10);
        addBtn.setMinSize(100, 10);
        stage.setMinWidth(400);

        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });

        StackPane root = new StackPane();

        //Creating a GridPane container
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        ComboBox cbCategory = new ComboBox(FXCollections.observableArrayList(new Separator(), "Top"));
        cbCategory.setMinSize(300, 10);
        cbCategory.showingProperty();
        GridPane.setConstraints(cbCategory, 1, 0);
        grid.getChildren().add(cbCategory);

        //Defining the Name text fields
        final TextField name = new TextField();
        name.setPromptText("");
        name.getText();
        GridPane.setConstraints(name, 1, 1);
        grid.getChildren().add(name);

        //Defining the  text field
        final TextField weight = new TextField();
        weight.setPromptText("");
        weight.getText();
        GridPane.setConstraints(weight, 1, 2);
        grid.getChildren().add(weight);


        //Defining the labels
        Label labelCategory = new Label("Category:");
        GridPane.setConstraints(labelCategory, 0, 0);
        grid.getChildren().add(labelCategory);
        Label labelName = new Label("Name:");
        GridPane.setConstraints(labelName, 0, 1);
        grid.getChildren().add(labelName);
        Label labelWeight = new Label("Weight:");
        GridPane.setConstraints(labelWeight, 0, 2);
        grid.getChildren().add(labelWeight);


        HBox hButtonBox = new HBox();
        hButtonBox.getChildren().addAll(addBtn, cancelBtn);
        hButtonBox.setAlignment(Pos.CENTER);
        hButtonBox.setSpacing(50);

        GridPane.setConstraints(hButtonBox, 1,11);
        grid.getChildren().add(hButtonBox);
        root.getChildren().add(grid);
        Scene scene = new Scene(root, 300, 300);

        primaryStage.setTitle("Create Class");
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
