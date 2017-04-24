package com.jpiser.hubclient.presentation.features.issues.presenter;

import com.jpiser.hubclient.data.models.shared.Credentials;
import com.jpiser.hubclient.domain.interactors.HubInteractor;
import com.jpiser.hubclient.domain.models.HubIssue;
import com.jpiser.hubclient.presentation.models.IssueModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
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
        issuesPresenter.bind(viewLayer, credentials);
    }

    @Test
    public void shouldBind(){
        assertEquals(viewLayer, issuesPresenter.viewLayer);
        verify(hubInteractor).bind(issuesPresenter);
    }

    @Test
    public void shouldCreateHeading(){
        //TODO: implement this
    }

    @Test
    public void shouldLoadIssues(){
        issuesPresenter.loadIssues(TEST_OWNER, TEST_REPO);

        verify(hubInteractor).loadIssues(TEST_OWNER, TEST_REPO, credentials, true);
    }


    @Test
    public void shouldDisplayIssuesInViewLayerAfterReceivingIssues(){
        issuesPresenter.receiveIssues(issueModels);

        verify(viewLayer).displayIssues(any(List.class));
    }

    @Test
    public void shouldPrepareIssueModelsForDisplayAfterReceivingIssues(){
        ArgumentCaptor<List<IssueModel>> issuesModelArgumentCaptor = ArgumentCaptor.forClass(List.class);

        issuesPresenter.receiveIssues(listOfHubIssues());

        verify(viewLayer).displayIssues(issuesModelArgumentCaptor.capture());

        List<IssueModel> issueModelList = issuesModelArgumentCaptor.getValue();
        for(int i=0; i<10; i++){
            String actual = issueModelList.get(i).getNumberForDisplay();
            assertEquals("#" + i, actual);
        }
    }

    @Test
    public void shouldToggleIssueStateOpen(){
        issuesPresenter.toggleIssueState(true, TEST_OWNER, TEST_REPO);

        assertEquals(true, issuesPresenter.showOpenIssues);
        verify(hubInteractor).loadIssues(TEST_OWNER, TEST_REPO, credentials, true);
    }

    @Test
    public void shouldToggleIssueStateClosed(){

        issuesPresenter.toggleIssueState(false, TEST_OWNER, TEST_REPO);

        assertEquals(false, issuesPresenter.showOpenIssues);
        verify(hubInteractor).loadIssues(TEST_OWNER, TEST_REPO, credentials, false);
    }

    private List<HubIssue> listOfHubIssues() {
        List<HubIssue> hubIssues = new ArrayList<>();
        for(int i=0; i< 10; i++){
            HubIssue hubIssue = new HubIssue();
            hubIssue.setNumber(i);
            hubIssues.add(hubIssue);
        }
        return hubIssues;
    }

}