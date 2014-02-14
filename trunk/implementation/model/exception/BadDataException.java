package model.exception;

class BadDataException extends Exception
{
      //Parameterless Constructor
      public BadDataException() {}

      //Constructor that accepts a message
      public BadDataException(String message)
      {
         super(message);
      }
 }
