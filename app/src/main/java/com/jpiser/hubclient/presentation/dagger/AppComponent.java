package com.jpiser.hubclient.presentation.dagger;

import com.jpiser.hubclient.presentation.features.main.MainActivity;
import com.jpiser.hubclient.presentation.features.profile.ProfileActivity;
import com.jpiser.hubclient.presentation.features.profile.ProfilePresenterImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author John Piser johnpiser@yahoo.com
 */
@Singleton
@Component(modules={AppModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);
    void inject(ProfileActivity profileActivity);
    void inject(ProfilePresenterImpl profilePresenterImpl);
}
