package model.graph;

import java.io.Serializable;
import java.util.HashMap;

import model.assignments_categories.*;

/**
 * Graph model class where all the underlying functionality
 * to set up the graphs and ajust the curves exists
 * 
 * @author erikowen
 *
 */
public class Graph implements Serializable {
	
	private Category cat;
	private Assignment ass;
	
	/**
	 * Creates a new Graph instance with category and null
	 */
	public Graph() {
		System.out.println("Graph and curve class created, given a category.");
		this.cat = null;
		this.ass = null;
	}
	
	/**
	 * Sets the category that the graph's data is coming from
	 * 
	 * @param cat the category that the data is coming from
	 */
	public void setCategory(Category cat) {
		this.cat = cat;
	}
	
	/**
	 * Sets the assignment that the graph's data is coming from
	 * 
	 * @param ass the assignment that the data is coming from
	 */
	public void setAssignment(Assignment ass) {
		this.ass = ass;
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
}
