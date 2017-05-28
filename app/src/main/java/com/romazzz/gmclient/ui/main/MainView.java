package com.romazzz.gmclient.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.romazzz.gmclient.R;
import com.romazzz.gmclient.mailclient.IMessage;
import com.romazzz.gmclient.ui.BaseActivity;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by z01tan on 5/16/17.
 */

public class MainView extends BaseActivity implements IMainView {
    @Inject
    IMainPresenter mPresenter;

    @Bind(R.id.login_btn)
    Button loginBtn;

    public MainView() {
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
    public void showMessages(Collection<IMessage> messages) {

    }

    @OnClick(R.id.login_btn)
    @Override
    public void loginPressed() {
        mPresenter.getCredentials();
    }

    @Override
    public void showLoginError() {

    }

    @Override
    public void showPermissionRequest() {

    }

    @Override
    public void showAccountSelection() {

    }
}
