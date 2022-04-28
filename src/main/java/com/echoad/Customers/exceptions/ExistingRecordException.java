package com.echoad.Customers.exceptions;

public class ExistingRecordException extends ServiceException {

    public ExistingRecordException(String message) {
        super(message);
    }

    public ExistingRecordException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistingRecordException(Throwable cause) {
        super(cause);
    }
}
