package assignments_categories;

import java.util.Date;

import spreadsheet.GradingScheme;
import spreadsheet.LatePolicy;

/**
 * Assignment contains a name, a weight, a gradeType, and a set of statistics
 * along with methods to change these fields
 */
abstract public class Assignment {
   String name;
   Double percentOfCategory;
   Integer maxPoints;
   Stats statistics;
   Date dueDate;
   GradingScheme gScheme;
   LatePolicy policy;
   Boolean hasElectrionicTurnin;
   Double percentCurve;
   GradeScheme scheme;
   
   /**
    * Sets the percentage curve for a particular assignment
    */
   /*@
   requires
   //
   //The amount curved parameter must be a decimal between -1 and 1
   //
   (amountCurved >= -1 && amountCurved <= 1);
   
   ensures
   //
   //the percentCurve field in Category is changed to match the amountCurved parameter
   //
   (assignment.percentCurve.equals(amountCurved));
   @*/
   abstract void adjustAssignmentCurve(Double amountCurved);

}
