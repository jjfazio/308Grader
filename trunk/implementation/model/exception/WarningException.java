package model.exception;

public class WarningException extends Exception
{
    //Parameterless Constructor
    public WarningException() {}

    //Constructor that accepts a message
    public WarningException(String message)
    {
       super(message);
    }
}
