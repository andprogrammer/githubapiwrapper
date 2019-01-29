package com.endpoints.githubapiwrapper.http;

import com.githubapiwrapper.http.GithubClient;
import com.githubapiwrapper.http.GithubFactory;
import com.githubapiwrapper.http.RestClient;
import com.githubapiwrapper.http.RestClientFactory;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static spark.Spark.stop;

public class GithubFactoryTestSuite {

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
    public void testCreate() {
        int requestPerSecond = 20;
        RestClientFactory factory = new GithubFactory();
        RestClient client = factory.create(requestPerSecond);
        RestClient expectedClient = new GithubClient(requestPerSecond);
        assertThat(client, equalTo(expectedClient));
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
