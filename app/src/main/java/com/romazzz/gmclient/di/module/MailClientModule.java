package com.romazzz.gmclient.di.module;

import com.romazzz.gmclient.mailclient.IMailClient;
import com.romazzz.gmclient.mailclient.MailClient;
import com.romazzz.gmclient.mailclient.localclient.IMessageStorage;
import com.romazzz.gmclient.mailclient.network.INetworkMailClient;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z01tan on 4/6/17.
 */

@Module
public class MailClientModule {
    @Provides
    protected IMailClient provideMailClient(INetworkMailClient networkMailClient,
                                  IMessageStorage messageStorage) {
        return new MailClient(networkMailClient, messageStorage);
    }
}
