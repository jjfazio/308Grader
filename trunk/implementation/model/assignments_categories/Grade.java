package model.assignments_categories;

import java.io.Serializable;
import java.util.Date;


/**
 * Grade contains a Course, an Assignment, a Date, and a Score, along with
 * methods available to change this data
 * @autho ericowen
 */
public class Grade implements Serializable {
   Date graded;
   Double score;
   String letterGrade; //prob should be an enum
   
   private static final long serialVersionUID = -4121067889826371429L;
   
   public Grade(Date graded, Double score,
           String letterGrade)
   {
       this.graded = graded;
       this.score = score;
       this.letterGrade = letterGrade;
   }

   public Date getTurnedIn()
   {
       return graded;
   }

   public void setTurnedIn(Date turnedIn)
   {
       this.graded = turnedIn;
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
