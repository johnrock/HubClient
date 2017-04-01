package com.jpiser.hubclient.presentation.features.issues.presenter;

import com.jpiser.hubclient.data.models.shared.Credentials;
import com.jpiser.hubclient.domain.interactors.HubInteractor;
import com.jpiser.hubclient.domain.models.HubIssue;

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
public class IssuesPresenterImplTest {

    public static final String TEST_REPO = "testRepo";
    public static final String TEST_OWNER = "testOwner";
    public static final String ISSUES_STATE = "open";
    IssuesPresenterImpl issuesPresenter;

    @Mock IssuesPresenter.ViewLayer viewLayer;
    @Mock List<HubIssue> issueModels;
    @Mock HubInteractor hubInteractor;

    Credentials credentials;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        issuesPresenter = new IssuesPresenterImpl(hubInteractor);
        credentials = Credentials.empty();
    }

    @Test
    public void shouldBind(){
        issuesPresenter.bind(viewLayer, credentials);
        assertEquals(viewLayer, issuesPresenter.viewLayer);
        verify(hubInteractor).bind(issuesPresenter);
    }

    @Test
    public void shouldCreateHeading(){
        //TODO: implement this
    }

    @Test
    public void shouldLoadIssues(){
        issuesPresenter.bind(viewLayer, credentials);
        issuesPresenter.loadIssues(TEST_OWNER, TEST_REPO);

        verify(hubInteractor).loadIssues(TEST_OWNER, TEST_REPO, credentials, ISSUES_STATE);
    }

    @Test
    public void shouldReceiveIssues(){
        issuesPresenter.bind(viewLayer, credentials);
        issuesPresenter.receiveIssues(issueModels);

        verify(viewLayer).displayIssues(any(List.class));
    }

}