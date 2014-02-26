/**
 * 
 */
package implementation.model.spreadsheet;

import static org.junit.Assert.*;
import model.exception.CourseDataException;
import model.spreadsheet.CourseInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


/****
*
* Class CourseInfoTest is the companion testing class for class CourseInfo.
* It implements the following module test plan:
*                                                                         <ul>
*                                                                      <p><li>
*     Phase 1: Unit test the simple access methods including getDept, 
*               GetSection, getCourseName, and getNumber for M4.
*                                                                      <p><li>
*     Phase 2: Unit test the rest of the access methods.
*                                                                      <p><li>
*     Phase 3: Unit test the constructor.
*                                                                      <p><li>
*     Phase 4: Unit test the toString and equals methods.
*                                                                      <p><li>
*     Phase 5: Unit test the teacher assistant methods that add, edit 
*               remove.
*                                                                      <p><li>
*     Phase 6: Stress test by adding and deleting 1000 TA's.
*                                                                        </ul>
*/


/**
 * @author Kevin Backers
 *
 */
public class CourseInfoTest
{

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
    }

    /**
     * Test method for {@link model.spreadsheet.CourseInfo#hashCode()}.
     */
    @Test
    public void testHashCode()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.spreadsheet.CourseInfo#CourseInfo(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int)}.
     */
    @Test
    public void testCourseInfo()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.spreadsheet.CourseInfo#addTeacherAssistant(model.users.TeacherAssistant)}.
     */
    @Test
    public void testAddTeacherAssistant()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.spreadsheet.CourseInfo#editTeacherAssistant(model.users.TeacherAssistant, model.users.TeacherAssistant)}.
     */
    @Test
    public void testEditTeacherAssistant()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.spreadsheet.CourseInfo#removeTeacherAssistant(model.users.TeacherAssistant)}.
     */
    @Test
    public void testRemoveTeacherAssistant()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.spreadsheet.CourseInfo#getCourseName()}.
     * Unit test getCourseName by calling getCourseName on a CourseInfo with a
     * null and non-null name field. 
     *                                                                    <pre>
     *  Test
     *  Case    Input                 Output                 Remarks
     * ====================================================================
     *   1      CourseInfo.name       CourseDataException    Null case
     *            == null
     *
     *   2      CourseInfo.name       same non-null          Non-null case
     *            =- non-null             value
     *                                                                   </pre>
     */
    @Test
    public void testGetCourseName()
    {
        try {
            CourseInfo ci = new CourseInfo(null, "spring", "01", "365", "cpe", 2014);
        } catch (CourseDataException e) {
            /** null section should throw exception. */
            assert(e.getMessage() != null);
        }
        
        try {
            CourseInfo ci = new CourseInfo("Databases", "spring", "01", "365", "cpe", 2014);
            assertEquals("Databases", ci.getCourseName());
        } catch (CourseDataException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test method for {@link model.spreadsheet.CourseInfo#getQuarter()}.
     */
    @Test
    public void testGetQuarter()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.spreadsheet.CourseInfo#getNumber()}.
     * Unit test getNumber by calling getNumber on a CourseInfo with a
     * null and non-null number field. 
     *                                                                    <pre>
     *  Test
     *  Case    Input                 Output                Remarks
     * ====================================================================
     *   1      CourseInfo.number     CourseDataException   Null case
     *            == null
     *
     *   2      CourseInfo.number     same non-null         Non-null case
     *            =- non-null             value
     *                                                                   </pre>
     */
    @Test
    public void testGetNumber()
    {
        try {
            CourseInfo ci = new CourseInfo("name", "spring", "01", null, "cpe", 2014);
        } catch (CourseDataException e) {
            /** null section should throw exception. */
            assert(e.getMessage() != null);
        }
        
        try {
            CourseInfo ci = new CourseInfo("name", "spring", "01", "365", null, 2014);
            assertEquals("365", ci.getNumber());
        } catch (CourseDataException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Unit test getDept by calling getDept on a CourseInfo with a
     * null and non-null department field. 
     *                                                                    <pre>
     *  Test
     *  Case    Input                 Output          Remarks
     * ====================================================================
     *   1      CourseInfo.dept       null            Null case
     *            == null
     *
     *   2      CourseInfo.dept       same non-null   Non-null case
     *            =- non-null             value
     *                                                                   </pre>
     */
    @Test
    public void testGetDept()
    {
        try {
            CourseInfo ci = new CourseInfo("name", "spring", "01", "365", "cpe", 2014);
            assertEquals("cpe", ci.getDept());
        } catch (CourseDataException e) {
            fail(e.getMessage());
        }
        
        try {
            CourseInfo ci = new CourseInfo("name", "spring", "01", "365", null, 2014);
            assertEquals(null, ci.getDept());
        } catch (CourseDataException e) {
            fail(e.getMessage());
        }
        
    }

    /**
     * Unit test getSection by calling getSection on a CourseInfo with a
     * null and non-null section field. 
     *                                                                    <pre>
     *  Test
     *  Case    Input                   Output               Remarks
     * ====================================================================
     *   1      CourseInfo.section     CourseDataException   Null case
     *            == null
     *
     *   2      CourseInfo.section     same non-null         Non-null case
     *            =- non-null           value
     *                                                                   </pre>
     */
    @Test
    public void testGetSection()
    {
        try {
            CourseInfo ci = new CourseInfo("name", "spring", null, "365", "cpe", 2014);
        } catch (CourseDataException e) {
            /** null section should throw exception. */
            assert(e.getMessage() != null);
        }
        
        try {
            CourseInfo ci = new CourseInfo("name", "spring", "01", "365", null, 2014);
            assertEquals("01", ci.getSection());
        } catch (CourseDataException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test method for {@link model.spreadsheet.CourseInfo#getTeacherAssistants()}.
     */
    @Test
    public void testGetTeacherAssistants()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.spreadsheet.CourseInfo#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.spreadsheet.CourseInfo#toString()}.
     */
    @Test
    public void testToString()
    {
        fail("Not yet implemented");
    }

}
