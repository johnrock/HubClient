package com.jpiser.hubclient.presentation.features.issue.presenter;

import com.jpiser.hubclient.data.models.shared.Credentials;
import com.jpiser.hubclient.domain.interactors.HubInteractor;
import com.jpiser.hubclient.domain.models.HubIssue;
import com.jpiser.hubclient.presentation.features.issues.model.IssueModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public class IssuePresenterImplTest {

    public static final String TEST_REPO = "testRepo";
    public static final String TEST_TITLE = "testTitle";
    public static final String TEST_BODY = "testBody";
    IssuePresenterImpl issuePresenter;

    @Mock HubInteractor hubInteractor;
    @Mock IssuePresenter.ViewLayer viewLayer;

    Credentials credentials;
    @Mock HubIssue hubIssue;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        issuePresenter = new IssuePresenterImpl(hubInteractor);

        credentials = Credentials.empty();
        issuePresenter.bind(viewLayer, credentials);
    }

    @Test
    public void shouldBind(){

        assertEquals(viewLayer, issuePresenter.viewLayer);
        assertEquals(credentials, issuePresenter.credentials);

        verify(hubInteractor).bind(issuePresenter);
    }

    @Test
    public void shouldCreateIssue(){
        issuePresenter.createIssue(TEST_REPO, TEST_TITLE, TEST_BODY);

        verify(hubInteractor).createIssue(TEST_TITLE, TEST_BODY, TEST_REPO, credentials);
    }

    @Test
    public void shouldReceiveIssue(){
        issuePresenter.receiveIssue(hubIssue);

        verify(viewLayer).displayIssue(any(IssueModel.class));
    }

}