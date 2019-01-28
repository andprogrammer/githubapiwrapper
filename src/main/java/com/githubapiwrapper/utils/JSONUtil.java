package com.githubapiwrapper.utils;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JSONUtil {

    public static final int SUCCESS = 200;
    public static final int ERROR = 400;

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static ResponseTransformer json() {
        return JSONUtil::toJson;
    }
}
