package com.endpoints.githubapiwrapper.dao;

import com.githubapiwrapper.dao.AbstractFactory;
import org.junit.After;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static spark.Spark.stop;

public class AbstractFactoryTestSuite {

    @After
    public void tearDown() {
        stop();
    }

    @Test
    public void testGetFactory() {
        AbstractFactory factory = AbstractFactory.getFactory();
        assertNotNull(factory);
    }
}
