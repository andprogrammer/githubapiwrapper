package com.endpoints.githubapiwrapper.http;

import com.githubapiwrapper.http.RestClientFactory;
import org.junit.After;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static spark.Spark.stop;

public class RestClientFactoryTestSuite {

    @After
    public void tearDown() {
        stop();
    }

    @Test
    public void testGetFactory() {
        RestClientFactory factory = RestClientFactory.getFactory();
        assertNotNull(factory);
    }
}
