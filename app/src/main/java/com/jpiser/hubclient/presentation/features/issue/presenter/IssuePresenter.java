package com.jpiser.hubclient.presentation.features.issue.presenter;

import com.jpiser.hubclient.data.models.shared.Credentials;
import com.jpiser.hubclient.presentation.models.IssueModel;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public interface IssuePresenter {

    interface ViewLayer{

        void displayHeading(String heading);
        void displayIssue(IssueModel issueModel);
        void updateIssueStateButton(boolean issueIsOpen);
    }

    void bind(ViewLayer viewLayer, Credentials credentials);

    void createHeading(String userLogin, String repoName);

    void createIssue(String repoName, String title, String body);

    void updateIssueBody(String repoName, IssueModel issueModel, String newBody);

    void toggleIssueState(String repoName, IssueModel issueModel);

    void resolveIssueStateButton(IssueModel issueModel);

}
