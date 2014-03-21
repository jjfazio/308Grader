/**
 *
 */
package implementation.model.assignments_categories;

import static org.junit.Assert.*;

import model.assignments_categories.Assignment;
import model.assignments_categories.Category;
import model.spreadsheet.GradingScheme;
import model.spreadsheet.LatePolicy;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

/****
 *
 * Class AssignmentTest is the companion testing class for class
 * {@link model.assignments_categories.Assignment}
 * It implements the following module test plan:
 *                                                                         <ul>
 *                                                                      <p><li>
 *     Phase 1: Unit test the constructors.
 *                                                                      <p><li>
 *     Phase 2: Unit test the access methods getName, getPercentCategory,
 *              getMaxPoints, getDueDate, getPolicy, getPercentCurve
 *                                                                      <p><li>
 *     Phase 3: Unit test adjustCurve
 *                                                                      <p><li>
 *     Phase 4: Unit test setName, setMaxPoints, setPolicy, setPercentCurve
 *                                                                      <p><li>
 *                                                                        </ul>
 *     @author Jirbert Dilanchian
 */
public class AssignmentTest
{

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
    }

    /**
     * Test method for {@link model.assignments_categories.Assignment#Assignment()}.
     */
    /**
     * Test method for {@link model.assignments_categories.Category#Category()}.
     */
    /**
     * Unit test creating an instance root Category.
     *  <pre>
     *  Test
     *  Case    Input                    Output                                   Remarks
     * ====================================================================
     *   1      none                Properly instantiate instance of Assignment        Only Case
     */
    @Test
    public void testAssignment()
    {
        Assignment testAss = new Assignment();
        assertTrue(testAss != null);
    }

    /**
     * Test method for {@link model.assignments_categories.Assignment#(java.lang.String, java.lang.Double, java.lang.Integer, java.util.Date, model.spreadsheet.GradingScheme, model.spreadsheet.LatePolicy, java.lang.Boolean)}.
     */
    /**
     * Unit test creating an instance root Category.
     *  <pre>
     *  Test
     *  Case    Input                         Output                                             Remarks
     * =========================================================================
     *   1      String name, double weight    Properly instantiate instance of Assignment        Only Case
     *          int points,
     *          GregorianCalendar date,
     *
     */
    @Test

    public void testAssignmentStringDoubleIntegerDateGradingSchemeLatePolicyBoolean()
    {
        Assignment newAss = new Assignment(new Category(), "theName", 3.4, 50, new GregorianCalendar(),
                new GradingScheme(), new LatePolicy(), false);
        assertEquals(newAss.getName(), "theName");
        assertEquals(newAss.getPercentOfCategory().toString(), "3.4");
        assertTrue(newAss.getMaxPoints() == 50);
     }

    /**
     * Test method for {@link model.assignments_categories.Assignment#adjustAssignmentCurve(java.lang.Double)}.
     */
    @Test
    public void testAdjustAssignmentCurve()
    {

    }

    /**
     * Test method for {@link model.assignments_categories.Assignment#getName()}.
     */
    @Test
    public void testGetName()
    {

    }

    /**
     * Test method for {@link model.assignments_categories.Assignment#setName(java.lang.String)}.
     */
    @Test
    public void testSetName()
    {

    }

    /**
     * Test method for {@link model.assignments_categories.Assignment#getPercentOfCategory()}.
     */
    @Test
    public void testGetPercentOfCategory()
    {

    }

    /**
     * Test method for {@link model.assignments_categories.Assignment#setPercentOfCategory(double)}.
     */
    @Test
    public void testSetPercentOfCategory()
    {

    }

    /**
     * Test method for {@link model.assignments_categories.Assignment#getMaxPoints()}.
     */
    @Test
    public void testGetMaxPoints()
    {

    }

    /**
     * Test method for {@link model.assignments_categories.Assignment#setMaxPoints(java.lang.Integer)}.
     */
    @Test
    public void testSetMaxPoints()
    {

    }

    /**
     * Test method for {@link model.assignments_categories.Assignment#getDueDate()}.
     */
    @Test
    public void testGetDueDate()
    {

    }

    /**
     * Test method for {@link model.assignments_categories.Assignment#setDueDate(java.util.GregorianCalendar)}.
     */
    @Test
    public void testSetDueDate()
    {

    }

    /**
     * Test method for {@link model.assignments_categories.Assignment#getgScheme()}.
     */
    @Test
    public void testGetgScheme()
    {

    }

    /**
     * Test method for {@link model.assignments_categories.Assignment#setgScheme(model.spreadsheet.GradingScheme)}.
     */
    @Test
    public void testSetgScheme()
    {

    }

    /**
     * Test method for {@link model.assignments_categories.Assignment#getPolicy()}.
     */
    @Test
    public void testGetPolicy()
    {
    }

    /**
     * Test method for {@link model.assignments_categories.Assignment#setPolicy(model.spreadsheet.LatePolicy)}.
     */
    @Test
    public void testSetPolicy()
    {
    }

    /**
     * Test method for {@link model.assignments_categories.Assignment#getHasElectronicTurnin()}.
     */
    @Test
    public void testGetHasElectronicTurnin()
    {
    }

    /**
     * Test method for {@link model.assignments_categories.Assignment#setHasElectrionicTurnin(java.lang.Boolean)}.
     */
    @Test
    public void testSetHasElectrionicTurnin()
    {
    }

    /**
     * Test method for {@link model.assignments_categories.Assignment#getPercentCurve()}.
     */
    @Test
    public void testGetPercentCurve()
    {
    }

    /**
     * Test method for {@link model.assignments_categories.Assignment#setPercentCurve(java.lang.Double)}.
     */
    @Test
    public void testSetPercentCurve()
    {
    }

}
