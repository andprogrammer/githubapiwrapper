package com.endpoints.githubapiwrapper.service;

import com.endpoints.githubapiwrapper.utils.Utils;
import com.githubapiwrapper.dao.impl.RepositoryDAOImpl;
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
        new RepositoryService(new RepositoryDAOImpl());
        awaitInitialization();
    }

    @After
    public void tearDown() {
        stop();
    }

    @Test
    public void testGetRepository() {
        String owner = "andprogrammer";
        String repositoryName = "DBHandler";
        Utils.Response response = request("GET", "/repositories/" + owner + "/" + repositoryName);
        Repository expectedRepository = new Repository("andprogrammer/DBHandler", "DBHandler for postgresql RDBMS.", "https://github.com/andprogrammer/DBHandler.git", 0, "2017-06-05T23:25:19Z");
        assertJSON(response, expectedRepository);
    }

    @Test
    public void testGetNoExistingRepository() {
        expectedExceptionThrow(CustomException.class, "Response error");
        request("GET", "/repositories/" + NO_EXISTING_OWNER + "/" + NO_EXISTING_REPOSITORY);
    }

    private void assertJSON(Utils.Response response, Repository expectedRepository) {
        assertThat(SUCCESS_RESPONSE, equalTo(response.status));
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
