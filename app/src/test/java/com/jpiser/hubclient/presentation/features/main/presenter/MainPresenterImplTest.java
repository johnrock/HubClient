package com.jpiser.hubclient.presentation.features.main.presenter;

import com.jpiser.hubclient.domain.interactors.HubInteractor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public class MainPresenterImplTest {

    public static final String TEST_HUB_NAME = "Test Hub  Name";
    MainPresenterImpl mainPresenter;

    @Mock MainPresenter.ViewLayer viewLayer;
    @Mock HubInteractor hubInteractor;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        when(hubInteractor.getName()).thenReturn(TEST_HUB_NAME);

        mainPresenter = new MainPresenterImpl(hubInteractor);
        mainPresenter.bind(viewLayer);
    }

    @Test
    public void shouldBind(){

        assertEquals(viewLayer, mainPresenter.viewLayer);
    }

    @Test
    public void shouldGetHubName(){
        String hubName = mainPresenter.getHubName();

        assertEquals(TEST_HUB_NAME, hubName);
    }

}