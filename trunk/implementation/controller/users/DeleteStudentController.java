package controller.users;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.spreadsheet.SpreadsheetCourse;
import model.users.Student;

import java.util.ArrayList;

/****
 *
 * Class DeleteStudentController represents the interaction
 * between the delete student dialog and the various methods
 * needed in the model package.  Class DeleteStudentController
 * contains all fields similar to the Student class, each of
 * which correspond to an entry field in the view package
 *
 * @author      Kevin Feutz     (kfeutz@calpoly.edu)
 *
 */
public class DeleteStudentController {
    @FXML
    private Label studentNameLabel;
    /** Contains the view of the courses for the display */
    @FXML
    private ListView<CheckBox> viewCourseList;
    /** Holds the student to delete */
    private Student studentToDelete;
    /** Holds the list of spreadsheet courses */
    private ArrayList<SpreadsheetCourse> courseList;

    private ObservableList<CheckBox> courseCheckBoxes;

    protected void initData(Student studentToDelete) {
        this.studentToDelete = studentToDelete;
        this.courseList = new ArrayList<SpreadsheetCourse>();
        this.courseList.addAll(studentToDelete.getCoursesEnrolled());
        for(SpreadsheetCourse currentCourse : this.courseList) {
            courseCheckBoxes.add(new CheckBox(currentCourse.getCourseInfo().getNumber()
                    + "-" + currentCourse.getCourseInfo().getSection()));
            viewCourseList.setItems(courseCheckBoxes);
        }
        studentNameLabel.setText(this.studentToDelete.getLastName()
            + ", " + this.studentToDelete.getFirstName());
    }

    /**
     * Initializes the delete student screen.
     */
    @FXML
    protected void initialize() {
        courseCheckBoxes = FXCollections.observableArrayList();
        viewCourseList.setItems(courseCheckBoxes);
    }

    /**
     * Called when the user selects the Delete button
     */
    @FXML
    private void handleDeleteButton() {
        int index = 0;
        ArrayList<SpreadsheetCourse> coursesToRemove = new ArrayList<SpreadsheetCourse>();
        String courseListString = "";
        for(CheckBox currentBox : courseCheckBoxes) {
            if(currentBox.isSelected()) {
                coursesToRemove.add(courseList.get(index));
                courseListString += (courseList.get(index).getCourseInfo().getNumber()
                    + "-" + courseList.get(index).getCourseInfo().getSection());
            }
            index++;
        }
        Dialogs.DialogResponse response = Dialogs.showConfirmDialog(
            (Stage) this.studentNameLabel.getScene().getWindow(),
            "Are you sure you want to delete student " + studentNameLabel.getText()
            + " from the following course(s)?\n   " + courseListString,
            "Delete Student confirmation", "Delete Student");

        if(response == Dialogs.DialogResponse.YES) {
            for(SpreadsheetCourse currentCourse : coursesToRemove) {
                currentCourse.deleteStudent(studentToDelete);
                studentToDelete.removeCourse(currentCourse);
            }
            Stage stage = (Stage) studentNameLabel.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * This method closes the dialog box when the cancel
     * button is selected
     */
    @FXML
    public void handleCancelButton() {
        Stage stage = (Stage) studentNameLabel.getScene().getWindow();
        stage.close();
    }

}
