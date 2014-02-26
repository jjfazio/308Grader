package model.spreadsheet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import model.assignments_categories.Category;
import model.assignments_categories.CategoryContainer;
import model.exception.CourseDataException;
import model.file.Settings;
import model.users.Student;

import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion.Setting;


/**
 * A SpreadsheetCourse contains all related information associated with 
 * a Course. A SpreadsheetCourse has courseInfo, categories, a studentRoster,
 * a GradingDistribution, a LatePolicy, and Settings.
 *
 * @author jamesfazio
 */

public class SpreadsheetCourse extends Observable implements Serializable {

    private static final long serialVersionUID = -2177807641688753638L;

    /** {@link CourseInfo} related to the course. */
    private CourseInfo courseInfo;

    /**
     * {@link CategoryContainer} holds the hierarchical structure of categories
     *  and assignments.
     */
    private CategoryContainer categoryContainer;

    /** A list of {@link Student}. */
    private List<Student> studentRoster;
    
    /**
     * A list of students that got newly added to the
     * SpreadsheetCourse. This list will assist the view
     * in not having to do a full refresh
     */
    private List<Student> addedStudents;

    /** The {@link GradingScheme} associated with the course. */
    private GradingScheme gradingDistribution;

    /** The {@link LatePolicy} associated with the course. */
    private LatePolicy latePolicy;

    /**  The {@link Setting} associated with the course. */
    private Settings settings;
    
    /** Whether students have been deleted */
    private boolean isStudentDeleted = false;

    /** List of students to delete */
    private Student studentToDelete;
    
    /** Unique id of the Spreadsheet course */
    private int id;
    
   
    /**
     * Constructs a SpreadsheetCourse.
     * @param ci {@link CourseInfo} containing data about the course
     * @param gs {@link GradingScheme} of the course
     * @param lp {@link LatePolicy} of the course
     * @throws CourseDataException
     */
   public SpreadsheetCourse(CourseInfo ci, GradingScheme gs, LatePolicy lp) throws CourseDataException {
	      
       if (gs == null)
       {
           CourseDataException e = new CourseDataException("Must select a grading scheme");
           e.setBadGradingScheme(true);
           throw e;
       } else if (ci == null) {
           CourseDataException e = new CourseDataException("Must have name, section, and number");
           throw e;
       } else {
           courseInfo = ci;
           gradingDistribution = gs;
           latePolicy = lp;
           categoryContainer = new CategoryContainer();
           studentRoster = new ArrayList<Student>();
           addedStudents = new ArrayList<Student>();
           this.id = CourseDB.getInstance().getID();
       }
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
        studentRoster.add(student);
        addedStudents.add(student);
        
        setChanged();
        notifyObservers();
    }
    
    public void addStudents(List<Student> students) {
        studentRoster.addAll(students);
        addedStudents.addAll(students);
        
        /** Adds the selected course to each students collection of enrolled courses */
        for(int index = 0; index < students.size(); index++)
        {
            students.get(index).addCourse(this);
        }
        
        setChanged();
        notifyObservers();
    }

    /**
     * Change the given old Student to the given new Student.  The old and the new student records must not be the same.
     * The old student record must already be in the current studentRoster.  The new student must meet the same conditions
     * as for the input to the addStudent operation.
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
    public void editStudent() {
       System.out.println("In SpreadsheetCourse.editStudent");
        setChanged();
        notifyObservers();
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
        isStudentDeleted = true;
        studentToDelete = student;
        studentRoster.remove(student);
        addedStudents.remove(student);
        setChanged();
        notifyObservers();
    }

    public Student getStudentToDelete() {
        return studentToDelete;
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
    
    public Boolean isStudentAdded() 
    {
        return !addedStudents.isEmpty();
    }

    public Boolean isStudentDeleted() {
        boolean holder = isStudentDeleted;
        isStudentDeleted = false;
        return holder;
    }
    
    /**
     * Gets the students recently added. Whenever this method gets called
     * it clears the recently added students so no duplicates occur.
     * @return List of recently added {@link Student}
     */
    public List<Student> getAddedStudents()
    {
        List<Student> temp = new ArrayList<Student>(addedStudents);
        addedStudents.clear();
        return temp;
    }
    

    public CourseInfo getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(CourseInfo courseInfo) {
        this.courseInfo = courseInfo;
    }

    public CategoryContainer getCategoryContainer() {
        return categoryContainer;
    }

    public List<Student> getStudentRoster() {
        return studentRoster;
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
    
    public int getID()
    {
        return id;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SpreadsheetCourse other = (SpreadsheetCourse) obj;
        if (addedStudents == null)
        {
            if (other.addedStudents != null)
                return false;
        }
        else if (!addedStudents.equals(other.addedStudents))
            return false;
        if (categoryContainer == null)
        {
            if (other.categoryContainer != null)
                return false;
        }
        else if (!categoryContainer.equals(other.categoryContainer))
            return false;
        if (courseInfo == null)
        {
            if (other.courseInfo != null)
                return false;
        }
        else if (!courseInfo.equals(other.courseInfo))
            return false;
        if (gradingDistribution == null)
        {
            if (other.gradingDistribution != null)
                return false;
        }
        else if (!gradingDistribution.equals(other.gradingDistribution))
            return false;
        if (id != other.id)
            return false;
        if (isStudentDeleted != other.isStudentDeleted)
            return false;
        if (latePolicy == null)
        {
            if (other.latePolicy != null)
                return false;
        }
        else if (!latePolicy.equals(other.latePolicy))
            return false;
        if (settings == null)
        {
            if (other.settings != null)
                return false;
        }
        else if (!settings.equals(other.settings))
            return false;
        if (studentRoster == null)
        {
            if (other.studentRoster != null)
                return false;
        }
        else if (!studentRoster.equals(other.studentRoster))
            return false;
        if (studentToDelete == null)
        {
            if (other.studentToDelete != null)
                return false;
        }
        else if (!studentToDelete.equals(other.studentToDelete))
            return false;
        return true;
    }

    
} 
