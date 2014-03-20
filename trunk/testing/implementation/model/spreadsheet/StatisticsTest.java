package implementation.model.spreadsheet;

import model.spreadsheet.Statistics;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/****
 *
 * Class StatisticsTest is the companion testing class for class
 * {@link model.gradebook.Statistics}
 * It implements the following module test plan:
 *                                                                         <ul>
 *                                                                      <p><li>
 *     Phase 1: Unit test the constructor.
 *                                                                      <p><li>
 *     Phase 2: Unit test the access method getStatName
 *                                                                      <p><li>
 *     Phase 3: Unit test the calculation methods: calcMean, calcMedian,
 *              calcMode, calcNumAssignmentsGraded, calcRange, and
 *              calcStandardDeviation.
 *
 *                                                                        </ul>
 *     @author Kevin Feutz
 */
public class StatisticsTest
{

    /**
     * Test method for {@link model.spreadsheet.Statistics#Statistics(String, java.util.List)}.
     *
     * Construct a Statistics. Ensure that once the Statistics
     * is created that it is not null.
     *                                                                    <pre>
     *  Test
     *  Case    Input                                    Output             Remarks
     * =================================================================================
     *   1      SpreadsheetCourse("test", null)          Success            Tests creating
     *                                                                      a new statistic
     *
     */
    @Test
    public void testConstructor()
    {
        Statistics stats = new Statistics("test", null);
        assertNotNull(stats);
    }

    /**
     * Test method for {@link model.spreadsheet.Statistics#getStatName()}.
     *
     * Construct a Statistics. After Statistics is correctly instantiated, call the getStatName()
     * method.
     *                                                                    <pre>
     *  Test
     *  Case    Input                                    Output             Remarks
     * =================================================================================
     *   1      SpreadsheetCourse("test", null)          getStatName()      Tests getStatName()
     *                                                   should return      method.
     *                                                   "test"
     *
     */
     public void testGetStatName()
     {
         Statistics stats = new Statistics("test", null);
         assertEquals("test", stats.getStatName());
     }
}
