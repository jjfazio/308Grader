package model.spreadsheet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import model.exception.BadDataException;
import model.exception.GradingSchemeDataException;
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
    
    public GradingScheme(List<GradeRange> ranges, String name) throws GradingSchemeDataException
    {
        String errorMessage = "";
        boolean isBadRanges = false;
        boolean isBadName = false;
        
        if (!areValidRanges(ranges))
        {
            errorMessage += "Error with grade ranges\n";
            isBadRanges = true;
        }
        if (name == null || name.equals(""))
        {
            errorMessage += "Grading Scheme must have a name\n";
            isBadName = true;
        }
        if (errorMessage.length() > 0) {
            GradingSchemeDataException e = new GradingSchemeDataException(errorMessage);
            e.setBadRanges(isBadRanges);
            e.setBadName(isBadName);
            throw e;
        } else {
        
            plusMinusEnabled = true;
            gradeRanges = ranges;
            schemeName = name;
            
            setChanged();
            notifyObservers();
            
        }
    }
    
    
    public GradingScheme(String name)
    {
        plusMinusEnabled = true;
        schemeName = name;
        
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
    
    public void setSchemeName(String name)
    {
        this.schemeName = name;
    }
    
    public String toString()
    {
        return schemeName;
    }
    
    public void addRange(GradeRange range) throws GradingSchemeDataException {
        
        if (isValidRangeToAdd(range)) {
            gradeRanges.add(range);
            setChanged();
            notifyObservers();
        } else {
            GradingSchemeDataException e = new GradingSchemeDataException("Invalid Range in grading scheme");
            e.setBadRanges(true);
            throw e;
        }
        
    }
    
    private boolean areValidRanges(List<GradeRange> ranges) {
        return ranges.size() != 0;
    }
    
    private boolean isValidRangeToAdd(GradeRange newRange) {
        return true;
    }
    
    
    public String getSymbolFromPercent(Double score) {
        for (GradeRange r : gradeRanges)
        {
            if (score <= r.getHigh() && score >= r.getLow())
            {
                //System.out.println(r.getLow() + " < " + score + " < " + r.getHigh());
                return r.getLetterGrade();
            }
        }
        return "";
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
        GradingScheme other = (GradingScheme) obj;
        if (gradeRanges == null)
        {
            if (other.gradeRanges != null)
                return false;
        }
        else if (!gradeRanges.equals(other.gradeRanges))
            return false;
        if (plusMinusEnabled == null)
        {
            if (other.plusMinusEnabled != null)
                return false;
        }
        else if (!plusMinusEnabled.equals(other.plusMinusEnabled))
            return false;
        if (schemeName == null)
        {
            if (other.schemeName != null)
                return false;
        }
        else if (!schemeName.equals(other.schemeName))
            return false;
        return true;
    }
    
    
}
