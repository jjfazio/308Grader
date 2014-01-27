package model.file;

import java.io.Serializable;

/**
 * Settings Object for a given SpreadsheetCourse.
 * @author jamesfazio
 *
 */
public class Settings implements Serializable {

/*
    *  Value specifiying if students can view grades before the final grade is posted	
    */
   Boolean studentsCanViewBeforeFinalGrade;
   
   /*
    *  Value specifiying if users that are not enrolled in the class can view the course grades	
    */
   Boolean allowOutsideViewers;
   
   /*
    *  Value specifiying if grades can be overridden	
    */
   Boolean canOverrideGrades;
   
   //More settings might exist
   private static final long serialVersionUID = -7661213772368159182L;
}
