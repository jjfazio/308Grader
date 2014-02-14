package model.spreadsheet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import controller.spreadsheet.AddClassController;

/**
 * Represents a grading scheme which is a list of ranges and symbols for each range.
 * @author Kevin Backers
 *
 */
public class GradingScheme extends Observable implements Serializable {
    private static final long serialVersionUID = 5306127170696546118L;
    
    private Boolean plusMinusEnabled;
    
    private List<GradeRange> gradeRanges;
    
    private String schemeName;

    public GradingScheme()
    {
        
        gradeRanges = new ArrayList<GradeRange>();
        
        /** Set to default scheme */
        gradeRanges.add(new GradeRange("A", 90.0, 100.0));
        gradeRanges.add(new GradeRange("B", 80.0, 89.9));
        gradeRanges.add(new GradeRange("C", 70.0, 79.9));
        gradeRanges.add(new GradeRange("D", 60.0, 69.9));
        gradeRanges.add(new GradeRange("F", 0.0, 59.9));
        schemeName = "Default";
        plusMinusEnabled = false;
        
        setChanged();
        notifyObservers();
    }
    
    public GradingScheme(List<GradeRange> ranges, String name)
    {
       
        plusMinusEnabled = true;
        gradeRanges = ranges;
        schemeName = name;
        //System.out.println("Created a new grading scheme with name: " + name);
        
        setChanged();
        notifyObservers();
    }
    
    public GradingScheme(String name)
    {
        plusMinusEnabled = true;
        schemeName = name;
        //System.out.println("Creted a new grading scheme with name: " + name);
        
        setChanged();
        notifyObservers();
    }

    public Boolean getPlusMinusEnabled()
    {
        return plusMinusEnabled;
    }

    public List<GradeRange> getGradeRanges()
    {
        return gradeRanges;
    }

    public String getSchemeName()
    {
        return schemeName;
    }
    
    public String toString()
    {
        return schemeName;
    }
    
    public void addRange(GradeRange range) {
        gradeRanges.add(range);
        
        setChanged();
        notifyObservers();
    }
    
}
