package com.jpiser.hubclient.data.repositories;

import com.jpiser.hubclient.data.models.github.Issue;
import com.jpiser.hubclient.data.models.github.Organization;
import com.jpiser.hubclient.data.models.github.Profile;
import com.jpiser.hubclient.data.models.github.Repo;
import com.jpiser.hubclient.data.models.shared.Credentials;

import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public interface GithubRepository {

    interface RepositoryAccessor {

        void receiveProfile(Profile profile);
        void receiveOrganiztions(List<Organization> organizations);
        void receiveRepos(List<Repo> repos);
        void receiveIssues(List<Issue> issues);
        void receiveIssue(Issue issue);
    }
    void bind(RepositoryAccessor repositoryAccessor);

    void loadProfile(String userLogin, Credentials credentials);

    void loadRepos(String userLogin, Credentials credentials);

    void loadIssues(String ownerName, String repoName, Credentials credentials);

    void createIssue(String repoName, Issue issue, Credentials credentials);

    void updateIssue(String repoName, Issue issue, Credentials credentials);
}
