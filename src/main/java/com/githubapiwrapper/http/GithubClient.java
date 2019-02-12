package com.githubapiwrapper.http;

import com.githubapiwrapper.exception.CustomException;
import com.githubapiwrapper.model.Repository;
import com.githubapiwrapper.utils.JacksonObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Objects;

import static com.githubapiwrapper.utils.Utils.checkIfNotNull;

public class GithubClient extends RestClient {

    public GithubClient() {
        super("https://api.github.com/");
    }

    @Override
    public Repository request(String owner, String repositoryName) throws UnirestException {
        checkIfNotNull(owner);
        checkIfNotNull(repositoryName);
        Unirest.setObjectMapper(new JacksonObjectMapper());
        String url = server + "repos/" + owner + "/" + repositoryName;
        HttpResponse<Repository> response = Unirest.get(url).asObject(Repository.class);
        if (200 != response.getStatus())
            throw new CustomException(response.getStatus());
        return response.getBody();
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GithubClient that = (GithubClient) o;
        return Objects.equals(server, that.server);
    }
}
