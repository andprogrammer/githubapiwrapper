package com.githubapiwrapper.service;

import com.githubapiwrapper.dao.RepositoryDAO;
import com.githubapiwrapper.exception.CustomException;
import com.githubapiwrapper.exception.ResponseError;
import com.githubapiwrapper.utils.JSONUtil;
import com.google.gson.Gson;

import static com.githubapiwrapper.utils.JSONUtil.ERROR;
import static spark.Spark.*;

public class RepositoryService {

    public RepositoryService(final RepositoryDAO repositoryDAO) {

        get("/repositories/:owner/:repository-name", (request, response) -> {
            String owner = request.params(":owner");
            String repositoryName = request.params(":repository-name");
            return new Gson().toJson(repositoryDAO.getRepository(owner, repositoryName));
        });

        after((request, response) -> {
            response.type("application/json");
        });

        exception(IllegalArgumentException.class, (exception, request, response) -> {
            response.status(ERROR);
            response.body(JSONUtil.toJson(new ResponseError(exception)));
        });

        exception(CustomException.class, (exception, request, response) -> {
            response.status(ERROR);
            response.body(JSONUtil.toJson(new ResponseError(exception)));
        });
    }
}
