package com.romazzz.gmclient.ui.login;

import com.romazzz.gmclient.di.component.DaggerViewComponent;
import com.romazzz.gmclient.di.component.ViewComponent;
import com.romazzz.gmclient.di.module.ViewModule;

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
public class LoginPresenterTest {
    @Mock
    LoginView mockLoginView;
    @Mock
    LoginPresenter mockLoginPresenter;
    LoginView loginView;
    LoginPresenter loginPresenter;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        loginPresenter = new LoginPresenter();
        loginPresenter.onAttach(mockLoginView);

        ViewComponent viewComponent = DaggerViewComponent.builder().viewModule(new ViewModule() {
            @Override
            protected ILoginPresenter getLoginPresenter(){
                return mockLoginPresenter;
            }
        }).build();

        loginView = new LoginView() {
            @Override
            protected ViewComponent getViewComponent() {
                return viewComponent;
            }
        };
    }

    @Test
    public void presenterStartLoginTest() {
        loginPresenter.tryToLogin();
        verify(mockLoginView).showProgress();
    }

    @Test
    public void  presenterLoginSuccesTest() {
        loginPresenter.onLoginError();
        verify(mockLoginView).hideProgress();
        verify(mockLoginView).showLoginError();
    }

    @Test
    public void presenterLoginErrorTest() {
        loginPresenter.onLoginSuccess();
        verify(mockLoginView).hideProgress();
        verify(mockLoginView).onLoginSuccess();
    }

    @Test
    public void viewLoginClickTest() {
        loginView.loginPressed();
        verify(mockLoginPresenter).tryToLogin();
    }
}
