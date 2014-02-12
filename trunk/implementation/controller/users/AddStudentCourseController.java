package controller.users;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import model.gradebook.Gradebook;
import model.spreadsheet.CourseInfo;
import model.spreadsheet.SpreadsheetCourse;
import model.users.Student;

import java.util.ArrayList;

/****
 *
 * This class represents the communication between
 * the add student course dialog and the actual Student
 * object.  
 *
 * @author      Kevin Feutz     (kfeutz@calpoly.edu)
 */

public class AddStudentCourseController {
    
    /** The list of all courses currently taught by the instructor */
    @FXML
    private ListView<String> viewCourseList;

    @FXML
    private TableColumn<CourseInfo, String> courseColumn;

    private ObservableList<String> courseData;

    private static ArrayList<SpreadsheetCourse> courseList;

    private static ArrayList<SpreadsheetCourse> studentCourseList = new ArrayList<>();
    /**
     * Contructor for this class
     */
    public AddStudentCourseController() {
    }

    @FXML
    private void initialize() {
        if(studentCourseList != null)
        {
            studentCourseList.clear();
        }
        viewCourseList.setItems(this.getCourseInfoList());
    }

    /**
     * This method retrieves all of the courses from the main gradebook
     *
     * @return  ObservableList  List of all courses in the main gradebook.
     */
    protected ObservableList<String> getCourseInfoList()
    {
        courseData = FXCollections.observableArrayList();
        courseList = Gradebook.getInstance().getCourses();
        for(SpreadsheetCourse currentCourse: courseList)
        {
            courseData.add(currentCourse.getCourseInfo().getCourseName()
                + "-" + currentCourse.getCourseInfo().getNumber());
        }
        return courseData;
    }

    /**
     * This method is prompted when the user selects the 
     * "Select" button on the add student course dialog.
     * This method adds the selected course to the student's
     * list of enrolled courses
     */ 
    @FXML
    private void handleSelectButton() {
        /*
         * Call a method in student.java model
         * to add a course to a student
         */
        int indexSelected = viewCourseList.getSelectionModel().getSelectedIndex();
        AddStudentController.addCourseToDialog(courseList.get(indexSelected).getCourseInfo());
        studentCourseList.add(courseList.get(indexSelected));

        Stage stage = (Stage) viewCourseList.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancelButton() {
        Stage stage = (Stage) viewCourseList.getScene().getWindow();
        stage.close();
    }

    /**
     * This method returns the course list to be added to this student
     *
     * @return  ArrayList   The collection of SpreadsheetCourses to be added
     *                      to this student
     */
    protected static ArrayList<SpreadsheetCourse> getCourseList()
    {
        return studentCourseList;
    }
}
