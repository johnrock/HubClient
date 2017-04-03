package com.jpiser.hubclient.domain.models;

import com.jpiser.hubclient.data.models.github.GithubIssue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class HubIssueAdapter {

    public List<HubIssue> adapt(List<GithubIssue> githubIssues) {

        List<HubIssue> items = new ArrayList<>();
        if(githubIssues != null){
            for (GithubIssue githubIssue : githubIssues) {
                HubIssue hubIssue = getHubIssue(githubIssue);
                items.add(hubIssue);
            }
        }
        return items;
    }

    public HubIssue adapt(GithubIssue githubIssue){

        if(githubIssue != null){
            HubIssue hubIssue = getHubIssue(githubIssue);
            return hubIssue;
        }
        return null;
    }



    private HubIssue getHubIssue(GithubIssue githubIssue) {
        HubIssue hubIssue = new HubIssue();
        hubIssue.setHubUser(new HubUserAdapter().adapt(githubIssue.getGithubUser()));
        hubIssue.setTitle(githubIssue.getTitle());
        hubIssue.setState(githubIssue.getState());
        hubIssue.setComments(githubIssue.getComments());
        hubIssue.setNumber(githubIssue.getNumber());
        hubIssue.setBody(githubIssue.getBody());
        return hubIssue;
    }

    public GithubIssue adapt(HubIssue hubIssue) {
        if(hubIssue != null){
            GithubIssue githubIssue = new GithubIssue();
            githubIssue.setGithubUser(new HubUserAdapter().adapt(hubIssue.getHubUser()));
            githubIssue.setTitle(hubIssue.getTitle());
            githubIssue.setState(hubIssue.getState());
            githubIssue.setComments(hubIssue.getComments());
            githubIssue.setNumber(hubIssue.getNumber());
            githubIssue.setBody(hubIssue.getBody());

            return githubIssue;
        }
        return null;
    }
}
