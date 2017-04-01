package com.jpiser.hubclient.presentation.features.main.presenter;

import com.jpiser.hubclient.domain.interactors.HubInteractor;

import javax.inject.Inject;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class MainPresenterImpl implements MainPresenter {

    HubInteractor hubInteractor;
    ViewLayer viewLayer;

    @Inject
    public MainPresenterImpl(HubInteractor hubInteractor) {
        this.hubInteractor = hubInteractor;
    }

    @Override
    public void bind(ViewLayer viewLayer) {
        this.viewLayer = viewLayer;
    }

    @Override
    public String getHubName() {
        return hubInteractor.getName();
    }
}
