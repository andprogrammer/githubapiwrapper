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
    public static final String NO_EXISTING_OWNER = "noExistingOwner" + new BigInteger(256, new Random());
    public static final String NO_EXISTING_REPOSITORY = "noExistingRepository" + new BigInteger(256, new Random());
    public static final String RESPONSE_ERROR = "Response error";

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
