
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
public class AddClass extends Application {
    
    Stage primaryStage;
    
    public AddClass(){
       start(new Stage());
   }
    
    @Override
    public void start(final Stage stage) {
        primaryStage = stage;
        Button btn = new Button("Create");
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Create pressed");
                
            }
        });
        
        StackPane root = new StackPane();       
        
        //Creating a GridPane container
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        
        //Defining the text fields
        final TextField name = new TextField();
        name.setPromptText("");
        name.setPrefColumnCount(10);
        name.getText();
        GridPane.setConstraints(name, 1, 0);
        grid.getChildren().add(name);
        
        //Defining the Last Name text field
        final TextField lastName = new TextField();
        lastName.setPromptText("");
        GridPane.setConstraints(lastName, 1, 1);
        grid.getChildren().add(lastName);
        
        //Defining the Comment text field
        final TextField comment = new TextField();
        comment.setPrefColumnCount(15);
        comment.setPromptText("");
        GridPane.setConstraints(comment, 1, 2);
        grid.getChildren().add(comment);
        
        //Defining the labels
        Label labelName = new Label("Name:");
        GridPane.setConstraints(labelName, 0, 0);
        grid.getChildren().add(labelName);
        Label labelSection = new Label("Section:");
        GridPane.setConstraints(labelSection, 0, 1);
        grid.getChildren().add(labelSection);
        Label labelTerm = new Label("Term:");
        GridPane.setConstraints(labelTerm, 0, 2);
        grid.getChildren().add(labelTerm);
        Label labelGradingScheme = new Label("Grading Scheme:");
        GridPane.setConstraints(labelGradingScheme, 0, 3);
        grid.getChildren().add(labelGradingScheme);
        Label labelTA = new Label("Teacher's Assistant:");
        GridPane.setConstraints(labelTA, 0, 5);
        grid.getChildren().add(labelTA);
        Label labelLatePolicy = new Label("Late Policy:");
        GridPane.setConstraints(labelLatePolicy, 0, 7);
        grid.getChildren().add(labelLatePolicy);
        
        // Defining the late policy section
        ToggleGroup groupLatePolicy = new ToggleGroup();
        RadioButton buttonNone = new RadioButton("None");
        buttonNone.setToggleGroup(groupLatePolicy);
        buttonNone.setSelected(true);
        GridPane.setConstraints(buttonNone, 1, 7);
        grid.getChildren().add(buttonNone);
        
        RadioButton buttonDecay = new RadioButton("Decay");
        buttonDecay.setToggleGroup(groupLatePolicy);
        GridPane.setConstraints(buttonDecay, 1, 8);
        grid.getChildren().add(buttonDecay);
        
        RadioButton buttonAllotment = new RadioButton("Allotment");
        buttonAllotment.setToggleGroup(groupLatePolicy);
        GridPane.setConstraints(buttonAllotment, 1, 9);
        grid.getChildren().add(buttonAllotment);
        
        ComboBox cbGradingScheme = new ComboBox(FXCollections.observableArrayList(new Separator(), "Create"));
        //cbGradingScheme.getItems().addAll("Create...");
        GridPane.setConstraints(cbGradingScheme, 1, 3);
        grid.getChildren().add(cbGradingScheme);
        
        
        ComboBox cbTeachersAssistant = new ComboBox(FXCollections.observableArrayList(new Separator(), "Create"));
        cbTeachersAssistant.showingProperty();
        GridPane.setConstraints(cbTeachersAssistant, 1, 5);
        grid.getChildren().add(cbTeachersAssistant); 
        
        
        // Putting it all in the window
        GridPane.setConstraints(btn, 1,11);
        grid.getChildren().add(btn);
        root.getChildren().add(grid);
        Scene scene = new Scene(root, 350, 300);
        
        
        // Create TA popup window 
        Button buttonCreateTA = new Button("Create TA");
        GridPane.setConstraints(buttonCreateTA, 1, 6);
        grid.getChildren().add(buttonCreateTA);  

        buttonCreateTA.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                 
                Label labelFirstName = new Label("First Name:");
                Label labelLastName = new Label("Last Name:");
                Label labelID = new Label("ID:");
                Label labelAllowedActions = new Label("Allowed Actions:");
                 
                StackPane stackPaneTA = new StackPane();
                stackPaneTA.getChildren().add(labelFirstName);
                stackPaneTA.getChildren().add(labelLastName);
                stackPaneTA.getChildren().add(labelID);
                stackPaneTA.getChildren().add(labelAllowedActions); 
                
                // Text Fields 
                final TextField firstName = new TextField();
                firstName.setPromptText("");
                final TextField lastName = new TextField();
                lastName.setPromptText("");
                final TextField taID = new TextField();
                taID.setPromptText("");
                
                // Checkboxes for allowed actions
                CheckBox cbCreateAssignments = new CheckBox("Create Assignments");
                cbCreateAssignments.setIndeterminate(false);
                CheckBox cbEnterGrades = new CheckBox("Enter Grades");
                cbEnterGrades.setIndeterminate(false);
                CheckBox cbPostGrades = new CheckBox("Post Grades");
                cbPostGrades.setIndeterminate(false);
                CheckBox cbEditRoster = new CheckBox("Edit Roster");
                cbEditRoster.setIndeterminate(false);
                        
                // Create button
                Button buttonCreate = new Button("Create");
                buttonCreate.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        System.out.println("Create pressed for TA");
                    }
                });
                
                
                GridPane gridTA = new GridPane();
                gridTA.setPadding(new Insets(10, 10, 10, 10));
                gridTA.setVgap(5);
                gridTA.setHgap(5);
                
                // Add labels to grid
                GridPane.setConstraints(labelFirstName, 0, 0);
                gridTA.getChildren().add(labelFirstName);
                GridPane.setConstraints(labelLastName, 0, 1);
                gridTA.getChildren().add(labelLastName);
                GridPane.setConstraints(labelID, 0, 2);
                gridTA.getChildren().add(labelID);
                GridPane.setConstraints(labelAllowedActions, 0, 3);
                gridTA.getChildren().add(labelAllowedActions);
                
                // Add textfields to grid
                GridPane.setConstraints(firstName, 1, 0);
                gridTA.getChildren().add(firstName);
                GridPane.setConstraints(lastName, 1, 1);
                gridTA.getChildren().add(lastName);
                GridPane.setConstraints(taID, 1, 2);
                gridTA.getChildren().add(taID);
                
                // Add check boxes to grid
                GridPane.setConstraints(cbCreateAssignments, 1, 3);
                gridTA.getChildren().add(cbCreateAssignments);
                GridPane.setConstraints(cbEnterGrades, 1, 4);
                gridTA.getChildren().add(cbEnterGrades);
                GridPane.setConstraints(cbPostGrades, 1, 5);
                gridTA.getChildren().add(cbPostGrades);
                GridPane.setConstraints(cbEditRoster, 1, 6);
                gridTA.getChildren().add(cbEditRoster);
                
                // Add button to grid
                GridPane.setConstraints(buttonCreate, 1, 7);
                gridTA.getChildren().add(buttonCreate);
                
                
                stackPaneTA.getChildren().add(gridTA);
                
                Scene secondScene = new Scene(stackPaneTA, 300, 250);
 
                Stage secondStage = new Stage();
                secondStage.setTitle("Teacher's Assistant");
                secondStage.setScene(secondScene);
                 
                //Set position of second window, related to primary window.
                secondStage.setX(primaryStage.getX()+10);
                secondStage.setY(primaryStage.getY()+10);
  
                secondStage.show();
            }
        });
        
        // Create Grading Scheme popup window 
        Button buttonCreateScheme = new Button("Create Grading Scheme");
        GridPane.setConstraints(buttonCreateScheme, 1, 4);
        grid.getChildren().add(buttonCreateScheme);  

        buttonCreateScheme.setOnAction(new EventHandler<ActionEvent>() {

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
