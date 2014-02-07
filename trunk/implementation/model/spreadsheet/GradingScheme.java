package model.spreadsheet;

import java.io.Serializable;
import java.util.List;
import java.util.Observable;

public class GradingScheme extends Observable implements Serializable {
    private static final long serialVersionUID = 5306127170696546118L;
    
    Boolean plusMinusEnabled;
    
    List<GradeRange> gradeRanges;
    
    String schemeName;

    public GradingScheme()
    {
        System.out.println("Creted a new grading scheme");
    }
    
    public GradingScheme(List<GradeRange> ranges, String name)
    {
        plusMinusEnabled = true;
        gradeRanges = ranges;
        schemeName = name;
        System.out.println("Creted a new grading scheme with name: " + name);
    }
    
    public GradingScheme(String name)
    {
        plusMinusEnabled = true;
        schemeName = name;
        System.out.println("Creted a new grading scheme with name: " + name);
    }
    
    public void addGradeRange(GradeRange g)
    {
        gradeRanges.add(g);
        setChanged();
        notifyObservers();
    }
}
