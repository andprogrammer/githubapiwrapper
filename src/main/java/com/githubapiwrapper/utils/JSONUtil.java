package com.githubapiwrapper.utils;

import com.google.gson.Gson;

public class JSONUtil {

    static Gson converter = new Gson();

    public static String toJson(Object object) {
        return converter.toJson(object);
    }
}
