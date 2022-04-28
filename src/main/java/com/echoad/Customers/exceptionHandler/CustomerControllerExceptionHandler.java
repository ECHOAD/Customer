package com.echoad.Customers.exceptionHandler;

import com.echoad.Customers.exceptions.InvalidArgumentException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerControllerExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidArgumentException.class)
    public String handleCustomerNotFoundException(InvalidArgumentException ex) {
        return ex.getMessage();
    }
}
