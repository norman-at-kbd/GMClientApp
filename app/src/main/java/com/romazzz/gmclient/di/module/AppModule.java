package com.romazzz.gmclient.di.module;

import android.content.Context;

import com.romazzz.gmclient.GCApp;
import com.romazzz.gmclient.di.PerAppScope;
import com.romazzz.gmclient.domain.IGetMessageListInteractor;
import com.romazzz.gmclient.mailclient.CredentialsProvider;
import com.romazzz.gmclient.mailclient.ICredentialsProvider;
import com.romazzz.gmclient.mailclient.IMessage;

import java.util.Collection;

import dagger.Module;
import dagger.Provides;
import rx.Observable;

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
    protected
    ICredentialsProvider provideCredentialsProvider(Context context) {
        return new CredentialsProvider(context);
    }

    @Provides
    @PerAppScope
    protected
    IGetMessageListInteractor provideGetMessageInteractor() {
        return new IGetMessageListInteractor() {
            @Override
            public Observable<Collection<IMessage>> getMessagesList() {
                return null;
            }
        };
    }
}
