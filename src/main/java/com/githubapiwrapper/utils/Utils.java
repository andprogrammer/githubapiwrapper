package com.githubapiwrapper.utils;

import com.githubapiwrapper.exception.CustomException;

public class Utils {

    public static void validate(String string) throws CustomException {
        if (string == null || string.isEmpty())
            throw new CustomException("Incorrect string=" + string);
    }
}
