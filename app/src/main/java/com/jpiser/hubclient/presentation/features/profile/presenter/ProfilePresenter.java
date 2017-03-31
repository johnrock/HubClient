package com.jpiser.hubclient.presentation.features.profile.presenter;

import com.jpiser.hubclient.data.models.shared.Credentials;
import com.jpiser.hubclient.presentation.features.profile.model.OrganizationModel;
import com.jpiser.hubclient.presentation.features.profile.model.RepoModel;
import com.jpiser.hubclient.presentation.features.profile.model.UserProfileModel;

import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public interface ProfilePresenter {

    interface ViewLayer{

        void displayProfile(UserProfileModel userProfileModel);
        void displayOrganizations(List<OrganizationModel> organizationModels);
        void displayRepos(List<RepoModel> repoModels);
    }

    void bind(ViewLayer viewLayer, Credentials credentials);

    void initProfile(String userLogin);
}
