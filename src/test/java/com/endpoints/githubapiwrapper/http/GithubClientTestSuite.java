package com.endpoints.githubapiwrapper.http;

import com.githubapiwrapper.http.GithubClient;
import com.githubapiwrapper.http.RestClient;
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

public class GithubClientTestSuite {

    @Rule
    public ExpectedException expectedExceptionThrown = ExpectedException.none();

    @After
    public void tearDown() {
        stop();
    }

    @Test
    public void testRequest() throws UnirestException {
        int requestPerSecond = 20;
        RestClient client = new GithubClient(requestPerSecond);
        String owner = REPOSITORY_OWNER_TEST;
        String repositoryName = REPOSITORY_NAME_TEST;
        Repository repository = client.request(owner, repositoryName);
        Repository expectedRepository = new Repository(owner + "/" + repositoryName, REPOSITORY_DESCRIPTION_TEST, REPOSITORY_CLONE_URL_TEST, 0, REPOSITORY_CREATE_AT_TEST);
        assertThat(expectedRepository, equalTo(repository));
    }

    @Test
    public void testRequestInvalidRepositoryOwner() throws UnirestException {
        int requestPerSecond = 20;
        RestClient client = new GithubClient(requestPerSecond);
        expectedExceptionThrow(com.githubapiwrapper.exception.CustomException.class, "Incorrect string=null");
        client.request(null, REPOSITORY_NAME_TEST);
    }

    @Test
    public void testRequestInvalidRepositoryName() throws UnirestException {
        int requestPerSecond = 20;
        RestClient client = new GithubClient(requestPerSecond);
        expectedExceptionThrow(com.githubapiwrapper.exception.CustomException.class, "Incorrect string=null");
        client.request(REPOSITORY_OWNER_TEST, null);
    }

    @Test
    public void testInvalidRequest() throws UnirestException {
        int requestPerSecond = 20;
        RestClient client = new GithubClient(requestPerSecond);
        expectedExceptionThrow(com.githubapiwrapper.exception.CustomException.class, "Incorrect request : Not Found");
        client.request(NO_EXISTING_REPOSITORY_OWNER, NO_EXISTING_REPOSITORY_NAME);
    }

    private <T> void expectedExceptionThrow(Class<T> exceptionType, String exceptionMessage) {
        expectedExceptionThrown.expect((Class<? extends Throwable>) exceptionType);
        expectedExceptionThrown.expectMessage(exceptionMessage);
    }
}
