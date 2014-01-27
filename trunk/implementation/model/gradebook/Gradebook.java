package model.gradebook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Logger;

import model.spreadsheet.SpreadsheetCourse;
import model.users.Teacher;

/****
 *  A Gradebook is the overarching object of the grader.
 *	It includes a teacher who "owns" the gradebook, a collection
 *  of {@link model.spreadsheet.SpreadsheetCourse}s that the 
 *  teacher teaches, and the current SpreadsheetCourse the
 *  teacher is editing. This class also contains a static reference
 *  to itself so it can only be instantiated once.
 *  @author jamesfazio
 */

public class Gradebook implements Serializable{


    /**
     * List of the {@link model.spreadsheet.SpreadsheetCourse}s
     * taught in the Gradebook.
     */
	private ArrayList<SpreadsheetCourse> courses;
	
	/**
	 * Current {@link model.spreadsheet.SpreadsheetCourse} being used.
	 */
	private SpreadsheetCourse currentCourse;
	
	/**
	 * {@link model.users.Teacher} Teacher who owns the gradebook.
	 */
	private Teacher teacher;
	
	/**
	 * Static reference to the Gradebook, used to ensure the Gradebook
	 * is only instantiated once.
	 */
	private static Gradebook instance;
	
	private static final Logger LOGGER =
	 Logger.getLogger(Gradebook.class.getName());
	
	private static final long serialVersionUID = 4568797145557381794L;
	/**
	 * Called once at the beginning of the application to 
	 * load the Gradebook.
	 */
	private Gradebook() {
	   System.out.println("Congrats, you loaded a Gradebook!");
	   loadGradebook();
	}
	
	/**
	 * Called if the Gradebook has never been saved before, the
	 * first time the user uses the application.
	 * @param firstTime
	 */
	private Gradebook(boolean firstTime) {
	    System.out.println("Congrats, you loaded a Gradebook!");
	}
	
	/**
	 * Returns a reference to the Gradebook. The Gradebook
	 * is only instantiated once, every time after a reference
	 * to the already created Gradebook is returned.
	 * @return - Reference to the Gradebook
	 */
	public static Gradebook getInstance() {
	   if (instance == null)
	      new Gradebook();
	   
	   return instance;
	}
	
	
  /**
    * Adds a SpreadsheetCourse to the Gradebook.
    * @param course - The Spreadsheet course being added to the Gradebook
    */
   /*@
     requires
        // The SpreadsheetCourse is not null.
        // The SpreadsheetCourse does not already exist in the Gradebook courses.
        // The SpreadsheetCourse must have CourseInfo with non-null non
        // empty courseName, quarter, number, and dept.
        // The SpreadsheetCourse must have a valid non-null non-overlapping grading
        // scheme.
        
        (courses != null)
        
        &&
        
        (course != null)
        
        && 
        
        ( !(\exists SpreadsheetCourse course_input ;
            courses.contains(course_input)))
            
        &&
        
        (course.courseInfo != null) 
        
        &&
        
        (course.courseInfo.courseName != null) && (course.courseInfo.courseName.length > 0)
        
        &&
        
        (course.courseInfo.quarter != null) && (course.courseInfo.quarter.length > 0)
        
        && 
        
        (course.courseInfo.number != null) && (course.courseInfo.number.length > 0)
        
        && 
        
        (course.courseInfo.dept != null) && (course.courseInfo.dept.length > 0)
        
        &&
        
        (course.gradingScheme != null) && (course.gradingScheme.length > 0)
        
        &&
        
        (\forall int i ; (i >= 0) && (i < course.gradingScheme.size() - 1);
           course.gradingScheme(i).gradeRange.high >
           course.gradingScheme(i).gradeRange.low)
           
        &&
        
        (\forall int i ; (i > 0) && (i < course.gradingScheme.size() -1) ;
           course.gradingScheme(i).gradeRange.low >
           course.gradingScheme(i-1).gradeRange.high)
           
     ensures
        // The SpreadsheetCourse is in the Gradebook if and only if it 
        // is a new SpreadsheetCourse, otherwise it already exists in 
        // the Gradebook.
           
        (\forall SpreadsheetCourse course_other ; 
           (courses.contains(course_other)) <==>
           course_other.equals(course) || \old(courses).contains(course_other));
    @*/
   
   public void addSpreadsheetCourse(SpreadsheetCourse course) {
      if (courses == null) {
         courses = new ArrayList<SpreadsheetCourse>();
      }
      
      courses.add(course);
   }
   
   /**
    * This method returns a SpreadsheetCourse based off of some keys.
    * These keys have not been figured out yet.
    * @return - The SpreadsheetCourse searched for.
    */
   public SpreadsheetCourse getSpreadsheetCourse() {
       System.out.println("Getting Spreadsheet course");
       return null;
   }
   
   /**
    * Delete a Spreadsheet from the Gradebook. 
    * @param course - The Spreadsheet Course that is to be deleted
    */
   /*@
     
     requires
       // The gradebook contains at least one course.
       // The course to be deleted is not null.
       // The course to be deleted is in the Gradebook courses.
       // (Possibly further conditions checking to see if the quarter has started)
       
        (courses != null) && (courses.length > 0)
       
       &&
       
       (course != null) 
       
       &&
       
       courses.contains(course);
       
     ensures
        // The course no longer exists in the Gradebook.
        
        !(courses.contains(course));
        
    @*/
   public void deleteSpreadsheetCourse(SpreadsheetCourse course) {
      courses.remove(course);
   }
   
   /**
    * Edit an existing SpreadsheetCourse in the Gradebook. The old and
    * new SpreadsheetCourses  must not be the same. The old SpreadsheetCourse
    * must be in the Gradebook. The new SpreadsheetCourse must meet the same
    * conditions as the the conditions foraddSpreadsheetCourse.
    * @param oldCourse - The old SpreadsheetCourse to be modified
    * @param newCourse - The new SpreadsheetCourse that will take the place of
    * the oldCourse
    */
   /*@
     
     requires
        // The gradebook has at least one entry.
        // The old course is non-null and exists in the Gradebook courses.
        // The new course is non-null and is different from the old course.
        // The new course does not already exist in the Gradebook courses.
        // The new course must have CourseInfo with non-null non
        // empty courseName, quarter, number, and dept.
        // The new course must have a valid non-null non-overlapping grading
        // scheme.
        
        (courses != null) && (courses.length > 0)
        
        &&
        
        (oldCourse != null) && (courses.contains(oldCourse))
        
        && 
        
        (newCourse != null) && !(courses.contains(newCourse) && !(oldCourse.equals(newCourse))
        
        &&
        
        (newCourse.courseInfo != null) 
        
        &&
        
        (newCourse.courseInfo.courseName != null) && (newCourse.courseInfo.courseName.length > 0)
        
        &&
        
        (newCourse.courseInfo.quarter != null) && (newCourse.courseInfo.quarter.length > 0)
        
        && 
        
        (newCourse.courseInfo.number != null) && (newCourse.courseInfo.number.length > 0)
        
        && 
        
        (newCourse.courseInfo.dept != null) && (newCourse.courseInfo.dept.length > 0)
        
        &&
        
        (newCourse.gradingScheme != null) && (newCourse.gradingScheme.length > 0)
        
        &&
        
        (\forall int i ; (i >= 0) && (i < newCourse.gradingScheme.size() - 1) ;
           newCourse.gradingScheme(i).gradeRange.high >
           newCourse.gradingScheme(i).gradeRange.low)
           
        &&
        
        (\forall int i ; (i > 0) && (i < newCourse.gradingScheme.size() -1) ;
           newCourse.gradingScheme(i).gradeRange.low >
           newCourse.gradingScheme(i-1).gradeRange.high));
        
     ensures
        // The new course exists in the gradebook courses
        // The old course does not exist in the gradebook. courses
        // The gradebook courses field is the same size as it was before
        // the operation.
        
        (courses.length() == \old(courses).length())
        
        &&
        
        (courses.contains(newCourse))
        
        &&
        
        !(courses.contains(oldCourse));
        
    @*/
   
   //possibly not used
   public boolean editSpreadsheetCourse(SpreadsheetCourse oldCourse,
    SpreadsheetCourse newCourse) {
//      SpreadsheetCourse c;
//      Boolean modified = false;
//      
//      for (int i = 0; i < courses.size() && !modified; i++) {
//         c = courses.get(i);
//         if (c.equals(oldCourse)) {
//            courses.set(i, newCourse);
//            modified = true;
//         }
//      }
//      
//      return modified;
      
      return false;
   }
   
   public void clearGradebook() {
       File f = new File("currentGradebook");
       PrintWriter writer;
    try
    {
        writer = new PrintWriter(f);
        writer.print("");
        writer.close();
    }
    catch (FileNotFoundException e)
    {
        System.out.println("Error clearing file");
        e.printStackTrace();
    }
   }
   
   /**
    * Loads the Gradebook from a file. The Gradebook
    * and all of its fields are serializable, so the Gradebook
    * can be directly loaded from the file.
    */
   private void loadGradebook() {
	  File f;
      FileInputStream fin;
      
      try {
    	 f = new File("currentGradebook");
    	 if (f.exists()) {
    		 fin = new FileInputStream("currentGradebook");
    		 ObjectInputStream ois = new ObjectInputStream(fin);
    		 instance = (Gradebook) ois.readObject();
    		 ois.close();
    	 } else {
    		 instance = new Gradebook(true);
    	 }
    	 LOGGER.info("Done loading gradebook");
      } catch (IOException e) {
         LOGGER.warning("Error in loading gradebook: ");
         e.printStackTrace();
      } catch (ClassNotFoundException e)  {
         LOGGER.warning("Error in loading gradebook: " + e.getStackTrace());
      }

   }
   
   /**
    * Saves the Gradebook to a file. The Gradebook and all of 
    * its fields are serializable, so it can be directly stored
    * into a file.
    */
   public void saveGradebook() {
      FileOutputStream fout;
      
      try {
         fout = new FileOutputStream("currentGradebook");
         ObjectOutputStream oos = new ObjectOutputStream(fout);
         oos.writeObject(instance);
         oos.close();
         LOGGER.info("Done saving gradebook");
         
      } catch (IOException e) {
         LOGGER.warning("Error in saving gradebook: ");
         e.printStackTrace();
      }
   }
   
   /**
    * Returns the Teacher of the Gradebook.
    * @return - the Teacher of the Gradebook.
    */
   public Teacher getTeacher() {
      return teacher;
   }
   
   /**
    * Returns the courses in the Gradebook.
    * @return - the courses in the Gradebook.
    */
   public ArrayList<SpreadsheetCourse> getCourses() {
      return courses;
   }

   /**
    * Returns the current course in the Gradebook.
    * @return - the current course in the Gradebook.
    */
   public SpreadsheetCourse getCurrentCourse() {
      return currentCourse;
   }

   /**
    * Sets the current SpreadsheetCourse to the course supplied.
    * @param currentCourse - The course to be set as the current one.
    */
   public void setCurrentCourse(SpreadsheetCourse currentCourse) {
      System.out.println("Setting current course to: " +
         currentCourse.getCourseInfo().getCourseName());
      this.currentCourse = currentCourse;
   }

   //generated method
   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Gradebook other = (Gradebook) obj;
      if (courses == null) {
         if (other.courses != null)
            return false;
      } else if (!courses.equals(other.courses))
         return false;
      if (currentCourse == null) {
         if (other.currentCourse != null)
            return false;
      } else if (!currentCourse.equals(other.currentCourse))
         return false;
      if (teacher == null) {
         if (other.teacher != null)
            return false;
      } else if (!teacher.equals(other.teacher))
         return false;
      return true;
   }
}