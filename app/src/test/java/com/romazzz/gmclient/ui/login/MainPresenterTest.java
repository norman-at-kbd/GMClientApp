package com.romazzz.gmclient.ui.login;

import com.google.android.gms.auth.GooglePlayServicesAvailabilityException;
import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.romazzz.gmclient.di.component.DaggerViewComponent;
import com.romazzz.gmclient.di.component.ViewComponent;
import com.romazzz.gmclient.di.module.ViewModule;
import com.romazzz.gmclient.mailclient.IMessage;
import com.romazzz.gmclient.ui.main.IMainPresenter;
import com.romazzz.gmclient.ui.main.MainPresenter;
import com.romazzz.gmclient.ui.main.MainView;

import org.apache.tools.ant.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

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
//    @Mock
//    com.romazzz.gmclient.ui.main.MainPresenter mockMainPresenter;

//    @Mock
//    CredentialsProvider credentialsProvider;
//
//    MainView mainView;
    MainPresenter MainPresenter;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        MainPresenter = new MainPresenter(null, null);
        MainPresenter.onAttach(mockMainView);

//        ViewComponent viewComponent = DaggerViewComponent.builder().viewModule(new ViewModule() {
//            @Override
//            protected IMainPresenter getMainPresenter(){
//                return mockMainPresenter;
//            }
//        }).build();
//        mainView = new MainView();
//        viewComponent.inject(mainView);
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
//
//    @Test
//    public void viewLoginClickTest() {
//        mainView.loginPressed();
//        verify(mockMainPresenter).requestMessages();
//    }
}
