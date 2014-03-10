package view;

import java.util.Observer;

import scripts.StudentDBScript;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.exception.BadDataException;
import model.exception.CourseDataException;
import model.gradebook.Gradebook;
import model.spreadsheet.SisDB;
import model.spreadsheet.CourseInfo;
import model.spreadsheet.GradingScheme;
import model.spreadsheet.SpreadsheetCourse;
import model.users.StudentDB;

/**
 * Starting point for the application. For now constructs a gradebook
 * and adds some test data for displaying purposes.
 * @author jamesfazio
 *
 */
public class Main extends Application
{
    /**
     * The main stage of the application.
     */
    private Stage primaryStage;
    
    /**
     * The root layout of the application.
     */
    private BorderPane rootLayout;
    
    /**
     * A reference to the current gradebook.
     */
    private Gradebook gradebook;

    /**
     * Sets up a Gradebook with two courses, sets the current course
     * and displays the main page.
     */
    @Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("GraderTool");
		
		//for jar
		StudentDBScript.createDBFile();
		
		
		gradebook = Gradebook.getInstance();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/file/MenuBar.fxml"));
		rootLayout = (BorderPane) ViewUtility.loadView(loader);
		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		showTabs();
	}
    
    /**
     * Display the tabs that represent SpreadsheetCourses
     * the current user can access.
     */
    public void showTabs() {
       // Load the fxml file and set into the center of the main layout
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/spreadsheet/Tabs.fxml"));
       AnchorPane tabs = (AnchorPane) ViewUtility.loadView(loader);
       gradebook.addObserver((Observer) loader.getController());
       rootLayout.setCenter(tabs);
    }

    /**
     * Entry point of the application.
     * @param args
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}
