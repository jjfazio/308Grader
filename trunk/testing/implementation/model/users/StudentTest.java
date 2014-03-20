/**
 * 
 */
package implementation.model.users;

import model.assignments_categories.Assignment;
import model.assignments_categories.Category;
import model.assignments_categories.CategoryContainer;
import model.assignments_categories.Grade;
import model.exception.BadDataException;
import model.exception.CourseDataException;
import model.exception.StudentDataException;
import model.gradebook.Gradebook;
import model.spreadsheet.CourseInfo;
import model.spreadsheet.GradingScheme;
import model.spreadsheet.LatePolicy;
import model.spreadsheet.SpreadsheetCourse;
import model.users.Student;
import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

/****
 *
 * Class StudentTest is the companion testing class for class <a href=
 * Student.html> Student </a>.  It implements the following module test plan:
 *                                                                         <ul>
 *                                                                      <p><li>
 *     Phase 1: Unit test the constructor.
 *                                                                      <p><li>
 *     Phase 2: Unit test the addGrade method
 *                                                                      <p><li>
 *     Phase 3: Unit test the removeGrade method
 *                                                                      <p><li>
 *     Phase 4: Unit test the editGrade method
 *                                                                      <p><li>
 *     Phase 5: Unit test the addCourse method
 *                                                                      <p><li>
 *     Phase 6: Unit test the removeCourse method
 *                                                                      <p><li>
 *     Phase 7: Unit test the removeCourse method
 *                                                                      <p><li>
 *     Phase 7: Stress test by creating and deleting 100000 students.
 *                                                                        </ul>
 *
 *     @author  Kevin Feutz
 */
public class StudentTest
{

    /**
     * The cases for creating a new student with blank fields are as follows:
     *                                                                                <pre>
     * Test                                     Expected
     * Case             Inputs                  Outputs                     Remarks
     * ==============================================================================
     * 1                userName = ""           Exception thrown            Tests all
     *                  firstName = ""                                      empty fields
     *                  lastName = ""                                       for a new
     *                  id = ""                                             student
     *                  major = ""
     *                  gradeLevel = ""
     *                                                                                 </pre>
     */
    @Test
    public void testEmptyStudent() {
        try {
            Student emptyStudent = new Student("", "", "",
                    "", "", "");
        }
        catch(StudentDataException exc) {
            String expectedError = "* First Name field cannot be blank\n\n"
                + "* Last Name field cannot be blank\n\n"
                + "* Student ID is a required text entry field\n\n";
            assertEquals(expectedError, exc.getMessage());
        }

    }

    /**
     * The cases for creating a new student with invalid first and last name
     * are as follows:
     *                                                                                <pre>
     * Test                                     Expected
     * Case             Inputs                  Outputs                     Remarks
     * ==============================================================================
     * 1                userName = "kfeutz"     Exception thrown            Tests invlaid
     *                  firstName = "lasd123"                               entries for
     *                  lastName = "123alsjk"                               first and last
     *                  id = "1234"                                         names
     *                  major = "csc"
     *                  gradeLevel = "junior"
     *                                                                                 </pre>
     */
    @Test
    public void testInvalidFirstLastNameStudent() {
        try {
            Student student = new Student("kfeutz", "lasd123", "123alsjk",
                "1234", "csc", "junior");
        }
        catch(StudentDataException exc) {
            String expectedError = "* First Name must contain only alphabetic characters\n\n"
                + "* Last Name must contain only alphabetic characters\n\n";
            assertEquals(expectedError, exc.getMessage());
        }
    }

    /**
     * The cases for creating a new student with all valid fields
     *                                                                                <pre>
     * Test                                     Expected
     * Case             Inputs                  Outputs                     Remarks
     * ==============================================================================
     * 1                userName = "kfeutz"     New student fields          Tests valid
     *                  firstName = "Kevin"     equal the passed            entries for
     *                  lastName = "Feutz"      inputs                      creating a new
     *                  id = "1234"                                         student
     *                  major = "SE"
     *                  gradeLevel = "Junior"
     *                                                                                 </pre>
     */
    @Test
    public void testValidStudentCreation() {
        try {
            Student student = new Student("kfeutz", "Kevin", "Feutz",
                "1234", "SE", "Junior");
            assertEquals("Kevin", student.getFirstName());
            assertEquals("Feutz", student.getLastName());
            assertEquals("kfeutz", student.getUserName());
            assertEquals("1234", student.getId());
            assertEquals("SE", student.getMajor());
            assertEquals("Junior", student.getGradeLevel());
        }
        catch(StudentDataException exc) {
            System.out.println(exc.getMessage());
        }
    }

    /**
     * The cases for adding a valid grade to a student
     *                                                                                <pre>
     * Test                                         Expected
     * Case             Inputs                      Outputs                     Remarks
     * ================================================================================
     * 1                course = new                The student's Hashmap       Tests a valid
     *                      SpreadsheetCourse(...)  should contain a mapping    addition of a
     *                  assignment = new            from grade to assignment    new grade
     *                      Assingment(...)
     *                  grade = new Grade(...)
     *
     *                                                                                </pre>
     */
    @Test
    public void testAddGrade() throws CourseDataException, StudentDataException, BadDataException {
        Gradebook gradebook = Gradebook.getInstance();
        SpreadsheetCourse course = new SpreadsheetCourse(
            new CourseInfo("test", "test", "test", "test", "test", 2014),
            new GradingScheme(),
            new LatePolicy()
        );
        gradebook.addSpreadsheetCourse(course);
        gradebook.setCurrentCourse(course);
        Assignment assignment = new Assignment(
            course.getCategoryContainer().getRoot(),
            "test", 100.0, 100, new GregorianCalendar(), new GradingScheme(),
            new LatePolicy(), false
        );
        Grade grade = new Grade(new Date(), "100");
        Student student = new Student("kfeutz", "Kevin", "Feutz",
                "1234", "SE", "Junior");
        CategoryContainer container = course.getCategoryContainer();
        container.getRoot().addAssignment(assignment);
        student.addGrade(course, assignment, grade);
        assertEquals(grade, student.getGrades().get(assignment.getID()));
    }

    /**
     * TODO
     */
    @Test
    public void testRemoveGrade()
    {

    }

    /**
     * TODO
     */
    @Test
    public void testEditGrade()
    {

    }

    /**
     * The cases for adding a valid course to a student
     *                                                                                <pre>
     * Test                                         Expected
     * Case             Inputs                      Outputs                     Remarks
     * ================================================================================
     * 1                course = new                The student's list of       Tests a valid
     *                      SpreadsheetCourse(...)  courses enrolled should     addition of a
     *                                              contain the passed          new course
     *                                              SpreadsheetCourse
     *
     *                                                                                 </pre>
     */
    @Test
    public void testAddCourse() throws StudentDataException, CourseDataException {
        Student student = new Student("kfeutz", "Kevin", "Feutz",
            "1234", "SE", "Junior");
        SpreadsheetCourse course = new SpreadsheetCourse(
            new CourseInfo("test", "test", "test", "test", "test", 2014),
            new GradingScheme(),
            new LatePolicy()
        );
        student.addCourse(course);
        assertEquals(course, student.getCoursesEnrolled().get(0));
    }

    /**
     * The cases for removing a valid course from a student
     *                                                                                <pre>
     * Test                                         Expected
     * Case             Inputs                      Outputs                     Remarks
     * ================================================================================
     * 1                course = new                The student's list of       Adds a new
     *                      SpreadsheetCourse(...)  courses enrolled should     course to the
     *                                              be empty after the          Student and
     *                                              course is removed           then removes it
     *
     *                                                                                 </pre>
     */
    @Test
    public void testRemoveCourse() throws StudentDataException, CourseDataException {
        Student student = new Student("kfeutz", "Kevin", "Feutz",
                "1234", "SE", "Junior");
        SpreadsheetCourse course = new SpreadsheetCourse(
                new CourseInfo("test", "test", "test", "test", "test", 2014),
                new GradingScheme(),
                new LatePolicy()
        );
        student.addCourse(course);
        student.removeCourse(course);
        assertEquals(0, student.getCoursesEnrolled().size());
    }

    /**
     * TODO
     */
    @Test
    public void testEditStudentInfo()
    {

    }

    /**
     * TODO - this is giving a null pointer exception right now
     */
    @Test
    public void testGetAssignmentGrade() throws BadDataException, StudentDataException, CourseDataException {
        Student student = new Student("kfeutz", "Kevin", "Feutz",
            "1234", "SE", "Junior");
        SpreadsheetCourse course = new SpreadsheetCourse(
                new CourseInfo("test", "test", "test", "test", "test", 2014),
                new GradingScheme(),
                new LatePolicy()
        );
        Assignment assignment = new Assignment(
                new Category(),
                "test", 100.0, 100, new GregorianCalendar(), new GradingScheme(),
                new LatePolicy(), false
        );
        CategoryContainer container = course.getCategoryContainer();
        container.getRoot().addAssignment(assignment);
        Grade grade = new Grade(new Date(), "100");
        student.addGrade(course, assignment, grade);
        assertEquals(grade, student.getAssignmentGrade(assignment));
    }

    /**
     * The cases for retrieving the username from a student
     *                                                                                <pre>
     * Test                                         Expected
     * Case             Inputs                      Outputs                     Remarks
     * ================================================================================
     * 1                none                        A username matching       Creates a new
     *                                              the username of the       student and gets
     *                                              created student           its username
     *
     *                                                                                 </pre>
     */
    @Test
    public void testGetUserName() throws StudentDataException {
        Student student = new Student("kfeutz", "Kevin", "Feutz",
                "1234", "SE", "Junior");
        assertEquals("kfeutz", student.getUserName());
    }

    /**
     * The cases for setting a new username to a student
     *                                                                                <pre>
     * Test                                         Expected
     * Case             Inputs                      Outputs                     Remarks
     * ================================================================================
     * 1                userName = "newUser"        getUserName() should       Creates a new
     *                                              return "newUser"           student, changes
     *                                              after calling              its username
     *                                              setUserName                and calls
     *                                                                         getUserName()
     *                                                                                 </pre>
     */
    @Test
    public void testSetUserName() throws StudentDataException {
        Student student = new Student("kfeutz", "Kevin", "Feutz",
                "1234", "SE", "Junior");
        student.setUserName("newUser");
        assertEquals("newUser", student.getUserName());
    }

    /**
     * The cases for retrieving the first name of a student
     *                                                                                <pre>
     * Test                                         Expected
     * Case             Inputs                      Outputs                     Remarks
     * ================================================================================
     * 1                none                        A first name matching       Creates a new
     *                                              the first name of the       student and gets
     *                                              created student             its first name
     *
     *                                                                                 </pre>
     */
    @Test
    public void testGetFirstName() throws StudentDataException {
        Student student = new Student("kfeutz", "Kevin", "Feutz",
                "1234", "SE", "Junior");
        assertEquals("Kevin", student.getFirstName());
    }

    /**
     * The cases for setting a new first name to a student
     *                                                                                <pre>
     * Test                                         Expected
     * Case             Inputs                      Outputs                     Remarks
     * ================================================================================
     * 1                firstName = "Tom"           getFirstName() should      Creates a new
     *                                              return "Tom"               student, changes
     *                                              after calling              its first name
     *                                              setFirstName               and calls
     *                                                                         getFirstName()
     *                                                                                 </pre>
     */
    @Test
    public void testSetFirstName() throws StudentDataException {
        Student student = new Student("kfeutz", "Kevin", "Feutz",
                "1234", "SE", "Junior");
        student.setFirstName("Tom");
        assertEquals("Tom", student.getFirstName());
    }

    /**
     * The cases for setting a new middle name to a student
     *                                                                                <pre>
     * Test                                         Expected
     * Case             Inputs                      Outputs                     Remarks
     * ================================================================================
     * 1                middleName = "Tom"          getMiddleName() should      Creates a new
     *                                              return "Tom"                student, changes
     *                                              after calling               its middle name
     *                                              setMiddleName               and calls
     *                                                                          getFirstName()
     *                                                                                 </pre>
     */
    @Test
    public void testSetMiddleName() throws StudentDataException {
        Student student = new Student("kfeutz", "Kevin", "Feutz",
                "1234", "SE", "Junior");
        student.setMiddleName("Tom");
        assertEquals("Tom", student.getMiddleName());
    }

    /**
     * The cases for retrieving the middle name of a student
     *                                                                                <pre>
     * Test                                         Expected
     * Case             Inputs                      Outputs                     Remarks
     * ================================================================================
     * 1                none                        A middle name matching       Creates a new
     *                                              the middle name of the       student and gets
     *                                              created student              its first name
     *
     *                                                                                 </pre>
     */
    @Test
    public void testGetMiddleName() throws StudentDataException {
        Student student = new Student("kfeutz", "Kevin", "Feutz",
                "1234", "SE", "Junior");
        student.setMiddleName("Tom");
        assertEquals("Tom", student.getMiddleName());
    }

    /**
     * The cases for retrieving the last name of a student
     *                                                                                <pre>
     * Test                                         Expected
     * Case             Inputs                      Outputs                     Remarks
     * ================================================================================
     * 1                none                        A last name matching       Creates a new
     *                                              the last name of the       student and gets
     *                                              created student            its last name
     *
     *                                                                                 </pre>
     */
    @Test
    public void testGetLastName() throws StudentDataException {
        Student student = new Student("kfeutz", "Kevin", "Feutz",
                "1234", "SE", "Junior");
        assertEquals("Feutz", student.getLastName());
    }

    /**
     * The cases for setting a new last name to a student
     *                                                                                <pre>
     * Test                                         Expected
     * Case             Inputs                      Outputs                     Remarks
     * ================================================================================
     * 1                lastName = "Jones"          getLastName() should        Creates a new
     *                                              return "Jones"              student, changes
     *                                              after calling               its last name
     *                                              setLastName                  and calls
     *                                                                          getLastName()
     *                                                                                 </pre>
     */
    @Test
    public void testSetLastName() throws StudentDataException {
        Student student = new Student("kfeutz", "Kevin", "Feutz",
                "1234", "SE", "Junior");
        student.setLastName("Jones");
        assertEquals("Jones", student.getLastName());
    }

    /**
     * The cases for retrieving the id of a student
     *                                                                                <pre>
     * Test                                         Expected
     * Case             Inputs                      Outputs                     Remarks
     * ================================================================================
     * 1                none                        An ID matching             Creates a new
     *                                              the ID of the              student and gets
     *                                              created student            its ID
     *
     *                                                                                 </pre>
     */
    @Test
    public void testGetId() throws StudentDataException {
        Student student = new Student("kfeutz", "Kevin", "Feutz",
                "1234", "SE", "Junior");
        assertEquals("1234", student.getId());
    }

    /**
     * The cases for setting a ID to a student
     *                                                                                <pre>
     * Test                                         Expected
     * Case             Inputs                      Outputs               Remarks
     * ================================================================================
     * 1                id = "4321"                 getId() should        Creates a new
     *                                              return "4321"         student, changes
     *                                              after calling         its id
     *                                              setId                 and calls
     *                                                                    getId()
     *                                                                                 </pre>
     */
    @Test
    public void testSetId() throws StudentDataException {
        Student student = new Student("kfeutz", "Kevin", "Feutz",
                "1234", "SE", "Junior");
        student.setId("4321");
        assertEquals("4321", student.getId());
    }

    /**
     * The cases for retrieving the major of a student
     *                                                                                <pre>
     * Test                                         Expected
     * Case             Inputs                      Outputs                     Remarks
     * ================================================================================
     * 1                none                        A major matching             Creates a new
     *                                              the major of the             student and gets
     *                                              created student              its major
     *
     *                                                                                 </pre>
     */
    @Test
    public void testGetMajor() throws StudentDataException {
        Student student = new Student("kfeutz", "Kevin", "Feutz",
                "1234", "SE", "Junior");
        assertEquals("SE", student.getMajor());
    }

    /**
     * The cases for setting a major to a student
     *                                                                                <pre>
     * Test                                         Expected
     * Case             Inputs                      Outputs               Remarks
     * ================================================================================
     * 1                major = "CSC"               getMajor() should     Creates a new
     *                                              return "CSC"          student, changes
     *                                              after calling         its major
     *                                              setMajor              and calls
     *                                                                    getMajor()
     *                                                                                 </pre>
     */
    @Test
    public void testSetMajor() throws StudentDataException {
        Student student = new Student("kfeutz", "Kevin", "Feutz",
                "1234", "SE", "Junior");
        student.setMajor("CSC");
        assertEquals("CSC", student.getMajor());
    }

    /**
     * The cases for retrieving the grade level of a student
     *                                                                                <pre>
     * Test                                Expected
     * Case             Inputs             Outputs                     Remarks
     * ================================================================================
     * 1                none               A grade level matching      Creates a new
     *                                     the grade level of the      student and gets
     *                                     created student             its grade level
     *
     *                                                                                 </pre>
     */
    @Test
    public void testGetGradeLevel() throws StudentDataException {
        Student student = new Student("kfeutz", "Kevin", "Feutz",
                "1234", "SE", "Junior");
        assertEquals("Junior", student.getGradeLevel());
    }

    /**
     * The cases for setting a major to a student
     *                                                                                <pre>
     * Test                                         Expected
     * Case             Inputs                      Outputs                  Remarks
     * ================================================================================
     * 1                gradeLevel = "Senior"       getGradeLevel() should   Creates a new
     *                                              return "Senior"          student, changes
     *                                              after calling            its grade level
     *                                              setGradeLevel            and calls
     *                                                                       getGradeLevel()
     *                                                                                 </pre>
     */
    @Test
    public void testSetGradeLevel() throws StudentDataException {
        Student student = new Student("kfeutz", "Kevin", "Feutz",
                "1234", "SE", "Junior");
        student.setGradeLevel("Senior");
        assertEquals("Senior", student.getGradeLevel());
    }

    /**
     * Test method for {@link model.users.Student#getEmail()}.
     */
    @Test
    public void testGetEmail()
    {

    }

    /**
     * Test method for {@link model.users.Student#setEmail(java.lang.String)}.
     */
    @Test
    public void testSetEmail()
    {

    }

    /**
     * Test method for {@link model.users.Student#getPhoneNumber()}.
     */
    @Test
    public void testGetPhoneNumber()
    {

    }

    /**
     * Test method for {@link model.users.Student#setPhoneNumber(java.lang.String)}.
     */
    @Test
    public void testSetPhoneNumber()
    {

    }

    /**
     * Test method for {@link model.users.Student#getCoursesEnrolled()}.
     */
    @Test
    public void testGetCoursesEnrolled()
    {

    }

    /**
     * Test method for {@link model.users.Student#getGrades()}.
     */
    @Test
    public void testGetGrades()
    {

    }

}
