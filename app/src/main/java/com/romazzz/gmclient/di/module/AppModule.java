package com.romazzz.gmclient.di.module;

import android.content.Context;

import com.romazzz.gmclient.GCApp;
import com.romazzz.gmclient.di.PerAppScope;
import com.romazzz.gmclient.mailclient.CredentialsProvider;
import com.romazzz.gmclient.mailclient.ICredentialsProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z01tan on 5/29/17.
 */

@Module
public class AppModule {

    @Provides
    @PerAppScope
    Context provideContext() {
        return GCApp.getAppContext();
    }

    @Provides
    @PerAppScope
    ICredentialsProvider provideCredentialsProvider(Context context) {
        return new CredentialsProvider(context);
    }
}
