package com.romazzz.gmclient.mailclient;

import com.romazzz.gmclient.di.module.NetworkMailClientModule;
import com.romazzz.gmclient.mailclient.gapi.ICredentialsProvider;
import com.romazzz.gmclient.mailclient.network.INetworkMailClient;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z01tan on 4/16/17.
 */
@Module
public class TestNetworkMailClientModule extends NetworkMailClientModule{
    @Provides
    @Override
    public INetworkMailClient provideNetworkMailClient(ICredentialsProvider  provider) {
        return super.provideNetworkMailClient(provider);
    }
}
