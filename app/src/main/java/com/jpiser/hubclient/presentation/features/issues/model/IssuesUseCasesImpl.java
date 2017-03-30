package com.jpiser.hubclient.presentation.features.issues.model;

import com.jpiser.hubclient.domain.HubApi;
import com.jpiser.hubclient.domain.model.HubIssue;
import com.jpiser.hubclient.domain.model.HubOrganization;
import com.jpiser.hubclient.domain.model.HubRepo;
import com.jpiser.hubclient.domain.model.HubUserProfile;

import java.util.List;

import javax.inject.Inject;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class IssuesUseCasesImpl implements IssuesUseCases, HubApi.HubAccessor {

    HubApi hubApi;
    IssuesReceiver issuesReceiver;

    @Inject
    public IssuesUseCasesImpl(HubApi hubApi) {
        this.hubApi = hubApi;
    }

    @Override
    public void bind(IssuesReceiver issuesReceiver) {
        this.issuesReceiver = issuesReceiver;
        hubApi.bind(this);
    }

    @Override
    public void loadIssues(String ownerName, String repoName) {

        if(hubApi != null){
            hubApi.loadIssues(ownerName, repoName);
        }
    }


    @Override
    public void receiveIssues(List<HubIssue> issues) {
        if(issuesReceiver != null){
            issuesReceiver.receiveIssues(new IssueModelAdapter().adapt(issues));
        }
    }

    @Override
    public void receiveProfile(HubUserProfile hubUserProfile) {
        //Not Implemented
    }

    @Override
    public void receiveOrganziations(List<HubOrganization> organizations) {
        //Not Implemented
    }

    @Override
    public void receiveRepos(List<HubRepo> repos) {
        //Not Implemented
    }
}
