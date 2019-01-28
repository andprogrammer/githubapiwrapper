package com.githubapiwrapper.service;

import com.githubapiwrapper.exception.CustomException;
import com.githubapiwrapper.model.Repository;
import com.githubapiwrapper.utils.JacksonObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RestClient {

    private final String server;
    private final int requestPerSecond;

    public RestClient(String server, int requestPerSecond) {
        this.server = server;
        this.requestPerSecond = requestPerSecond;
    }

    public Repository request(String owner, String repositoryName) throws UnirestException {
        Unirest.setObjectMapper(new JacksonObjectMapper());
        String url = server + "repos/" + owner + "/" + repositoryName;
        HttpResponse<Repository> response = Unirest.get(url).queryString("limit", requestPerSecond).asObject(Repository.class);
        if (200 != response.getStatus())
            throw new CustomException("Incorrect request : " + response.getStatusText());
        return response.getBody();
    }
}
