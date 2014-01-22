package users;
import java.util.Collection;

import spreadsheet.CourseInfo;
import spreadsheet.SpreadsheetCourse;
import assignments_categories.Grade;

/**
 * A student is composed of a first name, last name, username, id, email, 
 * dateOfBirth, a collection of courses enrolled, a phone number, and a grade 
 * level.  Students will belong to a student roster in the SpreadsheetCourse class.
 * Each student also contains a collection of grades that are linked to specific assignments.
 */

public abstract class Student {
   String userName;
   String firstName;
   String middleName;
   String lastName;
   String id;
   String major;
   String gradeLevel; //probably should be an enum
   String email;
   String phoneNumber;
   Collection<SpreadsheetCourse> courseEnrolled;
   Collection<Grade> grades;

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
    abstract void addGrade(Grade grade);

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
    abstract void removeGrade(Grade grade);
    
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

    abstract void editGrade(Grade oldGrade, Grade newGrade);
    
    
}

