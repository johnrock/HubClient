package com.jpiser.hubclient.presentation.features.issues.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jpiser.hubclient.R;
import com.jpiser.hubclient.data.models.shared.Credentials;
import com.jpiser.hubclient.presentation.application.HubClientApplication;
import com.jpiser.hubclient.presentation.features.issue.view.IssueActivity;
import com.jpiser.hubclient.presentation.models.IssueModel;
import com.jpiser.hubclient.presentation.features.issues.presenter.IssuesPresenter;
import com.jpiser.hubclient.presentation.util.Extras;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.GONE;
import static com.jpiser.hubclient.R.id.heading;

/**
 * @author John Piser johnpiser@yahoo.com
 *
 * Activity to display the
 */

public class IssuesActivity extends AppCompatActivity implements IssuesPresenter.ViewLayer, IssuesRecyclerViewAdapter.IssueTapper {

    @Inject IssuesPresenter issuesPresenter;

    @BindView(R.id.recyclerView)      RecyclerView recyclerView;
    @BindView(heading)                TextView headingTextView;
    @BindView(R.id.statusMessage)     TextView statusMessage;
    @BindView(R.id.createIssueButton) Button createIssueButton;

    LinearLayoutManager linearLayoutManager;
    private String repoName;
    private String userLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issues);

        HubClientApplication application = (HubClientApplication) getApplication();
        application.getAppComponent().inject(this);
        ButterKnife.bind(this);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        repoName = getIntent().getStringExtra(Extras.REPO_NAME);
        userLogin = application.getCredentials().getUsername();

        if(repoName == null || userLogin == null){
            finish();
        }

        initActionBar();
        initView(application.getCredentials());

        issuesPresenter.bind(this, application.getCredentials());
        issuesPresenter.createHeading(userLogin, repoName);
        issuesPresenter.loadIssues(userLogin, repoName);
    }

    private void initView(Credentials credentials) {
        if(!credentials.readOnly()){
            createIssueButton.setVisibility(View.VISIBLE);
        }
        else{
            createIssueButton.setVisibility(GONE);
        }
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
            recyclerView.setVisibility(GONE);
        }
        else{
            statusMessage.setVisibility(GONE);
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
            IssuesRecyclerViewAdapter adapter = new IssuesRecyclerViewAdapter(issueModels, this);
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

    @OnClick(R.id.createIssueButton)
    public void createIssue(){
        Intent intent = new Intent(this, IssueActivity.class);
        intent.putExtra(Extras.REPO_NAME, repoName);
        startActivity(intent);
    }

    @Override
    public void loadIssue(IssueModel issueModel) {
        Intent intent = new Intent(this, IssueActivity.class);
        intent.putExtra(Extras.ISSUE, issueModel);
        intent.putExtra(Extras.REPO_NAME, repoName);
        startActivity(intent);
    }
}
