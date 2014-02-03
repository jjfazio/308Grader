package controller.gradebook;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import model.gradebook.Gradebook;

public class DownloadRosterController
{
    private Gradebook gradebook;
    
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
        loadTreeItems();
    }
    
    @FXML
    public void downloadRoster()
    {
        
    }
    
    private void loadTreeItems()
    {
        TreeItem<String> root = new TreeItem<String>("Select Classes");
        TreeItem classes308 = new TreeItem<String>("308");
        TreeItem classes309 = new TreeItem<String>("309");
        TreeItem classes365 = new TreeItem<String>("365");
        
        classes308.getChildren().add(new TreeItem<CheckBox>(new CheckBox("Section 1")));
        classes308.getChildren().add(new TreeItem<CheckBox>(new CheckBox("Section 2")));
        
        classes309.getChildren().add(new TreeItem<CheckBox>(new CheckBox("Section 1")));
        classes309.getChildren().add(new TreeItem<CheckBox>(new CheckBox("Section 2")));
        
        classes365.getChildren().add(new TreeItem<CheckBox>(new CheckBox("Section 1")));
        
        
        classes308.setExpanded(true);
        classes309.setExpanded(true);
        classes365.setExpanded(true);
        
        
        root.getChildren().addAll(classes308, classes309, classes365);
        root.setExpanded(true);
        classTreeView.setRoot(root);
        classTreeView.setShowRoot(true);
    }

}
