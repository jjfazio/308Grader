package model.exception;

/**
 * This class is an exception that is thrown when
 * the user enters invalid data for a course
 *
 * @author Kevin Backers
 */
public class CourseDataException extends Exception {
    boolean badName;
    boolean badSection;
    boolean badGradingScheme;
    boolean badNumber;

    //Parameterless Constructor
    public CourseDataException() {}

    //Constructor that accepts a message
    public CourseDataException(String message)
    {
        super(message);
    }

    public void setBadName(boolean isBadName) {
        this.badName = isBadName;
    }

    public void setBadSection(boolean isBadSection) {
        this.badSection = isBadSection;
    }

    public void setBadGradingScheme(boolean isBadGradingScheme) {
        this.badGradingScheme = isBadGradingScheme;
    }
    
    public void setBadNumber(boolean isBadNumber)
    {
        this.badNumber = isBadNumber;
    }

    public boolean isBadName() {
        return this.badName;
    }

    public boolean isBadSection() {
        return this.badSection;
    }

    public boolean isBadGradingScheme() {
        return this.badGradingScheme;
    }
    
    public boolean isBadNumber() {
        return this.badNumber;
    }
}
