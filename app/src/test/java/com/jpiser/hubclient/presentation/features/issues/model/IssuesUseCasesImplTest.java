package com.jpiser.hubclient.presentation.features.issues.model;

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
public class IssuesUseCasesImplTest {

    public static final String TEST_REPO = "testRepo";
    public static final String TEST_OWNER = "testOwner";
    IssuesUseCasesImpl issuesUseCases;

    @Mock
    HubInteractor hubInteractor;
    @Mock IssuesUseCases.IssuesReceiver issuesReceiver;
    @Mock List<HubIssue> issueList;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        issuesUseCases = new IssuesUseCasesImpl(hubInteractor);
    }

    @Test
    public void shouldBind(){
        issuesUseCases.bind(issuesReceiver);
        assertEquals(issuesReceiver, issuesUseCases.issuesReceiver);
        verify(hubInteractor).bind(issuesUseCases);
    }

    @Test
    public void shouldLoadIssues(){
        issuesUseCases.loadIssues(TEST_OWNER, TEST_REPO);
        verify(hubInteractor).loadIssues(TEST_OWNER, TEST_REPO);
    }

    @Test
    public void shouldReceiveIssues(){
        issuesUseCases.bind(issuesReceiver);
        issuesUseCases.receiveIssues(issueList);

        verify(issuesReceiver).receiveIssues(any(List.class));
    }


}