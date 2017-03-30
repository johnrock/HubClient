package com.jpiser.hubclient.presentation.features.issues.presenter;

import com.jpiser.hubclient.presentation.features.issues.model.IssueModel;
import com.jpiser.hubclient.presentation.features.issues.model.IssuesUseCases;

import java.util.List;

import javax.inject.Inject;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class IssuesPresenterImpl implements IssuesPresenter, IssuesUseCases.IssuesReceiver {

    public static final String SLASH = "/";
    ViewLayer viewLayer;
    IssuesUseCases issuesUseCases;

    @Inject
    public IssuesPresenterImpl(IssuesUseCases issuesUseCases) {
        this.issuesUseCases = issuesUseCases;
    }

    @Override
    public void bind(ViewLayer viewLayer) {
        this.viewLayer = viewLayer;
        issuesUseCases.bind(this);

    }

    @Override
    public void createHeading(String userLogin, String repoName) {
        StringBuilder builder = new StringBuilder(userLogin)
                .append(SLASH)
                .append(repoName);

        viewLayer.displayHeading(builder.toString());
    }

    @Override
    public void loadIssues(String ownerName, String repoName) {
        issuesUseCases.loadIssues(ownerName, repoName);
    }

    @Override
    public void receiveIssues(List<IssueModel> issueModels) {
        viewLayer.displayIssues(issueModels);
    }
}
