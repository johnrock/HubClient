package com.jpiser.hubclient.presentation.dagger;

import com.jpiser.hubclient.presentation.features.main.MainPresenter;
import com.jpiser.hubclient.presentation.features.main.MainPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author John Piser johnpiser@yahoo.com
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    MainPresenter providesMainPresenter(){
        return new MainPresenterImpl();
    }

}
