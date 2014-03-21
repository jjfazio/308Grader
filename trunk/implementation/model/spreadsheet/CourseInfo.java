package model.spreadsheet;

import java.io.Serializable;
import java.util.Collection;

import model.exception.CourseDataException;

/**
 * Course contains a courseName, a quarter, a number, and a dept along with 
 * methods to manipulate these fields
 * @author kevinbackers
 */
public class CourseInfo implements Serializable{
    private static final long serialVersionUID = 8576375778985622591L;

    /**
     * The name of the course, for example 'Intro to Databases', must not be null
     */
    private String courseName;

    /**
     * The quarter this class is being taught, must not be null
     */
    private String quarter;
    
    /**
     * Section of the course
     */
    private String section;

    /**
     * The course number, for example '365', must not be null
     */
    private String number;
    
    private int year;

    /**
     * The dept the course belongs to, for example CPE, must not be null
     */
    private String dept;
    
    public CourseInfo(String name, String quarter, String section, String number,
            String dept, int year) throws CourseDataException {
        String errorMessage = "";
        boolean isBadName = false;
        boolean isBadSection = false;
        boolean isBadGradingScheme = false;
        boolean isBadNumber = false;
        
        if (name == null || name.equals(""))
        {
            errorMessage += "Course Name can not be null\n";
            isBadName = true;
        }
        if (section == null || section.equals("")){
            errorMessage += "Course Section can not be null\n";
            isBadSection = true;
        } 
        if (number == null || number.equals("")) {
            errorMessage += "Course Number can not be null\n";
            isBadNumber = true;
        }
        if (errorMessage.length() > 0) {
            CourseDataException e = new CourseDataException(errorMessage);
            e.setBadName(isBadName);
            e.setBadSection(isBadSection);
            e.setBadGradingScheme(isBadGradingScheme);
            e.setBadNumber(isBadNumber);
            throw e;
        } else {
            this.courseName = name;
            this.quarter = quarter;
            this.number = number;
            this.section = section;
            this.dept = dept;
            this.year = year;
        }
    }

    public String getCourseName()
    {
        return courseName;
    }

    public String getQuarter()
    {
        return quarter;
    }

    public String getNumber()
    {
        return number;
    }

    public String getDept()
    {
        return dept;
    }
    
    public String getSection()
    {
        return section;
    }
    
    public String getYear()
    {
        return "" + year;
    }
    
    
    public void setCourseName(String n)
    {
        this.courseName = n;
    }

    public void setQuarter(String q)
    {
        this.quarter = q;
    }

    public void setNumber(String n)
    {
        this.number = n;
    }

    public void setDept(String d)
    {
        this.dept = d;
    }
    
    public void setSection(String s)
    {
        this.section = s;
    }
    
    public void setYear(String y)
    {
        if (y == "")
        {
            this.year = 0;
        }
        else
        {
            this.year = Integer.parseInt(y);
        }
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((courseName == null) ? 0 : courseName.hashCode());
        result = prime * result + ((dept == null) ? 0 : dept.hashCode());
        result = prime * result + ((number == null) ? 0 : number.hashCode());
        result = prime * result + ((quarter == null) ? 0 : quarter.hashCode());
        result = prime * result + ((section == null) ? 0 : section.hashCode());
        result = prime * result + year;
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CourseInfo other = (CourseInfo) obj;
        if (courseName == null)
        {
            if (other.courseName != null)
                return false;
        }
        else if (!courseName.equals(other.courseName))
            return false;
        if (dept == null)
        {
            if (other.dept != null)
                return false;
        }
        else if (!dept.equals(other.dept))
            return false;
        if (number == null)
        {
            if (other.number != null)
                return false;
        }
        else if (!number.equals(other.number))
            return false;
        if (quarter == null)
        {
            if (other.quarter != null)
                return false;
        }
        else if (!quarter.equals(other.quarter))
            return false;
        if (section == null)
        {
            if (other.section != null)
                return false;
        }
        else if (!section.equals(other.section))
            return false;
        if (year != other.year)
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "CourseInfo [courseName=" + courseName + ", quarter=" + quarter
                + ", section=" + section + ", number=" + number + ", year="
                + year + ", dept=" + dept + "]";
    }
    
    
}
