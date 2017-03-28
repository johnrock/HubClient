package com.jpiser.hubclient.presentation.features.profile;

import com.jpiser.hubclient.presentation.features.profile.model.Profile;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public interface ProfilePresenter {

    interface ViewLayer{
        void displayProfile(Profile profile);
    }


    void bind(ViewLayer viewLayer);

    void loadProfile();
}
