package model.spreadsheet;

import java.util.ArrayList;
import java.util.List;

import model.assignments_categories.Assignment;
import model.assignments_categories.Category;
import model.assignments_categories.Grade;
import model.users.Student;

// not yet implemented
public class Statistics {
   
   public static Double averageAssignments(List<Student> students,
    List<Assignment> assignments) {
      List<Grade> grades = new ArrayList<Grade>();
      
      for (Assignment assign : assignments) {
         for (Student student : students) {
            grades.add(student.getAssignmentGrade(assign));
         }
      }
      
      //do math
      return null;
   }
   
   public static Double averageCategories(List<Student> students, 
    List<Category> category) {
      
      //do math
      
      return null;
   }

}
