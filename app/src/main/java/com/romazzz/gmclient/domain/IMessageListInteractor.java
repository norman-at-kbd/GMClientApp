package com.romazzz.gmclient.domain;

import com.romazzz.gmclient.mailclient.IMessage;

import java.util.List;

import rx.Observable;

/**
 * Created by z01tan on 5/22/17.
 */

public interface IMessageListInteractor {
    Observable<List<IMessage>> getMessagesList();
}
