package com.romazzz.gmclient.ui.main;

import com.romazzz.gmclient.mailclient.IMessage;
import com.romazzz.gmclient.ui.IMVPView;

import java.util.Collection;

/**
 * Created by z01tan on 5/15/17.
 */

public interface IMainView extends IMVPView {
    void loginPressed();
    void showError(Throwable error);
    void onLoginSuccess();
    void showMessages(Collection<IMessage> messages);
}
