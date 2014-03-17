package model.spreadsheet;

import java.io.Serializable;

import javafx.scene.paint.Color;

/**
 * A course has a List of GradeRanges. Each range corresponds to a letter
 * grade. A typical setup would be A (90, 100), B(80, 89), C(70, 79)
 * and so forth.
 * @author kevinbackers
 */
public class GradeRange implements Serializable {
    private static final long serialVersionUID = -3966549869734563187L;

    /**
     * The letter grade the GradeRange represents, ex A or A-, must not be null.
     */
    private String letterGrade;

    /**
     * The low number of the GradeRange, must not be null.
     */
    private Double low;

    /**
     * The high number of the GradeRange, must not be null.
     */
    private Double high;
    
    /**
     * The color to display in the gradebook.
     */
    private Color color;
    
    public GradeRange(String letter, double low, double high)
    {
        letterGrade = letter;
        this.low = low;
        this.high = high;
    }

    public String getLetterGrade()
    {
        return letterGrade;
    }

    public Double getLow()
    {
        return low;
    }

    public Double getHigh()
    {
        return high;
    }
    
    public Color getColor()
    {
        return color;
    }
    
    public void setColor(Color newColor)
    {
        this.color = newColor;
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
        GradeRange other = (GradeRange) obj;
        if (high == null)
        {
            if (other.high != null)
                return false;
        }
        else if (!high.equals(other.high))
            return false;
        if (letterGrade == null)
        {
            if (other.letterGrade != null)
                return false;
        }
        else if (!letterGrade.equals(other.letterGrade))
            return false;
        if (low == null)
        {
            if (other.low != null)
                return false;
        }
        else if (!low.equals(other.low))
            return false;
        return true;
    }
    
    
}
