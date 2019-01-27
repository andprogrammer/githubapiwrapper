package com.githubapiwrapper.dao;

import com.githubapiwrapper.dao.impl.RepositoryDAOImpl;

public class DAOFactory extends AbstractFactory {

    private final RepositoryDAOImpl repositoryDAO = new RepositoryDAOImpl();

    public RepositoryDAO getRepositoryDAO() {
        return repositoryDAO;
    }
}
