package model.exception;

import model.graph.AdjustableGradeRange;

public class OverlappingRangesException extends Exception{
	
	private AdjustableGradeRange range1;
	private AdjustableGradeRange range2;
	
	public OverlappingRangesException() {
		
	}
	
	public OverlappingRangesException(String message) {
		super(message);
	}
	
	public OverlappingRangesException(AdjustableGradeRange range1, AdjustableGradeRange range2) {
		this.range1 = range1;
		this.range2 = range2;
	}
	
	public AdjustableGradeRange getFirstRange() {
		return range1;
	}
	
	public AdjustableGradeRange getSecondRange() {
		return range2;
	}

}
