package com.githubapiwrapper.http;

import com.githubapiwrapper.model.Repository;
import com.mashape.unirest.http.exceptions.UnirestException;

public abstract class RestClient {

    final String server;
    final int requestPerSecond;

    public RestClient(String server, int requestPerSecond) {
        this.server = server;
        this.requestPerSecond = requestPerSecond;
    }

    public abstract Repository request(String owner, String repositoryName) throws UnirestException;
}
