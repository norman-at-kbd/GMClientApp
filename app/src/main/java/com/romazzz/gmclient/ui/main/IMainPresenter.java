package com.romazzz.gmclient.ui.main;

import com.romazzz.gmclient.ui.IMVPPresenter;

/**
 * Created by z01tan on 5/16/17.
 */

public interface IMainPresenter extends IMVPPresenter <IMainView> {
    void getCredentials();
    void onGetCredentialsSuccess();
    void onGetCredentialsError(Throwable throwable);
    void requestMessages();
    void onRequestMessagesError(Throwable throwable);
    void onRequestMessagesSuccess();
}
