package com.jpiser.hubclient.domain.model;

import java.util.Date;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class HubRepo {
    private String name;
    private String description;
    private String url;
    private int stargazersCount;
    private int forksCount;
    private Date updatedAt;
    private String language;

    public int getStargazersCount() {
        return stargazersCount;
    }

    public int getForksCount() {
        return forksCount;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getLanguage() {
        return language;
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

    public void setStargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
