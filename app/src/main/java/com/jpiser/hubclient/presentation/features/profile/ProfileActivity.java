package com.jpiser.hubclient.presentation.features.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jpiser.hubclient.R;
import com.jpiser.hubclient.presentation.application.HubClientApplication;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class ProfileActivity extends AppCompatActivity implements ProfilePresenter.ViewLayer
{

    @Inject ProfilePresenter profilePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ((HubClientApplication)getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);

        profilePresenter.bind(this);
        profilePresenter.loadProfile();

    }
}
