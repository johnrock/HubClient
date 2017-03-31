package com.jpiser.hubclient.presentation.features.issues.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class IssueUserModel implements Parcelable {

    public IssueUserModel() {
    }

    protected IssueUserModel(Parcel in) {
        login = in.readString();
    }

    public static final Creator<IssueUserModel> CREATOR = new Creator<IssueUserModel>() {
        @Override
        public IssueUserModel createFromParcel(Parcel in) {
            return new IssueUserModel(in);
        }

        @Override
        public IssueUserModel[] newArray(int size) {
            return new IssueUserModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login);
    }

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
