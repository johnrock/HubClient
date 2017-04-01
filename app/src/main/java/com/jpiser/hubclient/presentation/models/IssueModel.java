package com.jpiser.hubclient.presentation.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class IssueModel implements Parcelable{

    public static final String NUMBER_SIGN = "#";

    public IssueModel() {
    }


    protected IssueModel(Parcel in) {
        issueUserModel = in.readParcelable(IssueUserModel.class.getClassLoader());
        title = in.readString();
        comments = in.readInt();
        number = in.readInt();
        state = in.readString();
        body = in.readString();
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
        dest.writeParcelable(issueUserModel, flags);
        dest.writeString(title);
        dest.writeInt(comments);
        dest.writeInt(number);
        dest.writeString(state);
        dest.writeString(body);
    }

    private IssueUserModel issueUserModel;
    private String title;
    private int comments;
    private int number;
    private String state;
    private String body;

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

    public String getNumberForDisplay(){
        return NUMBER_SIGN + number;
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
                "issueUserModel=" + issueUserModel +
                ", title='" + title + '\'' +
                ", comments=" + comments +
                ", number=" + number +
                ", state='" + state + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

}
