package com.jpiser.hubclient.presentation.features.profile.presenter;

import com.jpiser.hubclient.data.models.shared.Credentials;
import com.jpiser.hubclient.domain.interactors.HubInteractor;
import com.jpiser.hubclient.domain.models.HubIssue;
import com.jpiser.hubclient.domain.models.HubOrganization;
import com.jpiser.hubclient.domain.models.HubRepo;
import com.jpiser.hubclient.domain.models.HubUserProfile;
import com.jpiser.hubclient.presentation.models.OrganizationModelAdapter;
import com.jpiser.hubclient.presentation.models.RepoModelAdapter;
import com.jpiser.hubclient.presentation.models.UserProfileModelAdapter;

import java.util.List;

import javax.inject.Inject;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class ProfilePresenterImpl implements ProfilePresenter, HubInteractor.HubAccessor{

    ViewLayer viewLayer;
    Credentials credentials;
    HubInteractor hubInteractor;

    @Inject
    public ProfilePresenterImpl(HubInteractor hubInteractor) {
        this.hubInteractor = hubInteractor;
    }

    @Override
    public void bind(ViewLayer viewLayer, Credentials credentials) {
        this.viewLayer = viewLayer;
        this.credentials = credentials;
        hubInteractor.bind(this);
    }

    @Override
    public void initProfile(String userLogin) {
        if(hubInteractor != null){
            hubInteractor.loadProfile(userLogin, credentials);
            hubInteractor.loadRepos(userLogin, credentials);
        }
    }

    @Override
    public void receiveProfile(HubUserProfile hubUserProfile) {
        if(hubUserProfile != null){
            hubInteractor.loadOrganizations(hubUserProfile.getLogin());
        }
        if(viewLayer != null){
            viewLayer.displayProfile(new UserProfileModelAdapter().adapt(hubUserProfile));
        }
    }

    @Override
    public void receiveOrganziations(List<HubOrganization> organizations) {
        if(viewLayer != null){
            viewLayer.displayOrganizations(new OrganizationModelAdapter().adapt(organizations));
        }
    }

    @Override
    public void receiveRepos(List<HubRepo> repos) {
        if(viewLayer != null){
            viewLayer.displayRepos(new RepoModelAdapter().adapt(repos));
        }
    }

    @Override
    public void receiveIssues(List<HubIssue> issues) {
        //Not Implemented
    }

    @Override
    public void receiveIssue(HubIssue hubIssue) {
        //Not Implemented
    }
}
