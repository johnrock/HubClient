package com.jpiser.hubclient.domain.github;

import com.jpiser.hubclient.data.github.GithubApiHelper;
import com.jpiser.hubclient.domain.HubApi;

public class GitHubApi implements HubApi, GithubApiHelper.GithubApiAccessor {

    HubAccessor hubAccessor;
    GithubApiHelper githubApiHelper;

    public GitHubApi(GithubApiHelper githubApiHelper) {
        this.githubApiHelper = githubApiHelper;
    }

    @Override
    public void loadProfile(HubAccessor hubAccessor) {
        this.hubAccessor = hubAccessor;

        githubApiHelper.loadProfile(this);
    }

    @Override
    public void receiveProfile() {
        //hubAccessor.receiveProfile();
    }
}
