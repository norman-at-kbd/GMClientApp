package com.romazzz.gmclient.di.component;

import com.romazzz.gmclient.di.PerAppScope;
import com.romazzz.gmclient.di.module.NetworkMailClientModule;
import com.romazzz.gmclient.mailclient.network.INetworkMailClient;

import dagger.Component;

/**
 * Created by z01tan on 4/6/17.
 */
@PerAppScope
@Component(dependencies = AppComponent.class, modules = NetworkMailClientModule.class)
public interface NetworkMailClientComponent {
    INetworkMailClient getMailNetworkClient();
}
