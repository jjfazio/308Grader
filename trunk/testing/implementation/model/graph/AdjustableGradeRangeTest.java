package implementation.model.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.exception.BadDataException;
import model.exception.OverlappingRangesException;
import model.graph.AdjustableGradeRange;
import model.spreadsheet.GradeRange;
import org.junit.Test;

public class AdjustableGradeRangeTest {

	/****
	 *
	 * Class AdjustableGradeRangeTest is the companion testing class for class
	 * AdjustableGradeRange.java. It implements the following module test plan:
	 *                                                                         <ul>
	 *                                                                      <p><li>
	 *     Phase 1: Unit test the constructor.
	 *                                                                      <p><li>
	 *     Phase 2: Unit test the setter and getter methods for the low field.
	 *                                                                      <p><li>
	 *     Phase 3: Unit test the setter and getter methods for the high field.
	 *                                                                      <p><li>
	 *     Phase 4: Unit test getGradeRangeVersion method.
	 *                                                                      <p><li>
	 *     Phase 5: Unit test the static method checkForOverlappingRanges.
	 *                                                                        </ul>                                                                 
	 *     Phase 6: Unit test the setStudentsInRange method.
	 *                                                                        </ul>                                                                  
	 */
	
	public AdjustableGradeRangeTest() {
		
	}

    /**
     * Unit test the constructor by building an AdjustableGradeRange object,
     * then checking to make sure all of the data passed into the constructor
     * is represented properly by the class
     *                                                              
     *  Test
     *  Case       Input                                                             Output        Remarks
     * =====================================================================================================================
     *   1         adjustableRange.getStudsInRange() != null                         null          The studsInRange value for the AdjustableGradeRangeObject is not null
     *   2         adjustableRange.getHigh() != null                                 null          The high value for the AdjustableGradeRangeObject is not null
     *   3         adjustableRange.getLow() != null                                  null          The low value for the AdjustableGradeRangeObject is not null
     *   5         adjustableRange.getLetterGrade().equals("A")                      null          The symbol is set correctly
     *   6         adjustableRange.getHigh() == 100.0                                null          The high value for the AdjustableGradeRangeObject is set correctly
     *   7         adjustableRange.getLow() == 90.0                                  null          The low value for the AdjustableGradeRangeObject is set correctly
     */
	@Test
	public void testConstructor() {
		GradeRange range = new GradeRange("A", 90.0, 100.0);
		Map<String, Integer> studInRangeMap = new HashMap<String, Integer>();
		studInRangeMap.put("A", 10);
		AdjustableGradeRange adjustableRange = new AdjustableGradeRange(range, studInRangeMap);
		
		assertTrue(adjustableRange.getStudsInRange() != null);
		assertTrue(adjustableRange.getHigh() != null);
		assertTrue(adjustableRange.getLow() != null);
		assertEquals("A", adjustableRange.getLetterGrade());
		
		assertEquals(Double.valueOf(90.0), adjustableRange.getLow());
		assertEquals(Double.valueOf(100.0), adjustableRange.getHigh());
	}
	
    /**
     * Unit test the getter and setter methods for the low field
     * by building an AdjustableGradeRange object, then using the setter methods to
     * change the low field, then using the getter methods to make sure it was set properly.
     *                                                              
     *  Test
     *  Case       Input                                                             Output        Remarks
     * =====================================================================================================================
     *   1         fail() occurs if BadDataException occurs                          null          Makes sure a BadDataException is not thrown when correct input is passed in to setter method
     *   2         Double.valueOf(50.0) == adjustableRange.getLow()                  null          The low value is initially set correctly
     *   3         assertTrue(true)                                                  null          Makes sure a BadDataException is thrown when incorrect input is passed in to setter method
     */	
	@Test
	public void testGetAndSetLow() {
		GradeRange range = new GradeRange("A", 90.0, 100.0);
		Map<String, Integer> studInRangeMap = new HashMap<String, Integer>();
		studInRangeMap.put("A", 10);
		AdjustableGradeRange adjustableRange = new AdjustableGradeRange(range, studInRangeMap);
		
		try {
			adjustableRange.setLow("50.0");
		}
		catch (BadDataException e) {
			//Shouldn't reach this block of code.
			fail();
		}
		
		assertEquals(Double.valueOf(50.0), adjustableRange.getLow());
		
		try {
			adjustableRange.setLow("ABC");
			fail();
		}
		catch (BadDataException e) {
			//Should always hit this block of code
			assertTrue(true);
		}
	}
	
    /**
     * Unit test the getter and setter methods for the high field
     * by building an AdjustableGradeRange object, then using the setter methods to
     * change the high field, then using the getter methods to make sure it was set properly.
     *                                                              
     *  Test
     *  Case       Input                                                             Output        Remarks
     * =====================================================================================================================
     *   1         fail() occurs if BadDataException occurs                          null          Makes sure a BadDataException is not thrown when correct input is passed in to setter method
     *   2         Double.valueOf(90.0) == adjustableRange.getHigh()                 null          The high value is initially set correctly
     *   3         assertTrue(true)                                                  null          Makes sure a BadDataException is thrown when incorrect input is passed in to setter method
     */
	@Test
	public void testGetAndSetHigh() {
		GradeRange range = new GradeRange("A", 90.0, 100.0);
		Map<String, Integer> studInRangeMap = new HashMap<String, Integer>();
		studInRangeMap.put("A", 10);
		AdjustableGradeRange adjustableRange = new AdjustableGradeRange(range, studInRangeMap);
		
		try {
			adjustableRange.setHigh("90.0");
		}
		catch (BadDataException e) {
			//Shouldn't reach this block of code.
			fail();
		}
		
		assertEquals(Double.valueOf(90.0), adjustableRange.getHigh());
		
		try {
			adjustableRange.setLow("ABC");
			fail();
		}
		catch (BadDataException e) {
			//Should always hit this block of code
			assertTrue(true);
		}
	}
	
    /**
     * Unit test the getGradeRangeVersion method by building an AdjustableGradeRange object,
     * then calling the getGradeRangeVersion and testing to make sure all of the fields of
     * the object returned match the criteria for which the AdjustagbleGradeRange object was
     * decorated around.
     *                                                              
     *  Test
     *  Case       Input                                                             Output        Remarks
     * =====================================================================================================================
     *   1         newRange.getHigh() == derivedRange.getHigh()                      null          Makes sure the returned GradeRange object has the correct 'high' field
     *   2         newRange.getLow() == derivedRange.getLow()                        null          Makes sure the returned GradeRange object has the correct 'low' field
     *   3         newRange.getLetterGrade() == derivedRange.getLetterGrade()        null          Makes sure the returned GradeRange object has the correct 'letterGrade' symbol field
     *   4         Integer.valueOf(7) == newAdjRange.getStudsInRange()               null          Makes sure the decorator AdjustableGradeRange object has the correct number of students in it's range
     */
	@Test
	public void testGetGradeRangeVersion() {
		GradeRange newRange = new GradeRange("B", 80.0, 90.0);
		Map<String, Integer> studInRangeMap = new HashMap<String, Integer>();
		studInRangeMap.put("A", 10);
		studInRangeMap.put("B", 7);
		AdjustableGradeRange newAdjRange = new AdjustableGradeRange(newRange, studInRangeMap);
		GradeRange derivedRange = newAdjRange.getGradeRangeVersion();
		
		assertEquals(newRange.getHigh(), derivedRange.getHigh());
		assertEquals(newRange.getLow(), derivedRange.getLow());
		assertEquals(newRange.getLetterGrade(), derivedRange.getLetterGrade());
		
		assertEquals(Integer.valueOf(7), newAdjRange.getStudsInRange());
	}
	
    /**
     * Unit test the static checkForOverlappingRanges method by creating a list of GradeRange
     * objects, have two of the GradeRanges intersect, and make sure the correct behavior occurs.
     *                                                              
     *  Test
     *  Case       Input                                                             Output        Remarks
     * =====================================================================================================================
     *   1         fail()                                                            null          Makes sure an OverlappingRangesException is thrown.
     *   2         Double.valueOf(60.0) == e.getFirstRange().getLow()                null          Makes sure the Exception thrown has the correct data so the user can see which ranges are overlapping.
     *   3         Double.valueOf(70.0) == e.getFirstRange().getHigh()               null          Makes sure the Exception thrown has the correct data so the user can see which ranges are overlapping.
     *   4         Double.valueOf(00.0) == e.getSecondRange().getLow()               null          Makes sure the Exception thrown has the correct data so the user can see which ranges are overlapping.
     *   5         Double.valueOf(66.0) == e.getSecondRange().getHigh()              null          Makes sure the Exception thrown has the correct data so the user can see which ranges are overlapping.
     */
	@Test
	public void testCheckForOverlappingRanges() {
		GradeRange rangeA = new GradeRange("A", 90.0, 100.0);
		GradeRange rangeB = new GradeRange("B", 80.0, 90.0);
		GradeRange rangeC = new GradeRange("C", 70.0, 80.0);
		GradeRange rangeD = new GradeRange("D", 60.0, 70.0);
		GradeRange rangeF = new GradeRange("F", 00.0, 66.0);
		
		Map<String, Integer> studInRangeMap = new HashMap<String, Integer>();
		studInRangeMap.put("A", 8);
		studInRangeMap.put("B", 8);
		studInRangeMap.put("C", 8);
		studInRangeMap.put("D", 8);
		studInRangeMap.put("F", 8);
		
		AdjustableGradeRange aRangeAdjustableGradeRange = new AdjustableGradeRange(rangeA, studInRangeMap);
		AdjustableGradeRange bRangeAdjustableGradeRange = new AdjustableGradeRange(rangeB, studInRangeMap);
		AdjustableGradeRange cRangeAdjustableGradeRange = new AdjustableGradeRange(rangeC, studInRangeMap);
		AdjustableGradeRange dRangeAdjustableGradeRange = new AdjustableGradeRange(rangeD, studInRangeMap);
		AdjustableGradeRange fRangeAdjustableGradeRange = new AdjustableGradeRange(rangeF, studInRangeMap);

		List<AdjustableGradeRange> ranges = new ArrayList<AdjustableGradeRange>();
		ranges.add(aRangeAdjustableGradeRange);
		ranges.add(bRangeAdjustableGradeRange);
		ranges.add(cRangeAdjustableGradeRange);
		ranges.add(dRangeAdjustableGradeRange);
		ranges.add(fRangeAdjustableGradeRange);
		
		try {
			
			AdjustableGradeRange.checkForOverlappingRanges(ranges);
			fail();
		}
		catch (OverlappingRangesException e) {
			assertEquals(Double.valueOf(60.0), e.getFirstRange().getLow());
			assertEquals(Double.valueOf(70.0), e.getFirstRange().getHigh());
			assertEquals(Double.valueOf(00.0), e.getSecondRange().getLow());
			assertEquals(Double.valueOf(66.0), e.getSecondRange().getHigh());
		}
	}
	
    /**
     * Unit test the setStudentsInRange method by building an AdjustableGradeRange object,
     * then calling the setStudentsInRange and testing to make sure that the numStudentsInRange
     * field gets set properly
     *                                                              
     *  Test
     *  Case       Input                                                             Output        Remarks
     * =====================================================================================================================
     *   1         Integer.valueOf(10) == newAdjRangeA.getStudsInRange()             null          Verifies that the numStudentsField has the correct integer value for an 'A'
     *   2         Integer.valueOf(7) == newAdjRangeB.getStudsInRange()              null          Verifies that the numStudentsField has the correct integer value for an 'B'
     *   3         Integer.valueOf(9) == newAdjRangeC.getStudsInRange()              null          Verifies that the numStudentsField has the correct integer value for an 'C'
     *   4         Integer.valueOf(8) == newAdjRangeD.getStudsInRange()              null          Verifies that the numStudentsField has the correct integer value for an 'D'
     *   5         Integer.valueOf(3) == newAdjRangeF.getStudsInRange()              null          Verifies that the numStudentsField has the correct integer value for an 'F'
     */
	@Test
	public void testSetStudentsInRange() {
		GradeRange newRangeA = new GradeRange("A", 80.0, 90.0);
		GradeRange newRangeB = new GradeRange("B", 90.0, 100.0);
		GradeRange newRangeC = new GradeRange("C", 70.0, 80.0);
		GradeRange newRangeD = new GradeRange("D", 60.0, 70.0);
		GradeRange newRangeF = new GradeRange("F", 00.0, 60.0);
		Map<String, Integer> studInRangeMap = new HashMap<String, Integer>();
		studInRangeMap.put("A", 10);
		studInRangeMap.put("B", 7);
		studInRangeMap.put("C", 9);
		studInRangeMap.put("D", 8);
		studInRangeMap.put("F", 3);
		AdjustableGradeRange newAdjRangeA = new AdjustableGradeRange(newRangeA, studInRangeMap);
		AdjustableGradeRange newAdjRangeB = new AdjustableGradeRange(newRangeB, studInRangeMap);
		AdjustableGradeRange newAdjRangeC = new AdjustableGradeRange(newRangeC, studInRangeMap);
		AdjustableGradeRange newAdjRangeD = new AdjustableGradeRange(newRangeD, studInRangeMap);
		AdjustableGradeRange newAdjRangeF = new AdjustableGradeRange(newRangeF, studInRangeMap);
		
		assertEquals(Integer.valueOf(10), newAdjRangeA.getStudsInRange());
		assertEquals(Integer.valueOf(7), newAdjRangeB.getStudsInRange());
		assertEquals(Integer.valueOf(9), newAdjRangeC.getStudsInRange());
		assertEquals(Integer.valueOf(8), newAdjRangeD.getStudsInRange());
		assertEquals(Integer.valueOf(3), newAdjRangeF.getStudsInRange());
		
	}

}
