package implementation.model.spreadsheet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import model.exception.CourseDataException;
import model.spreadsheet.CourseInfo;
import model.spreadsheet.GradingScheme;
import model.spreadsheet.LatePolicy;
import model.spreadsheet.SpreadsheetCourse;

import org.junit.Before;
import org.junit.Test;

/****
*
* Class SpreadsheetCourseTest is the companion testing class for class
* {@link model.gradebook.SpreadsheetCourse}
* It implements the following module test plan:
*                                                                         <ul>
*                                                                      <p><li>
*     Phase 1: Unit test the constructor.
*                                                                      <p><li>
*     Phase 2: Unit test the access methods, getCourseInfo, 
*              getCategoryContainer, getStudentRoster, getID,
*              getSettings, getLatePolicy, getGradingDistribution,
*              getStudentRoster, getAddedStudents, isStudentAdded,
*              isStudentDeleted, getStudentsToDelete
*                                                                      <p><li>
*     Phase 3: Unit test the equals method
*                                                                      <p><li>                                                                
*     Phase 4: Unit test the constructive methods, addStudent,
*              addStudents, editStudent, deleteStudent,
*              adjustCourseCurve, setCourseInfo, setGradingDistribution,
*              setLatePolicy, setSettings
*    
*                                                                        </ul>
*     @author jamesfazio                                                                  
*/
public class SpreadsheetCourseTest
{
    private CourseInfo info;
    private GradingScheme scheme;
    private LatePolicy policy;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        info = new CourseInfo("Test Course", "Spring", "04", "111",
                "Computer Science", 2014);
        scheme = new GradingScheme();
        policy = new LatePolicy();
    }

    /**
     * Test method for {@link model.spreadsheet.SpreadsheetCourse#SpreadsheetCourse(model.spreadsheet.CourseInfo, model.spreadsheet.GradingScheme, model.spreadsheet.LatePolicy)}.
     * 
     * Construct a SpreadsheetCourse. Ensure that once the SpreadsheetCourse
     * is created that it is not null. If you pass in null CourseInfo into
     * the SpreadsheetCourse, a BadDataException should be thrown
     *                                                                    <pre>
     *  Test
     *  Case    Input                                    Output             Remarks
     * =================================================================================
     *   1      SpreadsheetCourse(info, scheme, policy)  Success
     *   2      SpreadsheetCourse(info, null, policy)  BadDataException
     *
     * @throws CourseDataException 
     */
    @Test
    public void testSpreadsheetCourse() throws CourseDataException
    {
        GradingScheme scheme = new GradingScheme();
        LatePolicy policy = new LatePolicy();
        
        // Test 1
        try
        {
            SpreadsheetCourse course = new SpreadsheetCourse(info, scheme, policy);
            
            assertNotNull(course);
        }
        catch (CourseDataException e)
        {
            e.printStackTrace();
        }
        
        // Test 2
        try
        {
            SpreadsheetCourse course = new SpreadsheetCourse(info, null, policy);
        }
        catch (CourseDataException e)
        {
            assertNotNull(e.getMessage());
        }
    }
    
    /**
     * Test method for {@link model.spreadsheet.SpreadsheetCourse#getID()}.
     * 
     * Ensures that constructed SpreadsheetCourses all have different valid ids.
     * First test that a course gets an ID, then stress test to make sure 1000
     * courses have different ID's.
     *                                                                    <pre>
     *  Test
     *  Case    Input               Output             Remarks
     * ============================================================
     *   1      getCourseID         Success 
     *   2.     getCourseID         Unique ID's        Run 1000 times
     *   
     * @throws CourseDataException 
     */
    @Test
    public void testGetID()
    {
        List<Integer> ids = new ArrayList<Integer>();
        
        // Test 1
        try
        {
            SpreadsheetCourse course = new SpreadsheetCourse(info, scheme, policy);
            
            assertNotNull(course.getID());
        }
        catch (CourseDataException e)
        {
            e.printStackTrace();
        }
        
        // Test 2
        try
        {
            for (int i = 0; i < 1000; i ++)
            {
                SpreadsheetCourse course = new SpreadsheetCourse(
                        info, scheme, policy);
                
                assertTrue(!ids.contains(course.getID()));
                ids.add(course.getID());
            }
        }
        catch (CourseDataException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Test method for {@link model.spreadsheet.SpreadsheetCourse#getCourseInfo()}.
     * 
     * Ensures that after construction, the CourseInfo can be retrieved from
     * the SpreadsheetCourse. Test to make sure a valid CourseInfo can be retrieved.
     *                                                                    <pre>
     *  Test
     *  Case    Input               Output             Remarks
     * ============================================================
     *   1      getCourseInfo()     Success
     *   
     * @throws CourseDataException 
     */
    @Test
    public void testGetCourseInfo() throws CourseDataException
    {
        SpreadsheetCourse course = new SpreadsheetCourse(info, scheme, policy);
        
        assertEquals(course.getCourseInfo(), info);
        
    }
    
    /**
     * Test method for {@link model.spreadsheet.SpreadsheetCourse#getLatePolicy()}.
     * 
     * Ensures that after construction, the LatePolicy can be retrieved from
     * the SpreadsheetCourse. Test to make sure a valid LatePolicy can be retrieved.
     *                                                                    <pre>
     *  Test
     *  Case    Input                     Output             Remarks
     * ================================================================
     *   1      getCourseLatePolicy()     Success
     *   
     * @throws CourseDataException 
     */
    @Test
    public void testGetLatePolicy() throws CourseDataException
    {
        SpreadsheetCourse course = new SpreadsheetCourse(info, scheme, policy);
        
        assertEquals(course.getLatePolicy(), policy);
    }

    /**
     * Test method for {@link model.spreadsheet.SpreadsheetCourse#addStudent(model.users.Student)}.
     */
    @Test
    public void testAddStudent()
    {
    }

    /**
     * Test method for {@link model.spreadsheet.SpreadsheetCourse#addStudents(java.util.List)}.
     */
    @Test
    public void testAddStudents()
    {
    }

    /**
     * Test method for {@link model.spreadsheet.SpreadsheetCourse#editStudent(model.users.Student, model.users.Student)}.
     */
    @Test
    public void testEditStudent()
    {
    }

    /**
     * Test method for {@link model.spreadsheet.SpreadsheetCourse#deleteStudent(model.users.Student)}.
     */
    @Test
    public void testDeleteStudent()
    {
    }

    /**
     * Test method for {@link model.spreadsheet.SpreadsheetCourse#adjustCourseCurve(java.lang.Double)}.
     */
    @Test
    public void testAdjustCourseCurve()
    {
    }

    /**
     * Test method for {@link model.spreadsheet.SpreadsheetCourse#isStudentAdded()}.
     */
    @Test
    public void testIsStudentAdded()
    {
    }

    /**
     * Test method for {@link model.spreadsheet.SpreadsheetCourse#getAddedStudents()}.
     */
    @Test
    public void testGetAddedStudents()
    {
    }

    /**
     * Test method for {@link model.spreadsheet.SpreadsheetCourse#setCourseInfo(model.spreadsheet.CourseInfo)}.
     */
    @Test
    public void testSetCourseInfo()
    {
    }

    /**
     * Test method for {@link model.spreadsheet.SpreadsheetCourse#getTopCategory()}.
     */
    @Test
    public void testGetTopCategory()
    {
    }

    /**
     * Test method for {@link model.spreadsheet.SpreadsheetCourse#getStudentRoster()}.
     */
    @Test
    public void testGetStudentRoster()
    {
    }

    /**
     * Test method for {@link model.spreadsheet.SpreadsheetCourse#getGradingDistribution()}.
     */
    @Test
    public void testGetGradingDistribution()
    {
    }

    /**
     * Test method for {@link model.spreadsheet.SpreadsheetCourse#setGradingDistribution(model.spreadsheet.GradingScheme)}.
     */
    @Test
    public void testSetGradingDistribution()
    {
    }

    /**
     * Test method for {@link model.spreadsheet.SpreadsheetCourse#setLatePolicy(model.spreadsheet.LatePolicy)}.
     */
    @Test
    public void testSetLatePolicy()
    {
    }

    /**
     * Test method for {@link model.spreadsheet.SpreadsheetCourse#getSettings()}.
     */
    @Test
    public void testGetSettings()
    {
    }

    /**
     * Test method for {@link model.spreadsheet.SpreadsheetCourse#setSettings(model.file.Settings)}.
     */
    @Test
    public void testSetSettings()
    {
    }

    /**
     * Test method for {@link model.spreadsheet.SpreadsheetCourse#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject()
    {
    }

}
