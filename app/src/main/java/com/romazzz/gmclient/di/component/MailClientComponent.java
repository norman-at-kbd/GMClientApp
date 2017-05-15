package com.romazzz.gmclient.di.component;

import com.romazzz.gmclient.di.module.MailClientModule;
import com.romazzz.gmclient.di.module.MessageStorageModule;
import com.romazzz.gmclient.di.module.NetworkMailClientModule;
import com.romazzz.gmclient.mailclient.IMailClient;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by z01tan on 4/6/17.
 */

@Singleton
@Component(modules = {MailClientModule.class,
        MessageStorageModule.class,
        NetworkMailClientModule.class})
public interface MailClientComponent {
    IMailClient getMailClient();
}
