package com.endpoints.githubapiwrapper.http;

import com.githubapiwrapper.http.GithubFactory;
import com.githubapiwrapper.http.RestClientFactory;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static spark.Spark.stop;

public class GithubFactoryTestSuite {

    @Rule
    public ExpectedException expectedExceptionThrown = ExpectedException.none();

    @After
    public void tearDown() {
        stop();
    }

    @Test
    public void testIncorrectCreate() {
        int requestPerSecond = -1;
        RestClientFactory factory = new GithubFactory();
        expectedExceptionThrow(com.githubapiwrapper.exception.CustomException.class, "Invalid request per second : " + requestPerSecond);
        factory.create(requestPerSecond);
    }

    private <T> void expectedExceptionThrow(Class<T> exceptionType, String exceptionMessage) {
        expectedExceptionThrown.expect((Class<? extends Throwable>) exceptionType);
        expectedExceptionThrown.expectMessage(exceptionMessage);
    }
}
