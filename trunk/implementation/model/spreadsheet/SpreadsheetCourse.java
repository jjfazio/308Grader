package model.spreadsheet;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.control.ComboBox;
import model.assignments_categories.Category;
import model.file.Settings;
import model.users.Student;


/**
 * A SpreadsheetCourse contains all related information associated with 
 * a Course. A SpreadsheetCourse has courseInfo, categories, a studentRoster,
 * a GradingDistribution, a LatePolicy, and Settings.
 *
 */

public class SpreadsheetCourse implements Serializable {

    private static final long serialVersionUID = -2177807641688753638L;

    /**
     * {@link CourseInfo} related to the course.
     */
    private CourseInfo courseInfo;

    /**
     * Top level category.
     */
    private Category topCategory;

    /**
     * A list of {@link Student}.
     */
    private ArrayList<Student> studentRoster;

    /**
     * The {@link GradingScheme} associated with the course.
     */
    private GradingScheme gradingDistribution;

    /**
     * The {@link LatePolicy} associated with the course.
     */
    private LatePolicy latePolicy;

    /**
     * The {@link Setting} associated with the course.
     */
    private Settings settings;

    /**
     * The {@link addCategoryParentName} associated with the course.
     */
    private ComboBox addCategoryParentName;

    /**
     * Adds category of assignments in the spread sheet which organizes the assignments into groups.
     */
   
   public SpreadsheetCourse() {
      System.out.println("Created a Spreadsheet Course!");
      topCategory = new Category();      
   }
   
   public SpreadsheetCourse(CourseInfo ci, GradingScheme gs, LatePolicy lp) {
	      topCategory = new Category();
          //Shows the top category in the drop down list,
        //  addCategoryParentName.setValue(topCategory.getName());
	      courseInfo = ci;
	      gradingDistribution = gs;
	      latePolicy = lp;
	      System.out.println("Creating a Spreadsheet Course named: " + 
	      courseInfo.getCourseName());
	   }


    /**
     * Adds the passed student to the current spreadsheet course.  The Student is added to the SpreadsheetCourses's collection
     * of students.  The Id of the Student must not be the same Id of a Student already enrolled in the SpreadsheetCourse.
     * The first name and the last name of the Student are required Strings.  Middle Name, Username, and Major are
     * not required, but must be strings if included.  Email is not required, but must include an "@" symbol in the
     * string.  Phone number is not required, but if given, the number must be 10 digits in length.
     * Grade level is not required but must be a string if included.
     * @param student
     */
   /*@
     requires
       //
       // There is no Student already enrolled in the SpreadsheetCourse with the same Id as the student to add.
       //
       (! (\exists Student studentInCourse ;
           this.studentRoster.contains(student) ;
           studentInCourse.id.equals(student.id)))

           &&

       //
       // The first name, last name, and id are not empty and are all strings
       //
       (student.id != null) && (student.firstName != null) && (student.lastName != null) && (student.id.length() > 0)
       && (student.firstName.length() > 0) && (student.lastName.length() > 0)

           &&

       //
       // If an Email is present, it must include an @ symbol somewhere in the middle of the string
       //
       (student.email.length() > 0) ==> (student.email.indexOf("@") > 0)
       
       &&

       //
       // If the phone number is present, it must be a 10 digit number
       //
       ((student.phoneNumber.length() != 0) ==> student.phoneNumber.length() == 10);

     ensures
       //
       // A Student is in the new SpreadsheetCourse if and only if it is the new record to be added
       // or it is in the input data.
       //
       (\forall Student studentInCourse ;
            (this.studentRoster.contains(studentInCourse)) <==>
                  studentInCourse.equals(student) || \old(this.studentRoster).contains(studentInCourse));
    @*/
    public void addStudent(Student student) {
        System.out.println("In SpreadsheetCourse.addStudent");
    }

    /**
     * Change the given old Student to the given new Student.  The old and the new student records must not be the same.
     * The old student record must already be in the current studentRoster.  The new student must meet the same conditions
     * as for the input to the addStudent operation.
     * @param oldStudent
     * @param newStudent
     */
   /*@
     requires
       //
       // The old and new students are not the same
       //
       !oldStudent.equals(newStudent)

          &&

       //
       // There is no Student already enrolled in the course with the same Id as the student to add.
       //
       (! (\exists Student studentInCourse ;
           this.studentRoster.contains(student) ;
           studentInCourse.id.equals(student.id)))

           &&

       //
       // The first name, last name, and id of the new student are not empty and are all strings
       //
       (newStudent.id != null) && (newStudent.firstName != null) && (newStudent.lastName != null) && (newStudent.id.length() > 0)
       && (newStudent.firstName.length() > 0) && (newStudent.lastName.length() > 0)

           &&

       //
       // If an Email is present in the new student, it must include an @ symbol somewhere in the middle of the string
       //
       (newStudent.email.length() > 0) ==> (newStudent.email.indexOf('@') > 0)
       
       &&

       //
       // If the phone number is present for the new student, it must be a 10 digit number
       //
       ((newStudent.phoneNumber.length() != 0) ==> newStudent.phoneNumber.length() == 10);

     ensures
       //
       // A student is in the output this.studentRoster if and only if it is the new record to be added or it is
       // in the input data, and it is not the old record
       //
       (\forall Student studentInCourse ;
           this.studentRoster.contains(studentInCourse) <==>
               studentInCourse.equals(newStudent) ||
                  (\old(this.studentRoster).contains(studentInCourse) &&
                     !studentInCourse.equals(oldStudent)));
     @*/
    public void editStudent(Student oldStudent, Student newStudent) {
       System.out.println("In SpreadsheetCourse.editStudent");      
    }

    /**
     * Delete the given student from the current SpreadsheetCourse.  The given student must already be in
     * this.studentRoster.
     * @param student
     */
   /*@
    requires
      //
      // The given student is in this.studentRoster
      //
      this.studentRoster.contains(student);

    ensures
      //
      // A student is in the output this.studentRoster if and only if it is not the existing student to be deleted
      // and it is in the input this.studentRoster.
      //
      (\forall Student studentInCourse ;
          this.studentRoster.contains(studentInCourse) <==>
              !studentInCourse.equals(student) && \old(this.studentRoster).contains(studentInCourse));
    @*/

    public void deleteStudent(Student student) {
       System.out.println("In SpreadsheetCourse.deleteStudent");
    }
       
       /* TODO: CHANGE JML */

    /**
     * Sets the percentage curve for a particular category
     */
       /*@
        requires
        //
        //the amount curved parameter must be a decimal between -1 and 1
        //
        (amountCurved >= -1 && amountCurved <= 1);
        
        ensures
        //
        //the percentCurve field in Category is changed to match the amountCurved parameter
        //
        (category.percentCurve.equals(amountCurved));
       @*/
    public void adjustCourseCurve(Double amountCurved) {
       
    }

   public CourseInfo getCourseInfo() {
      return courseInfo;
   }

   public void setCourseInfo(CourseInfo courseInfo) {
      this.courseInfo = courseInfo;
   }

   public Category getTopCategory() {
      return topCategory;
   }

   public ArrayList<Student> getStudentRoster() {
      return studentRoster;
   }

   public void setStudentRoster(ArrayList<Student> studentRoster) {
      this.studentRoster = studentRoster;
   }

   public GradingScheme getGradingDistribution() {
      return gradingDistribution;
   }

   public void setGradingDistribution(GradingScheme gradingDistribution) {
      this.gradingDistribution = gradingDistribution;
   }

   public LatePolicy getLatePolicy() {
      return latePolicy;
   }

   public void setLatePolicy(LatePolicy latePolicy) {
      this.latePolicy = latePolicy;
   }

   public Settings getSettings() {
      return settings;
   }

   public void setSettings(Settings settings) {
      this.settings = settings;
   }

   //generated
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((topCategory == null) ? 0 : topCategory.hashCode());
      result = prime * result
            + ((courseInfo == null) ? 0 : courseInfo.hashCode());
      result = prime
            * result
            + ((gradingDistribution == null) ? 0 : gradingDistribution
                  .hashCode());
      result = prime * result
            + ((latePolicy == null) ? 0 : latePolicy.hashCode());
      result = prime * result + ((settings == null) ? 0 : settings.hashCode());
      result = prime * result
            + ((studentRoster == null) ? 0 : studentRoster.hashCode());
      return result;
   }


   //generated
   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      SpreadsheetCourse other = (SpreadsheetCourse) obj;
      if (topCategory == null) {
         if (other.topCategory != null)
            return false;
      } else if (!topCategory.equals(other.topCategory))
         return false;
      if (courseInfo == null) {
         if (other.courseInfo != null)
            return false;
      } else if (!courseInfo.equals(other.courseInfo))
         return false;
      if (gradingDistribution == null) {
         if (other.gradingDistribution != null)
            return false;
      } else if (!gradingDistribution.equals(other.gradingDistribution))
         return false;
      if (latePolicy == null) {
         if (other.latePolicy != null)
            return false;
      } else if (!latePolicy.equals(other.latePolicy))
         return false;
      if (settings == null) {
         if (other.settings != null)
            return false;
      } else if (!settings.equals(other.settings))
         return false;
      if (studentRoster == null) {
         if (other.studentRoster != null)
            return false;
      } else if (!studentRoster.equals(other.studentRoster))
         return false;
      return true;
   }
   
   
    
    
    
    
} 
