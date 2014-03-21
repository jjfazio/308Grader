package controller.spreadsheet;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.assignments_categories.Assignment;
import model.assignments_categories.Grade;
import model.exception.BadDataException;
import model.gradebook.Gradebook;
import model.spreadsheet.LatePolicy;
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
    
    /** The student whose grade you are modifying */
    private Student student;
    
    /** The assignment for the student you are modifying */
    private Assignment assign;
    
    /** The new grade percentage */
    private Double newGradePercent;
    
    /** Amount of grace days left for the student */
    private Integer graceDaysLeft;
    
    /** The root of the view */
    @FXML
    private Parent root;
    
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
    
    /** Whether new grade has been calculated or not */
    private boolean calculated;
    
    /**
     * Set the current course
     */
    @FXML
    private void initialize()
    {
        course = Gradebook.getInstance().getCurrentCourse();
        calculated = false;
    }
    
    /**
     * Sets up the view to display info based on the current assign and 
     * current student
     * @param student The student row clicked on
     * @param assign The assignment col clicked on
     */
    public void setUp(Student student, Assignment assign)
    {
        this.student = student;
        this.assign = assign;
        
        // If the student doesn't have a grade yet
        if (student.getGrades().get(assign.getID()) == null)
        {
            Dialogs.showErrorDialog((Stage) root.getScene().getWindow(), "Must have entered a grade");
            cancel();
        }
        else
        {
            GregorianCalendar date = assign.getDueDate();
            LatePolicy policy = course.getLatePolicy();

            dueDateText.setText(assign.getName() + " was due on " +
                    date.get(Calendar.MONTH) + "/" + date.get(Calendar.DATE) + "/"
                    + date.get(Calendar.YEAR));

            // Grace Days Enabled
            if (policy.getGraceDaysEnabled())
            {
                percentDecayText.setVisible(false);
                graceDayText.setText(student.getFirstName() + " " + student.getLastName()
                        + " has " + student.getGraceDays(course) + " grace days left.");
            }
            else
            {
                graceDayText.setVisible(false);
                percentDecayText.setText(student.getFirstName() + " " + student.getLastName() 
                        + " will be deducted " + policy.getDecayPercentage()
                        + "% for every " + policy.getDecayRate() + " day(s).");
            }
        }
        
    }
    
    /**
     * Calculate the new grade based on late policy conditions and amount of 
     * days entered
     */
    @FXML 
    public void daysChanged()
    {
        LatePolicy policy = course.getLatePolicy();
        Integer days;
        Grade grade = student.getGrades().get(assign.getID());
        Double decay;
        Double oldGradePercent = (grade.getScore() / assign.getMaxPoints()) * 100.0;
        StringBuilder sb = new StringBuilder();
        
        //Check that the user entered a valid number
        try 
        {
            days = Integer.parseInt(daysLate.getText());
            decay = policy.getDecayPercentage() * days;
        } 
        catch(NumberFormatException e)
        {
            Dialogs.showErrorDialog((Stage) root.getScene().getWindow(),
                    "Must enter in a number for days");
            return;
        }
        
        // Grace days enabled
        if (policy.getGraceDaysEnabled())
        {
            graceDaysLeft = Math.max(-1, student.getGraceDays(course) - days);
            
            sb.append(student.getFirstName() + " " + student.getLastName()
                    + " will have " + graceDaysLeft + " grace day(s) left");
            
            // Set new grade to 0 if grace days are out
            if (graceDaysLeft == -1)
            {
                sb.append("\nThey will receive a 0 on this assignment");
                newGradePercent = 0.0;
            }
        }
        else
        {
            newGradePercent = oldGradePercent - (oldGradePercent * (decay / 100));
            sb.append("Old Grade: " + oldGradePercent + "%");
            sb.append("\nNew Grade: " + String.format("%.2f", newGradePercent) + "%");
        }
        
        newGradeText.setText(sb.toString());
        calculated = true;
    }
    
    /**
     * Apply the new grade to the student, make sure the new grade
     * has been calculated. 
     */
    @FXML
    public void apply()
    {
        LatePolicy policy = course.getLatePolicy();
        
        if (calculated)
        {
            if (policy.getGraceDaysEnabled())
            {
                student.setGraceDays(course, graceDaysLeft);
                if (newGradePercent != null)
                {
                    try
                    {
                        student.getGrades().get(assign.getID()).setScore("" + 0.0);
                        course.updateCourse();
                        student.addGrade(course, assign, student.getGrades().get(assign.getID()));
                    }
                    catch (BadDataException e)
                    {
                        e.printStackTrace();
                    }
                }

            }
            else 
            {
                try
                {
                    student.getGrades().get(assign.getID()).setScore("" + (newGradePercent /
                            100.0) * assign.getMaxPoints());
                    course.updateCourse();
                    student.addGrade(course, assign, student.getGrades().get(assign.getID()));
                }
                catch (BadDataException e)
                {
                    e.printStackTrace();
                }
            }
            cancel();
        }
        else
        {
            Dialogs.showErrorDialog((Stage) root.getScene().getWindow(), "Must press calculate first!");
        }
        
    }
    
    /**
     * Close the view 
     */
    @FXML
    public void cancel()
    {
        Stage primaryStage = (Stage) root.getScene().getWindow();
        primaryStage.close();
    }

}
