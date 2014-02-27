package implementation.model.assignments_categories;

import static org.junit.Assert.*;

import java.util.Date;

import model.assignments_categories.Grade;
import model.exception.BadDataException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Grade class' functionality
 * 
 * @author erikowen
 *
 */
public class GradeTest
{
	
/****
 *
 * Class GraphTest is the companion testing class for class Graph.java.
 * It implements the following module test plan:
 *                                                                         <ul>
 *                                                                      <p><li>
 *     Phase 1: Unit test the constructor.
 *                                                                      <p><li>
 *     Phase 2: Unit test the setter method for TurnedIn
 *                                                                      <p><li>
 *     Phase 3: Unit test the getter method for TurnedIn
 *                                                                      <p><li>
 *     Phase 4: Unit test the setter method for the score
 *                                                                      <p><li>
 *     Phase 5: Unit test the getter method for the score
 *                                                                      <p><li>
 */

	
    /**
     * Unit test the Grade constructor by building a Grade object.
     *                                                              
     *  Test
     *  Case    Input                    Output             Remarks
     * ====================================================================
     *   1      grade != null            null               Grade should be properly initialized
     *   2      grade == null			 null				Improper data field passed in, BadDataException thrown
     *
     */
    @Test
    public void testGrade()
    {
        try {
			Grade grade = new Grade(new Date(), "95.2");
			assertTrue(grade != null);
		}
        catch (BadDataException e) {
        	/*Should never throw a bad data exception with the string "95.2"*/
			fail();
		}
        
        Grade grade2 = null;
        try {
        	grade2 = new Grade(new Date(), ".8.92");
        }
        catch (BadDataException e) {
        	assertTrue(grade2 == null);
		}
    }

    /**
     * Unit test the Graph's getTurnedIn() method.
     *                                                              
     *  Test
     *  Case    Input                          Output             Remarks
     * ====================================================================
     *   1      grade != null                  null               Grade should be properly initialized
     *   2      grade == null			       null				  Improper data field passed in, BadDataException thrown
     *   3      grade.getTurnedIn throws NPE   null               Accessing getTurnedIn throws NPE if constructor fails
     *
     */
    @Test
    public void testGetTurnedIn()
    {
    	Date curDate = new Date();
    	Grade grade = null;
        try {
			grade = new Grade(curDate, "96.2");
		}
        catch (BadDataException e) {
        	/*Data is properly formatted, should not reach this block*/
			fail();
		}
        
        assertEquals(curDate, grade.getTurnedIn());
        
        Date curDate2 = new Date();
        Grade grade2 = null;
        boolean grade2IsNull = false;
        try {
        	grade2 = new Grade(curDate2, "-100");
        }
        catch(BadDataException e) {
        	assertTrue(grade2 == null);
        	try {
        		grade2.getTurnedIn();
        	}
        	catch(NullPointerException ex) {
        		grade2IsNull = true;
        	}
        }
        assertTrue(grade2IsNull);
    }

    /**
     * Unit test the Grade's setTurnedIn() method.
     *                                                              
     *  Test
     *  Case    Input                                        Output             Remarks
     * =========================================================================================
     *   1      fail() when grade.setTurnedIn() gets called  null               Grade should not be able to setTurnedIn() if Grade object was not properly initialized
     *   2      grade == null			                     null				Improper data field passed in, BadDataException thrown
     *   3      grade2.getTurnedIn() == newDate              null               the setTurnedIn() method works if getTurnedIn() method returns new date that was set
     *
     */
    @Test
    public void testSetTurnedIn()
    {
    	Grade grade = null;
        try {
        	grade = new Grade(new Date(), "A+");
        	/*The code below should never get called because above line throws BDE*/
        	grade.setTurnedIn(new Date());
        	fail();
        }
        catch(BadDataException e) {
        	assertTrue(grade == null);
        }
        
        try {
        	Date newDate = new Date(2014, 4, 4);
			Grade grade2 = new Grade(new Date(), "44.1");
			grade2.setTurnedIn(newDate);
			
			assertEquals(newDate, grade2.getTurnedIn());
		}
        catch (BadDataException e) {
			fail();
		}
    }

    /**
     * Unit test the Grade's getScore() method.
     *                                                              
     *  Test
     *  Case    Input                                 Output             Remarks
     * =========================================================================================
     *   1      93.2 == grade.getScore()              null               Grade should not be able to setTurnedIn() if Grade object was not properly initialized
     *   
     */
    @Test
    public void testGetScore()
    {
        try
        {
			Grade grade = new Grade(new Date(), "93.2");
			assertEquals(new Double(93.2), grade.getScore());
			
		}
        catch (BadDataException e) {
			fail();
		}
    }
    
    /**
     * Unit test the Grade's setScore() method.
     *                                                              
     *  Test
     *  Case    Input                                 Output             Remarks
     * =========================================================================================
     *   1      ------------------------              null               Grade should not be able to setTurnedIn() if Grade object was not properly initialized
     *   
     */
    @Test
    public void testSetScore()
    {
        /*fail("Not yet implemented");*/
    }

    /**
     * Test method for {@link model.assignments_categories.Grade#getLetterGrade()}.
     */
    @Test
    public void testGetLetterGrade()
    {
        /*fail("Not yet implemented");*/
    }

}
