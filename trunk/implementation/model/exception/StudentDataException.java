package model.exception;

/**
 * This class is an exception that is thrown when
 * the user enters invalid data for a student
 *
 * @author Kevin Feutz
 */
public class StudentDataException extends Exception {
    boolean badFirstName;
    boolean badLastName;
    boolean badId;

    //Parameterless Constructor
    public StudentDataException() {}

    //Constructor that accepts a message
    public StudentDataException(String message)
    {
        super(message);
    }

    public void setBadFirstName(boolean isBadFirst) {
        this.badFirstName = isBadFirst;
    }

    public void setBadLastName(boolean isBadLast) {
        this.badLastName = isBadLast;
    }

    public void setBadId(boolean isBadId) {
        this.badId = isBadId;
    }

    public boolean isBadFirstName() {
        return this.badFirstName;
    }

    public boolean isBadLastName() {
        return this.badLastName;
    }

    public boolean isBadId() {
        return this.badId;
    }
}
