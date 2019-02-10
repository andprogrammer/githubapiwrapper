package com.githubapiwrapper.service;

import com.githubapiwrapper.dao.RepositoryDAO;
import com.githubapiwrapper.exception.CustomException;
import com.githubapiwrapper.exception.ResponseError;
import com.githubapiwrapper.utils.JSONUtil;
import com.mashape.unirest.http.exceptions.UnirestException;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static spark.Spark.*;

public class RepositoryService {

    public RepositoryService(final RepositoryDAO repositoryDAO) {

        get("/repositories/:owner/:repository-name", (request, response) -> {
            String owner = request.params(":owner");
            String repositoryName = request.params(":repository-name");
            return JSONUtil.toJson(repositoryDAO.getRepository(owner, repositoryName));
        });

        after((request, response) -> {
            response.type("application/json");
        });

        exception(IllegalArgumentException.class, (exception, request, response) -> {
            response.status(SC_BAD_REQUEST);
            response.body(JSONUtil.toJson(new ResponseError(exception.getMessage())));
        });

        exception(CustomException.class, (exception, request, response) -> {
            response.status(SC_BAD_REQUEST);
            response.body(JSONUtil.toJson(new ResponseError(exception.getMessage())));
        });

        exception(UnirestException.class, (exception, request, response) -> {
            response.status(SC_BAD_REQUEST);
            response.body(JSONUtil.toJson(new ResponseError(exception.getMessage())));
        });
    }
}
