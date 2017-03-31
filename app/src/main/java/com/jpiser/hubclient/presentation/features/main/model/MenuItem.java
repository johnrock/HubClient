package com.jpiser.hubclient.presentation.features.main.model;

import com.jpiser.hubclient.presentation.features.main.view.MainActivity;
import com.jpiser.hubclient.presentation.features.profile.view.ProfileActivity;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public enum MenuItem {

    PROFILE("UserProfileModel", ProfileActivity.class),
    REPOSITORIES("Repositories", MainActivity.class),
    ISSUES("Issues", MainActivity.class);

    private String name;
    private Class activityClass;

    MenuItem(String name, Class activityClass) {
        this.name = name;
        this.activityClass = activityClass;
    }

    public String getName() {
        return name;
    }

    public Class getActivityClass() {
        return activityClass;
    }
}
