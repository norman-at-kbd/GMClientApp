package com.romazzz.gmclient.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.romazzz.gmclient.R;
import com.romazzz.gmclient.ui.BaseActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by z01tan on 5/16/17.
 */

public class LoginView extends BaseActivity implements ILoginView {
    @Inject
    ILoginPresenter mPresenter;

    @Bind(R.id.login_btn)
    Button loginBtn;

    public LoginView() {
        super();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewComponent.inject(this);
        setContentView(R.layout.login_view);
        mPresenter.onAttach(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_btn)
    public void loginBtnClick() {
        Log.d("LoginView", "LOGIN BUTTON CLICKED");
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
        mPresenter.tryToLogin();
    }

    @Override
    public void showLoginError() {

    }

    @Override
    public void onLoginSuccess() {

    }
}
