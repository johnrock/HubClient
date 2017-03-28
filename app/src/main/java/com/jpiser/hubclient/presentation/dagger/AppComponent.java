package com.jpiser.hubclient.presentation.dagger;

import com.jpiser.hubclient.presentation.features.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author John Piser johnpiser@yahoo.com
 */
@Singleton
@Component(modules={AppModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);
}
