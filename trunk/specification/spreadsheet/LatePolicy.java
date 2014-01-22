package spreadsheet;

/**
 * Object representing a LatePolicy for an overall class or individual
 * assignment.
 */
public class LatePolicy {
   /**
    * Boolean that indicates whether graceDays are enabled. Grace Days
    * are days that a student have to turn in an assignment without penalty.
    * If late days are assigned at a category level, no sub-categories can
    * override the amount of graceDays. Multiple same level categories can
    * have different amounts of graceDays.
    */
   Boolean graceDaysEnabled;
   
   /**
    * The amount of time a student has before they start to get deducted.
    * The decayRate is an amount of time in hours.
    */
   Integer decayRate;
   
   /**
    * The amount of percentage deducted from a student's grade if they turn in 
    * and assignment late. If the decayRate is 1 hour and the decayPercentage is
    * 10% a student will lose 20% if they turn in an assignment 2 hours late.
    */
   Double decayPercentage;
   
   /**
    * The amount of graceDays a student has.
    */
   Integer graceDays;
}
