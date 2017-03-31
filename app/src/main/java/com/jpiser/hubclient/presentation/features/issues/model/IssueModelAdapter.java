package com.jpiser.hubclient.presentation.features.issues.model;

import com.jpiser.hubclient.domain.models.HubIssue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */

class IssueModelAdapter {
    public List<IssueModel> adapt(List<HubIssue> issues) {
        List<IssueModel> items = new ArrayList<>();

        if(issues != null && issues.size() > 0){
            for (HubIssue issue : issues) {
                IssueModel issueModel = new IssueModel();
                issueModel.setIssueUserModel(new IssueUserModelAdapter().adapt(issue.getHubUser()));
                issueModel.setTitle(issue.getTitle());
                issueModel.setComments(issue.getComments());
                issueModel.setNumber(issue.getNumber());
                issueModel.setState(issue.getState());
                items.add(issueModel);
            }
        }
        return items;
    }
}
