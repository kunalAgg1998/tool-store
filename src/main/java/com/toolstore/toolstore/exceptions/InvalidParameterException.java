package com.toolstore.toolstore.exceptions;
/**
 * Custom exception class for representing invalid parameters in tool rental operations.
 */
public class InvalidParameterException extends RuntimeException{

    /**
     * Constructs a new InvalidParameterException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
    public InvalidParameterException(String message) {
        super(message);
    }
}
