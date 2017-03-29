package com.jpiser.hubclient.presentation.features.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jpiser.hubclient.R;
import com.jpiser.hubclient.presentation.application.HubClientApplication;
import com.jpiser.hubclient.presentation.features.profile.model.UserProfile;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class ProfileActivity extends AppCompatActivity implements ProfilePresenter.ViewLayer
{

    @Inject ProfilePresenter profilePresenter;

    @BindView(R.id.name) TextView nameTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ((HubClientApplication)getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);

        profilePresenter.bind(this);
        profilePresenter.loadProfile();

    }


    @Override
    public void displayProfile(UserProfile userProfile) {
        if(userProfile != null){
            nameTextView.setText(userProfile.getName());
        }
    }


}
