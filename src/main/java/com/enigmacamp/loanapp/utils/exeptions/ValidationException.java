package com.enigmacamp.loanapp.utils.exeptions;

public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}
