package controller.gradebook;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.stage.Stage;
import model.gradebook.Gradebook;
import model.spreadsheet.SisDB;
import model.spreadsheet.CourseInfo;
import model.spreadsheet.SpreadsheetCourse;
import model.users.Student;
import model.users.StudentDB;

/**
 * Controller for downloading a roster to a SpreadsheetCourse.
 * A user chooses from a list of SIS classes they have access to
 * and selects a Gradebook SpreadsheetCourse to download to.
 * Does error checking to make sure a user has a Gradebook
 * SpreadsheetCourse selected and if a user has multiple
 * SIS classes selected a dialog appears to make sure
 * they want to combine rosters.
 * @author jamesfazio
 *
 */
public class DownloadRosterController
{
    /** Instance of the Gradebook */
    private Gradebook gradebook;
    
    /** CourseDB that holds SIS courses */
    private SisDB courseDB;
    
    /** StudentDB that holds SIS students */
    private StudentDB studentDB;
    
    /** List of SIS classes */
    private List<CheckBoxTreeItem<String>> selectedSISClasses;
    
    /** List of created Courses */
    private List<SpreadsheetCourse> createdCourses;
    
    /** List of available SIS classes */
    private List<CourseInfo> availableSISClasses;
    
    @FXML 
    /** The root of the layout */
    private Parent root;
    
    @FXML
    /** The TreeView that holds SIS classes */
    private TreeView<String> classTreeView;
    
    @FXML
    /** Download SIS roster button */
    private Button downloadButton;
    
    @FXML 
    /** View that holds created courses */
    private ListView<String> listView;
    
    
    @FXML
    /**
     * Called by FXML when view is loaded. Grabs
     * SIS courses and Gradebook courses and initializes views.
     */
    private void initialize()
    {
        gradebook = Gradebook.getInstance();
        courseDB = SisDB.getInstance();
        studentDB = StudentDB.getInstance();
        selectedSISClasses = new ArrayList<CheckBoxTreeItem<String>>();
        availableSISClasses = new ArrayList<CourseInfo>();
        loadTreeItems();
        loadListView();
    }
    
    @FXML
    /**
     * Called when a user presses the download button.
     * Takes the student rosters from the selected SIS classes
     * and puts them into the selected Gradebook class.
     */
    public void downloadRoster()
    {
        List<Student> students = new ArrayList<Student>();
        SpreadsheetCourse selectedCourse;
        DialogResponse choice;
        int selectedSISCount = 0;
        
        // Look for selected SISClasses
        for (int i = 0; i < selectedSISClasses.size(); i++)
        {
            if (selectedSISClasses.get(i).selectedProperty().getValue())
            {
                students.addAll(studentDB.getStudentsForClass(availableSISClasses.get(i)));
                selectedSISCount ++;
            }
        }
        
        // If the user selected more than one SIS course
        // prompt them with a warning
        if (selectedSISCount > 1) {
            choice = Dialogs.showConfirmDialog(getStage(),
                    "Do you wish to merge multiple SIS classes into"
                    + "one Spreadsheet Course?", "Confirm Dialog", "Multiple "
                            + "SIS classes chosen");
            
            // If the user decides they don't want multiple SIS
            // rosters in one Gradebook class, return
            if (choice != DialogResponse.YES)
                return;
        }
        
        // The user selected a Gradebook Course
        if (!listView.getSelectionModel().isEmpty())
            selectedCourse = createdCourses.get(listView.getSelectionModel()
                .getSelectedIndex());
        // The user has not selected a Gradebook Course
        else {
            Dialogs.showErrorDialog(getStage(), "You must select a course to download "
                    + "the roster into", "Error", "Choose a course");
            return;
        }
        
        selectedCourse.addStudents(students);
        
        getStage().close();
    }
    
    /**
     * Sets up the listview with SpreadsheetCourses in the Gradebook
     */
    private void loadListView()
    {
        ObservableList<String> createdCoursesObs = FXCollections.observableArrayList();
        createdCourses = gradebook.getCourses();
        
        for(SpreadsheetCourse currentCourse: createdCourses)
        {
            createdCoursesObs.add(currentCourse.getCourseInfo().getNumber()
                + "-" + currentCourse.getCourseInfo().getSection());
        }
        
        listView.setItems(createdCoursesObs);
    }
    
    /**
     * Sets up the TreeView with courses in SIS
     */
    private void loadTreeItems()
    {
        CheckBoxTreeItem<String> root = new CheckBoxTreeItem<String>("Select Classes");
        CheckBoxTreeItem<String> clazz;
        CheckBoxTreeItem<String> section;
        
        List<CourseInfo> courses;
        
        for (String courseNumber : courseDB.getCourseNumbers())
        {
            clazz = new CheckBoxTreeItem<String>(courseNumber);
            courses = courseDB.getCoursesByNumber(courseNumber);
            
            
            for (CourseInfo info : courses)
            {
                section = new CheckBoxTreeItem<String>("Section "
                        + info.getSection());
                clazz.getChildren().add(section);
                clazz.setExpanded(true);
                
                availableSISClasses.add(info);
                selectedSISClasses.add(section);
            }
            
            root.getChildren().add(clazz);
        }
        
        root.setExpanded(true);
        classTreeView.setRoot(root);
        classTreeView.setShowRoot(true);
        classTreeView.setCellFactory(CheckBoxTreeCell.<String>forTreeView());
        
    }
    
    /**
     * Get the stage of this view
     * @return the stage of this view
     */
    private Stage getStage() {
        return (Stage) root.getScene().getWindow();
    }
}
