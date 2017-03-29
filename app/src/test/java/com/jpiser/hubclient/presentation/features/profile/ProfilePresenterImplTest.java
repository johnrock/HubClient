package com.jpiser.hubclient.presentation.features.profile;

import com.jpiser.hubclient.presentation.features.profile.model.OrganizationModel;
import com.jpiser.hubclient.presentation.features.profile.model.UserProfile;

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

    @Mock ProfileUseCases profileUseCases;
    @Mock ProfilePresenter.ViewLayer viewLayer;
    @Mock UserProfile userProfile;
    @Mock List<OrganizationModel> organizationList;

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
    }

    @Test
    public void shouldLoadProfile(){
        profilePresenter.loadProfile(TEST_USER_LOGIN);

        verify(profileUseCases).loadProfile(profilePresenter, TEST_USER_LOGIN);
    }

    @Test
    public void shouldReceiveProfile(){
        profilePresenter.bind(viewLayer);
        profilePresenter.receiveProfile(userProfile);
        verify(viewLayer).displayProfile(userProfile);
    }

    @Test
    public void shouldReceiveOrganizations(){
        profilePresenter.bind(viewLayer);
        profilePresenter.receiveOrganziations(organizationList);
        verify(viewLayer).displayOrganizations(organizationList);
    }

}