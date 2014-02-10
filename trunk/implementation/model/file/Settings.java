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
   private Boolean alwaysViewGrades;
   
   /*
    *  Value specifiying if users that are not enrolled in the class can view the course grades	
    */
   private Boolean allowOutsideViews;
   
   /*
    *  Value specifiying if grades can be overridden	
    */
   private Boolean canOverrideGrades;
   
   //More settings might exist
   private static final long serialVersionUID = -7661213772368159182L;
   
   public Settings(Boolean alwaysViewGrades, Boolean allowOutsideViews,
           Boolean canOverrideGrades) {
       this.alwaysViewGrades = alwaysViewGrades;
       this.allowOutsideViews = allowOutsideViews;
       this.canOverrideGrades = canOverrideGrades;
   }

   public Boolean getAlwaysViewGrades()
   {
       return alwaysViewGrades;
   }

   public Boolean getAllowOutsideViews()
   {
       return allowOutsideViews;
   }

   public Boolean getCanOverrideGrades()
   {
       return canOverrideGrades;
   }
   
}
