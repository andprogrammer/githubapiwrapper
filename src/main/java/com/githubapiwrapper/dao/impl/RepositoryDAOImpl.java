package com.githubapiwrapper.dao.impl;

import com.githubapiwrapper.dao.RepositoryDAO;
import com.githubapiwrapper.http.RestClient;
import com.githubapiwrapper.http.RestClientFactory;
import com.githubapiwrapper.model.Repository;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.log4j.Logger;

import static com.githubapiwrapper.utils.Utils.checkIfNotNull;

public class RepositoryDAOImpl implements RepositoryDAO {

    private final static Logger logger = Logger.getLogger(RepositoryDAOImpl.class);
    protected static final int REQUEST_PER_SECOND = 20;

    public Repository getRepository(String owner, String repositoryName) throws UnirestException {
        checkIfNotNull(owner);
        checkIfNotNull(repositoryName);
        RestClientFactory factory = RestClientFactory.getFactory();
        RestClient client = factory.create(REQUEST_PER_SECOND);
        Repository repository = client.request(owner, repositoryName);
        if (logger.isDebugEnabled())
            logger.debug(repository);
        return repository;
    }
}
