package com.githubapiwrapper.dao;

import com.githubapiwrapper.dao.impl.RepositoryDAOImpl;

public class DAOFactory extends AbstractFactory {

    @Override
    public RepositoryDAO create() {
        return new RepositoryDAOImpl();
    }
}
