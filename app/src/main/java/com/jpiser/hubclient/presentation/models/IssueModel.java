package com.jpiser.hubclient.presentation.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class IssueModel implements Parcelable{

    public IssueModel() {
    }

    protected IssueModel(Parcel in) {
        userModel = in.readParcelable(UserModel.class.getClassLoader());
        title = in.readString();
        comments = in.readInt();
        number = in.readInt();
        state = in.readString();
        body = in.readString();
        numberForDisplay = in.readString();
    }

    public static final Creator<IssueModel> CREATOR = new Creator<IssueModel>() {
        @Override
        public IssueModel createFromParcel(Parcel in) {
            return new IssueModel(in);
        }

        @Override
        public IssueModel[] newArray(int size) {
            return new IssueModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(userModel, flags);
        dest.writeString(title);
        dest.writeInt(comments);
        dest.writeInt(number);
        dest.writeString(state);
        dest.writeString(body);
        dest.writeString(numberForDisplay);
    }

    private UserModel userModel;
    private String title;
    private int comments;
    private int number;
    private String state;
    private String body;
    private String numberForDisplay;

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
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

    public String getNumberForDisplay(){
        return numberForDisplay;
    }

    public void setNumberForDisplay(String numberForDisplay) {
        this.numberForDisplay = numberForDisplay;
    }

    public String getState() {
        return state;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "IssueModel{" +
                "userModel=" + userModel +
                ", title='" + title + '\'' +
                ", comments=" + comments +
                ", number=" + number +
                ", state='" + state + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

}
