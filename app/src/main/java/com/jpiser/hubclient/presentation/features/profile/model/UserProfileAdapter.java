package com.jpiser.hubclient.presentation.features.profile.model;

import com.jpiser.hubclient.domain.model.HubUserProfile;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public class UserProfileAdapter {

    public UserProfile adapt(HubUserProfile p){

        if(p != null){

            UserProfile userProfile = new UserProfile();

            userProfile.setLogin(p.getLogin());
            userProfile.setId(p.getId());
            userProfile.setAvatarUrl(p.getAvatarUrl());
            userProfile.setUrl(p.getUrl());
            userProfile.setHtmlUrl(p.getHtmlUrl());
            userProfile.setReposUrl(p.getReposUrl());
            userProfile.setOrganizationsUrl(p.getOrganizationsUrl());
            userProfile.setName(p.getName());
            userProfile.setSiteAdmin(p.isSiteAdmin());
            userProfile.setCompany(p.getCompany());
            userProfile.setBlog(p.getBlog());
            userProfile.setLocation(p.getLocation());
            userProfile.setEmail(p.getEmail());
            userProfile.setBio(p.getBio());
            userProfile.setPublicRepos(p.getPublicRepos());
            userProfile.setPublicGists(p.getPublicGists());
            userProfile.setFollowers(p.getFollowers());
            userProfile.setFollowing(p.getFollowing());
            userProfile.setCreatedAt(p.getCreatedAt());
            userProfile.setUpdatedAt(p.getUpdatedAt());

            return userProfile;
        }

        return null;
    }
}
