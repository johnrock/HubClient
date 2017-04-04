package com.jpiser.hubclient.presentation.features.issue.presenter;

import com.jpiser.hubclient.data.models.shared.Credentials;
import com.jpiser.hubclient.domain.interactors.HubInteractor;
import com.jpiser.hubclient.domain.models.HubIssue;
import com.jpiser.hubclient.domain.models.HubOrganization;
import com.jpiser.hubclient.domain.models.HubRepo;
import com.jpiser.hubclient.domain.models.HubUserProfile;
import com.jpiser.hubclient.presentation.models.IssueModel;
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
            HubIssue hubIssue = new HubIssue();
            hubIssue.setTitle(title);
            hubIssue.setBody(body);
            hubInteractor.createIssue(repoName, hubIssue, credentials);
        }
    }

    @Override
    public void updateIssueBody(String repoName, IssueModel issueModel, String newBody) {
        if(issueModel != null && repoName != null && newBody != null){
            //TODO: Handle error messaging and refresh of the view if updateIssueBody fails
            /**
             * If the update fails then the view must be refreshed
             * because the issueModel it is holding is dirty from the failed update
             */
            IssueModel updatedIssue = issueModel;
            updatedIssue.setBody(newBody);
            updateIssue(repoName, updatedIssue);
        }
    }

    @Override
    public void toggleIssueState(String repoName, IssueModel issueModel) {
        if(issueModel != null && repoName != null){

            //TODO: Handle error messaging and  refresh of the view if toggleIssueState fails
            /**
             * If toggleIssueState fails then the view must be refreshed
             * because the issueModel it is holding is dirty from the failed update
             */
            hubInteractor.toggleIssueState(repoName, new IssueModelAdapter().adapt(issueModel), credentials);
        }
    }

    @Override
    public void resolveIssueStateButton(IssueModel issueModel) {
        viewLayer.updateIssueStateButton(hubInteractor.issueIsOpen(new IssueModelAdapter().adapt(issueModel)));
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
