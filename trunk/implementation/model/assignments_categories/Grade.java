package model.assignments_categories;

import java.io.Serializable;
import java.util.Date;

import model.exception.BadDataException;


/**
 * Grade contains a Course, an Assignment, a Date, and a Score, along with
 * methods available to change this data
 * @autho erikowen
 */
public class Grade implements Serializable {
   Date graded;
   Double score;
   String letterGrade; //prob should be an enum
   
   private static final long serialVersionUID = -4121067889826371429L;
   
   /**
    * Constructs a new Grade object
    * 
    * @param graded Date the Grade was given
    * @param scoreString the score received
    * @throws BadDataException
    */
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

   /**
    * Accessor method to get the date turned in
    * 
    * @return the Date this Grade was graded
    */
   public Date getTurnedIn()
   {
       return graded;
   }

   /**
    * Setter method the see when the date was turned in
    * 
    * @param turnedIn the date to set this grade's turn in date to
    */
   public void setTurnedIn(Date turnedIn)
   {
       this.graded = turnedIn;
   }

   /**
    * Accessor method to set this grade's score
    * 
    */
   public Double getScore() {
	   return this.score;
   }
   /**
    * Setter method to change the score's grade
    */
   public void setScore(String newScore) {
       if(this.score != null) {
    	   this.score = Double.parseDouble(newScore);
       }
   }
   
   /**
    * Setter method to change the score's grade
    */

   /**
    * Accessor method to get the letter grade tied to this assignment
    * 
    * @return a string representing the letter grade
    */
   public String getLetterGrade()
   {
       return letterGrade;
   }
}
