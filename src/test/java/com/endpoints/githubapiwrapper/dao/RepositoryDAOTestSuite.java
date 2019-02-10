package com.endpoints.githubapiwrapper.dao;

import com.githubapiwrapper.dao.AbstractFactory;
import com.githubapiwrapper.dao.RepositoryDAO;
import com.githubapiwrapper.exception.CustomException;
import com.githubapiwrapper.model.Repository;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.endpoints.githubapiwrapper.utils.Utils.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static spark.Spark.stop;

public class RepositoryDAOTestSuite {

    @Rule
    public ExpectedException expectedExceptionThrown = ExpectedException.none();

    @After
    public void tearDown() {
        stop();
    }

    @Test
    public void testGetRepository() throws CustomException, UnirestException {
        Repository repository = createRepository();
        Repository expectedRepository = new Repository(REPOSITORY_OWNER_TEST + "/" + REPOSITORY_NAME_TEST, REPOSITORY_DESCRIPTION_TEST, REPOSITORY_CLONE_URL_TEST, 0, REPOSITORY_CREATE_AT_TEST);
        assertThat(expectedRepository, equalTo(repository));
    }

    @Test
    public void testGetRepositoryInvalidRepositoryOwner() throws CustomException, UnirestException {
        RepositoryDAO repositoryDAO = getRepositoryDAO();
        expectedExceptionThrow(com.githubapiwrapper.exception.CustomException.class, "Incorrect string=null");
        repositoryDAO.getRepository(null, REPOSITORY_NAME_TEST);
    }

    @Test
    public void testGetRepositoryInvalidRepositoryName() throws CustomException, UnirestException {
        RepositoryDAO repositoryDAO = getRepositoryDAO();
        expectedExceptionThrow(com.githubapiwrapper.exception.CustomException.class, "Incorrect string=null");
        repositoryDAO.getRepository(REPOSITORY_OWNER_TEST, null);
    }

    @Test
    public void testGetNoExistingRepository() throws CustomException, UnirestException {
        RepositoryDAO repositoryDAO = getRepositoryDAO();
        expectedExceptionThrow(com.githubapiwrapper.exception.CustomException.class, "404");
        repositoryDAO.getRepository(NO_EXISTING_REPOSITORY_OWNER, NO_EXISTING_REPOSITORY_NAME);
    }

    private <T> void expectedExceptionThrow(Class<T> exceptionType, String exceptionMessage) {
        expectedExceptionThrown.expect((Class<? extends Throwable>) exceptionType);
        expectedExceptionThrown.expectMessage(exceptionMessage);
    }

    private Repository createRepository() throws CustomException, UnirestException {
        RepositoryDAO repositoryDAO = getRepositoryDAO();
        Repository repository = repositoryDAO.getRepository(REPOSITORY_OWNER_TEST, REPOSITORY_NAME_TEST);
        return repository;
    }

    private RepositoryDAO getRepositoryDAO() {
        return AbstractFactory.getFactory().create();
    }
}
