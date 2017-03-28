package com.jpiser.hubclient.presentation.features.profile;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public interface ProfilePresenter {

    public interface ViewLayer{

    }


    void loadProfile();

    void bind(ViewLayer viewLayer);
}
