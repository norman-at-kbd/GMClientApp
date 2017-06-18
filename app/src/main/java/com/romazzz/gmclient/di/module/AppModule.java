package com.romazzz.gmclient.di.module;

import android.content.Context;

import com.romazzz.gmclient.GCApp;
import com.romazzz.gmclient.di.PerAppScope;
import com.romazzz.gmclient.domain.IGetMessageListInteractor;
import com.romazzz.gmclient.mailclient.CredentialsProvider;
import com.romazzz.gmclient.mailclient.GApiAvalibility;
import com.romazzz.gmclient.mailclient.ICredentialsProvider;
import com.romazzz.gmclient.mailclient.IGApiAvalability;
import com.romazzz.gmclient.mailclient.IMessage;
import com.romazzz.gmclient.mailclient.IPermissions;
import com.romazzz.gmclient.mailclient.Permissions;

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

    @Provides
    @PerAppScope
    IPermissions providePermissions() {
        return new Permissions();
    }

    @Provides
    @PerAppScope
    IGApiAvalability  provideGApiAvalibility() {
        return new GApiAvalibility();
    }
}
