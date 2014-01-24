package model.users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import model.assignments_categories.Assignment;
import model.assignments_categories.Grade;
import model.spreadsheet.SpreadsheetCourse;

/**
 * A student is composed of a first name, last name, username, id, email, 
 * dateOfBirth, a collection of courses enrolled, a phone number, and a grade 
 * level.  Students will belong to a student roster in the SpreadsheetCourse class.
 * Each student also contains a collection of grades that are linked to specific assignments.
 *
 * @author Kevin Feutz
 */

public class Student implements Serializable {
   String userName;
   String firstName;
   String middleName;
   String lastName;
   String id;
   String major;
   String gradeLevel; //probably should be an enum
   String email;
   String phoneNumber;
   
   //might use hashcode for list
   ArrayList<SpreadsheetCourse> coursesEnrolled;
   
   //might use hashcode for key
   HashMap<Assignment, Grade> grades;

   public Student() {
   }

    /**
     * Adds the passed grade to the Student's collection of grades.  The grade added
     * must not exist in the grades collection
     * @param grade
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
       if (grades == null) {
          grades = new HashMap<Assignment, Grade>();
       }
       
       grades.put(assignment, grade);
    }

    /**
     * Removes the passed grade from the Student's collection of grades.  The grade removed
     * must exist in the grades collection
     * @param grade
     */

    /*@
    requires
    //
    // The given grade is in grades collection
    //
    this.grades.contains(grade);

    ensures
        //
        // A grade is in the output this.grades if and only if it is not the existing grade to be deleted
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
     * This method is used to edit an existing grade.
     * The old and the new Grade records must not be the same.
     * The old Grade record must already be in the current grades collection.
     * @param oldGrade
     * @param newGrade
     */
    /*@
     requires
       //
       // The old and new Grades are not the same
       //
       !oldGrade.equals(newGrade)

     ensures
       //
       // A Grade is in the output this.grades if and only if it is the new record to be added or it is
       // in the input data, and it is not the old record
       //
       (\forall Grade gradeInCollection ;
           this.grades.contains(gradeInCollection) <==>
               gradeInCollection.equals(newGrade) ||
                  (\old(this.grades).contains(gradeInCollection) &&
                     !gradeInCollection.equals(oldGrade)));
     @*/

    //probably not needed
    public void editGrade(Grade oldGrade, Grade newGrade) {
       
    }
    
    /**
     * This method adds a SpreadsheetCourse to the collection
     * of the Student's enrolled courses
     */
    public void addCourse(SpreadsheetCourse course) {
       /*
       if (coursesEnrolled == null) {
          coursesEnrolled = new ArrayList<SpreadsheetCourse>();
       }
       
       coursesEnrolled.add(course);
       */
       System.out.println("In Student.addCourse");
    }
    
    /**
     * This method removes a SpreadsheetCourse from the collection
     * of the Student's enrolled courses
     */
    public void removeCourse(SpreadsheetCourse course) {
       /*
       coursesEnrolled.remove(course);
       */
       System.out.println("In Student.removeCourse");
    }

    /**
     * This method will be called when the user edits a Student's
     * information.  This method will call various setter methods in
     * the Student class
     */ 
    public void editStudentInfo() {
        System.out.println("In Student.editStudentInfo");
    }    


    public Grade getAssignmentGrade(Assignment assign) {
       return grades.get(assign);
    }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getMiddleName() {
      return middleName;
   }

   public void setMiddleName(String middleName) {
      this.middleName = middleName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getMajor() {
      return major;
   }

   public void setMajor(String major) {
      this.major = major;
   }

   public String getGradeLevel() {
      return gradeLevel;
   }

   public void setGradeLevel(String gradeLevel) {
      this.gradeLevel = gradeLevel;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public ArrayList<SpreadsheetCourse> getCoursesEnrolled() {
      return coursesEnrolled;
   }

   public HashMap<Assignment, Grade> getGrades() {
      return grades;
   }
}

