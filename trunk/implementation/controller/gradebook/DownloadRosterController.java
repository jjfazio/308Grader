package controller.gradebook;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import model.gradebook.Gradebook;
import model.spreadsheet.CourseDB;
import model.spreadsheet.CourseInfo;

public class DownloadRosterController
{
    private Gradebook gradebook;
    private CourseDB courseDB;
    
    @FXML
    private TreeView<String> classTreeView;
    
    @FXML
    private Button downloadButton;
    
    @FXML
    private CheckBox combineCheckBox;
    
    @FXML
    private void initialize()
    {
        gradebook = Gradebook.getInstance();
        courseDB = CourseDB.getInstance();
        loadTreeItems();
    }
    
    @FXML
    public void downloadRoster()
    {
        
    }
    
    private void loadTreeItems()
    {
        TreeItem<String> root = new TreeItem<String>("Select Classes");
        TreeItem item;
        List<CourseInfo> courses;
        
        for (String courseNumber : courseDB.getCourseNumbers())
        {
            item = new TreeItem<String>(courseNumber);
            courses = courseDB.getCoursesByNumber(courseNumber);
            
            for (CourseInfo info : courses)
            {
                item.getChildren().add(new TreeItem<CheckBox>(new CheckBox("Section " +info.getSection())));
                item.setExpanded(true);
            }
            
            root.getChildren().add(item);
        }
        root.setExpanded(true);
        classTreeView.setRoot(root);
        classTreeView.setShowRoot(true);
    }

}