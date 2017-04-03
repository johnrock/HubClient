package com.jpiser.hubclient.domain.interactors;

import com.jpiser.hubclient.data.models.github.GithubIssue;
import com.jpiser.hubclient.data.models.github.GithubOrganization;
import com.jpiser.hubclient.data.models.github.GithubProfile;
import com.jpiser.hubclient.data.models.github.GithubRepo;
import com.jpiser.hubclient.data.models.shared.Credentials;
import com.jpiser.hubclient.data.repositories.GithubRepository;
import com.jpiser.hubclient.domain.models.HubIssue;
import com.jpiser.hubclient.domain.models.HubUserProfile;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public class GitHubInteractorTest {

    public static final String TEST_USER_LOGIN = "testUserLogin";
    public static final String TEST_TITLE = "testTitle";
    public static final String TEST_BODY = "testBody";
    public static final String TEST_REPONAME = "testReponame";

    GitHubInteractor gitHubInteractor;

    @Mock  GithubRepository githubRepository;
    @Mock  HubInteractor.HubAccessor hubAccessor;
    @Mock List<GithubRepo> githubRepoList;
    @Mock
    GithubIssue githubIssue;
    @Mock HubIssue hubIssue;

    Credentials credentials;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        gitHubInteractor = new GitHubInteractor(githubRepository);
        credentials = Credentials.empty();
        gitHubInteractor.bind(hubAccessor);
    }


    @Test
    public void shouldBind(){

        assertEquals(hubAccessor, gitHubInteractor.hubAccessor);
        verify(githubRepository).bind(gitHubInteractor);
    }

    @Test
    public void shouldLoadProfile(){
        gitHubInteractor.loadProfile(TEST_USER_LOGIN, credentials);
        verify(githubRepository).loadProfile(TEST_USER_LOGIN, credentials);
    }

    @Test
    public void shouldLoadRepos(){
        gitHubInteractor.loadRepos(TEST_USER_LOGIN, credentials);
        verify(githubRepository).loadRepos(TEST_USER_LOGIN, credentials);
    }

    @Test
    public void shouldCreateIssue(){
        ArgumentCaptor<GithubIssue> issueArgument =  ArgumentCaptor.forClass(GithubIssue.class);

        gitHubInteractor.createIssue(TEST_REPONAME, hubIssue, credentials);
        verify(githubRepository).createIssue(anyString(), issueArgument.capture(), any(Credentials.class));

        GithubIssue actual = issueArgument.getValue();
        assertEquals(hubIssue.getTitle(), actual.getTitle());
        assertEquals(hubIssue.getBody(), actual.getBody());
    }

    @Test
    public void shouldUpdateIssue(){
        gitHubInteractor.updateIssue(TEST_REPONAME, hubIssue, credentials);
        verify(githubRepository).updateIssue(anyString(), any(GithubIssue.class), any(Credentials.class));
    }

    @Test
    public void shouldReceiveProfile(){
        gitHubInteractor.receiveProfile(testProfile());

        verify(hubAccessor).receiveProfile(any(HubUserProfile.class));
    }

    @Test
    public void shouldReceiveOrganizations(){
        gitHubInteractor.receiveOrganiztions(createTestOrganizations());

        verify(hubAccessor).receiveOrganziations(any(List.class));
    }

    @Test
    public void shouldReceiveRepos(){
        gitHubInteractor.receiveRepos(githubRepoList);

        verify(hubAccessor).receiveRepos(any(List.class));
    }

    @Test
    public void shouldReceiveIssue(){
        gitHubInteractor.receiveIssue(githubIssue);

        verify(hubAccessor).receiveIssue(any(HubIssue.class));
    }

    private List<GithubOrganization> createTestOrganizations() {
        List<GithubOrganization> githubOrganizations = new ArrayList<>();
        GithubOrganization githubOrganization = new GithubOrganization();
        githubOrganizations.add(githubOrganization);
        return githubOrganizations;
    }


    private GithubProfile testProfile() {
        GithubProfile githubProfile = new GithubProfile();
        githubProfile.setName("testUser");
        return githubProfile;
    }
}