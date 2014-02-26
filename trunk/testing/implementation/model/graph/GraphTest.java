/**
 * 
 */
package implementation.model.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.assignments_categories.Assignment;
import model.assignments_categories.Category;
import model.assignments_categories.Grade;
import model.exception.CourseDataException;
import model.graph.Graph;
import model.spreadsheet.CourseInfo;
import model.spreadsheet.SpreadsheetCourse;
import model.users.Student;

import org.junit.Test;

/**
 * @author erikowen
 *
 */
public class GraphTest
{

/****
 *
 * Class GraphTest is the companion testing class for class Graph.java.
 * It implements the following module test plan:
 *                                                                         <ul>
 *                                                                      <p><li>
 *     Phase 1: Unit test the constructor.
 *                                                                      <p><li>
 *     Phase 2: Unit test the setter and getter methods for categories
 *                                                                      <p><li>
 *     Phase 3: Unit test the setter and getter methods for assignments
 *                                                                      <p><li>
 *     Phase 4: Unit test applyStandardCurve method
 *                                                                      <p><li>
 *     Phase 5: Unit test the getAssignmentBarChartData for 1% granularity,
 *     and then 10% granularity
 *                                                                      <p><li>
 *     Phase 6: Unit test the getAssignmentPieChartData
 *                                                                        </ul>
 */	
	

    /**
     * Unit test the Graph constructor by building a Graph object.
     *                                                              
     *  Test
     *  Case    Input            Output                       Remarks
     * ====================================================================
     *   1      null             null                         Null case
     *   2      null			 null                         Null case
     *   3      null             null                         Null case
     *
     */
    @Test
    public void testGraph()
    {
    	Graph graph = new Graph();
    	assertTrue(graph.getAssignment() == null);
    	assertTrue(graph.getCategory() == null);
    	assertTrue(graph.getStudents() == null);
    }

    /**
     * Unit test the setCategory method by building a Graph object, a Category
     * object, then setting the graph's category to the category object.
     *                                                              
     *  Test
     *  Case    Input                           Output            Remarks
     * ====================================================================
     *   1      cat == graph.getCategory()      null              graph should have ref to cat
     *   2      students = graph.getStudents()  null              graph should have ref to student list
     */
    @Test
    public void testSetCategory()
    {
    	Graph graph = new Graph();
    	Category cat = new Category();
    	List<Student> students = new ArrayList<Student>();
    	
    	graph.setCategory(cat, students);
    	assertEquals(cat, graph.getCategory());
    	assertEquals(students, graph.getStudents());
    }

    /**
     * Unit test the setAssignment method by building a Graph object, an Assignment
     * object, then setting the graph's assignment to the assignment object.
     *                                                              
     *  Test
     *  Case    Input                           Output            Remarks
     * ====================================================================
     *   1      ass == graph.getAssignment()    null              graph should have ref to ass
     *   2      students = graph.getStudents()  null              graph should have ref to student list
     */
    @Test
    public void testSetAssignment()
    {
    	Graph graph = new Graph();
    	Assignment ass = new Assignment();
    	List<Student> students = new ArrayList<Student>();
    	
    	graph.setAssignment(ass, students);
    	assertEquals(ass, graph.getAssignment());
    	assertEquals(students, graph.getStudents());
    }

    /**
     * Test method for {@link model.graph.Graph#applyCustomCategoryCurve(java.util.HashMap)}.
     */
    @Test
    public void testApplyCustomCategoryCurve()
    {
        /*fail("Not yet implemented");*/
    }

    /**
     * Test method for {@link model.graph.Graph#applyCustomAssignmentCurve(java.util.HashMap)}.
     */
    @Test
    public void testApplyCustomAssignmentCurve()
    {
        /*fail("Not yet implemented");*/
    }

    /**
     * Test method for {@link model.graph.Graph#applyStandardCurve(int)}.
     */
    @Test
    public void testApplyStandardCurve()
    {
        /*fail("Not yet implemented");*/
    }
    
    
    /**
     * Unit test the setAssignment method by building a Graph object, an Assignment
     * object, then setting the graph's assignment to the assignment object.
     *                                                              
     *  Test
     *  Case       Input                                                             Output        Remarks
     * =====================================================================================================================
     *   1-10      expectedTenPercentMap.get(key) == outputTenPercentMap.get(key)    null          The map maps the number of students to a particular grade range
     *   11-20     expectedOnePercentMap.get(key) == outputOnePercentMap.get(key)    null          The map maps the number of students to a particular grade range
     */
    @Test
    public void testGetAssignmentBarChartData() {
    	String granularity = "10%";
    	CourseInfo info;
    	SpreadsheetCourse course = null;
		try {
			info = new CourseInfo("", "", "", "", "", 0);
			course = new SpreadsheetCourse(info, null, null);
		}
		catch (CourseDataException e1) {
			System.out.println("Invalid course info when setting up the " +
					" get assignment bar chart test");
		}
    	Assignment ass = new Assignment();
    	ass.setName("HW09");
    	List<Student> studentList = new ArrayList<Student>();

    	try {
    		Student erik = new Student("ejowen", "erik", "owen", "10370", "SE", "Junior");
    		erik.addGrade(course, ass, new Grade(new Date(), "88.11"));

    		Student james = new Student("jfazio", "james", "fazio", "32456", "SE", "Junior");
    		james.addGrade(course, ass, new Grade(new Date(), "99.0"));

    		Student kevin = new Student("kfeutz", "kevin", "feutz", "84145", "SE", "Junior");
    		kevin.addGrade(course, ass, new Grade(new Date(), "77.0"));

    		Student kevin2 = new Student("kbackers", "kevin", "backers", "1232465", "SE", "Senior");
    		kevin2.addGrade(course, ass, new Grade(new Date(), "88.0"));

    		Student jirbert = new Student("jdilanch", "Jirbert", "Dilanchian", "25642", "SE", "Junior");
    		jirbert.addGrade(course, ass, new Grade(new Date(), "82.0"));

    		Student sally = new Student("slou", "Sally", "Lou", "32463", "SE", "Sophmore");
    		sally.addGrade(course, ass, new Grade(new Date(), "85.0"));

    		Student steven = new Student("stevenShyinayga", "Steven", "Shinyagan", "46765", "SE", "Senior");
    		steven.addGrade(course, ass, new Grade(new Date(), "85.0"));

    		Student patrick = new Student("pweston", "Patrick", "Weston", "234523456", "ME", "Junior");
    		patrick.addGrade(course, ass, new Grade(new Date(), "95.2"));

    		Student jamesC = new Student("jcornsih", "James", "Cornish", "54634", "ME", "Junior");
    		jamesC.addGrade(course, ass, new Grade(new Date(), "66.66"));

    		Student jake = new Student("jcosmo", "jake", "cosmo", "7856", "ME", "Junior");
    		jake.addGrade(course, ass, new Grade(new Date(), "77.1"));

    		Student ferguson = new Student("fAnderz", "Ferguson", "Anderz", "63245", "CPE", "Freshmen");
    		ferguson.addGrade(course, ass, new Grade(new Date(), "52.99"));

    		Student tommy = new Student("ttall", "Tommy", "Tall", "34673", "CE", "Sophmore");
    		tommy.addGrade(course, ass, new Grade(new Date(), "85.88"));

    		studentList.add(erik);
    		studentList.add(james);
    		studentList.add(kevin);
    		studentList.add(kevin2);
    		studentList.add(jirbert);
    		studentList.add(sally);
    		studentList.add(steven);
    		studentList.add(patrick);
    		studentList.add(jamesC);
    		studentList.add(jake);
    		studentList.add(ferguson);
    		studentList.add(tommy);

    		Map<String, Integer> expectedTenPercentMap = new HashMap<String, Integer>();
    		expectedTenPercentMap.put("0", 0);
    		expectedTenPercentMap.put("10", 0);
    		expectedTenPercentMap.put("20", 0);
    		expectedTenPercentMap.put("30", 0);
    		expectedTenPercentMap.put("40", 0);
    		expectedTenPercentMap.put("50", 1);
    		expectedTenPercentMap.put("60", 1);
    		expectedTenPercentMap.put("70", 2);
    		expectedTenPercentMap.put("80", 6);
    		expectedTenPercentMap.put("90", 2);
    		expectedTenPercentMap.put("100", 0);

    		Graph graph = new Graph();
    		graph.setAssignment(ass, studentList);
    		Map<String, Integer> outputTenPercentMap = graph.getAssignmentBarChartData(granularity);

    		for(String key : expectedTenPercentMap.keySet()) {
    			assertEquals(expectedTenPercentMap.get(key), outputTenPercentMap.get(key));
    		}
    		
    		granularity = "1%";
    		Map<String, Integer> outputOnePercentMap = graph.getAssignmentBarChartData(granularity);
    		
    		Map<String, Integer> expectedOnePercentMap = new HashMap<String, Integer>();
    		
    		for(int ndx = 0; ndx <= 100; ndx++) {
    			expectedOnePercentMap.put(new Integer(ndx).toString(), 0);
    		}
    		
    		expectedOnePercentMap.put("88", 2);
    		expectedOnePercentMap.put("99", 1);
    		expectedOnePercentMap.put("77", 2);
    		expectedOnePercentMap.put("82", 1);
    		expectedOnePercentMap.put("85", 3);
    		expectedOnePercentMap.put("95", 1);
    		expectedOnePercentMap.put("66", 1);
    		expectedOnePercentMap.put("52", 1);
    		
    		for(String key : expectedOnePercentMap.keySet()) {
    			assertEquals(expectedOnePercentMap.get(key), outputOnePercentMap.get(key));
    		}
    		
    	}
    	catch(Exception e) {
    		System.out.println("Unable to set up testgetAssignmentBarChartData");
    	}
    }
    

}
