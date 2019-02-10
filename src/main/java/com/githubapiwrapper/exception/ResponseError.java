package com.githubapiwrapper.exception;

public class ResponseError {

    private String errorCode;

    public ResponseError(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }
}
