package com.romazzz.gmclient.ui.login;

import com.romazzz.gmclient.ui.IMVPPresenter;

/**
 * Created by z01tan on 5/16/17.
 */

public interface ILoginPresenter extends IMVPPresenter <ILoginView> {
    void tryToLogin();
    void onLoginSuccess();
    void onLoginError();
}
