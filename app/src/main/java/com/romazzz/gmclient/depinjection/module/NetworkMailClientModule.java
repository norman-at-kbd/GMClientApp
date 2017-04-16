package com.romazzz.gmclient.depinjection.module;

import com.romazzz.gmclient.mailclient.network.INetworkMailClient;
import com.romazzz.gmclient.mailclient.network.NetworkMailClient;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z01tan on 4/6/17.
 */

@Module
public class NetworkMailClientModule {
    @Provides
    INetworkMailClient provideNetworkMailClient() {
        return new NetworkMailClient();
    }
}
