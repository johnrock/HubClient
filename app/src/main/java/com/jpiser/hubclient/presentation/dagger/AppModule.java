package com.jpiser.hubclient.presentation.dagger;

import android.content.Context;

import com.jpiser.hubclient.BuildConfig;
import com.jpiser.hubclient.domain.helpers.ImageHelper;
import com.jpiser.hubclient.data.helpers.LogHelper;
import com.jpiser.hubclient.domain.interactors.HubInteractor;
import com.jpiser.hubclient.domain.interactors.GitHubInteractor;
import com.jpiser.hubclient.presentation.features.issue.presenter.IssuePresenter;
import com.jpiser.hubclient.presentation.features.issue.presenter.IssuePresenterImpl;
import com.jpiser.hubclient.presentation.features.issues.presenter.IssuesPresenter;
import com.jpiser.hubclient.presentation.features.issues.presenter.IssuesPresenterImpl;
import com.jpiser.hubclient.presentation.features.main.presenter.MainPresenter;
import com.jpiser.hubclient.presentation.features.main.presenter.MainPresenterImpl;
import com.jpiser.hubclient.presentation.features.profile.presenter.ProfilePresenter;
import com.jpiser.hubclient.presentation.features.profile.presenter.ProfilePresenterImpl;
import com.jpiser.hubclient.presentation.logging.LogHelperImpl;
import com.jpiser.hubclient.retrofit.github.RetrofitGithubRepository;
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
    ImageHelper providesImageHelper(){
        return new PicassoImageHelper(applicationContext, false);
    }

    @Provides
    @Singleton
    MainPresenter providesMainPresenter(HubInteractor hubInteractor){
        return new MainPresenterImpl(hubInteractor);
    }

    @Provides
    @Singleton
    ProfilePresenter providesProfilePresenter(HubInteractor hubInteractor){
        return new ProfilePresenterImpl(hubInteractor);
    }

    @Provides
    @Singleton
    IssuesPresenter providesIssuesPresenter(HubInteractor hubInteractor){
        return new IssuesPresenterImpl(hubInteractor);
    }

    @Provides
    @Singleton
    IssuePresenter providesIssuePresenter(HubInteractor hubInteractor){
        return new IssuePresenterImpl(hubInteractor);
    }



    @Provides
    @Singleton
    HubInteractor providesHubInteractor(LogHelper logHelper){
        //TODO: At runtime a user preference setting could be read to allow binding to some other HubInteractor implementation
        // To use another Http library other than Retrofit, just pass in a different GithubRepository implementation
        // and remove the dependency on the retrofit module in build.gradle
        return new GitHubInteractor(new RetrofitGithubRepository(logHelper));
    }

}
