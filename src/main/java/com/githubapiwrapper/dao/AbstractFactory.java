package com.githubapiwrapper.dao;

public abstract class AbstractFactory {

    public enum FactoryType {
        DAO();

        FactoryType() {
        }
    }

    public abstract RepositoryDAO create();

    public static AbstractFactory getFactory(FactoryType type) {
        switch (type) {
            case DAO:
                return new DAOFactory();
            default:
                return new DAOFactory();
        }
    }
}

