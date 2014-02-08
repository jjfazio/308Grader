package model.graph;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import model.assignments_categories.*;
import model.users.Student;

/**
 * Graph model class is where all the underlying functionality
 * is to set up the graphs and adjust the curves
 * 
 * @author erikowen
 *
 */
public class Graph implements Serializable {
	
	private Category cat;
	private Assignment ass;
	private List<Student> studentList;
	private static final int TEN = 10, TWENTY = 20, THIRTY = 30, FOURTY = 40,
		FIFTY = 50, SIXTY = 60, SEVENTY = 70, EIGHTY = 80, NINETY = 90, HUNDRED = 100;
	
	/**
	 * Creates a new Graph instance with category and null
	 */
	public Graph() {
		System.out.println("Graph and curve class created.");
		this.cat = null;
		this.ass = null;
		this.studentList = null;
	}
	
	/**
	 * Sets the category that the graph's data is coming from
	 * 
	 * @param cat the category that the data is coming from
	 */
	public void setCategory(Category cat, List<Student> students) {
		System.out.println("Graph given a category.");
		this.cat = cat;
		this.studentList = students;
	}
	
	/**
	 * Sets the assignment that the graph's data is coming from
	 * 
	 * @param ass the assignment that the data is coming from
	 */
	public void setAssignment(Assignment ass, List<Student> students) {
		System.out.println("Graph given an assignment.");
		this.ass = ass;
		this.studentList = students;
	}
	
	/**
	 * Applies the custom curve to the category
	 * which is in the graph's data
	 * 
	 * @param gradeRange the range of scores
	 */
	public void applyCustomCategoryCurve(HashMap gradeRange) {
		System.out.println("Applied curve");
	}
	
	/**
	 * Applies the custom curve to the assignment
	 * which is in the graph's data
	 * 
	 * @param gradeRange the range of scores
	 */
	public void applyCustomAssignmentCurve(HashMap gradeRange) {
		System.out.println("Applied curve");
	}
	
	/**
	 * Applies an evenly distributed curve to all the grades
	 * that are in the graph's data.
	 * 
	 * @param curveAmount the percent curve that the grades
	 * will be adjusted by.
	 */
	public void applyStandardCurve(int curveAmount) {
		System.out.println("Applied a " + curveAmount + "% curve");
	}
	
	public HashMap<Range, Integer> getAssignmentData() {
		HashMap<Range, Integer> map = new HashMap<Range, Integer>();
		Integer one = new Integer(0);
		int[] nums= {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int zero = 0, ten = 0, twenty = 0, thirty = 0, fourty = 0, fifty = 0,
			sixty = 0, seventy = 0, eighty = 0, ninety = 0, hundred = 0;
		
		
		for(Student stud : this.studentList) {
			HashMap<Assignment, Grade> studGrades = stud.getGrades();
			if(studGrades.get(this.ass) != null)
			{
				Grade studGrade = studGrades.get(this.ass);
				double percentScore = (studGrade.getScore() / this.ass.getMaxPoints().doubleValue()) * 100;
				if(percentScore < TEN) {
					nums[0]++;
				}
				else if(percentScore >= TEN && percentScore < TWENTY) {
					nums[1]++;
				}
				else if(percentScore >= TWENTY && percentScore < THIRTY) {
					nums[2]++;
				}
				else if(percentScore >= THIRTY && percentScore < FOURTY) {
					nums[3]++;
				}
				else if(percentScore >= FOURTY && percentScore < FIFTY) {
					nums[4]++;
				}
				else if(percentScore >= FIFTY && percentScore < SIXTY) {
					nums[5]++;
				}
				else if(percentScore >= SIXTY && percentScore < SEVENTY) {
					nums[6]++;
				}
				else if(percentScore >= SEVENTY && percentScore < EIGHTY) {
					nums[7]++;
				}
				else if(percentScore >= EIGHTY && percentScore < NINETY) {
					nums[8]++;
				}
				else if(percentScore >= NINETY && percentScore < HUNDRED) {
					nums[9]++;
				}
				else if(percentScore >= HUNDRED) {
					nums[10]++;
				}
			}
		}
		
		Range[] ranges = Range.values();
		for(int ndx = 0; ndx < ranges.length; ndx++) {
			map.put(ranges[ndx], new Integer(nums[ndx]));
		}
		
		return map;
	}
	
	public HashMap<Range, Integer> getCategoryData() {
		HashMap<Range, Integer> map = new HashMap<Range, Integer>();
		
		/*Implementation to come*/
		
		return map;
	}
}
