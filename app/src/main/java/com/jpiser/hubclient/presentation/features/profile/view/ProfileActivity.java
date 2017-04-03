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
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jpiser.hubclient.R;
import com.jpiser.hubclient.domain.helpers.ImageHelper;
import com.jpiser.hubclient.presentation.application.HubClientApplication;
import com.jpiser.hubclient.presentation.features.issues.view.IssuesActivity;
import com.jpiser.hubclient.presentation.features.profile.presenter.ProfilePresenter;
import com.jpiser.hubclient.presentation.helpers.ViewHelper;
import com.jpiser.hubclient.presentation.models.OrganizationModel;
import com.jpiser.hubclient.presentation.models.RepoModel;
import com.jpiser.hubclient.presentation.models.UserProfileModel;
import com.jpiser.hubclient.presentation.util.Extras;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author John Piser johnpiser@yahoo.com
 */

//TODO: Implement pagination of repos: currently only the first page is displayed

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
    @BindView(R.id.progressBar)  ProgressBar progressBar;

    private String userLogin;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        HubClientApplication application = (HubClientApplication) getApplication();
        application.getAppComponent().inject(this);
        ButterKnife.bind(this);

        userLogin = application.getCredentials().getUsername();
        if(userLogin == null){
            finish();
        }

        initActionBar();

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        profilePresenter.bind(this, application.getCredentials());
        profilePresenter.initProfile(userLogin);
    }


    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.feature_profile_view_title);
        }
    }


    @Override
    public void displayProfile(UserProfileModel userProfileModel) {
        if(userProfileModel != null){
            progressBar.setVisibility(View.GONE);
            imageHelper.loadImage(avatarImageView, userProfileModel.getAvatarUrl());
            nameTextView.setText(userProfileModel.getName());
            companyTextView.setText(userProfileModel.getCompany());
            locationTextView.setText(userProfileModel.getLocation());
            emailTextView.setText(userProfileModel.getEmail());
            blogTextView.setText(userProfileModel.getBlog());
        }
        else{
            Toast.makeText(this, R.string.feature_profile_message_profile_not_found, Toast.LENGTH_SHORT).show();
            finish();
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

    @Override
    public void displayRepos(List<RepoModel> repoModels) {
        if(repoModels != null && repoModels.size() > 0){
            ProfileRecyclerViewAdapter adapter = new ProfileRecyclerViewAdapter(this, repoModels);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onRepoTapped(RepoModel repoModel) {
        if(repoModel != null){
            Intent intent = new Intent(this, IssuesActivity.class);
            intent.putExtra(Extras.REPO_NAME, repoModel.getName());
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
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}