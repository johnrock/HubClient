package com.jpiser.hubclient.presentation.features.issues.presenter;

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

public class IssuesPresenterImpl implements IssuesPresenter, HubInteractor.HubAccessor {

    public static final String SLASH = "/";

    ViewLayer viewLayer;
    private Credentials credentials;
    HubInteractor hubInteractor;
    boolean showOpenIssues;

    @Inject
    public IssuesPresenterImpl(HubInteractor hubInteractor) {
        this.hubInteractor = hubInteractor;
        showOpenIssues = true;
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
    public void loadIssues(String ownerName, String repoName) {
        if(hubInteractor != null){
            hubInteractor.loadIssues(ownerName, repoName, credentials, showOpenIssues);
        }
    }

    @Override
    public void toggleIssueState(boolean open, String owner, String repoName) {
        showOpenIssues = open;
        loadIssues(owner, repoName);
    }

    @Override
    public boolean getIssuesState() {
        return showOpenIssues;
    }

    @Override
    public void receiveIssues(List<HubIssue> issues) {
        if(viewLayer != null){
           viewLayer.displayIssues(new IssueModelAdapter().adapt(issues));
        }
    }

    @Override
    public void receiveIssue(HubIssue hubIssue) {
        //Not Implemented
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
