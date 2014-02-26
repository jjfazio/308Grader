package model.exception;

/**
 * This class is an exception that is thrown when
 * the user enters invalid data for a grading scheme
 *
 * @author Kevin Backers
 */
public class GradingSchemeDataException extends Exception {
    boolean badRanges;
    boolean badName;

    //Parameterless Constructor
    public GradingSchemeDataException() {}

    //Constructor that accepts a message
    public GradingSchemeDataException(String message)
    {
        super(message);
    }

    public void setBadName(boolean isBadName) {
        this.badName = isBadName;
    }

    public void setBadRanges(boolean isBadRanges) {
        this.badRanges = isBadRanges;
    }

    public boolean isBadName() {
        return this.badName;
    }

    public boolean isBadRanges() {
        return this.badRanges;
    }
}
