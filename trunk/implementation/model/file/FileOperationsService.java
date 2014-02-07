package model.file;

/*
 *
 * This class defines major operations for a teacher.
 * It includes operations such as posting grades, downloading a roster, and syncing a roster.
 *
 */


import model.spreadsheet.SpreadsheetCourse;
import model.users.Teacher;

//currently unimplemented
abstract class FileOperationsService {
	/**
	 * Uploads the given grades for a course in order to make them 
	 * available for the students. Nothing should change in the teacher's
	 * version of the gradebook. The student's viewable version, though, 
	 * should see the changes in grades.
	 */
	/*@
	  requires
	    //
	    // The SpreadSheet course must exist
	    //
	    (course != null);
	     
	     
	  ensures
	    //
	    // The submitted grades are now submitted to the registrar.
	    // That data is not available to us?
	    //
	    (* not sure how to check this right now*);
	@*/ 
	 
	abstract void postChanges(SpreadsheetCourse course);

	/**
	 * Downloads the roster of the class from the administration of the department (SIS).
	 */
	/*@
	  requires
	    //
	    //  The only requirement is that a class must exist that a roster is being downloaded for.
	    //
	    (course != null)
	    
	    &&
	    
	    (teacher != null);
	     
	     
	  ensures
	    //
	    // Whichever students were selected from SIS are now part of the 
	    // given spreadsheet course
	    //
	    (* not sure how to check this right now because how can we check the SIS roster 
	       in order to compare it to the version downloaded into the gradebook *);
	@*/ 
	abstract void downloadRoster(Teacher teacher, SpreadsheetCourse course);
	
	/*@
	  requires
	    //
	    //  The SpreadsheetCourse must have a roster to be synced with the SIS.
	    //
	    (course != null);
	     
	     
	    // ensures
	    // To be defined. 
	    // Different teachers may have different definitions of what a "correct" result is of a sync.
	    // Possible "correct" results include
	    //    - User's roster is correct
	    // 	  - SIS roster is correct
	    //    - Add any students on user's roster to the SIS roster
	    //    - Remove any students from user's roster that aren't on SIS roster
	    //    - and any other combinations of the above are reasonable possibilities
	    //
	@*/ 
	
	abstract void sync(SpreadsheetCourse course);
}