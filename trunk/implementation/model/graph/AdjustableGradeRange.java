package model.graph;

import java.util.List;
import java.util.Map;

import javafx.stage.Stage;

import model.exception.BadDataException;
import model.exception.OverlappingRangesException;
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
		high = origRange.getHigh();
		low = origRange.getLow();
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
		return low;
	}

	/**
	 * Accessor method to get the high score associated with this range
	 * @return Double value of the high score of this GradeRange
	 */
	public Double getHigh()
	{
		return high;
	}
	
	public GradeRange getGradeRangeVersion() {
		return new GradeRange(letterGrade, low, high);
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
	
	/**
	 * Setter method to set the high score of this range
	 * 
	 * @param desiredHigh String representation of the value to be set
	 * @throws BadDataException if the string is not a number
	 */
	public void setHigh(String desiredHigh) throws BadDataException {
		try {
			high = Double.parseDouble(desiredHigh);
		}
		catch(Exception ex) {
			throw new BadDataException();
		}
	}
	
	/**
	 * Setter method to set the low score of this range
	 * 
	 * @param desiredLow String representation of the value to be set
	 * @throws BadDataException if the string is not a number
	 */
	public void setLow(String desiredLow) throws BadDataException {
		try {
			low = Double.parseDouble(desiredLow);
		}
		catch(Exception ex) {
			throw new BadDataException();
		}
	}
	
	public static void checkForOverlappingRanges(List<AdjustableGradeRange> ranges) throws OverlappingRangesException {
    	for(int ndx = 0; ndx < ranges.size(); ndx++) {
    		for(int checkNdx = 0; checkNdx < ranges.size(); checkNdx++) {
    			if(checkNdx != ndx) {
    				double curHigh = ranges.get(ndx).getHigh();
    				double curLow = ranges.get(ndx).getLow();
    				double checkHigh = ranges.get(checkNdx).getHigh();
    				double checkLow = ranges.get(checkNdx).getLow();
    				if((curHigh < checkHigh && curHigh > checkLow) || (curLow > checkLow && curLow < checkHigh)) {
    					throw new OverlappingRangesException(ranges.get(ndx), ranges.get(checkNdx));
    				}
    			}
    		}
    	}
	}
}


