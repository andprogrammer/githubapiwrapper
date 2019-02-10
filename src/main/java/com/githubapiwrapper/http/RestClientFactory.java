package com.githubapiwrapper.http;

public abstract class RestClientFactory {

    public enum FactoryType {
        GITHUB()
    }

    public abstract RestClient create(int requestPerSecond);

    public static RestClientFactory getFactory(FactoryType type) {
        switch (type) {
            case GITHUB:
                return new GithubFactory();
            default:
                return new GithubFactory();
        }
    }
}
