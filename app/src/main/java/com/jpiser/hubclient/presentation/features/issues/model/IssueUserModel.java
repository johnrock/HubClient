package com.jpiser.hubclient.presentation.features.issues.model;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class IssueUserModel {
    private String login;

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return "IssueUserModel{" +
                "login='" + login + '\'' +
                '}';
    }
}
