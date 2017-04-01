package com.jpiser.hubclient.presentation.features.issue.presenter;

import com.jpiser.hubclient.data.models.shared.Credentials;
import com.jpiser.hubclient.domain.interactors.HubInteractor;
import com.jpiser.hubclient.domain.models.HubIssue;
import com.jpiser.hubclient.domain.models.HubOrganization;
import com.jpiser.hubclient.domain.models.HubRepo;
import com.jpiser.hubclient.domain.models.HubUserProfile;
import com.jpiser.hubclient.presentation.models.IssueModel;
import com.jpiser.hubclient.presentation.models.IssueModelAdapter;
import com.jpiser.hubclient.presentation.models.IssueState;

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
            HubIssue hubIssue = new HubIssue();
            hubIssue.setTitle(title);
            hubIssue.setBody(body);
            hubInteractor.createIssue(repoName, hubIssue, credentials);
        }
    }

    @Override
    public void updateIssueBody(String repoName, IssueModel issueModel, String newBody) {
        if(issueModel != null && repoName != null && newBody != null){
            //TODO: must create new object to modify, can't use existing reference
            IssueModel updatedIssue = issueModel;
            updatedIssue.setBody(newBody);
            updateIssue(repoName, updatedIssue);
        }
    }

    @Override
    public void toggleIssueState(String repoName, IssueModel issueModel) {
        if(issueModel != null && repoName != null){

            //TODO: must create new object to modify, can't use existing reference
            IssueModel updatedIssue = issueModel;
            boolean updated = false;
            if(IssueState.OPEN.getValue().equals(updatedIssue.getState())){
                updatedIssue.setState(IssueState.CLOSED.getValue());
                updated = true;
            }
            else if(IssueState.CLOSED.getValue().equals(updatedIssue.getState())){
                updatedIssue.setState(IssueState.OPEN.getValue());
                updated = true;
            }

            if(updated){
                updateIssue(repoName, updatedIssue);
            }
        }
    }


    protected void updateIssue(String repoName, IssueModel updatedIssue) {
        if(hubInteractor != null){
            hubInteractor.updateIssue(repoName, new IssueModelAdapter().adapt(updatedIssue), credentials);
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
