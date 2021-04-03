package com.mooc.moocServer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class SignInFailureException extends RuntimeException {
    public SignInFailureException(String message) {
        super(message);
    }

    public SignInFailureException(String message, Throwable cause){
        super(message, cause);
    }
}
