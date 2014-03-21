package model.spreadsheet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javafx.scene.paint.Color;
import model.exception.BadDataException;
import model.exception.GradingSchemeDataException;

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
        GradeRange tempA = new GradeRange("A", 90.0, 100.0);
        tempA.setColor(getColorString(Color.GREEN));
        gradeRanges.add(tempA);
        
        GradeRange tempB = new GradeRange("B", 80.0, 89.9);
        tempB.setColor(getColorString(Color.GREENYELLOW));
        gradeRanges.add(tempB);
        
        GradeRange tempC = new GradeRange("C", 70.0, 79.9);
        tempC.setColor(getColorString(Color.YELLOW));
        gradeRanges.add(tempC);
        
        GradeRange tempD = new GradeRange("D", 60.0, 69.9);
        tempD.setColor(getColorString(Color.ORANGE));
        gradeRanges.add(tempD);
        
        GradeRange tempF = new GradeRange("F", 0.0, 59.9);
        tempF.setColor(getColorString(Color.RED));
        gradeRanges.add(tempF);
        
        schemeName = "Default";
        plusMinusEnabled = false;
        
        setChanged();
        notifyObservers();
    }
    
    /**
     * Non default constructor.
     * @param ranges the list of ranges
     * @param name the name 
     * @throws GradingSchemeDataException
     */
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

    /**
     * Get the list of ranges
     * @return
     */
    public List<GradeRange> getGradeRanges()
    {
        return gradeRanges;
    }

    /**
     * Get the name of this scheme.
     * @return
     */
    public String getSchemeName()
    {
        return schemeName;
    }
    
    /**
     * Set the name of this scheme
     * @param name
     */
    public void setSchemeName(String name)
    {
        this.schemeName = name;
    }
    
    /**
     * Get the name of this string
     */
    public String toString()
    {
        return schemeName;
    }
    
    /**
     * Add a grade range to this scheme
     * @param range
     * @throws GradingSchemeDataException
     */
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
    
    /**
     * Use a percent to determing the symbol
     * @param score
     * @return "" if it doesnt fit in any of the ranges
     */
    public String getSymbolFromPercent(Double score) {
        
        boolean higher = true;
        boolean lower = true;
        for (GradeRange r : gradeRanges)
        {
            if (score <= r.getHigh() && score >= r.getLow())
            {
                return r.getLetterGrade();
            }
        }
        
        return "";
    }
    
    /**
     * Get the color
     * @param symbol to get the color for
     * @return the color as a string or null if it doesnt fit in any of the ranges
     */
    public String getColorFromSymbol(String symbol) {
        for (GradeRange r : gradeRanges)
        {
            if (symbol.equals(r.getLetterGrade()))
            {
                return r.getColor();
            }
        }
        return null;
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
    
    /**
     * Helper method to get the correct format of a color string
     * @param c the Color object
     * @return String representatin for css to use
     */
    private String getColorString(Color c)
    {
        int r = (int) (c.getRed() * 256) ;
        int g = (int) (c.getGreen() * 256) ;
        int b = (int) (c.getBlue() * 256) ;
        return String.format("rgb(%d, %d, %d)", r, g, b);
    }
    
    
}
