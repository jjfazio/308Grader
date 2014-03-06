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
	
	/**The current category being examined in the graphs*/
	private Category cat;
	/**The current assignment being examined in the graphs*/
	private Assignment ass;
	/**The list of students in the class being examined in the graphs*/
	private List<Student> studentList;
	/**Constants representing the different grade ranges*/
	private static final int ZERO = 0, TEN = 10, TWENTY = 20, THIRTY = 30, FOURTY = 40,
		FIFTY = 50, SIXTY = 60, SEVENTY = 70, EIGHTY = 80, NINETY = 90, HUNDRED = 100,
		HUNDRED_PERCENT = 100, TEN_PERCENT_INCREMENT = 10;
	private static final double HUNDRED_PERCENT_DENOM = 100;
	
	/**
	 * Creates a new Graph instance with category and null
	 */
	/*@
	 requires
	 	(* no preconditions *);
	 ensures
	    this.cat = null &&
	    this.ass = null &&
	    this.studentList = null;
	 @*/
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
	/*@
	 requires
	 	(* no preconditions *);
	 ensures
	    this.cat == cat &&
	    this.studentList == students;
	 @*/
	public void setCategory(Category cat, List<Student> students) {
		System.out.println("Graph given a category.");
		this.cat = cat;
		this.studentList = students;
	}
	
	/**
	 * Accessor method to get the current category being examined by the view graphs
	 * and adjust curve page
	 * 
	 * @return Category of the current category being examined
	 */
	/*@
	 requires
	 	this.cat != null;
	 ensures
	    \result == this.cat;
	 @*/
	public Category getCategory() {
		return this.cat;
	}
	
	/**
	 * Accessor method to get the current assignment being examined by the view graphs
	 * and adjust curve page
	 * 
	 * @return Assignment of the current assignment being examined
	 */
	/*@
	 requires
	    this.ass != null;
	 ensures
	    \result == this.ass;
	 @*/
	public Assignment getAssignment() {
		return this.ass;
	}
	
	/**
	 * Accessor method to get the current list of students being examined by the view
	 * graphs and adjust curve page
	 * 
	 * @return List of the current students being examined
	 */
	/*@
	 requires
	    this.studentList != null;
	 ensures
	    \result == this.studentList;
	 @*/
	public List<Student>getStudents() {
		return this.studentList;
	}
	
	/**
	 * Sets the assignment that the graph's data is coming from
	 * 
	 * @param ass the assignment that the data is coming from
	 */
	/*@
	 requires
	    (* no preconditions *);
	 ensures
	    this.ass == ass &&
	    this.studentList == students;
	 @*/
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
		
		for(Student stud : studentList) {
			Double curScore = stud.getGrades().get(this.ass).getScore();
			Double curScoreAsDecimal = curScore / (double)this.ass.getMaxPoints();
			curScoreAsDecimal += curveAmount / HUNDRED_PERCENT_DENOM;
			Double curvedScore = curScoreAsDecimal * (double)this.ass.getMaxPoints();
			stud.getGrades().get(this.ass).setScore(curvedScore.toString());
		}
		
		System.out.println("Applied a " + curveAmount + "% curve");
	}
	
	/**
	 * Accessor method to get the scores used in the bar chart graph
	 * 
	 * @param granularity either 1% or 10% interval granularity
	 * @return a Map of the range (as a string) to an integer of the number
	 * of students in that range.
	 */
	public Map<String, Integer> getAssignmentBarChartData(String granularity) {
		Map<String, Integer> map;
		
		if(granularity.equals("1%")) {
			map = getAssignmentOnePercentBarChartData();
		}
		else {
			map = getAssignmentTenPercentBarChartData();
		}
		
		return map;
	}
	
	/**
	 * Accessor method to get the scores for the bar chart graph with 1% interval
	 * granularity
	 * 
	 * @return a map with the 1% score interval ranges to an integer with the number
	 * of students in that range
	 */
	private Map<String, Integer> getAssignmentOnePercentBarChartData() {
		Map<String, Integer> returnMap = new HashMap<String, Integer>();
		
		for(int percent = 0; percent <= HUNDRED_PERCENT; percent++) {
			Integer temp = new Integer(percent);
			returnMap.put(temp.toString(), 0);
		}
		
		for(Student stud : this.studentList) {
			HashMap<Integer, Grade> studGrades = stud.getGrades();
			if(studGrades.containsKey(this.ass.getID())) {
				Double studScore = studGrades.get(this.ass.getID()).getScore() / this.ass.getMaxPoints().doubleValue() * HUNDRED_PERCENT;
				Double studScoreFloor = Math.floor(studScore);
				Integer studScoreFloorInt = studScoreFloor.intValue();
				Integer numCurrentScore = returnMap.get(studScoreFloorInt.toString());
				numCurrentScore++;
				
				returnMap.put(studScoreFloorInt.toString(), numCurrentScore);
			}
			
		}
		
		return returnMap;
	}
	
	/**
	 * Accessor method to get the scores for the bar chart graph with 10% interval
	 * granularity
	 * 
	 * @return a map with the 10% score interval ranges to an integer with the number
	 * of students in that range
	 */
	private Map<String, Integer> getAssignmentTenPercentBarChartData() {
		Map<String, Integer> returnMap = new HashMap<String, Integer>();
		
		for(int percent = 0; percent <= HUNDRED_PERCENT; percent += TEN_PERCENT_INCREMENT) {
			Integer temp = new Integer(percent);
			returnMap.put(temp.toString(), 0);
		}
		
		for(Student stud : this.studentList) {
			HashMap<Integer, Grade> studGrades = stud.getGrades();
			if(studGrades.containsKey(this.ass.getID())) {
				Double studScore = (studGrades.get(this.ass.getID()).getScore() / this.ass.getMaxPoints().doubleValue()) * HUNDRED_PERCENT;
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
	
	/**
	 * Accessor method to get the data to initialize the pie chart graph
	 * 
	 * @return a map with a string representing the letter grade to the number
	 * of students in that letter grade range.
	 */
	public Map<String, Integer> getAssignmentPieChartData() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("A", 0);
		map.put("B", 0);
		map.put("C", 0);
		map.put("D", 0);
		map.put("F", 0);	
		
		for(Student stud : this.studentList) {
			HashMap<Integer, Grade> studGrades = stud.getGrades();
			if(studGrades.containsKey(ass.getID())) {
				if(studGrades.get(this.ass.getID()).getLetterGrade() != null) {
					int numThisScore = map.get(studGrades.get(this.ass.getID()).getLetterGrade());
					numThisScore++;
					map.put(studGrades.get(this.ass.getID()).getLetterGrade(), numThisScore);
				}
			}
		}
		
		return map;
	}
	
	/**
	 * Accessor method to get the scores used in the bar chart graph
	 * 
	 * @param granularity either 1% or 10% interval granularity
	 * @return a Map of the range (as a string) to an integer of the number
	 * of students in that range.
	 */
	public Map<String, Integer> getCategoryBarChartData(String granularity) {
		Map<String, Integer> map;
		
		if(granularity.equals("1%")) {
			map = getCategoryOnePercentBarChartData();
		}
		else {
			map = getCategoryTenPercentBarChartData();
		}
		
		return map;
	}
	
	private Map<String, Integer> getCategoryOnePercentBarChartData() {
		Map<String, Integer> scoreMap = new HashMap<String, Integer>();
		
		for(int percent = 0; percent <= HUNDRED_PERCENT; percent++) {
			Integer temp = new Integer(percent);
			scoreMap.put(temp.toString(), 0);
		}
		
		for(Student stud : studentList) {
			Integer studScore = getSubCategoryScore(this.cat, stud).intValue();
			
			if(studScore <= ZERO) {
				scoreMap.put(new Integer(ZERO).toString(), (scoreMap.get(new Integer(ZERO).toString()) + 1));
			}
			else if(studScore >= HUNDRED_PERCENT) {
				scoreMap.put(new Integer(HUNDRED_PERCENT).toString(), (scoreMap.get(new Integer(HUNDRED_PERCENT).toString()) + 1));
			}
			else {
				scoreMap.put(new Integer(studScore).toString(), (scoreMap.get(new Integer(studScore).toString()) + 1));
			}
		}
		
		return scoreMap;
	}
	
	private Map<String, Integer> getCategoryTenPercentBarChartData() {
		Map<String, Integer> scoreMap = new HashMap<String, Integer>();
		
		for(int percent = 0; percent <= HUNDRED_PERCENT; percent += TEN_PERCENT_INCREMENT) {
			Integer temp = new Integer(percent);
			scoreMap.put(temp.toString(), 0);
		}
		
		for(Student stud : studentList) {
			Integer studScore = getSubCategoryScore(this.cat, stud).intValue();
			Integer roundedScore = TEN * (studScore / TEN);
			
			if(roundedScore < TEN) {
				scoreMap.put(new Integer(ZERO).toString(), (scoreMap.get(new Integer(ZERO).toString()) + 1));
			}
			else if(roundedScore > NINETY) {
				scoreMap.put(new Integer(NINETY).toString(), (scoreMap.get(new Integer(NINETY).toString()) + 1));
			}
			else {
				scoreMap.put(new Integer(roundedScore).toString(), (scoreMap.get(new Integer(roundedScore).toString()) + 1));
			}
		}
		
		return scoreMap;
	}
	
	/**
	 * Accessor method to get category's data to initialize the pie chart graph
	 * 
	 * @return a map with a string representing the letter grade to the number
	 * of students in that letter grade range.
	 */
	public Map<String, Integer> getCategoryPieChartData() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		
//		map.put("A", 0);
//		map.put("B", 0);
//		map.put("C", 0);
//		map.put("D", 0);
//		map.put("F", 0);	
//		
//		for(Student stud : this.studentList) {
//			HashMap<Integer, Grade> studGrades = stud.getGrades();
//			if(studGrades.containsKey(ass.getID())) {
//				if(studGrades.get(this.ass.getID()).getLetterGrade() != null) {
//					int numThisScore = map.get(studGrades.get(this.ass.getID()).getLetterGrade());
//					numThisScore++;
//					map.put(studGrades.get(this.ass.getID()).getLetterGrade(), numThisScore);
//				}
//			}
//		}
		
		return map;
	}
	
	private Double getSubCategoryScore(Category cat, Student stud) {
		double score = 0.0;
		
		for(Assignment ass : cat.getAssignments()) {
			Grade curGrade = stud.getGrades().get(ass.getID());
			score += (stud.getGrades().get(ass.getID()).getScore() / 
			   ass.getMaxPoints().doubleValue()) * (ass.getPercentOfClass());
		}
		
		for(Category subCat : cat.getSubCategories()) {
			score += getSubCategoryScore(subCat, stud) /** (subCat.getPercentOfParent())*/;
		}
		
		return score;
	}
	
}
