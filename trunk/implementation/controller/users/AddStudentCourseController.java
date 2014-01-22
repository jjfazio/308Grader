package controller.users;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.spreadsheet.CourseInfo;
import model.spreadsheet.SpreadsheetCourse;
import model.users.Student;

/**
 * This class represents the communication between
 * the add student course dialog and the actual Student
 * object.  
 *
 * @author Kevin Feutz
 */

public class AddStudentCourseController {
    
    /**
     * The list of all courses currently taught by the instructor
     */
    @FXML
    private ListView<CourseInfo> coursesList;

    public AddStudentCourseController() {
    }

    /**
     * This method is prompted when the user selects the 
     * "Select" button on the add student course dialog.
     * This method adds the selected course to the student's
     * list of enrolled courses
     */ 
    @FXML
    private void handleSelectButton() {
        //call a method in student.java model to add a course to a student
        Student tempStudent = new Student();
        tempStudent.addCourse(new SpreadsheetCourse());
    }
}
