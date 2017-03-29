package com.jpiser.hubclient.presentation.features.profile;

import com.jpiser.hubclient.domain.HubApi;
import com.jpiser.hubclient.domain.model.HubOrganization;
import com.jpiser.hubclient.domain.model.HubUserProfile;
import com.jpiser.hubclient.presentation.features.profile.model.UserProfile;

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
    public void shouldSetProfileReceiverInLoadProfile(){
        profileUseCases.loadProfile(profileReceiver, TEST_USER_LOGIN);

        assertEquals(profileReceiver, profileUseCases.profileReceiver);
    }

    @Test
    public void shouldLoadProfileFromHubApi(){
        profileUseCases.loadProfile(profileReceiver, TEST_USER_LOGIN);

        verify(hubApi).loadProfile(profileUseCases, TEST_USER_LOGIN);
    }

    @Test
    public void shouldReceiveProfileAfterLoadProfile(){
        profileUseCases.loadProfile(profileReceiver, TEST_USER_LOGIN);
        profileUseCases.receiveProfile(hubUserProfile);

        verify(profileReceiver).receiveProfile(any(UserProfile.class));
    }

    @Test
    public void shouldReceiveOrganizations(){
        profileUseCases.loadProfile(profileReceiver, TEST_USER_LOGIN);
        profileUseCases.receiveOrganziations(organizationList);

        verify(profileReceiver).receiveOrganziations(any(List.class));
    }


}