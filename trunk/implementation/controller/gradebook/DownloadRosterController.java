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
import model.spreadsheet.CourseDB;
import model.spreadsheet.CourseInfo;
import model.spreadsheet.SpreadsheetCourse;
import model.users.Student;
import model.users.StudentDB;

public class DownloadRosterController
{
    private Gradebook gradebook;
    private CourseDB courseDB;
    private StudentDB studentDB;
    private ObservableList<String> createdCoursesObs;
    private List<CheckBoxTreeItem<String>> selectedSISClasses;
    private List<SpreadsheetCourse> createdCourses;
    private List<CourseInfo> availableSISClasses;
    
    @FXML 
    private Parent root;
    
    @FXML
    private TreeView<String> classTreeView;
    
    @FXML
    private Button downloadButton;
    
    @FXML 
    private ListView<String> listView;
    
    
    @FXML
    private void initialize()
    {
        gradebook = Gradebook.getInstance();
        courseDB = CourseDB.getInstance();
        studentDB = StudentDB.getInstance();
        selectedSISClasses = new ArrayList<CheckBoxTreeItem<String>>();
        availableSISClasses = new ArrayList<CourseInfo>();
        loadTreeItems();
        loadListView();
    }
    
    @FXML
    public void downloadRoster()
    {
        List<Student> students = new ArrayList<Student>();
        SpreadsheetCourse selectedCourse;
        DialogResponse choice;
        int selectedSISCount = 0;
        
        for (int i = 0; i < selectedSISClasses.size(); i++)
        {
            if (selectedSISClasses.get(i).selectedProperty().getValue())
            {
                students.addAll(studentDB.getStudentsForClass(availableSISClasses.get(i)));
                selectedSISCount ++;
            }
        }
        
        if (selectedSISCount > 1) {
            choice = Dialogs.showConfirmDialog(getStage(),
                    "Do you wish to merge multiple SIS classes into"
                    + "one Spreadsheet Course?", "Confirm Dialog", "Multiple "
                            + "SIS classes chosen");
            
            if (choice != DialogResponse.YES)
                return;
        }
        
        if (!listView.getSelectionModel().isEmpty())
            selectedCourse = createdCourses.get(listView.getSelectionModel()
                .getSelectedIndex());
        else {
            Dialogs.showErrorDialog(getStage(), "You must select a course to download "
                    + "the roster into", "Error", "Choose a course");
            return;
        }
        
        
        selectedCourse.addStudents(students);
    }
    
    private void loadListView()
    {
        createdCoursesObs = FXCollections.observableArrayList();
        createdCourses = Gradebook.getInstance().getCourses();
        
        for(SpreadsheetCourse currentCourse: createdCourses)
        {
            createdCoursesObs.add(currentCourse.getCourseInfo().getNumber()
                + "-" + currentCourse.getCourseInfo().getSection());
        }
        
        listView.setItems(createdCoursesObs);
    }
    
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
    
    private Stage getStage() {
        return (Stage) root.getScene().getWindow();
    }
}
