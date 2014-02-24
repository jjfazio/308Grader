package model.spreadsheet;

import java.util.ArrayList;
import java.util.List;

import model.exception.CourseDataException;

/**
 * This acts as the SIS server and has courses the user can
 * download rosters from.
 * @author jamesfazio
 *
 */
public class CourseDB
{
    private List<CourseInfo> courses;
    public static CourseDB instance;
    
    private CourseDB()
    {
        courses = new ArrayList<CourseInfo>();
        try {
            courses.add(new CourseInfo("Software Engineering I", "spring", "01", "308", "Computer Science", 2014));
            courses.add(new CourseInfo("Software Engineering I", "spring", "02", "308", "Computer Science", 2014));
            courses.add(new CourseInfo("Software Engineering II", "spring", "01", "309", "Computer Science", 2014));
            courses.add(new CourseInfo("Software Engineering I", "spring", "02", "309", "Computer Science", 2014));
            courses.add(new CourseInfo("Intro To Databases", "spring", "02", "365", "Computer Science", 2014));
        }catch (CourseDataException e){
            e.printStackTrace();
        }
    }
    
    public static CourseDB getInstance()
    {
        if (instance == null)
            instance = new CourseDB();
        
        return instance;
    }
    
    /**
     * Gets all of the courses in SIS
     * @return List of CourseInfo objects
     */
    public List<CourseInfo> getAllCourses()
    {
        return courses;
    }
    
    /**
     * Gets all of the course numbers in SIS. For example
     * '308', '309', '365'.
     * @return List of courseNumbers
     */
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
    
    /**
     * Returns all of the courses for a courseNumber.
     * For example '308' might have multiple sections offered
     * in one quarter.
     * @param courseNumber The courseNumber of the sections
     * @return List of CourseInfo objects
     */
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
