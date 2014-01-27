package model.graph;

import java.io.Serializable;
import java.util.HashMap;

import model.assignments_categories.*;

public class Graph implements Serializable {
	
	Category cat;
	Assignment ass;
	
	public Graph() {
		System.out.println("Graph and curve class created, given a category.");
		this.cat = null;
		this.ass = null;
	}
	
	public void setCategory(Category cat) {
		this.cat = cat;
	}
	
	public void setAssignment(Assignment ass) {
		this.ass = ass;
	}
	
	public void applyCustomCategoryCurve(HashMap gradeRange) {
		System.out.println("Applied curve");
	}
	
	public void applyCustomAssignmentCurve(HashMap gradeRange) {
		System.out.println("Applied curve");
	}
	
	public void applyStandardCurve(int curveAmount) {
		System.out.println("Applied a " + curveAmount + "% curve");
	}
}
