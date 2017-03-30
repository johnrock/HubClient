package com.jpiser.hubclient.presentation.features.profile;

import com.jpiser.hubclient.presentation.features.profile.model.OrganizationModel;
import com.jpiser.hubclient.presentation.features.profile.model.RepoModel;
import com.jpiser.hubclient.presentation.features.profile.model.UserProfileModel;

import java.util.List;

import javax.inject.Inject;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class ProfilePresenterImpl implements  ProfilePresenter, ProfileUseCases.ProfileReceiver {

    ProfileUseCases profileUseCases;
    ViewLayer viewLayer;

    @Inject
    public ProfilePresenterImpl(ProfileUseCases profileUseCases) {
        this.profileUseCases = profileUseCases;
    }

    @Override
    public void bind(ViewLayer viewLayer) {
        this.viewLayer = viewLayer;
        profileUseCases.bind(this);
    }

    @Override
    public void initProfile(String userLogin) {
        if(profileUseCases != null){
            profileUseCases.loadProfile(userLogin);
            profileUseCases.loadRepos(userLogin);
        }
    }

    @Override
    public void receiveProfile(UserProfileModel userProfileModel) {
        if(viewLayer != null){
            viewLayer.displayProfile(userProfileModel);
        }
    }

    @Override
    public void receiveOrganziations(List<OrganizationModel> organizationModels) {
        if(viewLayer != null){
            viewLayer.displayOrganizations(organizationModels);
        }
    }

    @Override
    public void receiveRepos(List<RepoModel> repoModels) {
        if(viewLayer != null){
            viewLayer.displayRepos(repoModels);
        }
    }
}
