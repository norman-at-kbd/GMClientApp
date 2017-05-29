package com.romazzz.gmclient.ui.main;

import com.romazzz.gmclient.mailclient.IMessage;
import com.romazzz.gmclient.ui.IMVPPresenter;

import java.util.Collection;

/**
 * Created by z01tan on 5/16/17.
 */

public interface IMainPresenter extends IMVPPresenter <IMainView> {
    void requestMessages();
    void onRequestMessagesError(Throwable throwable);
    void onRequestMessagesSuccess(Collection<IMessage> messages);
}
