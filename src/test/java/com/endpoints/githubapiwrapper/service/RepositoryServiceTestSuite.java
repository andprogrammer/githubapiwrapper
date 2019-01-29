package com.endpoints.githubapiwrapper.service;

import com.endpoints.githubapiwrapper.utils.Response;
import com.githubapiwrapper.dao.AbstractFactory;
import com.githubapiwrapper.dao.RepositoryDAO;
import com.githubapiwrapper.exception.CustomException;
import com.githubapiwrapper.model.Repository;
import com.githubapiwrapper.service.RepositoryService;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.endpoints.githubapiwrapper.utils.Utils.*;
import static com.githubapiwrapper.utils.JSONUtil.SUCCESS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static spark.Spark.awaitInitialization;
import static spark.Spark.stop;

public class RepositoryServiceTestSuite {

    private final static Logger logger = Logger.getLogger(new Throwable().getStackTrace()[0].getClassName().getClass());

    @Rule
    public ExpectedException expectedExceptionThrown = ExpectedException.none();

    @Before
    public void setUp() {
        if (logger.isDebugEnabled())
            logger.debug(new Throwable().getStackTrace()[0].getMethodName()
                    + "() Starting testSuite "
                    + new Throwable().getStackTrace()[0].getClassName()
                    + " on " + HTTP_LOCALHOST + ":" + PORT);
        startService();
        awaitInitialization();
    }

    private void startService() {
        AbstractFactory factory = AbstractFactory.getFactory(AbstractFactory.FactoryType.DAO);
        RepositoryDAO repository = factory.create();
        new RepositoryService(repository);
    }

    @After
    public void tearDown() {
        stop();
    }

    @Test
    public void testGetRepository() {
        String owner = REPOSITORY_OWNER_TEST;
        String repositoryName = REPOSITORY_NAME_TEST;
        Response response = request("GET", "/repositories/" + owner + "/" + repositoryName);
        Repository expectedRepository = new Repository(owner + "/" + repositoryName, REPOSITORY_DESCRIPTION_TEST, REPOSITORY_CLONE_URL_TEST, 0, REPOSITORY_CREATE_AT_TEST);
        assertResponse(response, expectedRepository);
    }

    @Test
    public void testGetNoExistingRepository() {
        expectedExceptionThrow(CustomException.class, RESPONSE_ERROR);
        request("GET", "/repositories/" + NO_EXISTING_REPOSITORY_OWNER + "/" + NO_EXISTING_REPOSITORY_NAME);
    }

    private void assertResponse(Response response, Repository expectedRepository) {
        assertThat(SUCCESS, equalTo(response.status));
        JSONObject json = new JSONObject(response.body);
        assertThat(expectedRepository.getCreatedAt(), equalTo(json.getString("createdAt")));
        assertThat(expectedRepository.getFullName(), equalTo(json.getString("fullName")));
        assertThat(expectedRepository.getDescription(), equalTo(json.getString("description")));
        assertThat(expectedRepository.getStars(), equalTo(json.getInt("stars")));
        assertThat(expectedRepository.getCloneUrl(), equalTo(json.getString("cloneUrl")));
    }

    private <T> void expectedExceptionThrow(Class<T> exceptionType, String exceptionMessage) {
        expectedExceptionThrown.expect((Class<? extends Throwable>) exceptionType);
        expectedExceptionThrown.expectMessage(exceptionMessage);
    }
}
