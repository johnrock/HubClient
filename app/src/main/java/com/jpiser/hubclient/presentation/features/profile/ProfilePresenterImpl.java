package com.jpiser.hubclient.presentation.features.profile;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class ProfilePresenterImpl implements  ProfilePresenter {

    private ViewLayer viewLayer;

    @Override
    public void loadProfile() {

    }

    @Override
    public void bind(ViewLayer viewLayer) {

        this.viewLayer = viewLayer;
    }
}
