package com.jpiser.hubclient.data.retrofit.model;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class Contributor {

    String login;
    String html_url;

    int contributions;

    @Override
    public String toString() {
        return login + " (" + contributions + ")";
    }
}

