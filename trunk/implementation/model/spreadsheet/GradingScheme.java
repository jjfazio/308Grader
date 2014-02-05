package model.spreadsheet;

import java.io.Serializable;
import java.util.List;

public class GradingScheme implements Serializable {
    private static final long serialVersionUID = 5306127170696546118L;
    
    Boolean plusMinusEnabled;
    
    List<GradeRange> gradeRanges;
    
    String schemeName;

    public GradingScheme()
    {
        System.out.println("Created a new grading scheme");
    }
    
    public GradingScheme(List<GradeRange> ranges, String name)
    {
        plusMinusEnabled = true;
        gradeRanges = ranges;
        schemeName = name;
        System.out.println("Created a new grading scheme with name: " + name);
    }
    
    public GradingScheme(String name)
    {
        plusMinusEnabled = true;
        schemeName = name;
        System.out.println("Creted a new grading scheme with name: " + name);
    }
    
}
