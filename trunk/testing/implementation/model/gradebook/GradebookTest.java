/**
 * 
 */
package implementation.model.gradebook;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

import model.exception.BadDataException;
import model.exception.CourseDataException;
import model.gradebook.Gradebook;
import model.spreadsheet.CourseInfo;
import model.spreadsheet.GradingScheme;
import model.spreadsheet.LatePolicy;
import model.spreadsheet.SpreadsheetCourse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/****
*
* Class GradebookTest is the companion testing class for class
* {@link model.gradebook.Gradebook}
* It implements the following module test plan:
*                                                                         <ul>
*                                                                      <p><li>
*     Phase 1: Unit test the constructor.
*                                                                      <p><li>
*     Phase 2: Unit test the constructive methods, addCourse,
*              setTeacher, addGradingScheme, setCurentCourse
*                                                                      <p><li>
*     Phase 3: Unit test the access methods, getCurrentCourse, 
*              getTeacher, getGradingSchemes
*                                                                      <p><li>                                                                 
*     Phase 4: Unit test the saving of the Gradebook.
*                                                                      <p><li>
*     Phase 5: Unit test the clearing of the Gradebook.
*                                                                      <p><li>
*    
*                                                                        </ul>
*     @author jamesfazio                                                                  
*/
public class GradebookTest
{
    
    @Before
    public void setUp()
    {
        File file = new File("currentGradebook");
        file.delete();
    }
    
    @After
    public void tearDown()
    {
        File file = new File("currentGradebook");
        file.delete();
    }
    
    /**
     * Test method for {@link model.gradebook.Gradebook#getInstance()}. No further
     * constructor testing is necessary since only one Gradebook object is ever
     * constructed in the Grader system.  I.e., it is a singleton class.
     *                                                                    <pre>
     *  Test
     *  Case    Input            Output             Remarks
     * ====================================================================
     *   1                       Proper init done   Only case
     *
     */
    @Test
    public void testGetInstance()
    {
        Gradebook gradebook = Gradebook.getInstance();
        
        assertNotNull(gradebook);
    }

    /**
     * Test method for {@link model.gradebook.Gradebook#addSpreadsheetCourse(model.spreadsheet.SpreadsheetCourse)}. 
     * Tests various cases for adding SpreadsheetCourses to the Gradebook. Once
     * the course is added to the Gradebook, it will be available as long as the 
     * program is running. Null courses cannot be added to the Gradebook.
     *                                                                    <pre>
     *  Test
     *  Case   Input              Output             Remarks
     * =============================================================================
     *   1     SpreadsheetCourse  Success            Should be added to the gradebook
     *   2     null               BadDataException   
     *   3     1000 course        Success            (Stress Tests) No gradebook should have more than 20 or so classes
     *
     * @throws CourseDataException 
     */
    @Test
    public void testAddSpreadsheetCourse() throws CourseDataException
    {
        SpreadsheetCourse course = new SpreadsheetCourse(new CourseInfo(
                "Test", "Spring", "01", "111",
                "Comp Sci", 2014), new GradingScheme(), new LatePolicy());
        
        restartGradebook();
        Gradebook gradebook = Gradebook.getInstance();
        
        // Test 1
        try
        {
            gradebook.addSpreadsheetCourse(course);
        }
        catch (BadDataException e)
        {
            e.printStackTrace();
        }
        
        assertEquals(gradebook.getCourses().size(), 1);
        
        // Test 2
        try
        {
            gradebook.addSpreadsheetCourse(null);
        }
        catch (BadDataException e)
        {
            assert(true);
        }
        
        
        // Test 3
        restartGradebook();
        gradebook = Gradebook.getInstance();
        
        for (int i = 0; i < 1000; i++)
        {
            SpreadsheetCourse c = new SpreadsheetCourse(new CourseInfo(
                    "Test", "Spring", "01", "111",
                    "Comp Sci", i + 2015), new GradingScheme(), new LatePolicy());
            try
            {
                gradebook.addSpreadsheetCourse(c);
                assertEquals(gradebook.getCourses().size(), i + 1);
            }
            catch (BadDataException e)
            {
                e.printStackTrace();
            }
        }
        
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
     * Ensure that after a call made to clearGradebook that the "currentGradebook"
     * file is in fact actually empty.
     *                                                                    <pre>
     *  Test
     *  Case    Input            Output                            Remarks
     * ================================================================================
     *   1     clearGradebook()  empty "currentGradebook" file          
     *   2     add to Gradebook
     *         clearGradebook()  empty "currentGradebook" file
     *         
     * @throws FileNotFoundException 
     *
     */
    @Test
    public void testClearGradebook() throws FileNotFoundException
    {
        Scanner scanner;
        Gradebook gradebook = Gradebook.getInstance();
        
        //Test Case 1
        gradebook.saveGradebook();
        gradebook.clearGradebook();
        
        scanner = new Scanner(new File("currentGradebook"));
        assertEquals(scanner.hasNext(), false);
        scanner.close();
        
        // Test Case 2
        scanner = new Scanner(new File("currentGradebook"));
        gradebook.addGradingScheme(new GradingScheme());
        gradebook.saveGradebook();
        gradebook.clearGradebook();
        
        assertEquals(scanner.hasNext(), false);
        scanner.close();
    }

    /**
     * Test method for {@link model.gradebook.Gradebook#saveGradebook()}.
     * Ensure that after a call made to saveGradebook that the 'currentGradebook'
     * file actually contains the serialized gradebook within in.
     *                                                                    <pre>
     *  Test
     *  Case    Input            Output                              Remarks
     * ================================================================================
     *   1      saveGradebook()  ensure saved gradebook is equal to
     *                           gradebook before saving
     *   2      Repeat step 1
     *          with different info   
     *           
     * @throws CourseDataException 
     * @throws IOException 
     * @throws ClassNotFoundException 
     * @throws BadDataException 
     */
    @Test
    public void testSaveGradebook() throws CourseDataException, IOException, ClassNotFoundException, BadDataException
    {
        Gradebook gradebook = Gradebook.getInstance();
        FileInputStream fin;
        ObjectInputStream ois;
        Gradebook gradebook2;
        
        // Run 1
        SpreadsheetCourse course = new SpreadsheetCourse(new CourseInfo(
                "Test", "Spring", "01", "111",
                "Comp Sci", 2014), new GradingScheme(), new LatePolicy());
        
        gradebook.addSpreadsheetCourse(course);
        gradebook.setCurrentCourse(course);
        gradebook.saveGradebook();
        
        fin = new FileInputStream("currentGradebook");
        ois = new ObjectInputStream(fin);
        gradebook2 = (Gradebook) ois.readObject();
        
        assertEquals(gradebook.getCurrentCourse(), gradebook2.getCurrentCourse());
        ois.close();
        fin.close();
        
        // Run 2
        SpreadsheetCourse course2 = new SpreadsheetCourse(new CourseInfo(
                "Test2", "Spring", "02", "222",
                "Comp Sci", 2014), new GradingScheme(), new LatePolicy());
        
        gradebook.addSpreadsheetCourse(course2);
        gradebook.saveGradebook();
        fin = new FileInputStream("currentGradebook");
        ois = new ObjectInputStream(fin);
        gradebook2 = (Gradebook) ois.readObject();
        
        assertEquals(gradebook.getCurrentCourse(), gradebook2.getCurrentCourse());
        ois.close();
        fin.close();
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
    
    private void restartGradebook()
    {
        Gradebook.getInstance().clearGradebook();
        
        File file = new File("currentGradebook");
        file.delete();
    }

}
