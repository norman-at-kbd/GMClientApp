package com.romazzz.gmclient.di.module;

import android.content.Context;

import com.romazzz.gmclient.GCApp;
import com.romazzz.gmclient.di.PerAppScope;
import com.romazzz.gmclient.domain.GetMessageListInteractor;
import com.romazzz.gmclient.domain.IGetMessageListInteractor;
import com.romazzz.gmclient.domain.ISendMessageInteractor;
import com.romazzz.gmclient.domain.SendMessageInteractor;
import com.romazzz.gmclient.mailclient.gapi.CredentialsProvider;
import com.romazzz.gmclient.mailclient.gapi.GApiAvalibility;
import com.romazzz.gmclient.mailclient.gapi.GApiHelper;
import com.romazzz.gmclient.mailclient.gapi.ICredentialsProvider;
import com.romazzz.gmclient.mailclient.gapi.IGApiAvalability;
import com.romazzz.gmclient.mailclient.IMessage;
import com.romazzz.gmclient.mailclient.gapi.IGApiHelper;
import com.romazzz.gmclient.mailclient.gapi.IPermissions;
import com.romazzz.gmclient.mailclient.gapi.Permissions;
import com.romazzz.gmclient.mailclient.network.INetworkMailClient;

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
    IGetMessageListInteractor provideGetMessageInteractor(INetworkMailClient mailClient) {
        return new GetMessageListInteractor(mailClient);
    }

    @Provides
    @PerAppScope
    ISendMessageInteractor provideSendMessageInteractor(INetworkMailClient client) {
        return new SendMessageInteractor(client);
    }

    @Provides
    @PerAppScope
    IPermissions providePermissions(ICredentialsProvider cred) {
        return new Permissions(cred);
    }

    @Provides
    @PerAppScope
    IGApiAvalability  provideGApiAvalibility() {
        return new GApiAvalibility();
    }

    @Provides
    @PerAppScope
    IGApiHelper providesGApiHelper(ICredentialsProvider cred,
                                   IPermissions perm,
                                   IGApiAvalability avail) {
        return new GApiHelper(cred, perm, avail);
    }
}
