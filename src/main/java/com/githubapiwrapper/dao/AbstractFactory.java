package com.githubapiwrapper.dao;

public abstract class AbstractFactory {

    public abstract RepositoryDAO create();

    public static AbstractFactory getFactory() {
        return new DAOFactory();
    }
}
