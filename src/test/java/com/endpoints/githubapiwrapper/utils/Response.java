package com.endpoints.githubapiwrapper.utils;

public class Response {

    public final String body;
    public final int status;

    public Response(int status, String body) {
        this.status = status;
        this.body = body;
    }
}
