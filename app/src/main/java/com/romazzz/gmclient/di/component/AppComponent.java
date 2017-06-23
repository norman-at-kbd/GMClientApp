package com.romazzz.gmclient.di.component;

import com.romazzz.gmclient.di.PerAppScope;
import com.romazzz.gmclient.di.module.AppModule;
import com.romazzz.gmclient.domain.IGetMessageListInteractor;
import com.romazzz.gmclient.mailclient.gapi.ICredentialsProvider;
import com.romazzz.gmclient.mailclient.gapi.IGApiHelper;
import com.romazzz.gmclient.ui.main.MainPresenter;

import dagger.Component;

/**
 * Created by z01tan on 5/29/17.
 */

@Component(modules = {AppModule.class})
@PerAppScope
public interface AppComponent {
    void inject(MainPresenter mainPresenter);
    ICredentialsProvider getCredentialsProvider();
    IGetMessageListInteractor getMessageListInteractor();
    IGApiHelper getGApiHelper();
}
