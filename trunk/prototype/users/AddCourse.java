package users;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Kevin
 */
public class AddCourse extends Application {

    public AddCourse(){
        start(new Stage());
    }

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Add Course");

        //Creating the main vertical frame
        VBox mainBox = new VBox(15);

        //Create the Courses vertical box
        VBox courses = new VBox();
        courses.setAlignment(Pos.CENTER);

        //Add label to the courses box
        HBox courseLabelHold = new HBox();
        Label courseLabel = new Label("Courses:");
        courseLabelHold.getChildren().add(courseLabel);
        courses.getChildren().add(courseLabelHold);

        //Adds the box for the list of courses
        ListView<String> courseList = new ListView<String>();
        courseList.setPrefWidth(300);
        courseList.setPrefHeight(150);
        courses.getChildren().add(courseList);

        mainBox.getChildren().add(courses);

        //Creating horizontal row for buttons
        HBox bottomBox = new HBox();

        //Create the actual Confirm Add and Cancel buttons
        Button confirmAddBtn = new Button();
        Button cancelBtn = new Button();
        confirmAddBtn.setText("Select");
        cancelBtn.setText("Cancel");

        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });

        //Add the buttons to the bottom box
        VBox leftBottomBox = new VBox();
        VBox rightBottomBox = new VBox();
        leftBottomBox.setPrefWidth(200);
        leftBottomBox.setAlignment(Pos.CENTER);
        leftBottomBox.getChildren().add(confirmAddBtn);
        bottomBox.getChildren().add(leftBottomBox);
        rightBottomBox.setPrefWidth(200);
        rightBottomBox.setAlignment(Pos.CENTER);
        rightBottomBox.getChildren().add(cancelBtn);
        bottomBox.getChildren().add(rightBottomBox);

        mainBox.getChildren().add(bottomBox);

        Scene scene = new Scene(mainBox, 400, 250);
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
