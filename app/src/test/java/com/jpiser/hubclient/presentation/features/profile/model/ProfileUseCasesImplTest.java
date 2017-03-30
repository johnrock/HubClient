package com.jpiser.hubclient.presentation.features.profile.model;

import com.jpiser.hubclient.domain.HubApi;
import com.jpiser.hubclient.domain.model.HubOrganization;
import com.jpiser.hubclient.domain.model.HubRepo;
import com.jpiser.hubclient.domain.model.HubUserProfile;

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
public class ProfileUseCasesImplTest {

    public static final String TEST_USER_LOGIN = "TestUserLogin";
    ProfileUseCasesImpl profileUseCases;

    @Mock HubApi hubApi;
    @Mock ProfileUseCases.ProfileReceiver profileReceiver;
    @Mock HubUserProfile hubUserProfile;
    @Mock List<HubOrganization> organizationList;
    @Mock List<HubRepo> repoList;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        profileUseCases = new ProfileUseCasesImpl(hubApi);
    }

    @Test
    public void shouldInitFieldsInConstructor(){
        assertEquals(hubApi, profileUseCases.hubApi);
    }

    @Test
    public void shouldBind(){
        profileUseCases.bind(profileReceiver);

        assertEquals(profileReceiver, profileUseCases.profileReceiver);
        verify(hubApi).bind(profileUseCases);
    }


    @Test
    public void shouldLoadProfileFromHubApi(){
        profileUseCases.bind(profileReceiver);
        profileUseCases.loadProfile(TEST_USER_LOGIN);

        verify(hubApi).loadProfile(TEST_USER_LOGIN);
    }

    @Test
    public void shouldLoadReposFromHubApi(){
        profileUseCases.bind(profileReceiver);
        profileUseCases.loadRepos(TEST_USER_LOGIN);

        verify(hubApi).loadRepos(TEST_USER_LOGIN);
    }

    @Test
    public void shouldReceiveProfile(){
        profileUseCases.bind(profileReceiver);
        profileUseCases.receiveProfile(hubUserProfile);

        verify(profileReceiver).receiveProfile(any(UserProfileModel.class));
    }

    @Test
    public void shouldReceiveOrganizations(){
        profileUseCases.bind(profileReceiver);
        profileUseCases.receiveOrganziations(organizationList);

        verify(profileReceiver).receiveOrganziations(any(List.class));
    }

    @Test
    public void shouldReceiveRepos(){
        profileUseCases.bind(profileReceiver);
        profileUseCases.receiveRepos(repoList);

        verify(profileReceiver).receiveRepos(any(List.class));
    }


}