package com.jpiser.hubclient.domain.interactors;

import com.jpiser.hubclient.data.models.github.GithubIssue;
import com.jpiser.hubclient.data.models.github.GithubOrganization;
import com.jpiser.hubclient.data.models.github.GithubProfile;
import com.jpiser.hubclient.data.models.github.GithubRepo;
import com.jpiser.hubclient.data.models.shared.Credentials;
import com.jpiser.hubclient.data.repositories.GithubRepository;
import com.jpiser.hubclient.domain.models.HubIssue;
import com.jpiser.hubclient.domain.models.HubIssueAdapter;
import com.jpiser.hubclient.domain.models.HubOrganizationAdapter;
import com.jpiser.hubclient.domain.models.HubRepoAdapter;
import com.jpiser.hubclient.domain.models.HubUserProfileAdapter;

import java.util.List;

public class GitHubInteractor implements HubInteractor, GithubRepository.RepositoryAccessor {

    public static final String HUB_NAME = "GitHub";

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
    public String getName() {
        return HUB_NAME;
    }

    @Override
    public void loadProfile(String userLogin, Credentials credentials) {
        if(githubRepository != null){
            githubRepository.loadProfile(userLogin, credentials);
        }
    }

    @Override
    public void loadOrganizations(String userLogin) {
        if(githubRepository != null){
            githubRepository.loadOrganizations(userLogin);
        }
    }


    @Override
    public void loadRepos(String userLogin, Credentials credentials) {
        if(githubRepository != null){
            githubRepository.loadRepos(userLogin, credentials);
        }
    }

    @Override
    public void loadIssues(String ownerName, String repoName, Credentials credentials, String state) {
        if(githubRepository != null){
            githubRepository.loadIssues(ownerName, repoName, credentials, state);
        }
    }

    @Override
    public void createIssue(String repoName, HubIssue hubIssue, Credentials credentials) {
        if(githubRepository != null){
            githubRepository.createIssue(repoName, new HubIssueAdapter().adapt(hubIssue), credentials);
        }
    }

    @Override
    public void updateIssue(String repoName, HubIssue hubIssue, Credentials credentials) {
        if(githubRepository !=  null){
            githubRepository.updateIssue(repoName, new HubIssueAdapter().adapt(hubIssue) ,credentials);
        }
    }


    @Override
    public void receiveProfile(GithubProfile githubProfile) {
        if(hubAccessor != null){
            hubAccessor.receiveProfile(new HubUserProfileAdapter().adapt(githubProfile));
        }
    }

    @Override
    public void receiveOrganiztions(List<GithubOrganization> githubOrganizations) {
        if(hubAccessor != null){
            hubAccessor.receiveOrganziations(new HubOrganizationAdapter().adapt(githubOrganizations));
        }
    }

    @Override
    public void receiveRepos(List<GithubRepo> githubRepos) {
        if(hubAccessor != null){
            hubAccessor.receiveRepos(new HubRepoAdapter().adapt(githubRepos));
        }
    }

    @Override
    public void receiveIssues(List<GithubIssue> githubIssues) {
        if(hubAccessor != null){
            hubAccessor.receiveIssues(new HubIssueAdapter().adapt(githubIssues));
        }
    }

    @Override
    public void receiveIssue(GithubIssue githubIssue) {
        if(hubAccessor != null){
            hubAccessor.receiveIssue(new HubIssueAdapter().adapt(githubIssue));
        }
    }
}
