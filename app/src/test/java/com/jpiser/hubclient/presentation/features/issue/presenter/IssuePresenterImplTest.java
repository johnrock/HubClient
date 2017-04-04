package com.jpiser.hubclient.presentation.features.issue.presenter;

import com.jpiser.hubclient.data.models.shared.Credentials;
import com.jpiser.hubclient.domain.interactors.HubInteractor;
import com.jpiser.hubclient.domain.models.HubIssue;
import com.jpiser.hubclient.presentation.models.IssueModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public class IssuePresenterImplTest {

    public static final String TEST_REPO = "testRepo";
    public static final String TEST_TITLE = "testTitle";
    public static final String TEST_BODY = "testBody";
    public static final String EDITED_BODY = "edited body";
    
    public static final String STATE_OPEN   = "open";
    public static final String STATE_CLOSED = "closed";
    
    
    IssuePresenterImpl issuePresenter;

    @Mock HubInteractor hubInteractor;
    @Mock IssuePresenter.ViewLayer viewLayer;

    Credentials credentials;
    @Mock HubIssue hubIssue;
    @Mock IssueModel issueModel;

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

        ArgumentCaptor<HubIssue> hubArgument = ArgumentCaptor.forClass(HubIssue.class);

        issuePresenter.createIssue(TEST_REPO, TEST_TITLE, TEST_BODY);

        verify(hubInteractor).createIssue(anyString(), hubArgument.capture(), any(Credentials.class));

        HubIssue actual = hubArgument.getValue();
        assertEquals(TEST_TITLE, actual.getTitle());
        assertEquals(TEST_BODY, actual.getBody());
    }

    @Test
    public void shouldUpdateIssue(){
        issuePresenter.updateIssue(TEST_REPO, issueModel);

        verify(hubInteractor).updateIssue(anyString(), any(HubIssue.class), any(Credentials.class));
    }

    @Test
    public void shouldUpdateIssueBody(){
        ArgumentCaptor<HubIssue> hubIssueCaptor = ArgumentCaptor.forClass(HubIssue.class);

        IssueModel sampleIssueModel = new IssueModel();
        sampleIssueModel.setBody("12345");
        issuePresenter.updateIssueBody(TEST_REPO, sampleIssueModel, EDITED_BODY);

        verify(hubInteractor).updateIssue(anyString(), hubIssueCaptor.capture(), any(Credentials.class));

        String actual = hubIssueCaptor.getValue().getBody();
        assertEquals(EDITED_BODY, actual);
    }

    @Test
    public void shouldToggleIssueState(){

        ArgumentCaptor<HubIssue> hubIssueCaptor = ArgumentCaptor.forClass(HubIssue.class);

        IssueModel sampleIssueModel = new IssueModel();
        sampleIssueModel.setState(STATE_CLOSED);
        issuePresenter.toggleIssueState(TEST_REPO, sampleIssueModel);

        verify(hubInteractor).toggleIssueState(anyString(), hubIssueCaptor.capture(), any(Credentials.class));
        HubIssue actual = hubIssueCaptor.getValue();

        assertEquals(STATE_CLOSED, actual.getState());
    }

    @Test
    public void shouldReceiveIssue(){
        issuePresenter.receiveIssue(hubIssue);

        verify(viewLayer).displayIssue(any(IssueModel.class));
    }

}