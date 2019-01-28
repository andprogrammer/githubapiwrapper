package com.endpoints.githubapiwrapper.utils;

import com.githubapiwrapper.exception.CustomException;
import org.apache.log4j.Logger;
import spark.utils.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {

    public static final int SUCCESS_RESPONSE = 200;
    public static final String HTTP_LOCALHOST = "http://localhost";
    public static final String PORT = "4567";

    private final static Logger logger = Logger.getLogger(new Throwable().getStackTrace()[0].getClassName().getClass());

    public static Response request(String method, String path) {
        try {
            URL url = new URL(HTTP_LOCALHOST + ":" + PORT + path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.connect();
            String body = IOUtils.toString(connection.getInputStream());
            return new Response(connection.getResponseCode(), body);
        } catch (IOException e) {
            logger.error(new Throwable().getStackTrace()[0].getMethodName() + "() " + e.getMessage());
            throw new CustomException("Response error");
        }
    }

    public static class Response {

        public final String body;
        public final int status;

        public Response(int status, String body) {
            this.status = status;
            this.body = body;
        }
    }
}
