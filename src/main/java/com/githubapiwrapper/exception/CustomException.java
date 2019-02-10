package com.githubapiwrapper.exception;

public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }

    public CustomException(int message) {
        super(String.valueOf(message));
    }

    public CustomException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
