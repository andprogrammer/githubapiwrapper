package com.githubapiwrapper.dao;

import com.githubapiwrapper.model.Repository;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface RepositoryDAO {

    Repository getRepository(String owner, String repositoryName) throws UnirestException;
}
