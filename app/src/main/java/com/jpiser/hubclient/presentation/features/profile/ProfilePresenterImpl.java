package com.jpiser.hubclient.presentation.features.profile;

import com.jpiser.hubclient.presentation.features.profile.model.OrganizationModel;
import com.jpiser.hubclient.presentation.features.profile.model.UserProfile;

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
    }

    @Override
    public void loadProfile(String userLogin) {
        if(profileUseCases != null){
            profileUseCases.loadProfile(this, userLogin);
        }
    }

    @Override
    public void receiveProfile(UserProfile userProfile) {
        if(viewLayer != null){
            viewLayer.displayProfile(userProfile);
        }
    }

    @Override
    public void receiveOrganziations(List<OrganizationModel> organizationModels) {
        if(viewLayer != null){
            viewLayer.displayOrganizations(organizationModels);
        }
    }
}
