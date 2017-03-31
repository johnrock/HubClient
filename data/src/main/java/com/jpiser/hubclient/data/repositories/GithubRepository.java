package com.jpiser.hubclient.data.repositories;

import com.jpiser.hubclient.data.models.github.Issue;
import com.jpiser.hubclient.data.models.github.Organization;
import com.jpiser.hubclient.data.models.github.Profile;
import com.jpiser.hubclient.data.models.github.Repo;

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
    }

    void bind(RepositoryAccessor repositoryAccessor);

    void loadProfile(String userLogin);

    void loadRepos(String userLogin);

    void loadIssues(String ownerName, String repoName);
}
