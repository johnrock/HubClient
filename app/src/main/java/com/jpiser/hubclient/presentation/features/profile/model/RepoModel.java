package com.jpiser.hubclient.presentation.features.profile.model;

import java.util.Date;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class RepoModel {
    private String name;
    private String description;
    private String url;
    private String language;
    private Date updatedAt;
    private int stargazersCount;
    private int forksCount;

    public String getLanguage() {
        return language;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }

    public int getForksCount() {
        return forksCount;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "RepoModel{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setStargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }
}
