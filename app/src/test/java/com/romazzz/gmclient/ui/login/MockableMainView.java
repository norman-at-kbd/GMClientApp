package com.romazzz.gmclient.ui.login;

import android.content.Intent;

import com.romazzz.gmclient.mailclient.IMessage;
import com.romazzz.gmclient.ui.main.IMainView;

import java.util.Collection;

/**
 * Created by z01tan on 12/06/2017.
 */

public class MockableMainView implements IMainView {
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
    public void showError(String errMessage) {

    }

    @Override
    public void showPermissionRequest() {

    }

    @Override
    public void showAccountSelection() {

    }

    @Override
    public void showMessages(Collection<IMessage> messages) {

    }

    @Override
    public void showGooglePlayServicesAvailabilityErrorDialog(int googleConnectionStatusCode) {

    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {

    }
}
