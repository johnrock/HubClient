package com.jpiser.hubclient.presentation.features.profile;

import com.jpiser.hubclient.domain.HubApi;
import com.jpiser.hubclient.domain.model.HubProfile;
import com.jpiser.hubclient.presentation.features.profile.model.ProfileAdapter;

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
    public void receiveProfile(HubProfile hubProfile) {
        if(profileReceiver != null){
            profileReceiver.receiveProfile(new ProfileAdapter().adapt(hubProfile));
        }
    }
}
