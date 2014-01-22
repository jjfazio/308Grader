package assignments_categories;


/**
 * Grade contains a Course, an Assignment, a Date, and a Score, along with
 * methods available to change this data
 */
abstract public class Grade {
   Date turnedIn;
   Double score;
   String letterGrade; //prob should be an enum
}
