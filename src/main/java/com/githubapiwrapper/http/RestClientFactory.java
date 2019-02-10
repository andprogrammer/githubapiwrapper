package com.githubapiwrapper.http;

public abstract class RestClientFactory {

    public abstract RestClient create(int requestPerSecond);

    public static RestClientFactory getFactory() {
        return new GithubFactory();
    }
}
