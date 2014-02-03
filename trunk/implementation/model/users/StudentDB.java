package model.users;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.spreadsheet.CourseInfo;
import model.spreadsheet.SpreadsheetCourse;

public class StudentDB
{
    private Map<CourseInfo, List<Student>> courseStudentMap;
    private static StudentDB instance;
    private static final String FILE_NAME = "studentDB.txt";
    
    private StudentDB()
    {
        courseStudentMap = new HashMap<CourseInfo, List<Student>>();
        generateStudents();
    }
    
    public static StudentDB getInstance() {
        if (instance == null)
           new StudentDB();
        
        return instance;
     }
    
    public List<Student> getStudentsForClass(SpreadsheetCourse course)
    {
        return courseStudentMap.get(course);
    }
    
    private void generateStudents()
    {
        Scanner scanner = null;
        try
        {
            scanner = new Scanner(new File(FILE_NAME));
            List<Student> studentList;
            String [] parts;
            Student student;
            CourseInfo course;
            
            while (scanner.hasNextLine())
            {
                parts = scanner.nextLine().split("\\|");
                student = new Student(parts[0], parts[1], parts[2],
                        parts[3], parts[4], parts[5]);
                course = new CourseInfo(parts[6], parts[7], parts[8],
                        parts[9], parts[10]);
                
                if (!courseStudentMap.containsKey(course))
                {
                    studentList = new ArrayList<Student>();
                }
                else 
                {
                    studentList = courseStudentMap.get(course);
                }
                
                studentList.add(student);
                courseStudentMap.put(course, studentList);
                
            }
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            if (scanner != null)
                scanner.close();
        }
    }
    
}