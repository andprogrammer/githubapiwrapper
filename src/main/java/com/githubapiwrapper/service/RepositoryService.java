package com.githubapiwrapper.service;

import com.githubapiwrapper.dao.RepositoryDAO;
import static spark.Spark.*;

import com.githubapiwrapper.exception.CustomException;
import com.githubapiwrapper.exception.ResponseError;
import com.githubapiwrapper.utils.JSONUtil;

import static com.githubapiwrapper.utils.JSONUtil.FAILED_RESPONSE;

public class RepositoryService {

    public RepositoryService(final RepositoryDAO repositoryDAO) {

        get("/repositories/:owner/:repository-name", (request, response) -> {
            String owner = request.params(":owner");
            String repositoryName = request.params(":repository-name");
            return repositoryDAO.getRepository(owner, repositoryName);
        }, JSONUtil.json());

        after((request, response) -> {
            response.type("application/json");
        });

        exception(IllegalArgumentException.class, (exception, request, response) -> {
            response.status(FAILED_RESPONSE);
            response.body(JSONUtil.toJson(new ResponseError(exception)));
        });

        exception(CustomException.class, (exception, request, response) -> {
            response.status(FAILED_RESPONSE);
            response.body(JSONUtil.toJson(new ResponseError(exception)));
        });
    }
}
