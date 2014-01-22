


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
 * @author kevinbackers
 */
public class DownloadRoster extends Application {
   
   public DownloadRoster() {
      start(new Stage());
   }
    
    Stage primaryStage;
    @Override
    public void start(final Stage stage) {
        primaryStage = stage;
        Button btn = new Button("OK");
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("OK pressed");
                
                StackPane stackPaneGradingScheme = new StackPane();

                // Add button
                final Button buttonAdd = new Button("Add");
                buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        System.out.println("Add to table");
                    }
                });
                
                // Download button
                Button buttonDownload = new Button("Download");
                buttonDownload.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        System.out.println("Download Pressed");
                    }
                });
                
                CheckBox cbCombineSections = new CheckBox("Combine sections in single spreadsheet");
                cbCombineSections.setIndeterminate(false);
                
                GridPane gridGradingScheme = new GridPane();
                gridGradingScheme.setPadding(new Insets(10, 10, 10, 10));
                gridGradingScheme.setVgap(5);
                gridGradingScheme.setHgap(5);
                
                // Add checkbox and button to grid 
                GridPane.setConstraints(buttonDownload, 0, 2);
                gridGradingScheme.getChildren().add(buttonDownload);
                GridPane.setConstraints(cbCombineSections, 0, 1);
                gridGradingScheme.getChildren().add(cbCombineSections);
                
                
                
                CheckBoxTreeItem<String> rootItem = new CheckBoxTreeItem<String> ("CPE 101");
                //CheckBoxTreeItem<String> treeItemCPE102 = new CheckBoxTreeItem<String> ("CPE 102");
                rootItem.setExpanded(true);
                for (int i = 1; i < 5; i++) {
                    CheckBoxTreeItem<String> item = new CheckBoxTreeItem<String> ("Section" + i);            
                    rootItem.getChildren().add(item);
                }  
                
                TreeView<String> tree = new TreeView<String> (rootItem);
                
                
                tree.setEditable(true);
                tree.setCellFactory(CheckBoxTreeCell.<String>forTreeView());
                
                GridPane.setConstraints(tree, 0, 0);
                gridGradingScheme.getChildren().add(tree);
   
                stackPaneGradingScheme.getChildren().add(gridGradingScheme);
                
                Scene thirdScene = new Scene(stackPaneGradingScheme, 300, 250);
 
                Stage thirdStage = new Stage();
                thirdStage.setTitle("Download Roster");
                thirdStage.setScene(thirdScene);
                 
                //Set position of second window, related to primary window.
                thirdStage.setX(primaryStage.getX()+10);
                thirdStage.setY(primaryStage.getY()+10);
  
                thirdStage.show();
                
            }
        });
        
        StackPane root = new StackPane();       
        
        //Creating a GridPane container
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        
        //Defining the text fields
        final TextField usernameText = new TextField();
        usernameText.setPromptText("");
        usernameText.setPrefColumnCount(10);
        usernameText.getText();
        GridPane.setConstraints(usernameText, 1, 0);
        grid.getChildren().add(usernameText);
        
        //Defining the Last Name text field
        final TextField pwText = new TextField();
        pwText.setPromptText("");
        GridPane.setConstraints(pwText, 1, 1);
        grid.getChildren().add(pwText);
        
        //Defining the labels
        Label labelUsername = new Label("Username:");
        GridPane.setConstraints(labelUsername, 0, 0);
        grid.getChildren().add(labelUsername);
        Label labelPassword = new Label("Password:");
        GridPane.setConstraints(labelPassword, 0, 1);
        grid.getChildren().add(labelPassword);
        
        
       
        // Putting it all in the window
        GridPane.setConstraints(btn, 1,11);
        grid.getChildren().add(btn);
        root.getChildren().add(grid);
        Scene scene = new Scene(root, 250, 150);
        
        primaryStage.setTitle("Instructor Login");
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
