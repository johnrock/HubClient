package com.jpiser.hubclient.data.repositories;

import com.jpiser.hubclient.data.models.github.GithubIssue;
import com.jpiser.hubclient.data.models.github.GithubOrganization;
import com.jpiser.hubclient.data.models.github.GithubProfile;
import com.jpiser.hubclient.data.models.github.GithubRepo;
import com.jpiser.hubclient.data.models.shared.Credentials;

import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public interface GithubRepository {

    interface RepositoryAccessor {

        void receiveProfile(GithubProfile githubProfile);
        void receiveOrganiztions(List<GithubOrganization> githubOrganizations);
        void receiveRepos(List<GithubRepo> githubRepos);
        void receiveIssues(List<GithubIssue> githubIssues);
        void receiveIssue(GithubIssue githubIssue);
    }
    void bind(RepositoryAccessor repositoryAccessor);

    void loadProfile(String userLogin, Credentials credentials);

    void loadOrganizations(String userLogin);

    void loadRepos(String userLogin, Credentials credentials);

    void loadIssues(String ownerName, String repoName, Credentials credentials, String state);

    void createIssue(String repoName, GithubIssue githubIssue, Credentials credentials);

    void updateIssue(String repoName, GithubIssue githubIssue, Credentials credentials);
}
