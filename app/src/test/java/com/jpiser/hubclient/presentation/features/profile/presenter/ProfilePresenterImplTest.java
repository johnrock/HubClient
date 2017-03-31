package com.jpiser.hubclient.presentation.features.profile.presenter;

import com.jpiser.hubclient.data.models.shared.Credentials;
import com.jpiser.hubclient.domain.interactors.HubInteractor;
import com.jpiser.hubclient.domain.models.HubOrganization;
import com.jpiser.hubclient.domain.models.HubRepo;
import com.jpiser.hubclient.domain.models.HubUserProfile;
import com.jpiser.hubclient.presentation.features.profile.model.UserProfileModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public class ProfilePresenterImplTest {

    public static final String TEST_USER_LOGIN = "TestUserLogin";
    ProfilePresenterImpl profilePresenter;

    @Mock HubInteractor hubInteractor;
    @Mock ProfilePresenter.ViewLayer viewLayer;
    @Mock HubUserProfile hubUserProfile;
    @Mock List<HubOrganization> organizationList;
    @Mock List<HubRepo> repoList;

    Credentials credentials;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        profilePresenter = new ProfilePresenterImpl(hubInteractor);
        credentials = Credentials.empty();
        profilePresenter.bind(viewLayer, credentials);
    }

    @Test
    public void shouldSetfieldsInConstructor(){
        assertEquals(hubInteractor, profilePresenter.hubInteractor);
    }

    @Test
    public void shouldBind(){
        assertEquals(viewLayer, profilePresenter.viewLayer);
        verify(hubInteractor).bind(profilePresenter);
    }

    @Test
    public void shouldLoadProfileDuringInitProfile(){
        profilePresenter.initProfile(TEST_USER_LOGIN);

        verify(hubInteractor).loadProfile(TEST_USER_LOGIN, credentials);
    }

    @Test
    public void shouldLoadReposDuringInitProfile(){
        profilePresenter.initProfile(TEST_USER_LOGIN);

        verify(hubInteractor).loadRepos(TEST_USER_LOGIN, credentials);
    }

    @Test
    public void shouldReceiveProfile(){
        profilePresenter.receiveProfile(hubUserProfile);
        verify(viewLayer).displayProfile(any(UserProfileModel.class));
    }

    @Test
    public void shouldReceiveOrganizations(){
        profilePresenter.receiveOrganziations(organizationList);
        verify(viewLayer).displayOrganizations(any(List.class));
    }

    @Test
    public void shouldReceiveRepos(){
        profilePresenter.receiveRepos(repoList);
        verify(viewLayer).displayRepos(any(List.class));
    }

}