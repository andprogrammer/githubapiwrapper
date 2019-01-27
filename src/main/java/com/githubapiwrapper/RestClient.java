package com.githubapiwrapper;

import com.githubapiwrapper.model.Repository;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
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
        final HttpResponse<JsonNode> response = Unirest.get("https://api.github.com/repos/" + owner + "/" + repositoryName).queryString("limit", 20).asJson();

        String fullName = response.getBody().getObject().getString("full_name");
        String description = response.getBody().getObject().getString("description");
        String cloneUrl = response.getBody().getObject().getString("clone_url");
        int stars = response.getBody().getObject().getInt("stargazers_count");
        String createdAt = response.getBody().getObject().getString("created_at");

        Repository repository = new Repository(fullName, description, cloneUrl, stars, createdAt);
        return repository;
    }
}
