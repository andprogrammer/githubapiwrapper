package com.githubapiwrapper.exception;

public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
