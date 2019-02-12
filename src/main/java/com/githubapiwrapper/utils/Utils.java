package com.githubapiwrapper.utils;

import com.githubapiwrapper.exception.CustomException;

public class Utils {

    public static void checkIfNotNull(String string) throws CustomException {
        if (string == null || string.trim().isEmpty())
            throw new CustomException("Incorrect string=" + string);
    }
}
