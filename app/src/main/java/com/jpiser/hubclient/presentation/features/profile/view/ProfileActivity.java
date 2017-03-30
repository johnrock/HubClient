package com.jpiser.hubclient.presentation.features.profile.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jpiser.hubclient.R;
import com.jpiser.hubclient.common.imaging.ImageHelper;
import com.jpiser.hubclient.presentation.application.HubClientApplication;
import com.jpiser.hubclient.presentation.features.issues.view.IssuesActivity;
import com.jpiser.hubclient.presentation.features.profile.presenter.ProfilePresenter;
import com.jpiser.hubclient.presentation.features.profile.model.OrganizationModel;
import com.jpiser.hubclient.presentation.features.profile.model.RepoModel;
import com.jpiser.hubclient.presentation.features.profile.model.UserProfileModel;
import com.jpiser.hubclient.presentation.imaging.ViewHelper;
import com.jpiser.hubclient.presentation.util.Extras;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class ProfileActivity extends AppCompatActivity implements ProfilePresenter.ViewLayer, ProfileRecyclerViewAdapter.RepoTapper {

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
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    private String userLogin;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ((HubClientApplication)getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Profile");
        }

        userLogin = getIntent().getStringExtra(Extras.USER_LOGIN);
        if(userLogin == null){
            finish();
        }

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        profilePresenter.bind(this);
        profilePresenter.initProfile(userLogin);

    }


    @Override
    public void displayProfile(UserProfileModel userProfileModel) {
        if(userProfileModel != null){
            imageHelper.loadImage(avatarImageView, userProfileModel.getAvatarUrl());
            nameTextView.setText(userProfileModel.getName());
            companyTextView.setText(userProfileModel.getCompany());
            locationTextView.setText(userProfileModel.getLocation());
            emailTextView.setText(userProfileModel.getEmail());
            blogTextView.setText(userProfileModel.getBlog());
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

    //TODO: Allow for pagination of repo instead of just showing the first page

    @Override
    public void displayRepos(List<RepoModel> repoModels) {
        if(repoModels != null && repoModels.size() > 0){
            ProfileRecyclerViewAdapter adapter = new ProfileRecyclerViewAdapter(this, repoModels);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onRepoTapped(RepoModel repoModel) {

        //TODO: For phase I tapping a repo will simply display a list of issues for that repo.
        if(repoModel != null){
            Intent intent = new Intent(this, IssuesActivity.class);
            intent.putExtra(Extras.REPO_NAME, repoModel.getName());
            intent.putExtra(Extras.USER_LOGIN, userLogin);
            startActivity(intent);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}