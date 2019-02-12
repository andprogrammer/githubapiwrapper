package com.githubapiwrapper.http;

import com.githubapiwrapper.model.Repository;
import com.mashape.unirest.http.exceptions.UnirestException;

public abstract class RestClient {

    final protected String server;

    public RestClient(String server) {
        this.server = server;
    }

    public abstract Repository request(String owner, String repositoryName) throws UnirestException;
}
