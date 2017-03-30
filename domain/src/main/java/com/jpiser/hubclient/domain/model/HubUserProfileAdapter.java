package com.jpiser.hubclient.domain.model;

import com.jpiser.hubclient.data.github.model.Profile;
import com.jpiser.hubclient.domain.model.HubUserProfile;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public class HubUserProfileAdapter {

    public HubUserProfile adapt(Profile profile) {
        if(profile != null){
            HubUserProfile hubUserProfile = new HubUserProfile();
            hubUserProfile.setLogin(profile.getLogin());
            hubUserProfile.setId(profile.getId());
            hubUserProfile.setAvatarUrl(profile.getAvatarUrl());
            hubUserProfile.setUrl(profile.getUrl());
            hubUserProfile.setHtmlUrl(profile.getHtmlUrl());
            hubUserProfile.setReposUrl(profile.getReposUrl());
            hubUserProfile.setOrganizationsUrl(profile.getOrganizationsUrl());
            hubUserProfile.setName(profile.getName());
            hubUserProfile.setSiteAdmin(profile.isSiteAdmin());
            hubUserProfile.setCompany(profile.getCompany());
            hubUserProfile.setBlog(profile.getBlog());
            hubUserProfile.setLocation(profile.getLocation());
            hubUserProfile.setEmail(profile.getEmail());
            hubUserProfile.setBio(profile.getBio());
            hubUserProfile.setPublicRepos(profile.getPublicRepos());
            hubUserProfile.setPublicGists(profile.getPublicGists());
            hubUserProfile.setFollowers(profile.getFollowers());
            hubUserProfile.setFollowing(profile.getFollowing());
            hubUserProfile.setCreatedAt(profile.getCreatedAt());
            hubUserProfile.setUpdatedAt(profile.getUpdatedAt());

            return hubUserProfile;
        }
        return null;
    }
}
