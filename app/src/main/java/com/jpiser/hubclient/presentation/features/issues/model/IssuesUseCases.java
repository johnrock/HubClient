package com.jpiser.hubclient.presentation.features.issues.model;

import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public interface IssuesUseCases {

    interface IssuesReceiver{
        void receiveIssues(List<IssueModel> issueModels);
    }

    void bind(IssuesReceiver issuesReceiver);
    void loadIssues(String ownerName, String repoName);
}
