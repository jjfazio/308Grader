package controller.users;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.spreadsheet.CourseInfo;

public class AddStudentCourseController {
    /*
     * The list of all courses currently taught by the instructor
     */
    @FXML
    private ListView<CourseInfo> coursesList;

    public AddStudentCourseController() {
    }

    @FXML
    private void handleSelectButton() {
        //call a method in student.java model to add a course to a student
    }
}