package model.assignments_categories;

import java.io.Serializable;

/**
 * 	The stats class is a way to hold multiple statistics for an assignment.
 *	These values will come from the collection of grades from different students on the same assignment. 
 *	The actual types of stats may change depending on customer's preference as we proceed.
 */

public class Stats implements Serializable {
    Double mean;
	Double median;
	Double mode;
	Double range;
	Double standardDeviation;
	Double highScore;
	Double lowScore;
	private static final long serialVersionUID = -8588414581936027698L;
}