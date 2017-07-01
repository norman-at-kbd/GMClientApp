package com.romazzz.gmclient.domain;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.romazzz.gmclient.mailclient.IMessage;
import com.romazzz.gmclient.mailclient.network.INetworkMailClient;

import java.io.IOException;
import java.util.Collection;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by z01tan on 6/4/17.
 */

public class GetMessageListInteractor implements IGetMessageListInteractor {
    INetworkMailClient mNMailClient;
    @Inject
    public GetMessageListInteractor(INetworkMailClient client) {
        mNMailClient = client;
    }

    @Override
    public Observable<Collection<IMessage>> getMessagesList() {
        return Observable.create(subscriber ->  {
                try {
                    subscriber.onNext(mNMailClient.getList());
                } catch (IOException e) {
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
        });
    }
}
