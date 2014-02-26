package model.assignments_categories;

import java.io.Serializable;
import java.util.Date;

import model.exception.BadDataException;


/**
 * Grade contains a Course, an Assignment, a Date, and a Score, along with
 * methods available to change this data
 * 
 * @author erikowen
 */
public class Grade implements Serializable {
   Date graded;
   Double score;
   String letterGrade; 
   
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
       
       setScore(scoreString);
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
 * @throws BadDataException 
    */
   public void setScore(String newScore) throws BadDataException {
       try {
           if(newScore != null) {
        	   Double changedScore = Double.parseDouble(newScore);
        	   if(changedScore < 0) {
        		   throw new BadDataException("Must enter a valid number");
        	   }
        	   else {
        		   this.score = changedScore;
        	   }
           }
       }
       catch (NumberFormatException e) {
           throw new BadDataException("Must enter in a valid number");
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
