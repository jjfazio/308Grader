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

/**
 * @author jamesfazio
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
     */
    @Test
    public void testGradingScheme()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.spreadsheet.GradingScheme#GradingScheme(java.util.List, java.lang.String)}.
     */
    @Test
    public void testGradingSchemeListOfGradeRangeString()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.spreadsheet.GradingScheme#GradingScheme(java.lang.String)}.
     */
    @Test
    public void testGradingSchemeString()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.spreadsheet.GradingScheme#getPlusMinusEnabled()}.
     */
    @Test
    public void testGetPlusMinusEnabled()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.spreadsheet.GradingScheme#getGradeRanges()}.
     */
    @Test
    public void testGetGradeRanges()
    {
        fail("Not yet implemented");
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
