package com.jpiser.hubclient.presentation.features.issue.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jpiser.hubclient.R;
import com.jpiser.hubclient.presentation.application.HubClientApplication;
import com.jpiser.hubclient.presentation.features.issue.presenter.IssuePresenter;
import com.jpiser.hubclient.presentation.models.IssueModel;
import com.jpiser.hubclient.presentation.models.IssueUserModel;
import com.jpiser.hubclient.presentation.util.Extras;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.GONE;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class IssueActivity extends AppCompatActivity implements IssuePresenter.ViewLayer {

    @Inject IssuePresenter issuePresenter;

    @BindView(R.id.heading)       TextView headingTextView;
    @BindView(R.id.newIssueTitle) TextView newIssueTitle;
    @BindView(R.id.newIssueBody)  TextView newIssueBody;

    @BindView(R.id.issueLayout)       RelativeLayout issueLayout;
    @BindView(R.id.createIssueLayout) LinearLayout createIssueLayout;

    @BindView(R.id.title) TextView  titleTextView;
    @BindView(R.id.state) TextView  stateTextView;
    @BindView(R.id.number) TextView numberTextView;
    @BindView(R.id.name) TextView   nameTextView;
    @BindView(R.id.body) TextView   bodyTextView;

    private IssueModel issueModel;
    private String repoName;
    private String userLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue);
        HubClientApplication application = (HubClientApplication) getApplication();

        application.getAppComponent().inject(this);
        ButterKnife.bind(this);

        repoName   = getIntent().getStringExtra(Extras.REPO_NAME);
        userLogin  = application.getCredentials().getUsername();
        issueModel = getIntent().getParcelableExtra(Extras.ISSUE);

        if(repoName == null || userLogin == null){
            finish();
        }

        initView();

        issuePresenter.bind(this, application.getCredentials());
        issuePresenter.createHeading(userLogin, repoName);
    }

    private void initView(){
        initActionBar();

        newIssueTitle.setText("");
        newIssueBody.setText("");

        ((HubClientApplication) getApplication()).getCredentials();
        if(issueModel != null){
            issueLayout.setVisibility(View.VISIBLE);
            titleTextView.setText(issueModel.getTitle());
            stateTextView.setText(issueModel.getState());
            numberTextView.setText(" " + issueModel.getNumberAsString());
            IssueUserModel issueUserModel = issueModel.getIssueUserModel();
            if(issueUserModel != null){
                nameTextView.setText(issueUserModel.getLogin());
            }
            bodyTextView.setText(issueModel.getBody());
        }
        else{
            issueLayout.setVisibility(GONE);
        }
        if(!((HubClientApplication) getApplication()).getCredentials().readOnly()){
            createIssueLayout.setVisibility(View.VISIBLE);
        }
        else{
            createIssueLayout.setVisibility(GONE);
        }
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            StringBuilder builder = new StringBuilder(getResources().getString(R.string.feature_issue_view_title ));
            actionBar.setDisplayHomeAsUpEnabled(true);
            if(issueModel != null){
                builder.append(" ").append(issueModel.getNumberAsString());
            }
            actionBar.setTitle(builder.toString());
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
    public void displayHeading(String heading) {
        headingTextView.setText(heading);
    }

    @Override
    public void displayIssue(IssueModel issueModel) {
        if(issueModel != null){
            this.issueModel = issueModel;
            initView();
        }
    }

    @OnClick(R.id.createIssueButton)
    public void createIssue(){
        CharSequence title = newIssueTitle.getText();
        CharSequence body = newIssueBody.getText();
        if(title != null && title.length() > 0 &&
            body != null && body.length() > 0    ){

            issuePresenter.createIssue(repoName, title.toString(), body.toString());
        }
        else{
            Toast.makeText(this, "Please enter a title and body for your issue", Toast.LENGTH_SHORT).show();
        }
    }
}
