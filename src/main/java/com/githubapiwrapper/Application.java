package com.githubapiwrapper;

import com.githubapiwrapper.dao.AbstractFactory;
import com.githubapiwrapper.dao.RepositoryDAO;
import com.githubapiwrapper.service.RepositoryService;
import org.apache.log4j.Logger;

public class Application {

    private final static Logger logger = Logger.getLogger(Application.class);

    public static void main(String[] args) {
        runApplication();
    }

    private static void runApplication() {
        logger.info("Starting Github Wrapper");
        startServices();
    }

    private static void startServices() {
        AbstractFactory factory = AbstractFactory.getFactory();
        RepositoryDAO repository = factory.create();
        new RepositoryService(repository);
    }
}
