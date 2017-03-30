package com.jpiser.hubclient.presentation.features.issues.model;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class IssueModel {

    private IssueUserModel issueUserModel;
    private String title;
    private int comments;
    private int number;
    private String state;

    public IssueUserModel getIssueUserModel() {
        return issueUserModel;
    }

    public void setIssueUserModel(IssueUserModel issueUserModel) {
        this.issueUserModel = issueUserModel;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public int getComments() {
        return comments;
    }

    public int getNumber() {
        return number;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "IssueModel{" +
                "issueUserModel=" + issueUserModel +
                ", title='" + title + '\'' +
                ", comments=" + comments +
                ", number=" + number +
                ", state='" + state + '\'' +
                '}';
    }
}
