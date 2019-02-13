package com.endpoints.githubapiwrapper.http;

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

import static org.apache.http.HttpStatus.SC_NOT_FOUND;

public class RestClientTestSuite {

    public static final String HTTPS_API_GITHUB_COM = "https://api.github.com/";
    @Rule
    public ExpectedException expectedExceptionThrown = ExpectedException.none();

    @After
    public void tearDown() {
        stop();
    }

    @Test
    public void testRequest() throws UnirestException {
        RestClient client = new RestClient(HTTPS_API_GITHUB_COM);
        String owner = REPOSITORY_OWNER_TEST;
        String repositoryName = REPOSITORY_NAME_TEST;
        Repository repository = client.request(owner, repositoryName);
        Repository expectedRepository = new Repository(owner + "/" + repositoryName, REPOSITORY_DESCRIPTION_TEST, REPOSITORY_CLONE_URL_TEST, 0, REPOSITORY_CREATE_AT_TEST);
        assertThat(expectedRepository, equalTo(repository));
    }

    @Test
    public void testRequestInvalidRepositoryOwner() throws UnirestException {
        RestClient client = new RestClient(HTTPS_API_GITHUB_COM);
        expectedExceptionThrow(com.githubapiwrapper.exception.CustomException.class, "Incorrect string=null");
        client.request(null, REPOSITORY_NAME_TEST);
    }

    @Test
    public void testRequestInvalidRepositoryName() throws UnirestException {
        RestClient client = new RestClient(HTTPS_API_GITHUB_COM);
        expectedExceptionThrow(com.githubapiwrapper.exception.CustomException.class, "Incorrect string=null");
        client.request(REPOSITORY_OWNER_TEST, null);
    }

    @Test
    public void testInvalidRequest() throws UnirestException {
        RestClient client = new RestClient(HTTPS_API_GITHUB_COM);
        expectedExceptionThrow(com.githubapiwrapper.exception.CustomException.class, Integer.toString(SC_NOT_FOUND));
        client.request(NO_EXISTING_REPOSITORY_OWNER, NO_EXISTING_REPOSITORY_NAME);
    }

    private <T> void expectedExceptionThrow(Class<T> exceptionType, String exceptionMessage) {
        expectedExceptionThrown.expect((Class<? extends Throwable>) exceptionType);
        expectedExceptionThrown.expectMessage(exceptionMessage);
    }
}
