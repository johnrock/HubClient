package com.jpiser.hubclient.presentation.features.issues.model;

import android.support.annotation.NonNull;

import com.jpiser.hubclient.domain.models.HubIssue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class IssueModelAdapter {
    public List<IssueModel> adapt(List<HubIssue> hubIssues) {
        List<IssueModel> items = new ArrayList<>();

        if(hubIssues != null && hubIssues.size() > 0){
            for (HubIssue hubIssue : hubIssues) {
                IssueModel issueModel = getIssueModel(hubIssue);
                items.add(issueModel);
            }
        }
        return items;
    }

    public IssueModel adapt(HubIssue hubIssue){
        if(hubIssue != null){
            IssueModel issueModel = getIssueModel(hubIssue);
            return issueModel;
        }
        return null;
    }

    @NonNull
    private IssueModel getIssueModel(HubIssue hubIssue) {
        IssueModel issueModel = new IssueModel();
        issueModel.setIssueUserModel(new IssueUserModelAdapter().adapt(hubIssue.getHubUser()));
        issueModel.setTitle(hubIssue.getTitle());
        issueModel.setComments(hubIssue.getComments());
        issueModel.setNumber(hubIssue.getNumber());
        issueModel.setState(hubIssue.getState());
        issueModel.setBody(hubIssue.getBody());
        return issueModel;
    }
}
