package model.users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import model.assignments_categories.Assignment;
import model.assignments_categories.Grade;
import model.spreadsheet.SpreadsheetCourse;

/****
 *
 * Class Student is composed of a first name, last name, username, id, email,
 * dateOfBirth, a collection of courses enrolled, a phone number, and a grade 
 * level.  Students will belong to a student roster in the SpreadsheetCourse class.
 * Each student also contains a collection of grades that are linked to specific
 * assignments.  Class Student provides methods to add, edit, or remove
 * grades; add or or remove courses; and edit student info.
 *
 * @author      Kevin Feutz     (kfeutz@calpoly.edu)
 *
 */

public class Student implements Serializable {
    /** Contains the username of the student */
    private String userName;
    /** Contains the first name of the student */
    private String firstName;
    /** Contains the middle name of the student */
    private String middleName;
    /** Contains the last name of the student */
    private String lastName;
    /** Contains the student id as a string */
    private String id;
    /** Contains the student's current major */
    private String major;
    /** Contains the student's current grade level */
    private String gradeLevel;
    /** Contains the student's email address */
    private String email;
    /** Contains the student's phone number */
    private String phoneNumber;
   
    /** Contains the collection of courses that the student is enrolled */
    private ArrayList<SpreadsheetCourse> coursesEnrolled;

    /**
     * Contains a link between an assignment and the student's grade on
     * that assignment
     */
    private HashMap<Assignment, Grade> grades;

    /**
     * Class constructor that takes in
     */
    public Student(String userName, String firstName, String lastName,
            String id, String major, String gradeLevel)
    {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.major = major;
        this.gradeLevel = gradeLevel;
    }

    /**
     * Adds the passed grade to the this Student's collection of grades.
     * The Grade "grade" added must not already exist in this Student's
     * grade collection.  A valid Assignment "assignment" must also be passed
     * in order to create the appropriate mapping from "assignment" to "grade"
     *
     * pre: Assignment assignment must be a valid assignment for this Student's
     *      course.  Also, the student must not have a Grade for the same assignment
     *      already in the collection
     *
     * post: Grade grade is mapped to Assignment assignment
     *
     * @param   assignment       The Assignment in which the passed
     *                           grade will be mapped to.
     * @param   grade            The Grade that is being added to
     *                           this Student.
     *
     */

     /*@
       requires
         //
         // There is no Grade for the same assignment already in the collection
         //
         (! (\exists Grade gradeInSet ;
             this.grades.contains(grade) ;
             gradeInSet.equals(grade)))

       ensures
         //
         // A Grade is in the grades collection if and only if it is the new grade to be added
         // or it is in the input data.
         //
         (\forall Grade gradeInSet ;
              (this.grades.contains(gradeInSet)) <==>
                    gradeInSet.equals(grade) || \oldthis.grads.contains(gradeInSet));
     @*/
    public void addGrade(Assignment assignment, Grade grade) {
        /*
         * Checks if this student's collection of grades is currently
         * empty.  If it is, a new HashMap collection is created
         */
        if (grades == null) {
            grades = new HashMap<Assignment, Grade>();
        }
        /*
         * Maps the passed Assignment to the passed Grade
         */
        grades.put(assignment, grade);
    }

    /**
     * Removes the passed grade from this Student's current collection of grades.
     * The grade removed must exist in this Student's current grades collection.
     *
     * pre:      Assignment assignment is a valid Assignment in this Student's grade
     *           collection.  This student's grade collection must contain a grade
     *           that maps to the passed Assignment
     *
     * post:     This Student's HashMap no longer contains a mapping from the passed
     *           Assignment to its corresponding grade.
     *
     * @param    assignment      The Assignment which maps to the correct
     *                           grade to be removed
     *
     */

    /*@
       requires
         //
         // The given grade is in grades collection
         //
         this.grades.contains(grade);

       ensures
         //
         // A grade is in the output this.grades if and only if it
         // is not the existing grade to be deleted
         // and it is in the input this.grades.
         //
         (\forall Grade gradeInSet ;
             this.grades.contains(gradeInSet) <==>
             !gradeInSet.equals(grade) && \old(this.grades).contains(gradeInSet));
    @*/
    public void removeGrade(Assignment assignment) {
        grades.remove(assignment);
    }

    /**
     * This method is used to edit an existing grade in this Student's
     * grade collection.  This method takes in a grade to be editted,
     * along with a grade containing the new value to be assigned.
     *
     * pre:     The Grade oldGrade and the Grade newGrade must not be the same.
     *          The Grade oldGrade must already be in the current grades collection.
     *
     * post:    Grade oldGrade changes to and now equals Grade newGrade
     *
     * @param    oldGrade     The Grade that will be editted by this
     *                        method
     * @param    newGrade     The Grade containing the new value for
     *                        oldGrade
     *
     */

    /*@
       requires
          //
          // The old and new Grades are not the same
          //
          !oldGrade.equals(newGrade)

       ensures
          //
          // A Grade is in the output this.grades if and only if
          // it is the new record to be added or it is
          // in the input data, and it is not the old record
          //
          (\forall Grade gradeInCollection ;
              this.grades.contains(gradeInCollection) <==>
                  gradeInCollection.equals(newGrade) ||
                     (\old(this.grades).contains(gradeInCollection) &&
                        !gradeInCollection.equals(oldGrade)));
    @*/
    public void editGrade(Grade oldGrade, Grade newGrade) {
    }

    /**
     * This method adds a SpreadsheetCourse to the collection
     * of the Student's enrolled courses.  This student must not
     * be enrolled in the passed SpreadsheetCourse.
     *
     * pre:     This Student is not already enrolled in the passed
     *          SpreadsheetCourse
     *
     * post:    The passed SpreadsheetCourse is now in this Student's
     *          collection of SpreadsheetCourses
     *
     * @param   course    The SpreadsheetCourse to add
     *                    to this Student's collection of
     *                    SpreadsheetCourses
     *
     */
    public void addCourse(SpreadsheetCourse course) {
        /*
         * Checks if this Student's collection of courses
         * enrolled is empty and creates a new ArrayList if
         * it is indeed empty
         */
        if(coursesEnrolled == null) {
            coursesEnrolled = new ArrayList<SpreadsheetCourse>();
        }

        coursesEnrolled.add(course);
        System.out.println("In Student.addCourse");
    }

    /**
     * This method removes a SpreadsheetCourse from the collection
     * of the Student's enrolled courses.  This student must
     * be enrolled in the passed SpreadsheetCourse.
     *
     * pre:     This Student is already enrolled in the passed
     *          SpreadsheetCourse
     *
     * post:    The passed SpreadsheetCourse is now not in this Student's
     *          collection of SpreadsheetCourses
     *
     * @param   course    The SpreadsheetCourse to remove
     *                    from this Student's collection of
     *                    SpreadsheetCourses
     *
     */
    public void removeCourse(SpreadsheetCourse course) {
        if(coursesEnrolled != null && coursesEnrolled.contains(course))
            coursesEnrolled.remove(course);
        System.out.println("In Student.removeCourse");
    }

    /**
     * This method  edits a Student's information.  This method will
     * call various setter methods in the Student class
     *
     * pre:     Student newStudentInfo contains at least one different
     *          field in Student and all of the fields are valid
     *
     * post:    This Student contains data matching the passed
     *          newStudentInfo's info
     *
     * @param   newStudentInfo              Contains data to be copied
     *                                      over to this Student
     *
     */ 
    public void editStudentInfo(Student newStudentInfo) {
        System.out.println("In Student.editStudentInfo");
    }

    /**
     * Computes the corresponding grade in this Student's grade collection
     * by finding the value mapped to by the passed Assignment.
     *
     * pre:     The passed Assignment is a key in this Student's map collection
     *          of assignments and grades
     *
     * post:    The correct Grade value is returned
     *
     * @param   assign              The assignment that maps to the desired
     *                              Grade in this Student's collection of
     *                              Grades
     * @return  Grade               The Grade mapped to by the passed Assignment
     */
    public Grade getAssignmentGrade(Assignment assign) {
        return grades.get(assign);
    }

    /**
     * Gets this Student's userName
     *
     * @return  String         The username of this Student
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets this Student's username to the passed String
     *
     * @param   userName     The new username to set to
     *                       this Student
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Retrieves and returns this Student's first name
     *
     * @return  String      The first name of this Student
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Changes this Student's current first name to the passed
     * String
     *
     * @param   firstName    The representation of the first
     *                              name of this Student
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retrieves and returns this Student's middle name
     *
     * @return  String      The middle name of this Student
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Changes this Student's current middle name to the passed
     * String
     *
     * @param   middleName   The new middle name to set to
     *                       this Student
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Retrieves and returns this Student's last name
     *
     * @return  String      The last name of this Student
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Changes this Student's current last name to the passed
     * String
     *
     * @param   lastName   The new last name to set to
     *                     this Student
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieves and returns this Student's id
     *
     * @return  String      The id of this Student
     */
    public String getId() {
        return id;
    }

    /**
     * Changes this Student's current id to the passed
     * String
     *
     * @param   id           The new id to set to
     *                       this Student
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retrieves and returns this Student's major
     *
     * @return  String      The major of this Student
     */
    public String getMajor() {
        return major;
    }

    /**
     * Changes this Student's current major to the passed
     * String
     *
     * @param   major      The new major to set to
     *                     this Student
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * Retrieves and returns this Student's grade level
     *
     * @return  String      The grade level of this Student
     */
    public String getGradeLevel() {
        return gradeLevel;
    }

    /**
     * Changes this Student's current grade level to the passed
     * String
     *
     * @param   gradeLevel   The new grade level to set to
     *                       this Student
     */
    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    /**
     * Retrieves and returns this Student's email
     *
     * @return  String      The email address of this Student
     */
    public String getEmail() {
        return email;
    }

    /**
     * Changes this Student's current email to the passed
     * String
     *
     * @param   email   The new email to set to
     *                  this Student
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves and returns this Student's phone number
     *
     * @return  String      The phone number of this Student
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Changes this Student's current phone number to the passed
     * String
     *
     * @param   phoneNumber  The new phone number to set to
     *                       this Student
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Retrieves and returns this Student's enrolled courses
     *
     * @return  ArrayList      The collection of SpreadSheetCourses
     *                         belonging to this student
     */
    public ArrayList<SpreadsheetCourse> getCoursesEnrolled() {
        return coursesEnrolled;
    }

    /**
     * Retrieves and returns this Student's grades
     *
     * @return  HashMap      The map containing links to this
     *                       Student's assignments and grades for
     *                       each assignment
     */
    public HashMap<Assignment, Grade> getGrades() {
        return grades;
    }
}

