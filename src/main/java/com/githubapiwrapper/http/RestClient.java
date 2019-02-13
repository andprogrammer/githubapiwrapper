package com.githubapiwrapper.http;

import com.githubapiwrapper.exception.CustomException;
import com.githubapiwrapper.model.Repository;
import com.githubapiwrapper.utils.JacksonObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Objects;

import static com.githubapiwrapper.utils.Utils.checkIfNotNull;

public class RestClient {

    final protected String server;

    public RestClient(String server) {
        this.server = server;
    }

    public Repository request(String owner, String repositoryName) throws UnirestException {
        checkIfNotNull(owner);
        checkIfNotNull(repositoryName);
        Unirest.setObjectMapper(new JacksonObjectMapper());
        Unirest.setTimeouts(60000, 30000);
        String url = server + "repos/" + owner + "/" + repositoryName;
        HttpResponse<Repository> response = Unirest.get(url).asObject(Repository.class);
        if (200 != response.getStatus())
            throw new CustomException(response.getStatus());
        return response.getBody();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RestClient)) return false;
        RestClient client = (RestClient) o;
        return Objects.equals(server, client.server);
    }

    @Override
    public int hashCode() {
        return server.hashCode();
    }
}
