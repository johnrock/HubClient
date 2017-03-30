package com.jpiser.hubclient.domain.model;

import com.jpiser.hubclient.data.github.model.Issue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class HubIssueAdapter {
    public List<HubIssue> adapt(List<Issue> issues) {

        List<HubIssue> items = new ArrayList<>();
        if(issues != null){

            for (Issue issue : issues) {
                HubIssue hubIssue = new HubIssue();
                hubIssue.setHubUser(new HubUserAdapter().adapt(issue.getUser()));
                hubIssue.setTitle(issue.getTitle());
                hubIssue.setState(issue.getState());
                hubIssue.setComments(issue.getComments());
                hubIssue.setNumber(issue.getNumber());
                items.add(hubIssue);
            }
        }
        return items;
    }
}
