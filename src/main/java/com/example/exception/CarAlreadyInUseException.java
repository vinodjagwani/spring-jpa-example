package com.example.exception;

/**
 * Created by vinodjagwani on 7/15/17.
 */
public class CarAlreadyInUseException extends Exception
{

    public CarAlreadyInUseException(final String message)
    {
        super(message);
    }

}
