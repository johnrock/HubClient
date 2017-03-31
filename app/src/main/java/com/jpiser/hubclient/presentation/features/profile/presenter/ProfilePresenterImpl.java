package com.jpiser.hubclient.presentation.features.profile.presenter;

import com.jpiser.hubclient.domain.interactors.HubInteractor;
import com.jpiser.hubclient.domain.models.HubIssue;
import com.jpiser.hubclient.domain.models.HubOrganization;
import com.jpiser.hubclient.domain.models.HubRepo;
import com.jpiser.hubclient.domain.models.HubUserProfile;
import com.jpiser.hubclient.presentation.features.profile.model.OrganizationModelAdapter;
import com.jpiser.hubclient.presentation.features.profile.model.RepoModelAdapter;
import com.jpiser.hubclient.presentation.features.profile.model.UserProfileModelAdapter;

import java.util.List;

import javax.inject.Inject;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class ProfilePresenterImpl implements ProfilePresenter, HubInteractor.HubAccessor{

    ViewLayer viewLayer;
    HubInteractor hubInteractor;

    @Inject
    public ProfilePresenterImpl(HubInteractor hubInteractor) {
        this.hubInteractor = hubInteractor;
    }

    @Override
    public void bind(ViewLayer viewLayer) {
        this.viewLayer = viewLayer;
        hubInteractor.bind(this);
    }

    @Override
    public void initProfile(String userLogin) {
        if(hubInteractor != null){
            hubInteractor.loadProfile(userLogin);
            hubInteractor.loadRepos(userLogin);
        }
    }

    @Override
    public void receiveProfile(HubUserProfile hubUserProfile) {
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
}
