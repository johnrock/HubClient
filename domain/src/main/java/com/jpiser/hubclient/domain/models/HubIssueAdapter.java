package com.jpiser.hubclient.domain.models;

import com.jpiser.hubclient.data.models.github.Issue;

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
                HubIssue hubIssue = getHubIssue(issue);
                items.add(hubIssue);
            }
        }
        return items;
    }

    public HubIssue adapt(Issue issue){

        if(issue != null){
            HubIssue hubIssue = getHubIssue(issue);
            return hubIssue;
        }
        return null;
    }



    private HubIssue getHubIssue(Issue issue) {
        HubIssue hubIssue = new HubIssue();
        hubIssue.setHubUser(new HubUserAdapter().adapt(issue.getUser()));
        hubIssue.setTitle(issue.getTitle());
        hubIssue.setState(issue.getState());
        hubIssue.setComments(issue.getComments());
        hubIssue.setNumber(issue.getNumber());
        hubIssue.setBody(issue.getBody());
        return hubIssue;
    }

    public Issue adapt(HubIssue hubIssue) {
        if(hubIssue != null){
            Issue issue = new Issue();
            issue.setUser(new HubUserAdapter().adapt(hubIssue.getHubUser()));
            issue.setTitle(hubIssue.getTitle());
            issue.setState(hubIssue.getState());
            issue.setComments(hubIssue.getComments());
            issue.setNumber(hubIssue.getNumber());
            issue.setBody(hubIssue.getBody());

            return issue;
        }
        return null;
    }
}
