package com.endpoints.githubapiwrapper.dao;

import com.githubapiwrapper.dao.AbstractFactory;
import com.githubapiwrapper.dao.DAOFactory;
import com.githubapiwrapper.dao.RepositoryDAO;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static spark.Spark.stop;

public class DAOFactoryTestSuite {

    private final static Logger logger = Logger.getLogger(new Throwable().getStackTrace()[0].getClassName().getClass());

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
        AbstractFactory factory = new DAOFactory();
        RepositoryDAO repository = factory.create();
        assertNotNull(repository);
    }
}
