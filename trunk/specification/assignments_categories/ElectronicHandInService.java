package assignments_categories;

import java.io.File;
import java.util.Collection;

import users.Student;


/**
 * A collection of file names and answers collected
 * from assignments that have Electronic HandIn activated. 
*/

abstract public class ElectronicHandInService {

   abstract void turnInAssignment(Student student, Collection<File> fileNames);
   
   //might be more stuff here
}