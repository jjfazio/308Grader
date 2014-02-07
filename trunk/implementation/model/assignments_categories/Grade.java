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
   
   private static final long serialVersionUID = -4121067889826371429L;
   
   public Grade(Assignment assign, Date turnedIn, Double score,
           String letterGrade)
   {
       this.assign = assign;
       this.turnedIn = turnedIn;
       this.score = score;
       this.letterGrade = letterGrade;
   }

   public Assignment getAssign()
   {
       return assign;
   }

   public Date getTurnedIn()
   {
       return turnedIn;
   }

   public void setTurnedIn(Date turnedIn)
   {
       this.turnedIn = turnedIn;
   }

   public Double getScore()
   {
       return score;
   }

   public String getLetterGrade()
   {
       return letterGrade;
   }
   
}
