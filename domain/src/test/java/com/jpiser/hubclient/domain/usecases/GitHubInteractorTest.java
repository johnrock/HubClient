package com.jpiser.hubclient.domain.usecases;

import com.jpiser.hubclient.data.models.github.Issue;
import com.jpiser.hubclient.data.models.shared.Credentials;
import com.jpiser.hubclient.data.repositories.GithubRepository;
import com.jpiser.hubclient.data.models.github.Organization;
import com.jpiser.hubclient.data.models.github.Profile;
import com.jpiser.hubclient.data.models.github.Repo;
import com.jpiser.hubclient.domain.interactors.HubInteractor;
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
    @Mock List<Repo> repoList;
    @Mock Issue issue;
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
        ArgumentCaptor<Issue> issueArgument =  ArgumentCaptor.forClass(Issue.class);

        gitHubInteractor.createIssue(TEST_REPONAME, hubIssue, credentials);
        verify(githubRepository).createIssue(anyString(), issueArgument.capture(), any(Credentials.class));

        Issue actual = issueArgument.getValue();
        assertEquals(hubIssue.getTitle(), actual.getTitle());
        assertEquals(hubIssue.getBody(), actual.getBody());
    }

    @Test
    public void shouldUpdateIssue(){
        gitHubInteractor.updateIssue(TEST_REPONAME, hubIssue, credentials);
        verify(githubRepository).updateIssue(anyString(), any(Issue.class), any(Credentials.class));
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
        gitHubInteractor.receiveRepos(repoList);

        verify(hubAccessor).receiveRepos(any(List.class));
    }

    @Test
    public void shouldReceiveIssue(){
        gitHubInteractor.receiveIssue(issue);

        verify(hubAccessor).receiveIssue(any(HubIssue.class));
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