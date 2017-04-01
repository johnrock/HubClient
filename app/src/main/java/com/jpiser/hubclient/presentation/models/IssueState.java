package com.jpiser.hubclient.presentation.models;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public enum IssueState {
    OPEN("open"),
    CLOSED("closed");

    private String value;

    IssueState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
