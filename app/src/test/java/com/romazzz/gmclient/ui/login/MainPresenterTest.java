package com.romazzz.gmclient.ui.login;

import com.romazzz.gmclient.di.component.AppComponent;
import com.romazzz.gmclient.di.component.DaggerAppComponent;
import com.romazzz.gmclient.di.component.DaggerViewComponent;
import com.romazzz.gmclient.di.component.ViewComponent;
import com.romazzz.gmclient.di.module.AppModule;
import com.romazzz.gmclient.di.module.ViewModule;
import com.romazzz.gmclient.domain.IGetMessageListInteractor;
import com.romazzz.gmclient.domain.TestGetMessageInteractor;
import com.romazzz.gmclient.mailclient.IMessage;
import com.romazzz.gmclient.ui.main.IMainPresenter;
import com.romazzz.gmclient.ui.main.MainPresenter;
import com.romazzz.gmclient.ui.main.MainView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;
/**
 * Created by z01tan on 17/05/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {
    @Mock
    MainView mockMainView;
    @Mock
    com.romazzz.gmclient.ui.main.MainPresenter mockMainPresenter;
    @Mock
    IGetMessageListInteractor mockGetMessagesInteractor;

//    @Mock
//    CredentialsProvider credentialsProvider;
//
    MainView mainView;
    MainPresenter MainPresenter;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        MainPresenter = new MainPresenter();
        MainPresenter.onAttach(mockMainView);

        ViewComponent viewComponent = DaggerViewComponent.builder().viewModule(new ViewModule() {
            @Override
            protected IMainPresenter getMainPresenter(){
                return mockMainPresenter;
            }
        }).build();
        mainView = new MainView();
        viewComponent.inject(mainView);
        AppComponent appComponent = DaggerAppComponent.builder().appModule(new AppModule() {
            @Override
            protected IGetMessageListInteractor provideGetMessageInteractor() {
                return new TestGetMessageInteractor();
            }
        }).build();
        appComponent.inject(MainPresenter);
    }

    @Test
    public void presenterLoginErrorTest() {
        MainPresenter.onRequestMessagesError(new Throwable());
        verify(mockMainView).hideProgress();
        verify(mockMainView).showLoginError();
    }

    @Test
    public void presenterOnRequestMessagesSuccess() {
        ArrayList<IMessage> messages = new ArrayList<>();
        MainPresenter.onRequestMessagesSuccess(messages);
        verify(mockMainView).hideProgress();
        verify(mockMainView).showMessages(messages);
    }

    @Test
    public void viewLoginClickTest() {
        mainView.loginPressed();
        verify(mockMainPresenter).requestMessages();
    }

    @Test
    public void presenterRequestMessagesTest() {
        MainPresenter.requestMessages();
    }
}
