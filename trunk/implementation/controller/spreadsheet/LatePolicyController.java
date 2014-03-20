package controller.spreadsheet;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.assignments_categories.Assignment;
import model.gradebook.Gradebook;
import model.spreadsheet.SpreadsheetCourse;
import model.users.Student;

/**
 * Controller for Applying a Late Policy on a Student's Assignment
 * @author jamesfazio
 *
 */
public class LatePolicyController
{
    /** The current SpreadsheetCourse */
    private SpreadsheetCourse course;
    
    /** Text for when assign is due */
    @FXML
    private Text dueDateText;
    
    /** Text for amount of grace days left */
    @FXML
    private Text graceDayText;
    
    /** Text for amount of percent that will be decayed */
    @FXML 
    private Text percentDecayText;
    
    /** Text for the new grade the student will receive */
    @FXML
    private Text newGradeText;
    
    /** Amount of days the assign is late */
    @FXML
    private TextField daysLate;
    
    /**
     * Set the current course
     */
    @FXML
    private void initialize()
    {
        course = Gradebook.getInstance().getCurrentCourse();
    }
    
    /**
     * Sets up the view to display info based on the current assign and 
     * current student
     * @param student The student row clicked on
     * @param assign The assignment col clicked on
     */
    public void setUp(Student student, Assignment assign)
    {
        GregorianCalendar date = assign.getDueDate();
        
        dueDateText.setText(assign.getName() + " was due on " +
                date.get(Calendar.MONTH) + "/" + date.get(Calendar.DATE) + "/"
                + date.get(Calendar.YEAR));
        
        if (course.getLatePolicy().getGraceDaysEnabled())
        {
            percentDecayText.setVisible(false);
        }
        else
        {
            graceDayText.setVisible(false);
        }
        
    }

}
