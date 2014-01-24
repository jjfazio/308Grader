package model.users;

import java.io.Serializable;

/**
 *	A Teacher represents the main user of the gradebook. 
 *	They are made up of a name, which is broken down into first name and last name.
 *	Additionally, they have a username. 
 */

public class Teacher implements Serializable {
	String first_name;
	String last_name;
	String username;
}