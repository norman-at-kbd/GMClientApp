package com.romazzz.gmclient.domain;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.romazzz.gmclient.mailclient.IMessage;

import java.util.Collection;

import rx.Observable;

/**
 * Created by z01tan on 6/4/17.
 */

public class GetMessageListInteractor implements IGetMessageListInteractor {
    public GetMessageListInteractor() {

    }

    @Override
    public Observable<Collection<IMessage>> getMessagesList() {
        return null;
    }
}
