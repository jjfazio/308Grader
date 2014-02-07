package model.users;

import java.io.Serializable;

import model.file.TaPermissions;

/**
 * Teacher Assistant object. A teacher can have 
 * multiple Teacher's Assistant. Each Teacher Assistant
 * can have different permissions 
 * @author jamesfazio
 */

public class TeacherAssistant implements Serializable {

    private static final long serialVersionUID = 3855894657641156555L;

    String id;
    String firstName;
    String lastName;
    TaPermissions permissions;

    public TeacherAssistant()
    {
        System.out.println("Created a new TA");
    }

    public TeacherAssistant(String id, String first, String last, TaPermissions p)
    {
        this.id = id;
        firstName = first;
        lastName = last;
        permissions = p;
    }

    public String getId()
    {
        return id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public TaPermissions getPermissions()
    {
        return permissions;
    }
}
