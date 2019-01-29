package com.endpoints.githubapiwrapper.utils;

import com.githubapiwrapper.exception.CustomException;
import org.apache.log4j.Logger;
import spark.utils.IOUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class Utils {

    public static final String HTTP_LOCALHOST = "http://localhost";
    public static final String PORT = "4567";
    public static final String RESPONSE_ERROR = "Response error";
    public static final String REPOSITORY_OWNER_TEST = "andprogrammer";
    public static final String REPOSITORY_NAME_TEST = "Blockchain";
    public static final String REPOSITORY_DESCRIPTION_TEST = "Simple blockchain";
    public static final String REPOSITORY_CLONE_URL_TEST = "https://github.com/" + REPOSITORY_OWNER_TEST + "/" + REPOSITORY_NAME_TEST + ".git";
    public static final String REPOSITORY_CREATE_AT_TEST = "2018-10-14T17:51:32Z";
    public static final String NO_EXISTING_REPOSITORY_OWNER = "noExistingOwner" + new BigInteger(256, new Random());
    public static final String NO_EXISTING_REPOSITORY_NAME = "noExistingRepository" + new BigInteger(256, new Random());

    private final static Logger logger = Logger.getLogger(new Throwable().getStackTrace()[0].getClassName().getClass());

    public static Response request(String method, String route) {
        try {
            URL url = new URL(HTTP_LOCALHOST + ":" + PORT + route);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.connect();
            String body = IOUtils.toString(connection.getInputStream());
            return new Response(connection.getResponseCode(), body);
        } catch (IOException e) {
            logger.error(new Throwable().getStackTrace()[0].getMethodName() + "() " + e.getMessage());
            throw new CustomException(RESPONSE_ERROR);
        }
    }
}
