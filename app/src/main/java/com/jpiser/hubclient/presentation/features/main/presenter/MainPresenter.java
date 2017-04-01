package com.jpiser.hubclient.presentation.features.main.presenter;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public interface MainPresenter {

    interface ViewLayer{

    }

    void bind(ViewLayer viewLayer);

    String getHubName();
}
