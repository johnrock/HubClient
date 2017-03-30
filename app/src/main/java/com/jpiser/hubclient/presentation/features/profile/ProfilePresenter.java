package com.jpiser.hubclient.presentation.features.profile;

import com.jpiser.hubclient.presentation.features.profile.model.OrganizationModel;
import com.jpiser.hubclient.presentation.features.profile.model.RepoModel;
import com.jpiser.hubclient.presentation.features.profile.model.UserProfile;

import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public interface ProfilePresenter {

    interface ViewLayer{

        void displayProfile(UserProfile userProfile);
        void displayOrganizations(List<OrganizationModel> organizationModels);
        void displayRepos(List<RepoModel> repoModels);
    }

    void bind(ViewLayer viewLayer);

    void initProfile(String userLogin);
}
