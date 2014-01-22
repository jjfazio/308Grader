package external;

import spreadsheet.Gradebook;

/**
 * Class for contacting the Local Database. Contains two methods
 * for updating the Database and retrieving from the DataBase.
 * @author Jirbert
 *
 */

public abstract class LocalDBService {
   /**
    * Updates the Local Database with the provided gradebook. All data
    * in the the local Database will be overwritten by the new gradebook
    * if successful. If the overwrite is unsuccessful the method returns
    * false.
    * @param gradebook - The gradebook with recent changes that the teacher
    * wants to make visible to students.
    * @return - True on success, False on failure.
    */
   /*@
    
     requires
        //The gradebook is not null
        //The course must not be null and
        //there must be at least one course within the Gradebook
    
        (gradebook != null)
    
        &&
    
        (gradebook.courses != null) && (gradebook.courses.size() > 0);
    
        //If successful the local Database actually got updated
        //If failure the local Database did not get updated.
    
        //Not sure how to ensure these ?
    
    @*/

    abstract Boolean update(Gradebook gradebook);
   /**
    * Retrieve the Gradebook data from the local Database.
    * @param userId - the id of the instructor making the call
    * @return
    */
   /*@
    
     requires
        //The userId is non null and not empty
        //The userId is valid
    
        (userId != null) && (userId.length > 0);
    
     ensures
        //The gradebook returned is non null
        //The Gradebook Teacher userId matches the
        //provided userId
    
        (\result != null)
    
        &&
    
        (\result.teacher.username == userId);
    @*/
    
   abstract Gradebook retrieve(String userId);

}
