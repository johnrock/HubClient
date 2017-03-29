package com.jpiser.hubclient.presentation.features.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jpiser.hubclient.R;
import com.jpiser.hubclient.common.imaging.ImageHelper;
import com.jpiser.hubclient.presentation.application.HubClientApplication;
import com.jpiser.hubclient.presentation.features.profile.model.OrganizationModel;
import com.jpiser.hubclient.presentation.features.profile.model.UserProfile;
import com.jpiser.hubclient.presentation.imaging.ViewHelper;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class ProfileActivity extends AppCompatActivity implements ProfilePresenter.ViewLayer
{

    @Inject ProfilePresenter profilePresenter;
    @Inject ImageHelper imageHelper;
    @Inject ViewHelper viewHelper;

    @BindView(R.id.name)   TextView nameTextView;
    @BindView(R.id.company)   TextView companyTextView;
    @BindView(R.id.location)   TextView locationTextView;
    @BindView(R.id.email)   TextView emailTextView;
    @BindView(R.id.blog)   TextView blogTextView;

    @BindView(R.id.avatar) ImageView avatarImageView;
    @BindView(R.id.organizationLayout) LinearLayout organizationLayout;

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
            imageHelper.loadImage(avatarImageView, userProfile.getAvatarUrl());
            nameTextView.setText(userProfile.getName());
            companyTextView.setText(userProfile.getCompany());
            locationTextView.setText(userProfile.getLocation());
            emailTextView.setText(userProfile.getEmail());
            blogTextView.setText(userProfile.getBlog());
        }
    }

    @Override
    public void displayOrganizations(List<OrganizationModel> organizationModels) {
        if(organizationModels != null && organizationModels.size() > 0){

            for (OrganizationModel organizationModel : organizationModels) {

                ImageView imageView = createOrganizationImageView();
                imageHelper.loadImage(imageView, organizationModel.getAvatarUrl());
                organizationLayout.addView(imageView);
            }

        }
    }

    @NonNull
    private ImageView createOrganizationImageView() {
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                                                LinearLayout.LayoutParams.WRAP_CONTENT));
        int size = (int) viewHelper.convertDpToPx(this, 40);
        imageView.getLayoutParams().height = size;
        imageView.getLayoutParams().width  = size;

        int pad = (int) viewHelper.convertDpToPx(this, 4);
        imageView.setPadding(pad,pad,pad,pad);
        return imageView;


    }


}
