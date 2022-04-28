package com.echoad.Customers.exceptions;

// This is a custom exception class for invalid arguments passed to the methods
public class InvalidArgumentException extends ServiceException {
    public InvalidArgumentException(String message) {
        super(message);
    }

    public InvalidArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidArgumentException(Throwable cause) {
        super(cause);
    }
}
