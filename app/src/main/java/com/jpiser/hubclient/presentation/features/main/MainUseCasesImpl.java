package com.jpiser.hubclient.presentation.features.main;

import com.jpiser.hubclient.presentation.features.main.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class MainUseCasesImpl implements MainUseCases{
    @Override
    public List<MenuItem> getMenuItems() {

        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(MenuItem.PROFILE);
        menuItems.add(MenuItem.REPOSITORIES);
        menuItems.add(MenuItem.ISSUES);
        return menuItems;
    }
}
