package external;
import java.util.Collection;

/**
 * This class provides the functionality for users to log in to the Grader. The idea
 * here is that the precondition checks for the existing pair of username and password.
 * If it exists then the login returns true for a succesful login. Otherwise, the login 
 * fails by returning false.
 *
 */

public abstract class LoginService {
	Collection<UserRecord> users;
   /**
    * A successful login means the username and password exist which means
    * the method will return true. If the pair of username and password
    * does not exist, the login fails and returns false.
    */
	/*@
	  requires
	  // Username exists
	  // password matches password for username
	   
	    \exists UserRecord user ; users.contains(user) ;
	    		user.username.equals(username) && user.password.equals(password);
	  ensures
	  // users dont change
	   
	    users = \old(users);
	    
	 @*/
   abstract Boolean login(String username, String password);
   
   /**
    * A succesful logout means a user was logged in and that the users data didnt change
    */
   /*@
	  requires
	  // A user is logged in
	   
	    \exists UserRecord user ; users.contains(user) ;
	    		user.username.equals(username) ;
	  ensures
	  // users dont change
	   
	    users = \old(users);
	    
	 @*/
   
   abstract Boolean logout(String userId);
}

abstract class UserRecord {
    String username;
    String password;
}