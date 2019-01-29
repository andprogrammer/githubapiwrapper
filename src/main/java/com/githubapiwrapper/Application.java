package com.githubapiwrapper;

import com.githubapiwrapper.dao.AbstractFactory;
import com.githubapiwrapper.dao.RepositoryDAO;
import com.githubapiwrapper.service.RepositoryService;
import org.apache.log4j.Logger;

public class Application {

    private final static Logger logger = Logger.getLogger(new Throwable().getStackTrace()[0].getClassName().getClass());

    public static void main(String[] args) {
        runApplication();
    }

    private static void runApplication() {
        logger.info(new Throwable().getStackTrace()[0].getMethodName() + "() Starting Github Wrapper");
        startServices();
    }

    private static void startServices() {
        AbstractFactory daoFactory = AbstractFactory.getFactory(AbstractFactory.FactoryType.DAO);
        RepositoryDAO repositoryDAO = daoFactory.create();
        new RepositoryService(repositoryDAO);
    }
}
