package spreadsheet;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import spreadsheet.MyClassTabs;
import spreadsheet.MyMenuBar;
import spreadsheet.ViewGraphsAndAdjustCurves;

/**
 * A simple table that uses cell factories to add a control to a table
 * column and to enable editing of first/last name and email.
 *
 * @see javafx.scene.control.TableCell
 * @see javafx.scene.control.TableColumn
 * @see javafx.scene.control.TablePosition
 * @see javafx.scene.control.TableRow
 * @see javafx.scene.control.TableView
 */
public class Main extends Application {

    private void init(Stage primaryStage) {
       MyMenuBar menuBar = new MyMenuBar();
       MyClassTabs tabs = new MyClassTabs();
       VBox vbox = new VBox();
       HBox hbox = new HBox();
       Button graphButton = new Button("View Graphs");
       Button postButton = new Button("Post Changes");
       
       graphButton.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
             new ViewGraphsAndAdjustCurves();
          }
       });
       
       postButton.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              new SubmitGrades();
          }
       });
       
       hbox.setSpacing(25);
       hbox.setAlignment(Pos.CENTER);
       hbox.getChildren().addAll(graphButton, postButton);
       
       vbox.setSpacing(25);
       
       primaryStage.setTitle("Grader Tool");
       vbox.getChildren().addAll(menuBar.getMenuBar(), tabs.getBorderPane(),
             hbox);
       
       primaryStage.setScene(new Scene(vbox));
        
    } 

    @Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }
    public static void main(String[] args) { launch(args); }
}

