package com.romazzz.gmclient.ui.login;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.romazzz.gmclient.domain.IGetMessageListInteractor;
import com.romazzz.gmclient.mailclient.IMessage;
import com.romazzz.gmclient.mailclient.gapi.ICredentialsProvider;
import com.romazzz.gmclient.mailclient.gapi.IGApiHelper;
import com.romazzz.gmclient.ui.main.MainPresenter;
import com.romazzz.gmclient.ui.main.MainView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by z01tan on 17/05/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {
    @Mock
    MainView mockMainView;
    @Mock
    IGApiHelper mockGapiHelper;
    @Mock
    IGetMessageListInteractor mockGetMessageListInteractor;

    MainPresenter MainPresenter;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        MainPresenter = new MainPresenter(mockGetMessageListInteractor, mockGapiHelper);
        MainPresenter.onAttach(mockMainView);
    }

    @Test
    public void presenterLoginErrorTest() {
        String errMessage = "errorMessage";
        MainPresenter.onRequestMessagesError(new Throwable(errMessage));
        verify(mockMainView).hideProgress();
        verify(mockMainView).showError(errMessage);
    }

    @Test
    public void presenterOnRequestMessagesSuccess() {
        ArrayList<IMessage> messages = new ArrayList<>();
        MainPresenter.onRequestMessagesSuccess(messages);
        verify(mockMainView).hideProgress();
        verify(mockMainView).showMessages(messages);
    }

    @Test
    public void presenterGapiHelperInteractionTest() {
        when(mockGapiHelper.isGooglePlayServicesAvailable()).thenReturn(false);
        MainPresenter.requestMessages();
        verify(mockGapiHelper).acquireGooglePlayServices();
        when(mockGapiHelper.isGooglePlayServicesAvailable()).thenReturn(true);
        when(mockGapiHelper.getAccountName()).thenReturn(null);
        MainPresenter.requestMessages();
        verify(mockGapiHelper).chooseAccount(any(),any(),any());
    }
}
