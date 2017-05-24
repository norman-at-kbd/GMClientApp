package com.romazzz.gmclient.ui.main;

import com.romazzz.gmclient.ui.IMVPView;

/**
 * Created by z01tan on 5/15/17.
 */

public interface IMainView extends IMVPView {
    void loginPressed();
    void showLoginError();
    void onLoginSuccess();
}
