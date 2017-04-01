package com.jpiser.hubclient.presentation.features.profile.presenter;

import com.jpiser.hubclient.data.models.shared.Credentials;
import com.jpiser.hubclient.presentation.models.OrganizationModel;
import com.jpiser.hubclient.presentation.models.RepoModel;
import com.jpiser.hubclient.presentation.models.UserProfileModel;

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
