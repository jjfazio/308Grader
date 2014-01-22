
package spreadsheet;

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
public class AddAssignment extends Application {

    Stage primaryStage;

    public AddAssignment(){
        start(new Stage());
    }

    @Override
    public void start(final Stage stage) {
        primaryStage = stage;
        Button addBtn = new Button("Ok");
        Button cancelBtn = new Button("Cancel");

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

        ComboBox cbCategory = new ComboBox(FXCollections.observableArrayList(new Separator(), "Overall Grade"));
        cbCategory.setMinSize(200, 10);
        cbCategory.showingProperty();
        GridPane.setConstraints(cbCategory, 1, 0);
        grid.getChildren().add(cbCategory);

        ComboBox cbLatePolicy = new ComboBox(FXCollections.observableArrayList("Default",new Separator(), "New"));
        cbLatePolicy.setMinSize(200, 10);
        cbLatePolicy.showingProperty();
        GridPane.setConstraints(cbLatePolicy, 1, 6);
        grid.getChildren().add(cbLatePolicy);

        ComboBox cbGradingScheme = new ComboBox(FXCollections.observableArrayList("Default",new Separator(), "New"));
        cbGradingScheme.setMinSize(200, 10);
        cbGradingScheme.showingProperty();
        GridPane.setConstraints(cbGradingScheme, 1, 5);
        grid.getChildren().add(cbGradingScheme);

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

        //Defining the Name text fields
        final TextField dueDate = new TextField();
        dueDate.setPromptText("");
        dueDate.getText();
        GridPane.setConstraints(dueDate, 1, 3);
        grid.getChildren().add(dueDate);

        //Defining the  text field
        final TextField points = new TextField();
        points.setPromptText("");
        points.getText();
        GridPane.setConstraints(points, 1, 4);
        grid.getChildren().add(points);


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
        Label labelDueDate = new Label("Due Date:");
        GridPane.setConstraints(labelDueDate, 0, 3);
        grid.getChildren().add(labelDueDate);
        Label labelPoints = new Label("Total Points:");
        GridPane.setConstraints(labelPoints, 0, 4);
        grid.getChildren().add(labelPoints);
        Label labelGradingScheme = new Label("Grading Scheme:");
        GridPane.setConstraints(labelGradingScheme, 0, 5);
        grid.getChildren().add(labelGradingScheme);
        Label labelLatePolicy = new Label("Late Policy:");
        GridPane.setConstraints(labelLatePolicy, 0, 6);
        grid.getChildren().add(labelLatePolicy);


        Button buttonElectHandIn = new Button("Electronic Hand In");
        GridPane.setConstraints(buttonElectHandIn, 1, 7);
        grid.getChildren().add(buttonElectHandIn);


        /*// Create Grading Scheme popup window
        Button buttonSetLatePolicy = new Button("Late Policy");
        GridPane.setConstraints(buttonSetLatePolicy, 1, 6);
        grid.getChildren().add(buttonSetLatePolicy);*/

        /*Button buttonCreateScheme = new Button("Set Grading Scheme");
        GridPane.setConstraints(buttonCreateScheme, 1, 5);
        grid.getChildren().add(buttonCreateScheme);  */


        //buttonCreateScheme.setOnAction(new EventHandler<ActionEvent>() {
        cbGradingScheme.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                StackPane stackPaneGradingScheme = new StackPane();

                // Table
                TableView tableGradingScheme = new TableView();
                tableGradingScheme.setEditable(true);

                TableColumn colSymbol = new TableColumn("Symbol");
                colSymbol.setMinWidth(100);
                TableColumn colPercentageRange = new TableColumn("Percentage Range");

                tableGradingScheme.getColumns().addAll(colSymbol, colPercentageRange);

                // Scheme Name
                Label labelSchemeName = new Label("Scheme Name:");
                final TextField schemeName = new TextField();
                schemeName.setPromptText("");


                // Add button
                final Button buttonAdd = new Button("Add");
                buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        System.out.println("Add to table");
                    }
                });

                // Create button
                Button buttonCreate = new Button("Create");
                buttonCreate.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        System.out.println("Create pressed for Grading Scheme");
                    }
                });

                GridPane gridGradingScheme = new GridPane();
                gridGradingScheme.setPadding(new Insets(10, 10, 10, 10));
                gridGradingScheme.setVgap(5);
                gridGradingScheme.setHgap(5);

                GridPane.setConstraints(tableGradingScheme, 0, 0);
                gridGradingScheme.getChildren().add(tableGradingScheme);

                GridPane.setConstraints(buttonAdd, 0, 1);
                gridGradingScheme.getChildren().add(buttonAdd);

                GridPane.setConstraints(labelSchemeName, 0, 2);
                gridGradingScheme.getChildren().add(labelSchemeName);

                GridPane.setConstraints(schemeName, 1, 2);
                gridGradingScheme.getChildren().add(schemeName);

                GridPane.setConstraints(buttonCreate, 1, 4);
                gridGradingScheme.getChildren().add(buttonCreate);

                stackPaneGradingScheme.getChildren().add(gridGradingScheme);

                Scene thirdScene = new Scene(stackPaneGradingScheme, 300, 250);

                Stage thirdStage = new Stage();
                thirdStage.setTitle("Grading Scheme");
                thirdStage.setScene(thirdScene);

                //Set position of second window, related to primary window.
                thirdStage.setX(primaryStage.getX()+10);
                thirdStage.setY(primaryStage.getY()+10);

                thirdStage.show();

            }
        });

        HBox hButtonBox = new HBox();
        hButtonBox.getChildren().addAll(addBtn, cancelBtn);
        hButtonBox.setAlignment(Pos.CENTER);
        hButtonBox.setSpacing(50);

        GridPane.setConstraints(hButtonBox, 1,11);
        grid.getChildren().add(hButtonBox);
        root.getChildren().add(grid);
        Scene scene = new Scene(root, 350, 400);

        primaryStage.setTitle("Create Assignment");
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
