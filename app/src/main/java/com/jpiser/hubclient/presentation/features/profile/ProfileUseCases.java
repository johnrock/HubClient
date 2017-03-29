package com.jpiser.hubclient.presentation.features.profile;

import com.jpiser.hubclient.presentation.features.profile.model.OrganizationModel;
import com.jpiser.hubclient.presentation.features.profile.model.UserProfile;

import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public interface ProfileUseCases {

    interface ProfileReceiver{
        void receiveProfile(UserProfile userProfile);
        void receiveOrganziations(List<OrganizationModel> organizationModels);
    }

    void loadProfile(ProfileReceiver profileReceiver);
}
