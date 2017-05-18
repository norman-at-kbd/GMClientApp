package com.romazzz.gmclient.ui.login;

import android.support.v7.app.AppCompatActivity;

import com.romazzz.gmclient.ui.BaseActivity;

import javax.inject.Inject;

/**
 * Created by z01tan on 5/16/17.
 */

public class LoginView extends BaseActivity implements ILoginView {
    @Inject
    ILoginPresenter mPresenter;

    public LoginView() {
        super();
        mViewComponent.inject(this);
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

    @Override
    public void showLoginError() {

    }

    @Override
    public void onLoginSuccess() {

    }
}
