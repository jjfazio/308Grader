package users;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Kevin
 */
public class DeleteStudentList extends Application {
    
    public DeleteStudentList(){
           start(new Stage());
    }
    
    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Delete Student");
        
        //Creating the main vertical frame
        VBox mainBox = new VBox(15);
        
        //Create the table
        TableView<String> studentView = new TableView<>();
        studentView.setPrefWidth(600);
        TableColumn<String, String> studentName = new TableColumn<>("Student Name");
        studentName.setPrefWidth(150);
        TableColumn<String, String> studentUsername = new TableColumn<>("Student Username");
        studentUsername.setPrefWidth(150);
        TableColumn<String, String> coursesEnrolled = new TableColumn<>("Course(s) Enrolled");
        coursesEnrolled.setPrefWidth(150);
        TableColumn<String, CheckBox> checkBox = new TableColumn<>("Check Here");
        checkBox.setPrefWidth(150);
        
        checkBox.setGraphic(new CheckBox());
        studentView.getColumns().add(studentName);
        studentView.getColumns().add(studentUsername);
        studentView.getColumns().add(coursesEnrolled);
        studentView.getColumns().add(checkBox);
        mainBox.getChildren().add(studentView);
        
                HBox bottomBox = new HBox();

        //Create the actual Confirm Delete and Cancel buttons
        Button confirmDeleteBtn = new Button();
        Button cancelBtn = new Button();
        confirmDeleteBtn.setText("Delete Selected");
        cancelBtn.setText("Cancel");
        
        confirmDeleteBtn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
              new ConfirmDeleteStudent();
           }
        });
        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
              primaryStage.close();
           }
        });
        
        //Add the buttons to the bottom box
        VBox leftBottomBox = new VBox();
        VBox rightBottomBox = new VBox();
        leftBottomBox.setPrefWidth(300);
        leftBottomBox.setAlignment(Pos.CENTER);
        leftBottomBox.getChildren().add(confirmDeleteBtn);
        bottomBox.getChildren().add(leftBottomBox);
        rightBottomBox.setPrefWidth(300);
        rightBottomBox.setAlignment(Pos.CENTER);
        rightBottomBox.getChildren().add(cancelBtn);
        bottomBox.getChildren().add(rightBottomBox);
        
        mainBox.getChildren().add(bottomBox);
        
        Scene scene = new Scene(mainBox, 600, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
