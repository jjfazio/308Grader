package model.users;

import java.io.Serializable;

import model.file.TaPermissions;

/**
 * Teacher Assistant object. A teacher can have 
 * multiple Teacher's Assistant. Each Teacher Assistant
 * can have different permissions 
 * @author jamesfazio
 *
 */
public class TeacherAssistant implements Serializable {
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
}
