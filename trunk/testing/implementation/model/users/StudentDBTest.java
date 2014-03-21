/**
 * 
 */
package implementation.model.users;

import model.exception.CourseDataException;
import model.spreadsheet.CourseInfo;
import model.users.Student;
import model.users.StudentDB;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/****
 *
 * Class StudentDBTest is the companion testing class for class <a href=
 * StudentDB.html> StudentDB </a>.  It implements the following module test plan:
 *                                                                         <ul>
 *                                                                      <p><li>
 *     Phase 1: Unit test the getInstance method
 *              This in turn tests the private constructor
 *                                                                      <p><li>
 *     Phase 2: Unit test the generateStudents method
 *                                                                      <p><li>
 *     Phase 3: Unit test the getStudentsForClass method
 *                                                                      <p><li>
 *     Phase 4: Stress test by generating 100000 students.
 *
 *   </ul>
 * @author  Kevin Feutz
 */
public class StudentDBTest
{

    /**
     * The cases for getting the instance of a new studentDB are as follows:
     *                                                                                <pre>
     * Test                                     Expected
     * Case             Inputs                  Outputs                     Remarks
     * ==============================================================================
     * 1                none                    Instance of studentDB       Tests getting
     *                                          does not equal null         the instance
     *                                                                      of a studentDB
     *                                                                                </pre>
     */
    @Test
    public void testGetInstance()
    {
        StudentDB database = StudentDB.getInstance();
        assertTrue(database != null);
    }

    /**
     * The cases for getting the list of students from a specific course
     *                                                                                <pre>
     * Test                                     Expected
     * Case             Inputs                  Outputs                     Remarks
     * ==============================================================================
     * 1                CourseInfo              Student 1 should have       Tests getting
     *                  corresponding to an     first name = "aartjan"      the student
     *                  instance of a course in for the CourseInfo          from a course
     *                  studentDB.txt file      I am using
     *                                                                               </pre>
     */
    @Test
    public void testGetStudentsForClass() throws CourseDataException {
        CourseInfo courseInfo = new CourseInfo("Software Engineering I",
            "spring","01","308","Computer Science", 2014);
        StudentDB database = StudentDB.getInstance();
        List<Student> students = database.getStudentsForClass(courseInfo);
        assertEquals(true, students.get(0).getFirstName().length() > 0);
    }

}
