package model.assignments_categories;

import java.io.Serializable;
import java.util.Date;


/**
 * Grade contains a Course, an Assignment, a Date, and a Score, along with
 * methods available to change this data
 */
public class Grade implements Serializable {
   Assignment assign;
   Date turnedIn;
   Double score;
   String letterGrade; //prob should be an enum
}
