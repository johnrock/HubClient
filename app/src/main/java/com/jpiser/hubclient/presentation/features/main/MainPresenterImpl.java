package com.jpiser.hubclient.presentation.features.main;

import javax.inject.Inject;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class MainPresenterImpl implements MainPresenter{

    @Inject MainUseCases mainUseCases;

    private ViewLayer viewLayer;

    @Override
    public void loadMenu() {
        viewLayer.displayMenu(mainUseCases.getMenuItems());
    }

    @Override
    public void bind(ViewLayer viewLayer) {

        this.viewLayer = viewLayer;
        viewLayer.getHubClientApplication().getAppComponent().inject(this);

    }
}
