package model.gradebook;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import model.spreadsheet.SpreadsheetCourse;
import model.users.Teacher;

/**
 * A Gradebook is the overarching object of the grader.
 *	It includes a collection spreadsheetCourses that all have a user, a teacher, in common. 
 *	It is derived from the Teacher's View as show in section 1.1 of the requirements. 
 */

public class Gradebook implements Serializable{
   
   //possibly be implemented as HashMap
	private ArrayList<SpreadsheetCourse> courses;
	private SpreadsheetCourse currentCourse;
	private Teacher teacher;
	private static Gradebook instance;
	private static final Logger LOGGER =
	 Logger.getLogger(Gradebook.class.getName());
	
	private Gradebook() {
	   System.out.println("Congrats, you created a Gradebook!");
	   loadGradebook();
	}
	
	public static Gradebook getInstance() {
	   if (instance == null)
	      instance = new Gradebook();
	   
	   return instance;
	}
	
	
	/**
    * Adds a SpreadsheetCourse to the Gradebook. Each instance of the Grader Tool
    * only has one Gradebook. The Gradebook is the top level Object that contains a list of
    * SpreadsheetCourses and a Teacher.
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
   
   public SpreadsheetCourse getSpreadsheetCourse(Integer key) {
      
//      if (courses.containsKey(key)) 
//         return courses.get(key);
      
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
    * Edit an existing SpreadsheetCourse in the Gradebook. The old and new SpreadsheetCourses 
    * must not be the same. The old SpreadsheetCourse must be in the Gradebook. The new 
    * SpreadsheetCourse must meet the same conditions as the the conditions for
    * addSpreadsheetCourse.
    * @param oldCourse - The old SpreadsheetCourse to be modified
    * @param newCourse - The new SpreadsheetCourse that will take the place of the oldCourse
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
   
   private void loadGradebook() {
      FileInputStream fin;
      
//      try {
//         fin = new FileInputStream("someAddress");
//         ObjectInputStream ois = new ObjectInputStream(fin);
//         instance = (Gradebook) ois.readObject();
//         ois.close();
//         LOGGER.info("Done loading gradebook");
//         
//      } catch (IOException | ClassNotFoundException e) {
//         LOGGER.warning("Error in loading gradebook: " + e.getStackTrace());
//      }
   }
   
   public void saveGradebook() {
      FileOutputStream fout;
      
      try {
         fout = new FileOutputStream("someAddress");
         ObjectOutputStream oos = new ObjectOutputStream(fout);
         oos.writeObject(instance);
         oos.close();
         LOGGER.info("Done saving gradebook");
         
      } catch (IOException e) {
         LOGGER.warning("Error in saving gradebook: " + e.getStackTrace());
      }
   }
   
   public Teacher getTeacher() {
      return teacher;
   }
   
   public void setTeacher(Teacher teacher) {
      this.teacher = teacher;
   }
   
   public ArrayList<SpreadsheetCourse> getCourses() {
      return courses;
   }

   public SpreadsheetCourse getCurrentCourse() {
      return currentCourse;
   }

   public void setCurrentCourse(SpreadsheetCourse currentCourse) {
<<<<<<< HEAD
=======
      System.out.println("Setting current course to: " +
         currentCourse.getCourseInfo().getCourseName());
>>>>>>> 9fe84d803cfd6e1e86687215486d9201a83a1161
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