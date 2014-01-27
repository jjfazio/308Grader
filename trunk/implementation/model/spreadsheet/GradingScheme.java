package model.spreadsheet;

import java.io.Serializable;
import java.util.List;

public class GradingScheme implements Serializable {
    private static final long serialVersionUID = 5306127170696546118L;
    
    Boolean plusMinusEnabled;
    
    List<GradeRange> gradeRanges;

    public GradingScheme()
    {
        System.out.println("Creted a new grading scheme");
    }
}
