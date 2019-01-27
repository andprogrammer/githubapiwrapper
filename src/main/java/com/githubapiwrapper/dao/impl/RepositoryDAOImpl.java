package com.githubapiwrapper.dao.impl;

import com.githubapiwrapper.model.Repository;
import com.githubapiwrapper.RestClient;
import com.githubapiwrapper.dao.RepositoryDAO;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RepositoryDAOImpl implements RepositoryDAO {

    public Repository getRepository(String owner, String repositoryName) throws UnirestException {
        RestClient client = new RestClient("https://api.github.com/repos/andprogrammer/DBHandler", 20);
//        Repository repository = client.request("andprogrammer", "DBHandler");
        Repository repository = client.request(owner, repositoryName);
        return repository;
    }
}
