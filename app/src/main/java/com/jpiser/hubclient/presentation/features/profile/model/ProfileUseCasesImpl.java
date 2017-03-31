package com.jpiser.hubclient.presentation.features.profile.model;

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
public class ProfileUseCasesImpl implements ProfileUseCases, HubInteractor.HubAccessor {

    HubInteractor hubInteractor;
    ProfileReceiver profileReceiver;

    @Inject
    public ProfileUseCasesImpl(HubInteractor hubInteractor) {
        this.hubInteractor = hubInteractor;
    }

    @Override
    public void bind(ProfileReceiver profileReceiver) {
        this.profileReceiver = profileReceiver;
        hubInteractor.bind(this);
    }

    @Override
    public void loadProfile(String userLogin) {
        if(hubInteractor != null && profileReceiver != null){
            hubInteractor.loadProfile(userLogin);
        }
    }

    @Override
    public void loadRepos(String userLogin) {
        if(hubInteractor != null && profileReceiver != null){
            hubInteractor.loadRepos(userLogin);
        }
    }


    @Override
    public void receiveProfile(HubUserProfile hubUserProfile) {
        if(profileReceiver != null){
            profileReceiver.receiveProfile(new UserProfileModelAdapter().adapt(hubUserProfile));
        }
    }

    @Override
    public void receiveOrganziations(List<HubOrganization> organizations) {
        if(profileReceiver != null){
            profileReceiver.receiveOrganziations(new OrganizationModelAdapter().adapt(organizations));
        }
    }

    @Override
    public void receiveRepos(List<HubRepo> repos) {
        if(profileReceiver != null){
            profileReceiver.receiveRepos(new RepoModelAdapter().adapt(repos));
        }
    }

    @Override
    public void receiveIssues(List<HubIssue> issues) {
        //Not Implemented
    }
}
