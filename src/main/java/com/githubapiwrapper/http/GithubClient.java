package com.githubapiwrapper.http;

import com.githubapiwrapper.exception.CustomException;
import com.githubapiwrapper.model.Repository;
import com.githubapiwrapper.utils.JacksonObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Objects;

public class GithubClient extends RestClient {

    public GithubClient(int requestPerSecond) {
        super("https://api.github.com/", requestPerSecond);
    }

    @Override
    public Repository request(String owner, String repositoryName) throws UnirestException {
        Unirest.setObjectMapper(new JacksonObjectMapper());
        String url = server + "repos/" + owner + "/" + repositoryName;
        HttpResponse<Repository> response = Unirest.get(url).queryString("limit", requestPerSecond).asObject(Repository.class);
        if (200 != response.getStatus())
            throw new CustomException("Incorrect request : " + response.getStatusText());
        return response.getBody();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GithubClient that = (GithubClient) o;
        return Objects.equals(requestPerSecond, that.requestPerSecond) &&
                Objects.equals(server, that.server);
    }
}
