package com.jpiser.hubclient.presentation.features.issues.model;

import com.jpiser.hubclient.domain.interactors.HubInteractor;
import com.jpiser.hubclient.domain.models.HubIssue;
import com.jpiser.hubclient.domain.models.HubOrganization;
import com.jpiser.hubclient.domain.models.HubRepo;
import com.jpiser.hubclient.domain.models.HubUserProfile;

import java.util.List;

import javax.inject.Inject;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class IssuesUseCasesImpl implements IssuesUseCases, HubInteractor.HubAccessor {

    HubInteractor hubInteractor;
    IssuesReceiver issuesReceiver;

    @Inject
    public IssuesUseCasesImpl(HubInteractor hubInteractor) {
        this.hubInteractor = hubInteractor;
    }

    @Override
    public void bind(IssuesReceiver issuesReceiver) {
        this.issuesReceiver = issuesReceiver;
        hubInteractor.bind(this);
    }

    @Override
    public void loadIssues(String ownerName, String repoName) {
        if(hubInteractor != null){
            hubInteractor.loadIssues(ownerName, repoName);
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
