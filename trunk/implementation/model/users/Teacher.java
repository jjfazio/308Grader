package model.users;

import java.io.Serializable;

/**
 *	A Teacher represents the main user of the gradebook. 
 *	They are made up of a name, which is broken down into first name and last name.
 *	Additionally, they have a username. 
 *  @author erikowen
 */
public class Teacher implements Serializable {
    private static final long serialVersionUID = 8078832822412193181L;
    
    private String firstName;
	private String lastName;
	private String userName;
	
    public Teacher(String firstName, String lastName, String userName)
    {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getUserName()
    {
        return userName;
    }
}