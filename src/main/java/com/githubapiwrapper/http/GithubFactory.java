package com.githubapiwrapper.http;

public class GithubFactory extends RestClientFactory {

    @Override
    public RestClient create() {
        return new GithubClient();
    }
}
