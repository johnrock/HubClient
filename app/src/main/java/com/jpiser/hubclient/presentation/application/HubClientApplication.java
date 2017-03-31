package com.jpiser.hubclient.presentation.application;

import android.app.Application;
import android.support.annotation.NonNull;

import com.jpiser.hubclient.data.models.shared.Credentials;
import com.jpiser.hubclient.presentation.dagger.AppComponent;
import com.jpiser.hubclient.presentation.dagger.AppModule;
import com.jpiser.hubclient.presentation.dagger.DaggerAppComponent;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class HubClientApplication extends Application {

    private AppComponent appComponent;
    private Credentials credentials;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(getApplicationContext()))
                .build();
    }


    public AppComponent getAppComponent() {
        return appComponent;
    }

    @NonNull
    public Credentials getCredentials() {
        return credentials != null ? credentials : Credentials.empty();
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public void resetCredentials() {
        setCredentials(null);
    }
}
