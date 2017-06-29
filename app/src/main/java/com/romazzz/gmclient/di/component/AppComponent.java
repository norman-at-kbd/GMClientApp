package com.romazzz.gmclient.di.component;

import com.romazzz.gmclient.di.PerAppScope;
import com.romazzz.gmclient.di.module.AppModule;
import com.romazzz.gmclient.di.module.MailClientModule;
import com.romazzz.gmclient.di.module.MessageStorageModule;
import com.romazzz.gmclient.di.module.NetworkMailClientModule;
import com.romazzz.gmclient.domain.IGetMessageListInteractor;
import com.romazzz.gmclient.domain.ISendMessageInteractor;
import com.romazzz.gmclient.mailclient.IMailClient;
import com.romazzz.gmclient.mailclient.gapi.ICredentialsProvider;
import com.romazzz.gmclient.mailclient.gapi.IGApiHelper;
import com.romazzz.gmclient.mailclient.localclient.IMessageStorage;
import com.romazzz.gmclient.ui.main.MainPresenter;

import dagger.Component;

/**
 * Created by z01tan on 5/29/17.
 */

@Component(modules = {AppModule.class,
        MailClientModule.class,
        NetworkMailClientModule.class,
        MessageStorageModule.class})
@PerAppScope
public interface AppComponent {
    void inject(MainPresenter mainPresenter);
    ICredentialsProvider getCredentialsProvider();
    IGetMessageListInteractor getMessageListInteractor();
    IGApiHelper getGApiHelper();
    IMailClient getMailClient();
    ISendMessageInteractor getSendMessageInteractor();
    IMessageStorage getMessageStorage();
}
