package com.jpiser.hubclient.presentation.features.profile.presenter;

import com.jpiser.hubclient.presentation.features.profile.model.OrganizationModel;
import com.jpiser.hubclient.presentation.features.profile.model.ProfileUseCases;
import com.jpiser.hubclient.presentation.features.profile.model.RepoModel;
import com.jpiser.hubclient.presentation.features.profile.model.UserProfileModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public class ProfilePresenterImplTest {

    public static final String TEST_USER_LOGIN = "TestUserLogin";
    ProfilePresenterImpl profilePresenter;

    @Mock
    ProfileUseCases profileUseCases;
    @Mock ProfilePresenter.ViewLayer viewLayer;
    @Mock
    UserProfileModel userProfileModel;
    @Mock List<OrganizationModel> organizationList;
    @Mock List<RepoModel> repoList;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        profilePresenter = new ProfilePresenterImpl(profileUseCases);
    }

    @Test
    public void shouldSetfieldsInConstructor(){
        assertEquals(profileUseCases, profilePresenter.profileUseCases);
    }

    @Test
    public void shouldBind(){
        profilePresenter.bind(viewLayer);

        assertEquals(viewLayer, profilePresenter.viewLayer);
        verify(profileUseCases).bind(profilePresenter);
    }

    @Test
    public void shouldLoadProfileDuringInitProfile(){
        profilePresenter.initProfile(TEST_USER_LOGIN);

        verify(profileUseCases).loadProfile(TEST_USER_LOGIN);
    }

    @Test
    public void shouldLoadReposDuringInitProfile(){
        profilePresenter.initProfile(TEST_USER_LOGIN);

        verify(profileUseCases).loadRepos(TEST_USER_LOGIN);
    }

    @Test
    public void shouldReceiveProfile(){
        profilePresenter.bind(viewLayer);
        profilePresenter.receiveProfile(userProfileModel);
        verify(viewLayer).displayProfile(userProfileModel);
    }

    @Test
    public void shouldReceiveOrganizations(){
        profilePresenter.bind(viewLayer);
        profilePresenter.receiveOrganziations(organizationList);
        verify(viewLayer).displayOrganizations(organizationList);
    }

    @Test
    public void shouldReceiveRepos(){
        profilePresenter.bind(viewLayer);
        profilePresenter.receiveRepos(repoList);
        verify(viewLayer).displayRepos(repoList);
    }

}