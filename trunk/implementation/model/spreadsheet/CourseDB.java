package model.spreadsheet;

import java.util.ArrayList;
import java.util.List;

//name quarter section number dept
public class CourseDB
{
    private List<CourseInfo> courses;
    public static CourseDB instance;
    
    private CourseDB()
    {
        courses = new ArrayList<CourseInfo>();
        courses.add(new CourseInfo("Software Engineering I", "spring", "01", "308", "Computer Science"));
        courses.add(new CourseInfo("Software Engineering I", "spring", "02", "308", "Computer Science"));
        courses.add(new CourseInfo("Software Engineering II", "spring", "01", "309", "Computer Science"));
        courses.add(new CourseInfo("Software Engineering I", "spring", "02", "309", "Computer Science"));
        courses.add(new CourseInfo("Intro To Databases", "spring", "02", "365", "Computer Science"));
    }
    
    public static CourseDB getInstance()
    {
        if (instance == null)
            instance = new CourseDB();
        
        return instance;
    }
    
    public List<CourseInfo> getAllCourses()
    {
        return courses;
    }
    
    public List<String> getCourseNumbers()
    {
        List<String> desired = new ArrayList<String>();
        
        for (CourseInfo course : courses)
        {
            if (!desired.contains(course.getNumber()))
            {
                desired.add(course.getNumber());
            }
        }
        return desired;
    }
    
    public List<CourseInfo> getCoursesByNumber(String courseNumber)
    {
        List<CourseInfo> desired = new ArrayList<CourseInfo>();
        
        for (CourseInfo course : courses)
        {
            if (course.getNumber().equals(courseNumber))
            {
                desired.add(course);
            }
        }
        
        return desired;
    }

}
