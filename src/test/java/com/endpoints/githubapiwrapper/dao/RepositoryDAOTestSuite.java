package com.endpoints.githubapiwrapper.dao;

import com.githubapiwrapper.dao.AbstractFactory;
import com.githubapiwrapper.dao.RepositoryDAO;
import com.githubapiwrapper.exception.CustomException;
import com.githubapiwrapper.model.Repository;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.endpoints.githubapiwrapper.utils.Utils.NO_EXISTING_OWNER;
import static com.endpoints.githubapiwrapper.utils.Utils.NO_EXISTING_REPOSITORY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static spark.Spark.stop;

public class RepositoryDAOTestSuite {

    private final static Logger logger = Logger.getLogger(new Throwable().getStackTrace()[0].getClassName().getClass());

    @Rule
    public ExpectedException expectedExceptionThrown = ExpectedException.none();

    @Before
    public void setUp() {
        if (logger.isDebugEnabled())
            logger.debug(new Throwable().getStackTrace()[0].getMethodName()
                    + "() Starting testSuite "
                    + new Throwable().getStackTrace()[0].getClassName());
    }

    @After
    public void tearDown() {
        stop();
    }

    @Test
    public void testGetRepository() throws CustomException, UnirestException {
        Repository repository = createRepository();
        Repository expectedRepository = new Repository("andprogrammer/DBHandler", "DBHandler for postgresql RDBMS.", "https://github.com/andprogrammer/DBHandler.git", 0, "2017-06-05T23:25:19Z");
        assertThat(expectedRepository, equalTo(repository));
    }

    @Test
    public void testGetNoExistingRepository() throws CustomException, UnirestException {
        RepositoryDAO repositoryDAO = getRepositoryDAO();
        expectedExceptionThrow(com.githubapiwrapper.exception.CustomException.class, "Incorrect request : Not Found");
        repositoryDAO.getRepository(NO_EXISTING_OWNER, NO_EXISTING_REPOSITORY);
    }

    private <T> void expectedExceptionThrow(Class<T> exceptionType, String exceptionMessage) {
        expectedExceptionThrown.expect((Class<? extends Throwable>) exceptionType);
        expectedExceptionThrown.expectMessage(exceptionMessage);
    }

    private Repository createRepository() throws CustomException, UnirestException {
        RepositoryDAO repositoryDAO = getRepositoryDAO();
        Repository repository = repositoryDAO.getRepository("andprogrammer", "DBHandler");
        return repository;
    }

    private RepositoryDAO getRepositoryDAO() {
        return AbstractFactory.getFactory(AbstractFactory.FactoryType.DAO).create();
    }
}
