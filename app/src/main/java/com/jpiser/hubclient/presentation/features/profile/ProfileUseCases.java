package com.jpiser.hubclient.presentation.features.profile;

import com.jpiser.hubclient.presentation.features.profile.model.UserProfile;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public interface ProfileUseCases {

    interface ProfileReceiver{
        void receiveProfile(UserProfile userProfile);
    }

    void loadProfile(ProfileReceiver profileReceiver);
}
