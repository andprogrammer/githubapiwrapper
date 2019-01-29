package com.githubapiwrapper.http;

public class GithubFactory extends RestClientFactory {

    @Override
    public RestClient create(int requestPerSecond) {
        return new GithubClient(requestPerSecond);
    }
}
