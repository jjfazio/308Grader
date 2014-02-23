/**
 * 
 */
package implementation.model.gradebook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import model.gradebook.Gradebook;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jamesfazio
 *
 */
public class GradebookTest
{

    private Gradebook gradebook;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        gradebook = Gradebook.getInstance();
    }

    /**
     * Test method for {@link model.gradebook.Gradebook#getInstance()}.
     */
    @Test
    public void testGetInstance()
    {
        Gradebook gradebook2 = Gradebook.getInstance();
        assertNotNull("Gradebook is null!", gradebook);
        assertEquals(gradebook, gradebook2);
        
    }

    /**
     * Test method for {@link model.gradebook.Gradebook#addSpreadsheetCourse(model.spreadsheet.SpreadsheetCourse)}.
     */
    @Test
    public void testAddSpreadsheetCourse()
    {
        
    }

    /**
     * Test method for {@link model.gradebook.Gradebook#getSpreadsheetCourse()}.
     */
    @Test
    public void testGetSpreadsheetCourse()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.gradebook.Gradebook#deleteSpreadsheetCourse(model.spreadsheet.SpreadsheetCourse)}.
     */
    @Test
    public void testDeleteSpreadsheetCourse()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.gradebook.Gradebook#editSpreadsheetCourse(model.spreadsheet.SpreadsheetCourse, model.spreadsheet.SpreadsheetCourse)}.
     */
    @Test
    public void testEditSpreadsheetCourse()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.gradebook.Gradebook#clearGradebook()}.
     */
    @Test
    public void testClearGradebook()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.gradebook.Gradebook#saveGradebook()}.
     */
    @Test
    public void testSaveGradebook()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.gradebook.Gradebook#getTeacher()}.
     */
    @Test
    public void testGetTeacher()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.gradebook.Gradebook#getCourses()}.
     */
    @Test
    public void testGetCourses()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.gradebook.Gradebook#getCurrentCourse()}.
     */
    @Test
    public void testGetCurrentCourse()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.gradebook.Gradebook#setCurrentCourse(model.spreadsheet.SpreadsheetCourse)}.
     */
    @Test
    public void testSetCurrentCourse()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.gradebook.Gradebook#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject()
    {
        fail("Not yet implemented");
    }

}
