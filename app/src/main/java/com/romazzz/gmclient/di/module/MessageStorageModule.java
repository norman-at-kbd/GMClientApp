package com.romazzz.gmclient.di.module;

import com.romazzz.gmclient.mailclient.localclient.IMessageStorage;
import com.romazzz.gmclient.mailclient.localclient.MessageStorage;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z01tan on 4/6/17.
 */

@Module
public class MessageStorageModule {
    @Provides
    IMessageStorage provideMessageStorage() {
        return new MessageStorage();
    }
}
