package com.romazzz.gmclient.ui.main;

import android.content.Intent;

import com.romazzz.gmclient.mailclient.IMessage;
import com.romazzz.gmclient.ui.IMVPView;

import java.util.Collection;

/**
 * Created by z01tan on 5/15/17.
 */

public interface IMainView extends IMVPView {
    void loginPressed();
    void showLoginError();
    void showPermissionRequest();
    void showAccountSelection();
    void showMessages(Collection<IMessage> messages);
    void showGooglePlayServicesAvailabilityErrorDialog(final int googleConnectionStatusCode);
    void startActivityForResult(Intent intent, int requestCode);
}
