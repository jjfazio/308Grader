package model.users;

import model.file.TaPermissions;

/**
 * Teacher Assistant object. A teacher can have 
 * multiple Teacher's Assistant. Each Teacher Assistant
 * can have different permissions 
 * @author jamesfazio
 *
 */
public class TeacherAssistant {
   String id;
   String firstName;
   String lastName;
   TaPermissions permissions;
}
