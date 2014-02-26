package model.users;

import model.exception.CourseDataException;
import model.exception.StudentDataException;
import model.spreadsheet.CourseInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Class that maps courses to a list of students.
 * Used for the download roster functionality.
 * @author jamesfazio
 *
 */
public class StudentDB
{
    private Map<CourseInfo, List<Student>> courseStudentMap;
    private static StudentDB instance;
    private static final String FILE_NAME = "studentDB.txt";

    /**
     * Private constructor for this singleton class
     */
    private StudentDB()
    {
        courseStudentMap = new HashMap<CourseInfo, List<Student>>();
        generateStudents();
    }

    /**
     * Static method that returns the instance of this
     * singleton class
     *
     * @return  StudentDB   The instance of this singleton
     *                      class.
     */
    public static StudentDB getInstance() {
        if (instance == null)
           instance = new StudentDB();
        
        return instance;
    }
    
    /**
     * Gets the list of students for a Course
     *
     * @param course The course you want students for
     * @return List of students for a Course
     */
    public List<Student> getStudentsForClass(CourseInfo course)
    {
        return courseStudentMap.get(course);
    }
    
    /**
     * Reads the students from the Script created file.
     */
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
                        parts[9], parts[10], 2014);
                
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
        catch (FileNotFoundException | CourseDataException | StudentDataException e)
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