package users;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Kevin
 */
public class ConfirmDeleteStudent extends Application {
    public ConfirmDeleteStudent(){
           start(new Stage());
    }
    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Confirm Delete");
        
        VBox mainBox = new VBox();
        mainBox.setPrefWidth(700);
        HBox courseLabelHold = new HBox(700);
        Text t = new Text();
        t.setFont(new Font(12));
        t.setText("Are you sure you want to permanently delete Student: <empty>; Student Username: <empty>\n"
                + " from Course(s): <empty> ?\n\n");
        
        courseLabelHold.setHgrow(t, Priority.ALWAYS);
        courseLabelHold.getChildren().add(t);
        mainBox.getChildren().add(courseLabelHold);
        
        Button okBtn = new Button();
        okBtn.setText("Ok");
        okBtn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        
        Button cancelBtn = new Button();
        cancelBtn.setText("Cancel");
        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            
        @Override
        public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        
        HBox buttonHolders = new HBox();
        VBox leftBottomBox = new VBox();
        VBox rightBottomBox = new VBox();
        leftBottomBox.setPrefWidth(300);
        leftBottomBox.setAlignment(Pos.CENTER);
        leftBottomBox.getChildren().add(okBtn);
        buttonHolders.getChildren().add(leftBottomBox);
        rightBottomBox.setPrefWidth(300);
        rightBottomBox.setAlignment(Pos.CENTER);
        rightBottomBox.getChildren().add(cancelBtn);
        buttonHolders.getChildren().add(rightBottomBox);
        mainBox.getChildren().add(buttonHolders);
        
        Scene scene = new Scene(mainBox, 600, 100);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
