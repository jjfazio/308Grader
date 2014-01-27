package view;

import java.io.IOException;

import model.gradebook.Gradebook;
import model.spreadsheet.CourseInfo;
import model.spreadsheet.SpreadsheetCourse;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Starting point for the application. Constructs a gradebook, adds
 * a few test courses and loads the primary stage
 * @author jamesfazio
 *
 */
public class Main extends Application
{
    private Stage primaryStage;
    private BorderPane rootLayout;
    private Gradebook gradebook;

    @Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("GraderTool");
		
		gradebook = Gradebook.getInstance();
		
		if (gradebook.getCourses() == null) {
			SpreadsheetCourse course = new SpreadsheetCourse();
			course.setCourseInfo(new CourseInfo("308", "adfad", "01", "asdf"));
			
			SpreadsheetCourse course2 = new SpreadsheetCourse();
			course2.setCourseInfo(new CourseInfo("309", "fasdfadf", "02", "adfasdf"));
			
			gradebook.addSpreadsheetCourse(course);
			gradebook.addSpreadsheetCourse(course2);
			gradebook.setCurrentCourse(course);
		}
		
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
       rootLayout.setCenter(tabs);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
