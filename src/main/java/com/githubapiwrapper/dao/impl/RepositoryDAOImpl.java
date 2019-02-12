package com.githubapiwrapper.dao.impl;

import com.githubapiwrapper.dao.RepositoryDAO;
import com.githubapiwrapper.http.RestClient;
import com.githubapiwrapper.model.Repository;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.log4j.Logger;

import static com.githubapiwrapper.utils.Utils.checkIfNotNull;

public class RepositoryDAOImpl implements RepositoryDAO {

    private final static Logger logger = Logger.getLogger(RepositoryDAOImpl.class);

    public Repository getRepository(String owner, String repositoryName) throws UnirestException {
        checkIfNotNull(owner);
        checkIfNotNull(repositoryName);
        RestClient client = new RestClient("https://api.github.com/");
        Repository repository = client.request(owner, repositoryName);
        if (logger.isDebugEnabled())
            logger.debug(repository);
        return repository;
    }
}
