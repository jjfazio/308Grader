package model.exception;

import model.graph.AdjustableGradeRange;

/**
 * Exception that is thrown when two grading schemes overlap one another
 * 
 * @author erikowen
 *
 */
public class OverlappingRangesException extends Exception{
	
	/**The first of the two overlapping ranges*/
	private AdjustableGradeRange range1;
	/**The second of the two overlapping ranges*/
	private AdjustableGradeRange range2;
	
	/**
	 * Initializes a new OverlappingRangesException object.
	 */
	public OverlappingRangesException() {
		
	}
	
	/**
	 * Initializes a new OverlappingRangesException object.
	 * @param message the message tied with this exception
	 */
	public OverlappingRangesException(String message) {
		super(message);
	}
	
	/**
	 * Initializes a new OverlappingRangesException object.
	 * @param range1 the first of the overlapping ranges
	 * @param range2 the second of the overlapping ranges
	 */
	public OverlappingRangesException(AdjustableGradeRange range1, AdjustableGradeRange range2) {
		this.range1 = range1;
		this.range2 = range2;
	}
	
	/**
	 * Accessor method to the first of the overlapping ranges
	 * 
	 * @return the first overlapping AdjustableGradeRange
	 */
	public AdjustableGradeRange getFirstRange() {
		return range1;
	}
	
	/**
	 * Accessor method to the second of the overlapping ranges
	 * 
	 * @return the second overlapping AdjustableGradeRange
	 */
	public AdjustableGradeRange getSecondRange() {
		return range2;
	}

}
