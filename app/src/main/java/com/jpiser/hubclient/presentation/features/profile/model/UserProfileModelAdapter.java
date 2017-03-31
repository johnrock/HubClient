package com.jpiser.hubclient.presentation.features.profile.model;

import com.jpiser.hubclient.domain.models.HubUserProfile;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public class UserProfileModelAdapter {

    public UserProfileModel adapt(HubUserProfile p){

        if(p != null){

            UserProfileModel userProfileModel = new UserProfileModel();

            userProfileModel.setLogin(p.getLogin());
            userProfileModel.setId(p.getId());
            userProfileModel.setAvatarUrl(p.getAvatarUrl());
            userProfileModel.setUrl(p.getUrl());
            userProfileModel.setHtmlUrl(p.getHtmlUrl());
            userProfileModel.setReposUrl(p.getReposUrl());
            userProfileModel.setOrganizationsUrl(p.getOrganizationsUrl());
            userProfileModel.setName(p.getName());
            userProfileModel.setSiteAdmin(p.isSiteAdmin());
            userProfileModel.setCompany(p.getCompany());
            userProfileModel.setBlog(p.getBlog());
            userProfileModel.setLocation(p.getLocation());
            userProfileModel.setEmail(p.getEmail());
            userProfileModel.setBio(p.getBio());
            userProfileModel.setPublicRepos(p.getPublicRepos());
            userProfileModel.setPublicGists(p.getPublicGists());
            userProfileModel.setFollowers(p.getFollowers());
            userProfileModel.setFollowing(p.getFollowing());
            userProfileModel.setCreatedAt(p.getCreatedAt());
            userProfileModel.setUpdatedAt(p.getUpdatedAt());

            return userProfileModel;
        }

        return null;
    }
}
