package model.file;

import java.io.Serializable;

/**
 * Permissions object for a Teacher's Assistant.
 * Contains pertinent information about what operations
 * a Teacher's assistant can perform.
 *
 */
public class TaPermissions implements Serializable{
	/*
	 * Value specifying if a TA is allowed to download roster from SIS
	 */
   Boolean canDownloadRoster;
   
   /*
	* Value specifying if a TA is allowed to post grades
	*/
   Boolean canPostGrades;
   
   /*
	* Value specifying if a TA is allowed to create categories for a course
	*/
   Boolean canCreateCategories;
   
   /*
	* Value specifying if a TA is allowed to create assignments for a course
	*/
   Boolean canCreateAssignmnets;
   
   /*
	* Value specifying if a TA is allowed to edit student info
	*/
   Boolean canEditStudents;
   
   /*
	* Value specifying if a TA is allowed to add new students to a course
	*/
   Boolean canAddStudents;
   
   /*
	* Value specifying if a TA is allowed to adjust the curve for a course
	*/
   Boolean canAdjustCurve;
   
   /*
	* Value specifying if a TA is allowed to adjust settings for a course
	*/
   Boolean canAdjustCourseSettings;
}
