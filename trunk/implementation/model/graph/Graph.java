package model.graph;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.assignments_categories.*;
import model.users.Student;
import model.exception.*;

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
	private static final int HUNDRED_PERCENT = 100;
	private static final int TEN_PERCENT_INCREMENT = 10;
	
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
	public void applyStandardCurve(String curveString) throws BadDataException {
		int curveAmount;
		try {
			curveAmount = Integer.parseInt(curveString);
		}
		catch(Exception e) {
			throw new BadDataException("Invalid input, must enter an integer.");
		}
		
		this.ass.setPercentCurve((double)curveAmount);
		
		System.out.println("Applied a " + curveAmount + "% curve");
	}
	
	public Map<String, Integer> getAssignmentBarChartData(String granularity) {
		Map<String, Integer> map;
		
		if(granularity.equals("1%")) {
			map = getOnePercentBarChartData();
		}
		else {
			map = getTenPercentBarChartData();
		}
		
		return map;
	}
	
	private Map<String, Integer> getOnePercentBarChartData() {
		Map<String, Integer> returnMap = new HashMap<String, Integer>();
		
		for(int percent = 0; percent <= HUNDRED_PERCENT; percent++) {
			Integer temp = new Integer(percent);
			returnMap.put(temp.toString(), 0);
		}
		
		for(Student stud : this.studentList) {
			HashMap<Assignment, Grade> studGrades = stud.getGrades();
			if(studGrades.containsKey(this.ass)) {
				Double studScore = studGrades.get(this.ass).getScore() / this.ass.getMaxPoints().doubleValue() * HUNDRED_PERCENT;
				Double studScoreFloor = Math.floor(studScore);
				Integer studScoreFloorInt = studScoreFloor.intValue();
				Integer numCurrentScore = returnMap.get(studScoreFloorInt.toString());
				numCurrentScore++;
				
				returnMap.put(studScoreFloorInt.toString(), numCurrentScore);
			}
			
		}
		
		return returnMap;
	}
	
	private Map<String, Integer> getTenPercentBarChartData() {
		Map<String, Integer> returnMap = new HashMap<String, Integer>();
		
		for(int percent = 0; percent <= HUNDRED_PERCENT; percent += TEN_PERCENT_INCREMENT) {
			Integer temp = new Integer(percent);
			returnMap.put(temp.toString(), 0);
		}
		
		for(Student stud : this.studentList) {
			HashMap<Assignment, Grade> studGrades = stud.getGrades();
			if(studGrades.containsKey(this.ass)) {
				Double studScore = (studGrades.get(this.ass).getScore() / this.ass.getMaxPoints().doubleValue()) * HUNDRED_PERCENT;
				int studScoreInt = studScore.intValue();
				int studScoreIntDivTen = studScoreInt / TEN_PERCENT_INCREMENT;
				Integer studScoreRoundedDown = new Integer(studScoreIntDivTen * TEN_PERCENT_INCREMENT);
				Integer numCurrentScore = returnMap.get(studScoreRoundedDown.toString());
				numCurrentScore++;
				
				returnMap.put(studScoreRoundedDown.toString(), numCurrentScore);
			}
			
		}
		
		return returnMap;
	}
	
	public Map<String, Integer> getAssignmentPieChartData() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("A", 0);
		map.put("B", 0);
		map.put("C", 0);
		map.put("D", 0);
		map.put("F", 0);	
		
		for(Student stud : this.studentList) {
			HashMap<Assignment, Grade> studGrades = stud.getGrades();
			if(studGrades.get(this.ass) != null) {
				if(studGrades.get(this.ass).getLetterGrade() != null) {
					int numThisScore = map.get(studGrades.get(this.ass).getLetterGrade());
					numThisScore++;
					map.put(studGrades.get(this.ass).getLetterGrade(), numThisScore);
				}
			}
		}
		
		return map;
	}
	
	public HashMap<Range, Integer> getCategoryData() {
		HashMap<Range, Integer> map = new HashMap<Range, Integer>();
		
		/*Implementation to come*/
		
		return map;
	}
	
}
