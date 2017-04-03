package com.jpiser.hubclient.presentation.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class UserModel implements Parcelable {

    public UserModel() {
    }

    protected UserModel(Parcel in) {
        login = in.readString();
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
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
        return "UserModel{" +
                "login='" + login + '\'' +
                '}';
    }
}
