package com.githubapiwrapper.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository {

    private String fullName;
    private String description;
    private String cloneUrl;
    private int stars;
    private String createdAt;

    @JsonCreator
    public Repository(@JsonProperty("full_name") String fullName, @JsonProperty("description") String description, @JsonProperty("clone_url") String cloneUrl, @JsonProperty("stargazers_count") int stars, @JsonProperty("created_at") String createdAt) {
        this.fullName = fullName;
        this.description = description;
        this.cloneUrl = cloneUrl;
        this.stars = stars;
        this.createdAt = createdAt;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public int getStars() {
        return stars;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "com.githubapiwrapper.dao.modelithubapiwrapper.dao.Repository{" +
                "fullName='" + fullName + '\'' +
                ", description='" + description + '\'' +
                ", cloneUrl='" + cloneUrl + '\'' +
                ", stars='" + stars + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Repository)) return false;
        Repository repository = (Repository) o;
        return Objects.equals(fullName, repository.fullName) &&
                Objects.equals(description, repository.description) &&
                Objects.equals(cloneUrl, repository.cloneUrl) &&
                Objects.equals(stars, repository.stars) &&
                Objects.equals(createdAt, repository.createdAt);
    }
}
