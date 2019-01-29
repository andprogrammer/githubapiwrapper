package com.githubapiwrapper.http;

import com.githubapiwrapper.exception.CustomException;

public class GithubFactory extends RestClientFactory {

    @Override
    public RestClient create(int requestPerSecond) {
        if (requestPerSecond <= 0)
            throw new CustomException("Invalid request per second : " + requestPerSecond);
        return new GithubClient(requestPerSecond);
    }
}
