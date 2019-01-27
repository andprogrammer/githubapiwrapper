package com.githubapiwrapper.dao.impl;

import com.githubapiwrapper.model.Repository;
import com.githubapiwrapper.service.RestClient;
import com.githubapiwrapper.dao.RepositoryDAO;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.log4j.Logger;

import static com.githubapiwrapper.utils.Utils.validate;

public class RepositoryDAOImpl implements RepositoryDAO {

    private final static Logger logger = Logger.getLogger(new Throwable().getStackTrace()[0].getClassName().getClass());

    public Repository getRepository(String owner, String repositoryName) throws UnirestException {
        validate(owner);
        validate(repositoryName);
        RestClient client = new RestClient("https://api.github.com/", 20);
        Repository repository = client.request(owner, repositoryName);
        if (logger.isDebugEnabled())
            logger.debug(new Throwable().getStackTrace()[0].getMethodName() + "() " + repository);
        return repository;
    }
}
