package model.spreadsheet;

import java.io.Serializable;
import java.util.Collection;

import model.users.TeacherAssistant;

/**
 * Course contains a courseName, a quarter, a number, and a dept along with 
 * methods to manipulate these fields
 */
public class CourseInfo implements Serializable{
    private static final long serialVersionUID = 8576375778985622591L;

    /**
     * The name of the course, for example 'Intro to Databases', must not be null
     */
    private String courseName;

    /**
     * The quarter this class is being taught, must not be null
     */
    private String quarter;
    
    private String section;

    /**
     * The course number, for example '365', must not be null
     */
    private String number;

    /**
     * The dept the course belongs to, for example CPE, must not be null
     */
    private String dept;
    private Collection<TeacherAssistant> teacherAssistants;
    
    public CourseInfo(String name, String quarter, String section, String number,
            String dept) {
        this.courseName = name;
        this.quarter = quarter;
        this.number = number;
        this.section = section;
        this.dept = dept;
        System.out.println("Created a new CourseInfo for a SpreadsheetCourse");
    }


    /**
     * Adds the passed TeacherAssistant to the current CourseInfo.  The teacher assistant is added to the CourseInfo's collection
     * of TeacherAssistants.  The Id of the TA must not be the same Id of a TA already enrolled as one of the course info's TAs.
     * The first name and the last name of the Student are required Strings.  This new Teacher Assistant also has TaPermissions, which
     * are a set of booleans that define what a TA can and cannot do in the application.  This field is not required at the time of this
     * method
     * @param ta
     */
   /*@
     requires
       //
       // There is no TeacherAssistant already in the teacherAssistants collection with the same Id as the TeacherAssistant to add.
       //
       (! (\exists TeacherAssistant taInCollection ;
           this.teacherAssistants.contains(ta) ;
           taInCollection.id.equals(ta.id)))

           &&

       //
       // The first name, and last name, and id are not empty strings
       //
       (ta.id != null) && (ta.firstName != null) && (ta.lastName != null) && (ta.id.length() > 0)
       && (ta.firstName.length() > 0) && (ta.lastName.length() > 0);

     ensures
       //
       // A TeacherAssistant is in the teacherAssistants collection if and only if it is the new record to be added
       // or it is in the input data.
       //
       (\forall TeacherAssistant taInCollection ;
            (this.teacherAssistants.contains(taInCollection)) <==>
                  taInCollection.equals(ta) || \old(this.teacherAssistants).contains(taInCollection));
    @*/
    public void addTeacherAssistant(TeacherAssistant ta) {
        
    }

    /**
     * Change the given old TA to the given new TA.  The old and the new TA records must not be the same.
     * The old TA record must already be in the current teacherAssistants collection.  The new TA must meet the same
     * conditions as for the input to the addTeacherAssistant operation.
     * @param oldTa
     * @param newTa
     */
   /*@
     requires
       //
       // The old and new TAs are not the same
       //
       !oldTa.equals(newTa)

          &&

       //
       // There is no TA already included in this teacherAssistants collection with the same Id as the TA to add.
       //
       (! (\exists TeacherAssistant taInCollection ;
           this.teacherAssistants.contains(taInCollection) ;
           taInCollection.id.equals(newTa.id)))

           &&

       //
       // The first name, last name, and id of the new TA are not empty and are all strings
       //
       (newTa.id != null) && (newTa.firstName != null) && (newTa.lastName != null) && (newTa.id.length() > 0)
       && (newTa.firstName.length() > 0) && (newTa.lastName.length() > 0);

     ensures
       //
       // A TA is in the output this.teacherAssistants if and only if it is the new record to be added or it is
       // in the input data, and it is not the old record
       //
       (\forall TeacherAssistant taInCollection ;
           this.teacherAssistants.contains(taInCollection) <==>
               taInCollection.equals(newTa) ||
                  (\old(this.teacherAssistants).contains(taInCollection) &&
                     !taInCollection.equals(oldTa)));
     @*/
    //prob not used
    public void editTeacherAssistant(TeacherAssistant oldTa, TeacherAssistant newTa) {
        
    }

    /**
     * Delete the given TA from the the current teacherAssistants collection.  The given TA must already be in the
     * current teacherAssistants collection.
     * @param student
     * @param course
     */
   /*@
    requires
      //
      // The given TA is in the current collection of teacherAssistants
      //
      this.teacherAssistants.contains(ta);

    ensures
      //
      // A TA is in the output this.teacherAssistants if and only if it is not the existing TA to be deleted
      // and it is in the current this.teacherAssistants.
      //
      (\forall TeacherAssistant taInCollection ;
          this.teacherAssistants.contains(taInCollection) <==>
              !taInCollection.equals(ta) && \old(this.teacherAssistants).contains(taInCollection));
    @*/

    public void removeTeacherAssistant(TeacherAssistant ta) {
        
    }

    public String getCourseName()
    {
        return courseName;
    }

    public String getQuarter()
    {
        return quarter;
    }

    public String getNumber()
    {
        return number;
    }

    public String getDept()
    {
        return dept;
    }

    public Collection<TeacherAssistant> getTeacherAssistants()
    {
        return teacherAssistants;
    }
    
}
