package controller.gradebook;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.gradebook.Gradebook;
import model.spreadsheet.SpreadsheetCourse;
import model.users.Student;

public class DeleteClassController {
    
    /** The entire table of course from the gradebook to edit */
    @FXML
    private TableView<SpreadsheetCourse> courseTable;

    /** First column of the table, the courses' names */
    @FXML
    private TableColumn<SpreadsheetCourse, String>  courseNameColumn;

    /** First column of the table, the courses' dept's */
    @FXML
    private TableColumn<SpreadsheetCourse, String>  courseDeptNumColumn;

    /** Third column of the table, the courses' sections */
    @FXML
    private TableColumn<SpreadsheetCourse, String>  courseSectionColumn;

    /** Contains the observable list of students */
    private static ObservableList<SpreadsheetCourse> allCourses = FXCollections.observableArrayList();

    /** Holds the instance of the gradebook */
    private Gradebook gradebook;

    public DeleteClassController()
    {
        gradebook = Gradebook.getInstance();
    }
    
    /**
     * Initializes the delete course screen.
     */
    @FXML
    protected void initialize() {
        
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<SpreadsheetCourse, String>("courseName"));
        courseDeptNumColumn.setCellValueFactory(new PropertyValueFactory<SpreadsheetCourse, String>("courseDeptNum"));
        courseSectionColumn.setCellValueFactory(new PropertyValueFactory<SpreadsheetCourse, String>("courseSection"));

        gradebook = Gradebook.getInstance();
        //allCourses.clear();
        
        allCourses.addAll(gradebook.getCourses());
        
        
        courseTable.setItems(allCourses);
    }
    
    /**
     * Called when the user selects the Delete button
     */
    @FXML
    private void handleDeleteButton() {
        int indexSelected = courseTable.getSelectionModel().getSelectedIndex();
        
        gradebook.removeSpreadsheetCourse(allCourses.get(indexSelected));
        
        getStage().close();
        
    }
    
    /**
     * This method closes the dialog box when the cancel
     * button is selected
     */
    @FXML
    public void handleCancelButton() {
        getStage().close();
    }
    
    /**
     * Get the stage of this view
     * @return the stage of this view
     */
    private Stage getStage() {
        return (Stage) courseTable.getScene().getWindow();
    }

}
