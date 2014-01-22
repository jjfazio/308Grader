package spreadsheet;

import java.util.Collection;

import assignments_categories.Category;

import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion.Setting;

import file.Settings;

/**
 * A SpreadsheetCourse contains all related information associated with 
 * a Course. A SpreadsheetCourse has courseInfo, categories, a studentRoster,
 * a GradingDistribution, a LatePolicy, and Settings.
 *
 */

public abstract class SpreadsheetCourse {
   
    private int key;
    /**
     * {@link CourseInfo} related to the course.
     */
    private CourseInfo courseInfo;

    /**
     * Top level category.
     */
    private Category category;

    /**
     * A list of {@link Student}.
     */
    private Collection<Student> studentRoster;

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
     * Adds category of assignments in the spread sheet which organizes the assignments into groups.
     */


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
    abstract void addStudent(Student student);

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
    abstract void editStudent(Student oldStudent, Student newStudent);

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

    abstract void deleteStudent(Student student);
       
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
    abstract void adjustCourseCurve(Double amountCurved);
    
    
} 
