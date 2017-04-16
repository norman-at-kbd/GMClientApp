package com.romazzz.gmclient.depinjection.component;

import com.romazzz.gmclient.depinjection.module.MailClientModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by z01tan on 4/6/17.
 */

@Singleton
@Component(modules = {MailClientModule.class})
public interface MailClientComponent {
}
