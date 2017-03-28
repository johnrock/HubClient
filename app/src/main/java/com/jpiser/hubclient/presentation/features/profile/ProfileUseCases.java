package com.jpiser.hubclient.presentation.features.profile;

import com.jpiser.hubclient.presentation.features.profile.model.Profile;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public interface ProfileUseCases {

    interface ProfileReceiver{
        void receiveProfile(Profile profile);
    }

    void loadProfile(ProfileReceiver profileReceiver);
}
