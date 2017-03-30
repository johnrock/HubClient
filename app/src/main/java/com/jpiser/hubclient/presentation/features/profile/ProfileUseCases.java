package com.jpiser.hubclient.presentation.features.profile;

import com.jpiser.hubclient.presentation.features.profile.model.OrganizationModel;
import com.jpiser.hubclient.presentation.features.profile.model.RepoModel;
import com.jpiser.hubclient.presentation.features.profile.model.UserProfileModel;

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
