/**
 * 
 */
package implementation.model.spreadsheet;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.exception.CourseDataException;
import model.exception.GradingSchemeDataException;
import model.spreadsheet.CourseInfo;
import model.spreadsheet.GradeRange;
import model.spreadsheet.GradingScheme;

import org.junit.Before;
import org.junit.Test;


/****
*
* Class CourseInfoTest is the companion testing class for class CourseInfo.
* It implements the following module test plan:
*                                                                         <ul>
*                                                                      <p><li>
*     Phase 1: Test the default constructor with no arguments
*                                                                      <p><li>
*     Phase 2: Unit test the simple access methods including getPlusMinusEnabled, 
*              getGradeRanges, and getSchemeName for M4.
*                                                                      <p><li>
*     Phase 3: Unit test the rest of the access methods.
*                                                                      <p><li>
*     Phase 4: Unit test the other constructors with arguments.
*                                                                      <p><li>
*     Phase 5: Unit test the toString and equals methods.
*                                                                      <p><li>
*     Phase 6: Unit test the addGradeRange method.
*                                                                      <p><li>
*     Phase 7: Stress test by adding 100 grade ranges.
*                                                                        </ul>
*/

/**
 * @author Kevin Backers kbackers
 *
 */
public class GradingSchemeTest
{

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
    }

    /**
     * Test method for {@link model.spreadsheet.GradingScheme#GradingScheme()}.
     * Unit test constructor by calling constructor for a GradingScheme 
     *                                                                    <pre>
     *  Test
     *  Case    Input                  Output                   Remarks
     * ================================================================================
     *   1      GradingScheme.name     proper initialization    Default grading scheme  
     *            == null               done
     *                                                                   </pre>
     */
    @Test
    public void testGradingScheme()
    {
        GradingScheme defaultGS = new GradingScheme();
        assertEquals("Default", defaultGS.getSchemeName());
        assertEquals("A", defaultGS.getSymbolFromPercent(95.0));
        assertEquals("B", defaultGS.getSymbolFromPercent(85.0));
        assertEquals("C", defaultGS.getSymbolFromPercent(75.0));
        // test that all new grading schemes are same
        assert(defaultGS.equals(new GradingScheme()));
    }

    /**
     * Test method for {@link model.spreadsheet.GradingScheme#GradingScheme(java.util.List, java.lang.String)}.
     */
    @Test
    public void testGradingSchemeListOfGradeRangeString()
    {
        //fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.spreadsheet.GradingScheme#GradingScheme(java.lang.String)}.
     */
    @Test
    public void testGradingSchemeString()
    {
        //fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.spreadsheet.GradingScheme#getPlusMinusEnabled()}.
     * Unit test getPlusMinusEnabled by calling getPlusMinusEnabled on a GradingScheme with a
     * null and non-null plusMinusEnabled field. 
     *                                                                    <pre>
     *  Test
     *  Case    Input                            Output                      Remarks
     * ==================================================================================
     *   1      GradingScheme.plusMinusEnabled   GradingSchemeDataException  Null case, which throws
     *            == null                                                     the given exception
     *
     *   2      GradingScheme.plusMinusEnabled   same non-null               Non-null case
     *            != null                         value
     *                                                                   </pre>
     */
    @Test
    public void testGetPlusMinusEnabled()
    {
        GradingScheme gs = new GradingScheme();
        assertEquals(false, gs.getPlusMinusEnabled());
        
        ArrayList<GradeRange> ranges = new ArrayList<GradeRange>();
        ranges.add(new GradeRange("A", 90.0, 100.0));
        try {
            gs = new GradingScheme(ranges, "newScheme");
            assertEquals(true, gs.getPlusMinusEnabled());
        } catch (GradingSchemeDataException e) {
            fail(e.getMessage());
        }
        
    }

    /**
     * Test method for {@link model.spreadsheet.GradingScheme#getGradeRanges()}.
     */
    /**
     * Test method for {@link model.spreadsheet.GradingScheme#getSchemeName()}.
     * Unit test getSchemeName by calling getSchemeName on a GradingScheme with a
     * null and non-null name field. 
     *                                                                    <pre>
     *  Test
     *  Case    Input                  Output                      Remarks
     * ====================================================================
     *   1      GradingScheme.name     GradingSchemeDataException  Null case
     *            == null
     *
     *   2      GradingScheme.name     same non-null               Non-null case
     *            =- non-null             value
     *                                                                   </pre>
     */
    @Test
    public void testGetGradeRanges()
    {
        //fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.spreadsheet.GradingScheme#getSchemeName()}.
     * Unit test getSchemeName by calling getSchemeName on a GradingScheme with a
     * null and non-null name field. 
     *                                                                    <pre>
     *  Test
     *  Case    Input                  Output                      Remarks
     * ====================================================================
     *   1      GradingScheme.name     GradingSchemeDataException  Null case
     *            == null
     *
     *   2      GradingScheme.name     same non-null               Non-null case
     *            =- non-null             value
     *                                                                   </pre>
     */
    @Test
    public void testGetSchemeName()
    {
        try {
            List<GradeRange> gradeRanges = new ArrayList<GradeRange>();
            gradeRanges.add(new GradeRange("A", 90.0, 100.0));
            GradingScheme gs = new GradingScheme(gradeRanges, "name");
            gs.setSchemeName(null);
            assertEquals(null, gs.getSchemeName());
        } catch (GradingSchemeDataException e) {
            /** null section should throw exception. */
            fail(e.getMessage());
        }
        
        try {
            List<GradeRange> gradeRanges = new ArrayList<GradeRange>();
            gradeRanges.add(new GradeRange("A", 90.0, 100.0));
            GradingScheme gs = new GradingScheme(gradeRanges, "scheme1");
            assert("scheme1".equals(gs.getSchemeName()));
        } catch (GradingSchemeDataException e) {
            fail(e.getMessage());
        }
    }

}
