
package spreadsheet;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author erikowen
 */
public class QuitNotification extends Application{
   private Stage primaryStage;
   private Button cancelButton, saveAndQuitButton, quitWithoutSavingButton;
   
    public QuitNotification() {
        start(new Stage());
    }
    
    public void start(final Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("");
        primaryStage.setResizable(false);
        
        VBox mainBox = new VBox(15);
        
        initQuitBoxContent(mainBox);
        initOnClickListeners();
        
        Scene mainScene = new Scene(mainBox, 400, 150);

        primaryStage.setScene(mainScene);
        mainBox.setAlignment(Pos.TOP_CENTER);
        primaryStage.show();
    }
    
    private void initQuitBoxContent(VBox mainBox) {
        Label quitLabel = new Label("There are unsaved changes.\nAre you sure you want to quit?");
        quitLabel.setTextAlignment(TextAlignment.CENTER);
        quitLabel.setAlignment(Pos.TOP_CENTER);
        quitLabel.setFont(new Font(18.0));
        
        HBox buttonLayout = new HBox(10);
        this.cancelButton = new Button("Cancel");
        this.saveAndQuitButton = new Button("Save and Quit");
        this.quitWithoutSavingButton = new Button("Quit without Saving");
        
        buttonLayout.getChildren().addAll(cancelButton, saveAndQuitButton, quitWithoutSavingButton);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.setPadding(new Insets(25, 0, 0, 0));
        
        mainBox.getChildren().addAll(quitLabel, buttonLayout);
    }
    
    private void initOnClickListeners() {
        this.cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        
        this.saveAndQuitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Save data before quitting
            }
        });
        
        this.quitWithoutSavingButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                
                //Functionality should go here to quit application
                
            }
        });
    }
    
}
