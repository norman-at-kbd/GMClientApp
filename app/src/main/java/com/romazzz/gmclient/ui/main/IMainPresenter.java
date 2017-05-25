package com.romazzz.gmclient.ui.main;

import com.romazzz.gmclient.ui.IMVPPresenter;

/**
 * Created by z01tan on 5/16/17.
 */

public interface IMainPresenter extends IMVPPresenter <IMainView> {
    void tryToLogin();
    void onLoginSuccess();
    void onLoginError();
    void requestMessages();
}
