package com.jpiser.hubclient.domain.model;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class HubIssue {

    private HubUser hubUser;
    private String title;
    private String state;
    private int comments;
    private int number;

    public String getTitle() {
        return title;
    }

    public String getState() {
        return state;
    }

    public int getComments() {
        return comments;
    }

    public int getNumber() {
        return number;
    }

    public HubUser getHubUser() {
        return hubUser;
    }

    public void setHubUser(HubUser hubUser) {
        this.hubUser = hubUser;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}