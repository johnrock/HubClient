package com.jpiser.hubclient.presentation.features.profile;

import com.jpiser.hubclient.domain.HubApi;
import com.jpiser.hubclient.domain.model.HubUserProfile;
import com.jpiser.hubclient.presentation.features.profile.model.UserProfileAdapter;

import javax.inject.Inject;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public class ProfileUseCasesImpl implements ProfileUseCases, HubApi.HubAccessor {

    HubApi hubApi;
    private ProfileReceiver profileReceiver;

    @Inject
    public ProfileUseCasesImpl(HubApi hubApi) {
        this.hubApi = hubApi;
    }

    @Override
    public void loadProfile(ProfileReceiver profileReceiver) {
        this.profileReceiver = profileReceiver;
        hubApi.loadProfile(this);

    }

    @Override
    public void receiveProfile(HubUserProfile hubUserProfile) {
        if(profileReceiver != null){
            profileReceiver.receiveProfile(new UserProfileAdapter().adapt(hubUserProfile));
        }
    }
}
