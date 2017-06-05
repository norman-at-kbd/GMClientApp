package com.romazzz.gmclient.domain;

import rx.Completable;

/**
 * Created by z01tan on 5/3/17.
 */

public interface IMessageSendInteractor {
    Completable getSenderCompletable();
}
