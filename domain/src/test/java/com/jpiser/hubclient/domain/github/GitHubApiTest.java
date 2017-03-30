package com.jpiser.hubclient.domain.github;

import com.jpiser.hubclient.data.github.GithubApiHelper;
import com.jpiser.hubclient.data.github.model.Organization;
import com.jpiser.hubclient.data.github.model.Profile;
import com.jpiser.hubclient.data.github.model.Repo;
import com.jpiser.hubclient.domain.HubApi;
import com.jpiser.hubclient.domain.model.HubUserProfile;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public class GitHubApiTest {

    public static final String TEST_USER_LOGIN = "testUserLogin";
    GitHubApi gitHubApi;

    @Mock GithubApiHelper githubApiHelper;
    @Mock  HubApi.HubAccessor hubAccessor;
    @Mock List<Repo> repoList;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        gitHubApi = new GitHubApi(githubApiHelper);
    }


    @Test
    public void shouldBind(){
        gitHubApi.bind(hubAccessor);

        assertEquals(hubAccessor, gitHubApi.hubAccessor);
        verify(githubApiHelper).bind(gitHubApi);
    }

    @Test
    public void shouldLoadProfile(){
        gitHubApi.loadProfile(TEST_USER_LOGIN);
        verify(githubApiHelper).loadProfile(TEST_USER_LOGIN);
    }

    @Test
    public void shouldLoadRepos(){
        gitHubApi.loadRepos(TEST_USER_LOGIN);
        verify(githubApiHelper).loadRepos(TEST_USER_LOGIN);
    }

    @Test
    public void shouldReceiveProfile(){
        gitHubApi.bind(hubAccessor);
        gitHubApi.receiveProfile(testProfile());

        verify(hubAccessor).receiveProfile(any(HubUserProfile.class));
    }

    @Test
    public void shouldReceiveOrganizations(){
        gitHubApi.bind(hubAccessor);
        gitHubApi.receiveOrganiztions(createTestOrganizations());

        verify(hubAccessor).receiveOrganziations(any(List.class));
    }

    @Test
    public void shouldReceiveRepos(){
        gitHubApi.bind(hubAccessor);
        gitHubApi.receiveRepos(repoList);

        verify(hubAccessor).receiveRepos(any(List.class));
    }

    private List<Organization> createTestOrganizations() {
        List<Organization> organizations = new ArrayList<>();
        Organization organization = new Organization();
        organizations.add(organization);
        return organizations;
    }


    private Profile testProfile() {
        Profile profile = new Profile();
        profile.setName("testUser");
        return profile;
    }
}