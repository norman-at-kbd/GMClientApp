package com.romazzz.gmclient.di.module;

import com.romazzz.gmclient.mailclient.gapi.ICredentialsProvider;
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
    protected INetworkMailClient provideNetworkMailClient(ICredentialsProvider prov) {
        return new NetworkMailClient(prov);
    }
}
