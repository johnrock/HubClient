package com.jpiser.hubclient.presentation.features.profile.model;

import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public interface ProfileUseCases {

    interface ProfileReceiver{

        void receiveProfile(UserProfileModel userProfileModel);
        void receiveOrganziations(List<OrganizationModel> organizationModels);
        void receiveRepos(List<RepoModel> repoModels);
    }

    void bind(ProfileReceiver profileReceiver);

    void loadProfile(String userLogin);

    void loadRepos(String userLogin);
}
