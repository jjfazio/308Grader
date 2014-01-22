package spreadsheet;

import java.util.ArrayList;
import java.util.Collection;

import users.TeacherAssistant;

/**
 * Course contains a courseName, a quarter, a number, and a dept along with 
 * methods to manipulate these fields
 */
public class CourseInfo {
    /**
     * The name of the course, for example 'Intro to Databases', must not be null
     */
    String courseName;

    /**
     * The quarter this class is being taught, must not be null
     */
    String quarter;

    /**
     * The course number, for example '365', must not be null
     */
    String number;

    /**
     * The dept the course belongs to, for example CPE, must not be null
     */
    String dept;
    Collection<TeacherAssistant> teacherAssistants;


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
       if (teacherAssistants == null) {
          teacherAssistants = new ArrayList<TeacherAssistant>();
       }
       
       teacherAssistants.add(ta);
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
    //probably not needed
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
       teacherAssistants.remove(ta);
    }

   public String getCourseName() {
      return courseName;
   }

   public void setCourseName(String courseName) {
      this.courseName = courseName;
   }

   public String getQuarter() {
      return quarter;
   }

   public void setQuarter(String quarter) {
      this.quarter = quarter;
   }

   public String getNumber() {
      return number;
   }

   public void setNumber(String number) {
      this.number = number;
   }

   public String getDept() {
      return dept;
   }

   public void setDept(String dept) {
      this.dept = dept;
   }

   //auto generated
   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      CourseInfo other = (CourseInfo) obj;
      if (courseName == null) {
         if (other.courseName != null)
            return false;
      } else if (!courseName.equals(other.courseName))
         return false;
      if (dept == null) {
         if (other.dept != null)
            return false;
      } else if (!dept.equals(other.dept))
         return false;
      if (number == null) {
         if (other.number != null)
            return false;
      } else if (!number.equals(other.number))
         return false;
      if (quarter == null) {
         if (other.quarter != null)
            return false;
      } else if (!quarter.equals(other.quarter))
         return false;
      if (teacherAssistants == null) {
         if (other.teacherAssistants != null)
            return false;
      } else if (!teacherAssistants.equals(other.teacherAssistants))
         return false;
      return true;
   }
   
   
    
    
}
