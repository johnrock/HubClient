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

    private ViewLayer viewLayer;

    @Inject
    public ProfilePresenterImpl(ProfileUseCases profileUseCases) {
        this.profileUseCases = profileUseCases;
    }

    @Override
    public void bind(ViewLayer viewLayer) {
        this.viewLayer = viewLayer;
    }

    @Override
    public void loadProfile() {
        profileUseCases.loadProfile(this);
    }

    @Override
    public void receiveProfile(UserProfile userProfile) {
        viewLayer.displayProfile(userProfile);
    }

    @Override
    public void receiveOrganziations(List<OrganizationModel> organizationModels) {
        viewLayer.displayOrganizations(organizationModels);
    }
}
