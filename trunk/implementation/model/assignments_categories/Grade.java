package model.assignments_categories;

import java.io.Serializable;
import java.util.Date;

import model.exception.BadDataException;


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
   
   public Grade(Date graded, String scoreString) throws BadDataException
   {
       this.graded = graded;
       
       try {
           this.score = Double.parseDouble(scoreString);
       }
       catch (NumberFormatException e) {
           throw new BadDataException("Must enter in a valid number");
       }
       // Calc letter grade using grading scheme
       // this.letterGrade = letterGrade;
       
       if (score < 0)
           throw new BadDataException("Score cannot be less than 0");
       
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
