package com.jpiser.hubclient.domain.github;

import com.jpiser.hubclient.data.github.GithubApiHelper;
import com.jpiser.hubclient.data.github.model.Organization;
import com.jpiser.hubclient.data.github.model.Profile;
import com.jpiser.hubclient.domain.HubApi;
import com.jpiser.hubclient.domain.HubOrganizationAdapter;
import com.jpiser.hubclient.domain.HubUserProfileAdapter;

import java.util.List;

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
    public void receiveProfile(Profile profile) {
        hubAccessor.receiveProfile(new HubUserProfileAdapter().adapt(profile));
    }

    @Override
    public void receiveOrganiztions(List<Organization> organizations) {
        hubAccessor.receiveOrganziations(new HubOrganizationAdapter().adapt(organizations));
    }
}
