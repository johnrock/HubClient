package com.jpiser.hubclient.presentation.features.issues.presenter;

import com.jpiser.hubclient.presentation.features.issues.model.IssueModel;
import com.jpiser.hubclient.presentation.features.issues.model.IssuesUseCases;

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
    IssuesPresenterImpl issuesPresenter;

    @Mock IssuesUseCases issuesUseCases;
    @Mock IssuesPresenter.ViewLayer viewLayer;
    @Mock IssuesUseCases.IssuesReceiver issuesReceiver;
    @Mock List<IssueModel> issueModels;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        issuesPresenter = new IssuesPresenterImpl(issuesUseCases);
    }

    @Test
    public void shouldBind(){
        issuesPresenter.bind(viewLayer);
        assertEquals(viewLayer, issuesPresenter.viewLayer);
        verify(issuesUseCases).bind(issuesPresenter);
    }

    @Test
    public void shouldCreateHeading(){
        //TODO: implement this
    }

    @Test
    public void shouldLoadIssues(){
        issuesPresenter.bind(viewLayer);
        issuesPresenter.loadIssues(TEST_OWNER, TEST_REPO);

        verify(issuesUseCases).loadIssues(TEST_OWNER, TEST_REPO);
    }

    @Test
    public void shouldReceiveIssues(){
        issuesPresenter.bind(viewLayer);
        issuesPresenter.receiveIssues(issueModels);

        verify(viewLayer).displayIssues(any(List.class));
    }

}