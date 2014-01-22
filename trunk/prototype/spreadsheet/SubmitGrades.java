
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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author erikowen
 */
public class SubmitGrades extends Application {
    Button cancelButton;
    Button submitButton;
    Stage primaryStage;
    public SubmitGrades() {
        start(new Stage());
    }
    
    public void start(final Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Grade Submission Confirmation");
        primaryStage.setResizable(false);
        
        VBox mainBox = new VBox(15);
        
        initMainBoxContent(mainBox);
        initOnClickListeners();
        
        Scene mainScene = new Scene(mainBox, 500, 300);

        primaryStage.setScene(mainScene);
        mainBox.setAlignment(Pos.TOP_CENTER);
        primaryStage.show();
    }
    
    private void initMainBoxContent(VBox mainBox) {
        mainBox.setPrefSize(500, 300);
        
        Label submissionLabel = new Label("Before Submitting, Please Authenticate with Your\n Cal Poly Username and Password");
        submissionLabel.setTextAlignment(TextAlignment.CENTER);
        submissionLabel.setAlignment(Pos.TOP_CENTER);
        submissionLabel.setFont(new Font(20.0));
        
        HBox userNameLayout = new HBox(5);
        HBox passwordLayout = new HBox(5);
        HBox buttonLayout = new HBox(10);
        
        Label userNameLabel = new Label("Username: ");
        TextField userNameInput = new TextField();
        userNameLayout.getChildren().add(userNameLabel);
        userNameLayout.getChildren().add(userNameInput);
        userNameLayout.setAlignment(Pos.CENTER);
        userNameLayout.setPadding(new Insets(40, 0, 0, 0));
        
        Label passwordLabel = new Label("Password: ");
        TextField passwordInput = new TextField();
        passwordLayout.getChildren().add(passwordLabel);
        passwordLayout.getChildren().add(passwordInput);
        passwordLayout.setAlignment(Pos.CENTER);
        
        this.cancelButton = new Button("Cancel");
        this.submitButton = new Button("Submit Grades");
        buttonLayout.getChildren().add(cancelButton);
        buttonLayout.getChildren().add(submitButton);
        buttonLayout.setAlignment(Pos.BOTTOM_CENTER);
        buttonLayout.setPadding(new Insets(40, 0, 0 , 0));
        
        mainBox.getChildren().add(submissionLabel);
        mainBox.getChildren().add(userNameLayout);
        mainBox.getChildren().add(passwordLayout);
        mainBox.getChildren().add(buttonLayout);
    }
    
    private void initOnClickListeners() {
        this.cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
    }
}
