package model.graph;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import model.spreadsheet.GradeRange;
import model.users.Student;

/**
 * A course has a List of GradeRanges. Each range corresponds to a letter
 * grade. A typical setup would be A (90, 100), B(80, 89), C(70, 79)
 * and so forth.
 * @author kevinbackers
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
	
	
	public AdjustableGradeRange(GradeRange range, Map<String, Integer> rangeMap) {
		this.origRange = range;
		low = origRange.getHigh();
		high = origRange.getLow();
		letterGrade = origRange.getLetterGrade();
		
		setStudentsInRange(rangeMap);
	}
	
	private void setStudentsInRange(Map<String, Integer> rangeMap) {
		studsInRange = rangeMap.get(letterGrade);
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String getLetterGrade()
	{
		return origRange.getLetterGrade();
	}

	public Double getLow()
	{
		return origRange.getLow();
	}

	public Double getHigh()
	{
		return origRange.getHigh();
	}
	
	public Integer getStudsInRange()
	{
		return studsInRange;
	}

	@Override
	public boolean equals(Object obj)
	{
		return origRange.equals(obj);
	}
}


