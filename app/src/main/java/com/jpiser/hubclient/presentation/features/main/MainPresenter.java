package com.jpiser.hubclient.presentation.features.main;

import com.jpiser.hubclient.presentation.features.main.model.MenuItem;

import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public interface MainPresenter {

    interface ViewLayer{
        void displayMenu(List<MenuItem> menuItems);
    }

    void loadMenu();

    void bind(ViewLayer viewLayer);
}
