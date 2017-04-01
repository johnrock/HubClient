package com.jpiser.hubclient.presentation.features.issue.presenter;

import com.jpiser.hubclient.data.models.shared.Credentials;
import com.jpiser.hubclient.domain.interactors.HubInteractor;
import com.jpiser.hubclient.domain.models.HubIssue;
import com.jpiser.hubclient.domain.models.HubOrganization;
import com.jpiser.hubclient.domain.models.HubRepo;
import com.jpiser.hubclient.domain.models.HubUserProfile;
import com.jpiser.hubclient.presentation.models.IssueModelAdapter;

import java.util.List;

import javax.inject.Inject;


/**
 * @author John Piser johnpiser@yahoo.com
 */

public class IssuePresenterImpl implements IssuePresenter, HubInteractor.HubAccessor {

    public static final String SLASH = "/";

    HubInteractor hubInteractor;
    ViewLayer viewLayer;
    Credentials credentials;

    @Inject
    public IssuePresenterImpl(HubInteractor hubInteractor) {
        this.hubInteractor = hubInteractor;
    }


    @Override
    public void bind(ViewLayer viewLayer, Credentials credentials) {
        this.viewLayer = viewLayer;
        this.credentials = credentials;
        hubInteractor.bind(this);
    }

    @Override
    public void createHeading(String userLogin, String repoName) {
        StringBuilder builder = new StringBuilder(userLogin)
                .append(SLASH)
                .append(repoName);

        viewLayer.displayHeading(builder.toString());
    }

    @Override
    public void createIssue(String repoName, String title, String body) {
        if(hubInteractor != null){
            hubInteractor.createIssue(title, body, repoName, credentials);
        }
    }

    @Override
    public void receiveProfile(HubUserProfile hubUserProfile) {
        //NOT IMPLEMENTED
    }

    @Override
    public void receiveOrganziations(List<HubOrganization> organizations) {
        //NOT IMPLEMENTED
    }

    @Override
    public void receiveRepos(List<HubRepo> repos) {
        //NOT IMPLEMENTED
    }

    @Override
    public void receiveIssues(List<HubIssue> issues) {
        //NOT IMPLEMENTED
    }

    @Override
    public void receiveIssue(HubIssue hubIssue) {
        if(viewLayer != null){
            viewLayer.displayIssue(new IssueModelAdapter().adapt(hubIssue));
        }
    }
}
