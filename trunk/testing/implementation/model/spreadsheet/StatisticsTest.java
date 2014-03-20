package implementation.model.spreadsheet;

import model.assignments_categories.Assignment;
import model.assignments_categories.Grade;
import model.exception.BadDataException;
import model.exception.CourseDataException;
import model.exception.StudentDataException;
import model.gradebook.Gradebook;
import model.spreadsheet.*;
import model.users.Student;
import org.junit.Test;
import java.util.Date;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/****
 *
 * Class StatisticsTest is the companion testing class for class
 * {@link model.gradebook.Statistics}
 * It implements the following module test plan:
 *                                                                         <ul>
 *                                                                      <p><li>
 *     Phase 1: Unit test the constructor.
 *                                                                      <p><li>
 *     Phase 2: Unit test the access method getStatName
 *                                                                      <p><li>
 *     Phase 3: Unit test the calculation methods: calcMean, calcMedian,
 *              calcMode, calcNumAssignmentsGraded, calcRange, and
 *              calcStandardDeviation.
 *
 *                                                                        </ul>
 *     @author Kevin Feutz
 */
public class StatisticsTest
{

    /**
     * Test method for {@link model.spreadsheet.Statistics#Statistics(String, java.util.List)}.
     *
     * Construct a Statistics. Ensure that once the Statistics
     * is created that it is not null.
     *                                                                    <pre>
     *  Test
     *  Case    Input                                    Output             Remarks
     * =================================================================================
     *   1      SpreadsheetCourse("test", null)          Success            Tests creating
     *                                                                      a new statistic
     *
     */
    @Test
    public void testConstructor()
    {
        Statistics stats = new Statistics("test", null);
        assertNotNull(stats);
    }

    /**
     * Test method for {@link model.spreadsheet.Statistics#getStatName()}
     *
     * Construct a Statistics. Checks the name of the stat by calling getStatName().
     *                                                                    <pre>
     *  Test
     *  Case    Input                                    Output             Remarks
     * =================================================================================
     *   1      SpreadsheetCourse("test", null)          getStatName()      Tests getter method
     *                                                      == "test"       for retrieving the
     *                                                                      stat name
     */
    public void testGetStatName()
    {
        Statistics stats = new Statistics("test", null);
        assertEquals("test", stats.getStatName());
    }

    /**
     * Test method for {@link model.spreadsheet.Statistics#calcMean(model.assignments_categories.Assignment)}()}.
     *
     * Construct a Statistics. After Statistics is correctly instantiated, call the calcMean()
     * method.
     *                                                                    <pre>
     *  Test
     *  Case    Input                                    Output             Remarks
     * =================================================================================
     *   1      SpreadsheetCourse("test", empty list)    calcMean()         Tests meanCalc
     *                                                   should return      with zero students,
     *                                                   0.0                implying zero graded
     *                                                                      assignments
     *   2      SpreadsheetCourse with one assignment    calcMean()         Tests meanCalc with
     *          graded with a score of 100%              should return      one valid assignment
     *                                                   0.0                graded.
     */
    @Test
    public void testCalcMean() throws StudentDataException, CourseDataException, BadDataException
    {
        Gradebook gradebook = Gradebook.getInstance();
        /** Test case 1 */
        ArrayList<Student> students = new ArrayList<Student>();
        Statistics stats = new Statistics("test", students);
        assertEquals(0.0, stats.calcMean(null));

        /** Test case 2 */
        Student mockStudent = new Student("kfeutz", "kevin", "feutz", "1234", "SE", "Junior");
        students.add(mockStudent);
        CourseInfo info = new CourseInfo("Test Course", "Spring", "04", "111",
                "Computer Science", 2014);
        GradingScheme scheme = new GradingScheme();
        LatePolicy policy = new LatePolicy();
        SpreadsheetCourse course = new SpreadsheetCourse(info, scheme, policy);
        gradebook.addSpreadsheetCourse(course);
        gradebook.setCurrentCourse(course);
        Assignment assign = new Assignment();
        course.getCategoryContainer().getRoot().addAssignment(assign);
        mockStudent.addGrade(course, assign, new Grade(new Date(), "30.0"));
        students.add(mockStudent);
        stats = new Statistics("mean", students);
        assertEquals(30.0, stats.calcMean(assign));
    }

    /**
     * Test method for {@link model.spreadsheet.Statistics#calcNumAssignmentsGraded(model.assignments_categories.Assignment)}
     *
     * Construct a Statistics. After Statistics is correctly instantiated, call the calcMedian()
     * method.
     *                                                                    <pre>
     *  Test
     *  Case    Input                                    Output             Remarks
     * =================================================================================
     *   1      Assignment with no assignments graded    Method             Tests calcNumAssignmentsGraded
     *                                                   should return      with zero students,
     *                                                   0                  implying zero graded
     *                                                                      assignments
     *   2      Assignment with 4 assignments graded     Method             Tests method with
     *                                                   should return      four valid assignments
     *                                                   4                  graded.
     */
    @Test
    public void testCalcNumAssignmentsGraded() throws StudentDataException, CourseDataException, BadDataException {
        Gradebook gradebook = Gradebook.getInstance();
        ArrayList<Student> students = new ArrayList<Student>();
        Statistics stats = new Statistics("test", students);

        Student mockStudent = new Student("kfeutz", "kevin", "feutz", "1234", "SE", "Junior");
        students.add(mockStudent);
        Student mockStudent2 = new Student("asdf", "dfas", "feusdfasdftz", "1234", "SE", "Junior");
        Student mockStudent3 = new Student("saee", "dfas", "feusdfasdftz", "1234", "SE", "Junior");
        Student mockStudent4 = new Student("EEEEE", "dfas", "feusdfasdftz", "1234", "SE", "Junior");
        students.add(mockStudent2);
        students.add(mockStudent3);
        students.add(mockStudent4);
        CourseInfo info = new CourseInfo("Test Course", "Spring", "04", "111",
                "Computer Science", 2014);
        GradingScheme scheme = new GradingScheme();
        LatePolicy policy = new LatePolicy();
        SpreadsheetCourse course = new SpreadsheetCourse(info, scheme, policy);
        gradebook.addSpreadsheetCourse(course);
        gradebook.setCurrentCourse(course);
        Assignment assign = new Assignment();
        course.getCategoryContainer().getRoot().addAssignment(assign);
        /** Test case 1 */
        assertEquals(0, stats.calcNumAssignmentsGraded(assign));

        mockStudent.addGrade(course, assign, new Grade(new Date(), "30.0"));
        mockStudent2.addGrade(course, assign, new Grade(new Date(), "40.0"));
        mockStudent3.addGrade(course, assign, new Grade(new Date(), "80.0"));
        mockStudent4.addGrade(course, assign, new Grade(new Date(), "100.0"));
        /** Test case 2 */
        assertEquals(4, stats.calcNumAssignmentsGraded(assign));
    }
}