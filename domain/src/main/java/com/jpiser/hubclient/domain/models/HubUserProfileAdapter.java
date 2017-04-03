package com.jpiser.hubclient.domain.models;

import com.jpiser.hubclient.data.models.github.GithubProfile;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public class HubUserProfileAdapter {

    public HubUserProfile adapt(GithubProfile githubProfile) {
        if(githubProfile != null){
            HubUserProfile hubUserProfile = new HubUserProfile();
            hubUserProfile.setLogin(githubProfile.getLogin());
            hubUserProfile.setId(githubProfile.getId());
            hubUserProfile.setAvatarUrl(githubProfile.getAvatarUrl());
            hubUserProfile.setUrl(githubProfile.getUrl());
            hubUserProfile.setHtmlUrl(githubProfile.getHtmlUrl());
            hubUserProfile.setReposUrl(githubProfile.getReposUrl());
            hubUserProfile.setOrganizationsUrl(githubProfile.getOrganizationsUrl());
            hubUserProfile.setName(githubProfile.getName());
            hubUserProfile.setSiteAdmin(githubProfile.isSiteAdmin());
            hubUserProfile.setCompany(githubProfile.getCompany());
            hubUserProfile.setBlog(githubProfile.getBlog());
            hubUserProfile.setLocation(githubProfile.getLocation());
            hubUserProfile.setEmail(githubProfile.getEmail());
            hubUserProfile.setBio(githubProfile.getBio());
            hubUserProfile.setPublicRepos(githubProfile.getPublicRepos());
            hubUserProfile.setPublicGists(githubProfile.getPublicGists());
            hubUserProfile.setFollowers(githubProfile.getFollowers());
            hubUserProfile.setFollowing(githubProfile.getFollowing());
            hubUserProfile.setCreatedAt(githubProfile.getCreatedAt());
            hubUserProfile.setUpdatedAt(githubProfile.getUpdatedAt());

            return hubUserProfile;
        }
        return null;
    }
}
