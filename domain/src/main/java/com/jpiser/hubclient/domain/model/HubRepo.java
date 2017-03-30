package com.jpiser.hubclient.domain.model;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class HubRepo {
    private String name;
    private String description;
    private String url;

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
}
