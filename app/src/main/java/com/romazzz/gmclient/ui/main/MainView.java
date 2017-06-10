package com.romazzz.gmclient.ui.main;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.google.android.gms.common.GoogleApiAvailability;
import com.romazzz.gmclient.GCApp;
import com.romazzz.gmclient.R;
import com.romazzz.gmclient.mailclient.IMessage;
import com.romazzz.gmclient.ui.BaseActivity;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

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
        getViewComponent().inject(this);
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
        mPresenter.requestMessages();
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

    @Override
    public void showGooglePlayServicesAvailabilityErrorDialog(int googleConnectionStatusCode) {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        Dialog dialog = apiAvailability.getErrorDialog(
                MainView.this,
                googleConnectionStatusCode,
                GCApp.REQUEST_GOOGLE_PLAY_SERVICES);
        dialog.show();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(
                requestCode, permissions, grantResults, mPresenter);
    }
}
