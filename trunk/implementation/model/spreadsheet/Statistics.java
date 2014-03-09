package model.spreadsheet;

import model.assignments_categories.Assignment;
import model.assignments_categories.Category;
import model.assignments_categories.Grade;
import model.users.Student;

import java.util.ArrayList;
import java.util.List;

// not yet implemented
public class Statistics
{
    String statName;

    double value;

    Assignment assignment;

    ArrayList<Student> students;

    public Statistics(String name, ArrayList<Student> studentList)
    {
        this.statName = name;
        this.students = studentList;
    }

    public double calcMean(Assignment assignment)
    {
        return 0.0;
    }

   
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
   
   public static Double medianAssignments(List<Student> students,
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
          
          public static Double MedianCategories(List<Student> students, 
           List<Category> category) {
             
             //do math
             
             return null;
          }

}
