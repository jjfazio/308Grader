/**
 * 
 */
package implementation.model.spreadsheet;

import static org.junit.Assert.*;
import model.spreadsheet.GradeRange;

import org.junit.Before;
import org.junit.Test;


/****
*
* Class GradeRangeTest is the companion testing class for class GradeRange.
* It implements the following module test plan:
*                                                                         <ul>
*                                                                      <p><li>
*     Phase 1: Unit test the constructor.
*                                                                      <p><li>
*     Phase 2: Unit test the simple access methods getLetterGrade, 
*              getLow, and getHigh.
*                                                                      <p><li> 
*     Phase 3: Unit test the rest of the access methods.
*                                                                        </ul>
*/

/**
 * @author Kevin Backers
 *
 */
public class GradeRangeTest
{

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
    }

    /**
     * Test method for {@link model.spreadsheet.GradeRange#GradeRange()}.
     * Unit test constructor by calling constructor for a GradeRange 
     *                                                                    <pre>
     *  Test
     *  Case    Input                  Output                   Remarks
     * ================================================================================
     *   1      GradeRange.name     proper initialization   Set symbol to empty string  
     *            == null               done
     *            
     *   2      GradeRange.name     proper initialization   3 fields are set as expected
     *            != null               done   
     *
     *   3      GradeRange.name     proper initialization   3 fields are set as expected
     *            = 1 letter            done
     *            
     *   4      GradeRange.name     proper initialization   3 fields are set as expected
     *            > 1 letter            done  
     *
     *   5      GradeRange.low     proper initialization    3 fields are set as expected
     *            is a double            done                                           
     *                                                                   </pre>
     */
    @Test
    public void testGradeRange()
    {
        GradeRange gr1 = new GradeRange(null, 0, 0);
        assertEquals(gr1.getLetterGrade(), "");
        
        GradeRange gr2 = new GradeRange("A", 0, 0);
        assertEquals(gr2.getLetterGrade(), "A");
        
        GradeRange gr3 = new GradeRange("A", 0, 90);
        assertEquals(gr3.getLetterGrade(), "A");
        assert(gr3.getLow().equals(0.0));
        assert(gr3.getHigh().equals(90.0));
        
        GradeRange gr4 = new GradeRange("pass", 5, 10);
        assertEquals(gr4.getLetterGrade(), "pass");
        assert(gr4.getLow().equals(5.0));
        assert(gr4.getHigh().equals(10.0));
        
        GradeRange gr5 = new GradeRange("fail", 10.5, 50.0);
        assertEquals(gr5.getLetterGrade(), "fail");
        assert(gr5.getLow().equals(10.5));
        assert(gr5.getHigh().equals(50.0));
    }

    /**
     * Test method for {@link model.spreadsheet.GradeRange#getLetterGrade()}.
     * Unit test getter method for the letter grade 
     *                                                                    <pre>
     *  Test
     *  Case    Input                   Output              Remarks
     * ================================================================================
     *   1      GradeRange.letterGrade  return the string   return ""  
     *            == ""                   
     *            
     *   2      GradeRange.letterGrade  return the string   return the letter
     *            1 letter                   
     *
     *   3      GradeRange.letterGrade  return the string   return the string
     *            > 4 letters               
     *
     *   4      GradeRange.letterGrade  return the string   return the string
     *            > 10 letters                                                                    
     *                                                                   </pre>
     */
    @Test
    public void testGetLetterGrade()
    {
        GradeRange gr1 = new GradeRange("", 90, 100);
        assertEquals(gr1.getLetterGrade(), "");
        
        GradeRange gr2 = new GradeRange("F", 90, 100);
        assertEquals(gr2.getLetterGrade(), "F");
        
        GradeRange gr3 = new GradeRange("A++++", 105, 110);
        assertEquals(gr3.getLetterGrade(), "A++++");
        
        GradeRange gr4 = new GradeRange("passingGrade", 71, 100);
        assertEquals(gr4.getLetterGrade(), "passingGrade");
    }

    /**
     * Test method for {@link model.spreadsheet.GradeRange#getLow()}.
     * Unit test getter method for the low number
     *                                                                    <pre>
     *  Test
     *  Case    Input               Output      Remarks
     * ================================================================================
     *   1      GradeRange.low      return 0.0      data unchanged  
     *            == 0.0                   
     *            
     *   2      GradeRange.low      return 1.1      data unchanged
     *            == 1.1                   
     *
     *   3      GradeRange.low      return 50.5     data unchanged
     *            == 50.5               
     *
     *   4      GradeRange.low      return 101.0    data unchanged
     *            == 101.0                                                                    
     *                                                                   </pre>
     */
    @Test
    public void testGetLow()
    {
        GradeRange gr1 = new GradeRange("", 0.0, 100.0);
        assertEquals(gr1.getLow(), Double.valueOf(0.0));
        
        GradeRange gr2 = new GradeRange("",1.1, 100.0);
        assertEquals(gr2.getLow(), Double.valueOf(1.1));
        
        GradeRange gr3 = new GradeRange("", 50.5, 100.0);
        assertEquals(gr3.getLow(), Double.valueOf(50.5));
        
        GradeRange gr4 = new GradeRange("", 101.0, 102.0);
        assertEquals(gr4.getLow(), Double.valueOf(101.0));
    }

    /**
     * Test method for {@link model.spreadsheet.GradeRange#getHigh()}.
     * Unit test getter method for the high number 
     *                                                                    <pre>
     *  Test
     *  Case    Input               Output      Remarks
     * ================================================================================
     *   1      GradeRange.high      return 0.0      data unchanged  
     *            == 1.0                   
     *            
     *   2      GradeRange.high      return 50.5      data unchanged
     *            == 50.5                   
     *
     *   3      GradeRange.high      return 99.1     data unchanged
     *            == 99.1               
     *
     *   4      GradeRange.high      return 101.0    data unchanged
     *            == 101.0                                                                    
     *                                                                   </pre>
     */
    @Test
    public void testGetHigh()
    {
        GradeRange gr1 = new GradeRange("", 0.0, 1.0);
        assertEquals(gr1.getHigh(), Double.valueOf(1.0));
        
        GradeRange gr2 = new GradeRange("", 0.0, 50.5);
        assertEquals(gr2.getHigh(), Double.valueOf(50.5));
        
        GradeRange gr3 = new GradeRange("", 0.0, 99.1);
        assertEquals(gr3.getHigh(), Double.valueOf(99.1));
        
        GradeRange gr4 = new GradeRange("", 0.0, 101.0);
        assertEquals(gr4.getHigh(), Double.valueOf(101.0));
    }

}
