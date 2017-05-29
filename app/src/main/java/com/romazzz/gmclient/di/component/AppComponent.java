package com.romazzz.gmclient.di.component;

import com.romazzz.gmclient.di.PerAppScope;
import com.romazzz.gmclient.di.module.AppModule;

import dagger.Component;

/**
 * Created by z01tan on 5/29/17.
 */

@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject();
}
