package com.romazzz.gmclient.ui.login;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
/**
 * Created by z01tan on 17/05/2017.
 */

public class LoginPresenterTest {
    @Mock
    LoginView loginView;
    LoginPresenter loginPresenter;

    @Before
    void init(){
        MockitoAnnotations.initMocks(this);
        loginPresenter = new LoginPresenter();
        loginPresenter.onAttach(loginView);
    }

    @Test
    void presenterTest() {
        loginPresenter.tryToLogin();
        verify(loginView).showProgress();
    }
}
