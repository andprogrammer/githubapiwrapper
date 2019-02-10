package com.endpoints.githubapiwrapper.dao;

import com.githubapiwrapper.dao.AbstractFactory;
import com.githubapiwrapper.dao.DAOFactory;
import com.githubapiwrapper.dao.RepositoryDAO;
import org.junit.After;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static spark.Spark.stop;

public class DAOFactoryTestSuite {

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
