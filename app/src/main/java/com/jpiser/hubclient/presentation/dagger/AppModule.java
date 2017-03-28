package com.jpiser.hubclient.presentation.dagger;

import com.jpiser.hubclient.presentation.features.main.MainPresenter;
import com.jpiser.hubclient.presentation.features.main.MainPresenterImpl;
import com.jpiser.hubclient.presentation.features.main.MainUseCases;
import com.jpiser.hubclient.presentation.features.main.MainUseCasesImpl;
import com.jpiser.hubclient.presentation.features.profile.ProfilePresenter;
import com.jpiser.hubclient.presentation.features.profile.ProfilePresenterImpl;

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

    @Provides
    @Singleton
    MainUseCases providesMainUseCases(){
        return new MainUseCasesImpl();
    }

    @Provides
    @Singleton
    ProfilePresenter providesProfilePresenter(){
        return new ProfilePresenterImpl();
    }



}
