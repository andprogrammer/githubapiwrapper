package com.endpoints.githubapiwrapper.dao;

import com.githubapiwrapper.dao.AbstractFactory;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.githubapiwrapper.dao.AbstractFactory.FactoryType.DAO;
import static junit.framework.TestCase.assertNotNull;
import static spark.Spark.stop;

public class AbstractFactoryTestSuite {

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
        AbstractFactory factory = AbstractFactory.getFactory(DAO);
        assertNotNull(factory);
    }

    @Test
    public void testGetFactoryNullFactoryType() {
        expectedExceptionThrow(java.lang.NullPointerException.class);
        AbstractFactory.getFactory(null);
    }

    private <T> void expectedExceptionThrow(Class<T> exceptionType) {
        expectedExceptionThrown.expect((Class<? extends Throwable>) exceptionType);
    }
}
