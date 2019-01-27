package com.githubapiwrapper.dao;

public abstract class AbstractFactory {

    public enum FactoryType {
        DAO("Default DAO factory type");

        private String type;

        FactoryType(String type) {
            this.type = type;
        }
    }

    public abstract RepositoryDAO getRepositoryDAO();

    public static DAOFactory getFactory(FactoryType type) {
        switch (type) {
            case DAO:
                return new DAOFactory();
            default:
                return new DAOFactory();
        }
    }
}

