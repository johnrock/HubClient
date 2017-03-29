package com.jpiser.hubclient.presentation.dagger;

import com.jpiser.hubclient.BuildConfig;
import com.jpiser.hubclient.common.logging.LogHelper;
import com.jpiser.hubclient.domain.HubApi;
import com.jpiser.hubclient.domain.github.GitHubApi;
import com.jpiser.hubclient.presentation.features.main.MainPresenter;
import com.jpiser.hubclient.presentation.features.main.MainPresenterImpl;
import com.jpiser.hubclient.presentation.features.main.MainUseCases;
import com.jpiser.hubclient.presentation.features.main.MainUseCasesImpl;
import com.jpiser.hubclient.presentation.features.profile.ProfilePresenter;
import com.jpiser.hubclient.presentation.features.profile.ProfilePresenterImpl;
import com.jpiser.hubclient.presentation.features.profile.ProfileUseCases;
import com.jpiser.hubclient.presentation.features.profile.ProfileUseCasesImpl;
import com.jpiser.hubclient.presentation.logging.LogHelperImpl;
import com.jpiser.hubclient.retrofit.github.RetrofitGithubApiHelper;

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
    LogHelper providesLogHelper(){
        return new LogHelperImpl(BuildConfig.DEBUG);
    }

    @Provides
    @Singleton
    MainPresenter providesMainPresenter(MainUseCases mainUseCases){
        return new MainPresenterImpl(mainUseCases);
    }

    @Provides
    @Singleton
    MainUseCases providesMainUseCases(){
        return new MainUseCasesImpl();
    }

    @Provides
    @Singleton
    ProfilePresenter providesProfilePresenter(ProfileUseCases profileUseCases){
        return new ProfilePresenterImpl(profileUseCases);
    }

    @Provides
    @Singleton
    ProfileUseCases providesProfileUseCases(HubApi hubApi){
        return new ProfileUseCasesImpl(hubApi);
    }

    @Provides
    @Singleton
    HubApi providesHubApi(LogHelper logHelper){
        //TODO: At runtime a user preference setting could be read to allow binding to some other HubApi implementation
        // To use another Http library other than Retrofit, just pass in a different GithubApiHelper implementation
        // and remove the dependency on the retrofit module in build.gradle
        return new GitHubApi(new RetrofitGithubApiHelper(logHelper));
    }





}
