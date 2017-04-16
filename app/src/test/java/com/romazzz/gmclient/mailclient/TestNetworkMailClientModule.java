package com.romazzz.gmclient.mailclient;

import com.romazzz.gmclient.depinjection.module.NetworkMailClientModule;
import com.romazzz.gmclient.mailclient.network.INetworkMailClient;
import com.romazzz.gmclient.mailclient.network.NetworkMailClient;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z01tan on 4/16/17.
 */
@Module
public class TestNetworkMailClientModule extends NetworkMailClientModule{
    @Provides
    @Override
    public INetworkMailClient provideNetworkMailClient() {
        return super.provideNetworkMailClient();
    }
}
