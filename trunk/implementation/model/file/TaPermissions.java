package model.file;

import java.io.Serializable;

/**
 * Permissions object for a Teacher's Assistant.
 * Contains pertinent information about what operations
 * a Teacher's assistant can perform.
 * @author jamesfazio
 */
public class TaPermissions implements Serializable{
    
    private static final long serialVersionUID = -5719542235726581536L;

    /*
	 * Value specifying if a TA is allowed to download roster from SIS
	 */
   private Boolean canDownloadRoster;
   
   /*
	* Value specifying if a TA is allowed to post grades
	*/
   private Boolean canPostGrades;
   
   /*
	* Value specifying if a TA is allowed to create categories for a course
	*/
   private Boolean canCreateCategories;
   
   /*
	* Value specifying if a TA is allowed to create assignments for a course
	*/
   private Boolean canCreateAssignmnets;
   
   /*
	* Value specifying if a TA is allowed to edit student info
	*/
   private Boolean canEditStudents;
   
   /*
	* Value specifying if a TA is allowed to add new students to a course
	*/
   private Boolean canAddStudents;
   
   /*
	* Value specifying if a TA is allowed to adjust the curve for a course
	*/
   private Boolean canAdjustCurve;
   
   /*
	* Value specifying if a TA is allowed to adjust settings for a course
	*/
   private Boolean canAdjustCourseSettings;
   
   
   
   /**
    * Default constructor that sets all permissions to false
    */
   public TaPermissions()
   {
	   canDownloadRoster = false;
	   canPostGrades = false;
	   canCreateCategories = false;
	   canCreateAssignmnets = false;
	   canEditStudents = false;
	   canAddStudents = false;
	   canAdjustCurve = false;
	   canAdjustCourseSettings = false;
   }

   public TaPermissions(Boolean canDownloadRoster, Boolean canPostGrades,
           Boolean canCreateCategories, Boolean canCreateAssignmnets,
           Boolean canEditStudents, Boolean canAddStudents,
           Boolean canAdjustCurve, Boolean canAdjustCourseSettings)
   {
       this.canDownloadRoster = canDownloadRoster;
       this.canPostGrades = canPostGrades;
       this.canCreateCategories = canCreateCategories;
       this.canCreateAssignmnets = canCreateAssignmnets;
       this.canEditStudents = canEditStudents;
       this.canAddStudents = canAddStudents;
       this.canAdjustCurve = canAdjustCurve;
       this.canAdjustCourseSettings = canAdjustCourseSettings;
   }

   public Boolean getCanDownloadRoster()
   {
       return canDownloadRoster;
   }

   public Boolean getCanPostGrades()
   {
       return canPostGrades;
   }

   public Boolean getCanCreateCategories()
   {
       return canCreateCategories;
   }

   public Boolean getCanCreateAssignmnets()
   {
       return canCreateAssignmnets;
   }

   public Boolean getCanEditStudents()
   {
       return canEditStudents;
   }

   public Boolean getCanAddStudents()
   {
       return canAddStudents;
   }

   public Boolean getCanAdjustCurve()
   {
       return canAdjustCurve;
   }

   public Boolean getCanAdjustCourseSettings()
   {
       return canAdjustCourseSettings;
   }

   public void setCanDownloadRoster(Boolean canDownloadRoster)
   {
       this.canDownloadRoster = canDownloadRoster;
   }

   public void setCanPostGrades(Boolean canPostGrades)
   {
       this.canPostGrades = canPostGrades;
   }

   public void setCanCreateCategories(Boolean canCreateCategories)
   {
       this.canCreateCategories = canCreateCategories;
   }

   public void setCanCreateAssignmnets(Boolean canCreateAssignmnets)
   {
       this.canCreateAssignmnets = canCreateAssignmnets;
   }

   public void setCanEditStudents(Boolean canEditStudents)
   {
       this.canEditStudents = canEditStudents;
   }

   public void setCanAddStudents(Boolean canAddStudents)
   {
       this.canAddStudents = canAddStudents;
   }

   public void setCanAdjustCurve(Boolean canAdjustCurve)
   {
       this.canAdjustCurve = canAdjustCurve;
   }

   public void setCanAdjustCourseSettings(Boolean canAdjustCourseSettings)
   {
       this.canAdjustCourseSettings = canAdjustCourseSettings;
   }
}
