package com.jpiser.hubclient.presentation.application;

import android.app.Application;

import com.jpiser.hubclient.presentation.dagger.AppComponent;
import com.jpiser.hubclient.presentation.dagger.AppModule;
import com.jpiser.hubclient.presentation.dagger.DaggerAppComponent;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class HubClientApplication extends Application {

    private AppComponent appComponent;

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
}
