package com.romazzz.gmclient.ui.login;

import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

/**
 * Created by z01tan on 5/16/17.
 */

public class LoginView extends AppCompatActivity implements ILoginView {
    @Inject
    ILoginPresenter mPresenter;

    public LoginView() {
        super();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showConnectionError() {

    }

    @Override
    public void loginPressed() {

    }
}
