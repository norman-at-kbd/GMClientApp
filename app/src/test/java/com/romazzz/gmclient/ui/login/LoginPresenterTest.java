package com.romazzz.gmclient.ui.login;

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
    LoginView loginView;
    LoginPresenter loginPresenter;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        loginPresenter = new LoginPresenter();
        loginPresenter.onAttach(loginView);
    }

    @Test
    public void presenterTest() {
        loginPresenter.tryToLogin();
        verify(loginView).showProgress();
    }
}
