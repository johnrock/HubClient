package com.jpiser.hubclient.presentation.features.profile;

import com.jpiser.hubclient.domain.HubApi;
import com.jpiser.hubclient.domain.model.HubOrganization;
import com.jpiser.hubclient.domain.model.HubUserProfile;
import com.jpiser.hubclient.presentation.features.profile.model.OrganizationModelAdapter;
import com.jpiser.hubclient.presentation.features.profile.model.UserProfileAdapter;

import java.util.List;

import javax.inject.Inject;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public class ProfileUseCasesImpl implements ProfileUseCases, HubApi.HubAccessor {

    HubApi hubApi;
    ProfileReceiver profileReceiver;

    @Inject
    public ProfileUseCasesImpl(HubApi hubApi) {
        this.hubApi = hubApi;
    }

    @Override
    public void loadProfile(ProfileReceiver profileReceiver, String userLogin) {
        this.profileReceiver = profileReceiver;
        if(hubApi != null){
            hubApi.loadProfile(this, userLogin);
        }
    }

    @Override
    public void receiveProfile(HubUserProfile hubUserProfile) {
        if(profileReceiver != null){
            profileReceiver.receiveProfile(new UserProfileAdapter().adapt(hubUserProfile));
        }
    }

    @Override
    public void receiveOrganziations(List<HubOrganization> organizations) {
        if(profileReceiver != null){
            profileReceiver.receiveOrganziations(new OrganizationModelAdapter().adapt(organizations));
        }
    }
}
