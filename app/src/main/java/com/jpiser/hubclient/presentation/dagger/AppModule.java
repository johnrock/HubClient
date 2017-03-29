package com.jpiser.hubclient.presentation.dagger;

import android.content.Context;

import com.jpiser.hubclient.BuildConfig;
import com.jpiser.hubclient.common.imaging.ImageHelper;
import com.jpiser.hubclient.common.logging.LogHelper;
import com.jpiser.hubclient.domain.HubApi;
import com.jpiser.hubclient.domain.github.GitHubApi;
import com.jpiser.hubclient.presentation.features.profile.ProfilePresenter;
import com.jpiser.hubclient.presentation.features.profile.ProfilePresenterImpl;
import com.jpiser.hubclient.presentation.features.profile.ProfileUseCases;
import com.jpiser.hubclient.presentation.features.profile.ProfileUseCasesImpl;
import com.jpiser.hubclient.presentation.logging.LogHelperImpl;
import com.jpiser.hubclient.retrofit.github.RetrofitGithubApiHelper;
import com.jpiser.picasso.PicassoImageHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author John Piser johnpiser@yahoo.com
 */
@Module
public class AppModule {

    private Context applicationContext;

    public AppModule(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Provides
    @Singleton
    LogHelper providesLogHelper(){
        return new LogHelperImpl(BuildConfig.DEBUG);
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

    @Provides
    @Singleton
    ImageHelper providesImageHelper(){
        return new PicassoImageHelper(applicationContext, false);
    }




}
