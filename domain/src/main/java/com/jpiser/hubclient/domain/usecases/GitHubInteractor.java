package com.jpiser.hubclient.domain.usecases;

import com.jpiser.hubclient.data.models.shared.Credentials;
import com.jpiser.hubclient.data.repositories.GithubRepository;
import com.jpiser.hubclient.data.models.github.Issue;
import com.jpiser.hubclient.data.models.github.Organization;
import com.jpiser.hubclient.data.models.github.Profile;
import com.jpiser.hubclient.data.models.github.Repo;
import com.jpiser.hubclient.domain.interactors.HubInteractor;
import com.jpiser.hubclient.domain.models.HubIssueAdapter;
import com.jpiser.hubclient.domain.models.HubOrganizationAdapter;
import com.jpiser.hubclient.domain.models.HubRepoAdapter;
import com.jpiser.hubclient.domain.models.HubUserProfileAdapter;

import java.util.List;

public class GitHubInteractor implements HubInteractor, GithubRepository.RepositoryAccessor {

    HubAccessor hubAccessor;
    GithubRepository githubRepository;

    public GitHubInteractor(GithubRepository githubRepository) {
        this.githubRepository = githubRepository;
    }


    @Override
    public void bind(HubAccessor hubAccessor) {
        this.hubAccessor = hubAccessor;
        githubRepository.bind(this);
    }

    @Override
    public void loadProfile(String userLogin, Credentials credentials) {
        if(githubRepository != null){
            githubRepository.loadProfile(userLogin, credentials);
        }
    }

    @Override
    public void loadRepos(String userLogin, Credentials credentials) {
        if(githubRepository != null){
            githubRepository.loadRepos(userLogin, credentials);
        }
    }

    @Override
    public void loadIssues(String ownerName, String repoName, Credentials credentials) {
        if(githubRepository != null){
            githubRepository.loadIssues(ownerName, repoName, credentials);
        }
    }

    @Override
    public void createIssue(String title, String body, String repoName, Credentials credentials) {
        if(githubRepository != null){
            githubRepository.createIssue(repoName, title, body, credentials);
        }
    }

    @Override
    public void receiveProfile(Profile profile) {
        if(hubAccessor != null){
            hubAccessor.receiveProfile(new HubUserProfileAdapter().adapt(profile));
        }
    }

    @Override
    public void receiveOrganiztions(List<Organization> organizations) {
        if(hubAccessor != null){
            hubAccessor.receiveOrganziations(new HubOrganizationAdapter().adapt(organizations));
        }
    }

    @Override
    public void receiveRepos(List<Repo> repos) {
        if(hubAccessor != null){
            hubAccessor.receiveRepos(new HubRepoAdapter().adapt(repos));
        }
    }

    @Override
    public void receiveIssues(List<Issue> issues) {
        if(hubAccessor != null){
            hubAccessor.receiveIssues(new HubIssueAdapter().adapt(issues));
        }
    }

    @Override
    public void receiveIssue(Issue issue) {
        if(hubAccessor != null){
            hubAccessor.receiveIssue(new HubIssueAdapter().adapt(issue));
        }
    }
}
