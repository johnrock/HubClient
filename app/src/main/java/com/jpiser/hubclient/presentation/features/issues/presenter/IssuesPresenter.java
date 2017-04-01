package com.jpiser.hubclient.presentation.features.issues.presenter;

import com.jpiser.hubclient.data.models.shared.Credentials;
import com.jpiser.hubclient.presentation.models.IssueModel;

import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public interface IssuesPresenter {

    interface ViewLayer{

        void displayIssues(List<IssueModel> issueModels);
        void displayHeading(String heading);
    }
    void bind(ViewLayer viewLayer, Credentials credentials);

    void createHeading(String userLogin, String repoName);

    void loadIssues(String ownerName, String repoName);

    void toggleIssueState(boolean open, String owner, String repoName);

    boolean getIssuesState();
}
