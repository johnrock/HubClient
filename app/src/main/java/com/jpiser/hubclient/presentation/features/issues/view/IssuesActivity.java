package com.jpiser.hubclient.presentation.features.issues.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.jpiser.hubclient.R;
import com.jpiser.hubclient.presentation.application.HubClientApplication;
import com.jpiser.hubclient.presentation.features.issues.model.IssueModel;
import com.jpiser.hubclient.presentation.features.issues.presenter.IssuesPresenter;
import com.jpiser.hubclient.presentation.util.Extras;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jpiser.hubclient.R.id.heading;

/**
 * @author John Piser johnpiser@yahoo.com
 *
 * Activity to display the
 */

public class IssuesActivity extends AppCompatActivity implements IssuesPresenter.ViewLayer {

    @Inject IssuesPresenter issuesPresenter;

    @BindView(R.id.recyclerView)  RecyclerView recyclerView;
    @BindView(heading)       TextView headingTextView;
    @BindView(R.id.statusMessage) TextView statusMessage;

    LinearLayoutManager linearLayoutManager;
    private String repoName;
    private String userLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issues);

        ((HubClientApplication)getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


        repoName = getIntent().getStringExtra(Extras.REPO_NAME);
        userLogin = getIntent().getStringExtra(Extras.USER_LOGIN);
        if(repoName == null || userLogin == null){
            finish();
        }

        initActionBar();

        issuesPresenter.bind(this);
        issuesPresenter.createHeading(userLogin, repoName);
        issuesPresenter.loadIssues(userLogin, repoName);
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.feature_issues_view_title);
        }
    }

    private void toggleStatusMessage(boolean visible) {
        if(visible){
            statusMessage.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        else{
            statusMessage.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
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

    @Override
    public void displayIssues(List<IssueModel> issueModels) {
        if(issueModels != null && issueModels.size() > 0){
            toggleStatusMessage(false);
            IssuesRecyclerViewAdapter adapter = new IssuesRecyclerViewAdapter(issueModels);
            recyclerView.setAdapter(adapter);
        }
        else{
            toggleStatusMessage(true);
        }
    }

    @Override
    public void displayHeading(String heading) {
        headingTextView.setText(heading);
    }
}
