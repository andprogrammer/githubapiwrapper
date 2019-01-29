package com.endpoints.githubapiwrapper.http;

import com.githubapiwrapper.http.RestClientFactory;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.githubapiwrapper.http.RestClientFactory.FactoryType.GITHUB;
import static junit.framework.TestCase.assertNotNull;
import static spark.Spark.stop;

public class RestClientFactoryTestSuite {

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
    public void testGetFactory() {
        RestClientFactory factory = RestClientFactory.getFactory(GITHUB);
        assertNotNull(factory);
    }

    @Test
    public void testGetFactoryNullFactoryType() {
        expectedExceptionThrow(java.lang.NullPointerException.class);
        RestClientFactory.getFactory(null);
    }

    private <T> void expectedExceptionThrow(Class<T> exceptionType) {
        expectedExceptionThrown.expect((Class<? extends Throwable>) exceptionType);
    }
}
