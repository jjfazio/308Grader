package model.assignments_categories;

import java.io.Serializable;
import java.util.Date;

import model.spreadsheet.GradingScheme;
import model.spreadsheet.LatePolicy;

/**
 * Assignment contains a name, a weight, a gradeType, and a set of statistics
 * along with methods to change these fields
 */
public class Assignment implements Serializable {
   String name;
   Double percentOfCategory;
   Integer maxPoints;
   Date dueDate;
   GradingScheme gScheme;
   LatePolicy policy;
   Boolean hasElectrionicTurnin;
   Double percentCurve;
   
   public Assignment() {
      this("changeName", 100.0, 100, new Date(), new GradingScheme(), new
       LatePolicy(), false);
   }
   
   public Assignment(String name, Double percentOfCategory, Integer maxPoints,
    Date dueDate, GradingScheme gScheme, LatePolicy latePolicy, 
    Boolean hasElectronicTurnin) {
      this.name = name;
      this.percentOfCategory = percentOfCategory;
      this.maxPoints = maxPoints;
      this.dueDate = dueDate;
      this.gScheme = gScheme;
      this.policy = latePolicy;
      this.hasElectrionicTurnin = hasElectronicTurnin;
      
      //this.gScheme = defaultGScheme;
   }
   
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
   public void adjustAssignmentCurve(Double amountCurved) {
	   
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Double getPercentOfCategory() {
      return percentOfCategory;
   }
   
   //requires work
//   public void setPercentOfCategory() {
//      
//   }

   public Integer getMaxPoints() {
      return maxPoints;
   }

   //requires work
//   public void setMaxPoints(Integer maxPoints) {
//      this.maxPoints = maxPoints;
//   }

   public Date getDueDate() {
      return dueDate;
   }

   //requires work
//   public void setDueDate(Date dueDate) {
//      this.dueDate = dueDate;
//   }

   public GradingScheme getgScheme() {
      return gScheme;
   }

   public void setgScheme(GradingScheme gScheme) {
      this.gScheme = gScheme;
   }

   public LatePolicy getPolicy() {
      return policy;
   }

   //probably can't set after assignment given
//   public void setPolicy(LatePolicy policy) {
//      this.policy = policy;
//   }

   public Boolean getHasElectrionicTurnin() {
      return hasElectrionicTurnin;
   }

   //probably should be set in beginning
//   public void setHasElectrionicTurnin(Boolean hasElectrionicTurnin) {
//      this.hasElectrionicTurnin = hasElectrionicTurnin;
//   }

   public Double getPercentCurve() {
      return percentCurve;
   }

   public void setPercentCurve(Double percentCurve) {
      this.percentCurve = percentCurve;
   }
   
   

}
