package com.jpiser.hubclient.domain.github;

import com.jpiser.hubclient.data.github.GithubApiHelper;
import com.jpiser.hubclient.data.github.model.Issue;
import com.jpiser.hubclient.data.github.model.Organization;
import com.jpiser.hubclient.data.github.model.Profile;
import com.jpiser.hubclient.data.github.model.Repo;
import com.jpiser.hubclient.domain.HubApi;
import com.jpiser.hubclient.domain.model.HubIssueAdapter;
import com.jpiser.hubclient.domain.model.HubOrganizationAdapter;
import com.jpiser.hubclient.domain.model.HubRepoAdapter;
import com.jpiser.hubclient.domain.model.HubUserProfileAdapter;

import java.util.List;

public class GitHubApi implements HubApi, GithubApiHelper.GithubApiAccessor {

    HubAccessor hubAccessor;
    GithubApiHelper githubApiHelper;

    public GitHubApi(GithubApiHelper githubApiHelper) {
        this.githubApiHelper = githubApiHelper;
    }


    @Override
    public void bind(HubAccessor hubAccessor) {
        this.hubAccessor = hubAccessor;
        githubApiHelper.bind(this);
    }

    @Override
    public void loadProfile(String userLogin) {
        if(githubApiHelper != null){
            githubApiHelper.loadProfile(userLogin);
        }
    }

    @Override
    public void loadRepos(String userLogin) {
        if(githubApiHelper != null){
            githubApiHelper.loadRepos(userLogin);
        }
    }

    @Override
    public void loadIssues(String ownerName, String repoName) {
        if(githubApiHelper != null){
            githubApiHelper.loadIssues(ownerName, repoName);
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
}
