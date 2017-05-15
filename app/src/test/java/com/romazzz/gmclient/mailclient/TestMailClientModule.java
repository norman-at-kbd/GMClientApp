package com.romazzz.gmclient.mailclient;

import com.romazzz.gmclient.di.module.MailClientModule;
import com.romazzz.gmclient.mailclient.localclient.IMessageStorage;
import com.romazzz.gmclient.mailclient.network.INetworkMailClient;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z01tan on 4/16/17.
 */

@Module
public class TestMailClientModule extends MailClientModule {
    @Provides
    @Override
    protected IMailClient provideMailClient(INetworkMailClient networkMailClient,
                                            IMessageStorage messageStorage) {
        return super.provideMailClient(networkMailClient, messageStorage);
    }
}
