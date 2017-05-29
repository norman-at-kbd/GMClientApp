package com.romazzz.gmclient.ui.login;

import com.romazzz.gmclient.di.component.DaggerViewComponent;
import com.romazzz.gmclient.di.component.ViewComponent;
import com.romazzz.gmclient.di.module.ViewModule;
import com.romazzz.gmclient.ui.main.IMainPresenter;
import com.romazzz.gmclient.ui.main.MainPresenter;
import com.romazzz.gmclient.ui.main.MainView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

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
    }

    @Test
    public void presenterStartLoginTest() {
        MainPresenter.getCredentials();
        verify(mockMainView).showProgress();
    }

    @Test
    public void presenterLoginErrorTest() {
        MainPresenter.onGetCredentialsError(new Throwable());
        verify(mockMainView).hideProgress();
        verify(mockMainView).showLoginError();
    }

    @Test
    public void viewLoginClickTest() {
        mainView.loginPressed();
        verify(mockMainPresenter).getCredentials();
    }
}
