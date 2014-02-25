package model.gradebook;

import model.spreadsheet.GradingScheme;
import model.spreadsheet.SpreadsheetCourse;
import model.users.Teacher;

import java.io.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Logger;

/****
 * A Gradebook is the overarching object of the grader. It includes a teacher
 * who "owns" the gradebook, a collection of
 * {@link model.spreadsheet.SpreadsheetCourse}s that the teacher teaches, and
 * the current SpreadsheetCourse the teacher is editing. This class also
 * contains a static reference to itself so it can only be instantiated once.
 * 
 * @author jamesfazio
 */

public class Gradebook extends Observable implements Serializable
{
    
    private static final String GRADEBOOK = "currentGradebook";

    /**
     * List of the {@link model.spreadsheet.SpreadsheetCourse}s taught in the
     * Gradebook.
     */
    private ArrayList<SpreadsheetCourse> courses;

    /**
     * List of the {@link model.spreadsheet.GradingScheme}s taught in the
     * Gradebook.
     */
    private ArrayList<GradingScheme> gradingSchemes;

    /**
     * Current {@link model.spreadsheet.SpreadsheetCourse} being used.
     */
    private SpreadsheetCourse currentCourse;

    /**
     * {@link model.users.Teacher} Teacher who owns the gradebook.
     */
    private Teacher teacher;

    /**
     * Static reference to the Gradebook, used to ensure the Gradebook is only
     * instantiated once.
     */
    private static Gradebook instance;

    private static final Logger LOGGER = Logger.getLogger(Gradebook.class
            .getName());

    private static final long serialVersionUID = 4568797145557381794L;

    /**
     * Called once at the beginning of the application to load the Gradebook.
     */
    private Gradebook()
    {
        System.out.println("Congrats, you loaded a Gradebook!");
        loadGradebook();
    }

    /**
     * Called if the Gradebook has never been saved before, the first time the
     * user uses the application.
     * 
     * @param firstTime
     */
    private Gradebook(boolean firstTime)
    {
        // StudentDBScript.createDBFile();
        System.out.println("Congrats, you loaded a Gradebook!");
    }

    /**
     * Returns a reference to the Gradebook. The Gradebook is only instantiated
     * once, every time after a reference to the already created Gradebook is
     * returned.
     * 
     * @return - Reference to the Gradebook
     */
    public static Gradebook getInstance()
    {
        if (instance == null)
            new Gradebook();

        return instance;
    }

    /**
     * Adds a SpreadsheetCourse to the Gradebook.
     * 
     * @param course
     *            - The Spreadsheet course being added to the Gradebook
     */
    /*
     * @ requires // The SpreadsheetCourse is not null. // The SpreadsheetCourse
     * does not already exist in the Gradebook courses. // The SpreadsheetCourse
     * must have CourseInfo with non-null non // empty courseName, quarter,
     * number, and dept. // The SpreadsheetCourse must have a valid non-null
     * non-overlapping grading // scheme.
     * 
     * (courses != null)
     * 
     * &&
     * 
     * (course != null)
     * 
     * &&
     * 
     * ( !(\exists SpreadsheetCourse course_input ;
     * courses.contains(course_input)))
     * 
     * &&
     * 
     * (course.courseInfo != null)
     * 
     * &&
     * 
     * (course.courseInfo.courseName != null) &&
     * (course.courseInfo.courseName.length > 0)
     * 
     * &&
     * 
     * (course.courseInfo.quarter != null) && (course.courseInfo.quarter.length
     * > 0)
     * 
     * &&
     * 
     * (course.courseInfo.number != null) && (course.courseInfo.number.length >
     * 0)
     * 
     * &&
     * 
     * (course.courseInfo.dept != null) && (course.courseInfo.dept.length > 0)
     * 
     * &&
     * 
     * (course.gradingScheme != null) && (course.gradingScheme.length > 0)
     * 
     * &&
     * 
     * (\forall int i ; (i >= 0) && (i < course.gradingScheme.size() - 1);
     * course.gradingScheme(i).gradeRange.high >
     * course.gradingScheme(i).gradeRange.low)
     * 
     * &&
     * 
     * (\forall int i ; (i > 0) && (i < course.gradingScheme.size() -1) ;
     * course.gradingScheme(i).gradeRange.low >
     * course.gradingScheme(i-1).gradeRange.high)
     * 
     * ensures // The SpreadsheetCourse is in the Gradebook if and only if it //
     * is a new SpreadsheetCourse, otherwise it already exists in // the
     * Gradebook.
     * 
     * (\forall SpreadsheetCourse course_other ;
     * (courses.contains(course_other)) <==> course_other.equals(course) ||
     * \old(courses).contains(course_other));
     * 
     * @
     */

    public void addSpreadsheetCourse(SpreadsheetCourse course)
    {
        if (courses == null)
        {
            courses = new ArrayList<SpreadsheetCourse>();
        }

        courses.add(course);
        setChanged();
        notifyObservers();
    }

    /**
     * Delete a Spreadsheet from the Gradebook.
     * 
     * @param course
     *            - The Spreadsheet Course that is to be deleted
     */
    /*
     * @
     * 
     * requires // The gradebook contains at least one course. // The course to
     * be deleted is not null. // The course to be deleted is in the Gradebook
     * courses. // (Possibly further conditions checking to see if the quarter
     * has started)
     * 
     * (courses != null) && (courses.length > 0)
     * 
     * &&
     * 
     * (course != null)
     * 
     * &&
     * 
     * courses.contains(course);
     * 
     * ensures // The course no longer exists in the Gradebook.
     * 
     * !(courses.contains(course));
     * 
     * @
     */
    public void deleteSpreadsheetCourse(SpreadsheetCourse course)
    {
        courses.remove(course);
    }

    public ArrayList<GradingScheme> getGradingSchemes()
    {
        if (gradingSchemes == null)
        {
            System.out.println("tried getting empty scheme, made default");
            addGradingScheme(new GradingScheme());
        }
        return gradingSchemes;
    }

    public void addGradingScheme(GradingScheme scheme)
    {
        if (gradingSchemes == null)
        {
            gradingSchemes = new ArrayList<GradingScheme>();
        }

        gradingSchemes.add(scheme);
        setChanged();
        notifyObservers();
    }

    public void clearGradebook()
    {
        File f = new File(GRADEBOOK);
        PrintWriter writer;
        try
        {
            writer = new PrintWriter(f);
            writer.print("");
            writer.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error clearing file");
            e.printStackTrace();
        }
    }

    /**
     * Loads the Gradebook from a file. The Gradebook and all of its fields are
     * serializable, so the Gradebook can be directly loaded from the file.
     */
    private void loadGradebook()
    {
        File f;
        FileInputStream fin;

        try
        {
            f = new File(GRADEBOOK);
            if (f.exists())
            {
                fin = new FileInputStream(GRADEBOOK);
                ObjectInputStream ois = new ObjectInputStream(fin);
                instance = (Gradebook) ois.readObject();
                ois.close();
            }
            else
            {
                instance = new Gradebook(true);
            }
            LOGGER.info("Done loading gradebook");
        }
        catch (IOException e)
        {
            LOGGER.warning("Error in loading gradebook: ");
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            LOGGER.warning("Error in loading gradebook: " + e.getStackTrace());
        }

    }

    /**
     * Saves the Gradebook to a file. The Gradebook and all of its fields are
     * serializable, so it can be directly stored into a file.
     */
    public void saveGradebook()
    {
        FileOutputStream fout;

        try
        {
            fout = new FileOutputStream(GRADEBOOK);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(instance);
            oos.close();
            LOGGER.info("Done saving gradebook");

        }
        catch (IOException e)
        {
            LOGGER.warning("Error in saving gradebook: ");
            e.printStackTrace();
        }
    }

    /**
     * Returns the Teacher of the Gradebook.
     * 
     * @return - the Teacher of the Gradebook.
     */
    public Teacher getTeacher()
    {
        return teacher;
    }

    /**
     * Returns the courses in the Gradebook.
     * 
     * @return - the courses in the Gradebook.
     */
    public ArrayList<SpreadsheetCourse> getCourses()
    {
        return courses;
    }

    /**
     * Returns the current course in the Gradebook.
     * 
     * @return - the current course in the Gradebook.
     */
    public SpreadsheetCourse getCurrentCourse()
    {
        return currentCourse;
    }

    /**
     * Sets the current SpreadsheetCourse to the course supplied.
     * 
     * @param currentCourse
     *            - The course to be set as the current one.
     */
    public void setCurrentCourse(SpreadsheetCourse currentCourse)
    {
        System.out.println("Setting current course to: "
                + currentCourse.getCourseInfo().getCourseName());
        this.currentCourse = currentCourse;
    }

}