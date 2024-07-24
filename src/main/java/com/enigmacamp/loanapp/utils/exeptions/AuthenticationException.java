package com.enigmacamp.loanapp.utils.exeptions;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }

}
