package com.githubapiwrapper.utils;

import com.google.gson.Gson;

public class JSONUtil {

    public static final int SUCCESS = 200;
    public static final int ERROR = 400;

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }
}
