package com.jpiser.hubclient.presentation.features.main;

import com.jpiser.hubclient.presentation.features.main.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class MainPresenterImpl implements MainPresenter{

    private ViewLayer viewLayer;

    @Override
    public void loadMenu() {

        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(MenuItem.PROFILE);
        menuItems.add(MenuItem.REPOSITORIES);
        menuItems.add(MenuItem.ISSUES);

        viewLayer.displayMenu(menuItems);
    }

    @Override
    public void bind(ViewLayer viewLayer) {

        this.viewLayer = viewLayer;
    }
}
