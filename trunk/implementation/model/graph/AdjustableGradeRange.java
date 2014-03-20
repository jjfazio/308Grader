package model.graph;

import java.util.Map;

import model.spreadsheet.GradeRange;

/**
 * AdjustableGradeRange is a class which decorates the GradeRange class, it adds 
 * extra functionality to see the number of students in that grade range for a 
 * particular assignment or category.
 * 
 * @author erikowen
 */
public class AdjustableGradeRange{

	/**
	 * The letter grade the GradeRange represents, ex A or A-, must not be null.
	 */
	private String letterGrade;

	/**
	 * The low number of the GradeRange, must not be null.
	 */
	private Double low;

	/**
	 * The high number of the GradeRange, must not be null.
	 */
	private Double high;

	/**
	 * The number of students in this particular grade range
	 */
	private Integer studsInRange;
	
	/*GradeRange object which this class is decorating*/
	private GradeRange origRange;
	
	/**
	 * Constructor to create a new AdjustableGradeRange object.
	 * 
	 * @param range the range being decorated
	 * @param rangeMap the map which shows the number of students in this range
	 */
	public AdjustableGradeRange(GradeRange range, Map<String, Integer> rangeMap) {
		this.origRange = range;
		low = origRange.getHigh();
		high = origRange.getLow();
		letterGrade = origRange.getLetterGrade();
		
		setStudentsInRange(rangeMap);
	}
	
	/**
	 * Setter method to set the number of students in the grade range
	 * @param rangeMap the map to find the number of students in this range
	 */
	private void setStudentsInRange(Map<String, Integer> rangeMap) {
		studsInRange = rangeMap.get(letterGrade);
	}
	
	
	/**
	 * Accessor method to get the String representation of this GradeRange
	 * @return String representing the grade
	 */
	public String getLetterGrade()
	{
		return origRange.getLetterGrade();
	}

	/**
	 * Accessor method to get the low score associated with this range
	 * @return Double value of the low score of this GradeRange
	 */
	public Double getLow()
	{
		return origRange.getLow();
	}

	/**
	 * Accessor method to get the high score associated with this range
	 * @return Double value of the high score of this GradeRange
	 */
	public Double getHigh()
	{
		return origRange.getHigh();
	}
	
	/**
	 * Accessor method to get the number of students that fall in this GradeRange.
	 * @return Integer of the number of students in this grade range.
	 */
	public Integer getStudsInRange()
	{
		return studsInRange;
	}
	
	/**
	 * Helps determine if two GradeRange objects are equal
	 * @param obj the GradeRange to compare this to
	 * @return true if this is the same as the obj parameter, false otherwise.
	 */
	@Override
	public boolean equals(Object obj)
	{
		return origRange.equals(obj);
	}
}


