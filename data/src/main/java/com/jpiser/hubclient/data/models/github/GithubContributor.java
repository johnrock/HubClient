package com.jpiser.hubclient.data.models.github;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class GithubContributor {

    String login;
    String html_url;

    int contributions;

    @Override
    public String toString() {
        return login + " (" + contributions + ")";
    }
}

