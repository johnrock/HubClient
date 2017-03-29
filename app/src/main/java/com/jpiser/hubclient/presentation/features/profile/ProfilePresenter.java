package com.jpiser.hubclient.presentation.features.profile;

import com.jpiser.hubclient.presentation.features.profile.model.UserProfile;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public interface ProfilePresenter {

    interface ViewLayer{
        void displayProfile(UserProfile userProfile);
    }


    void bind(ViewLayer viewLayer);

    void loadProfile();
}
