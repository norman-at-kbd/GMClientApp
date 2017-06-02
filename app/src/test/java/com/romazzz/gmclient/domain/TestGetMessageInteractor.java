package com.romazzz.gmclient.domain;

import com.romazzz.gmclient.mailclient.IMessage;
import com.romazzz.gmclient.mailclient.TestMessageBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by z01tan on 6/2/17.
 */

public class TestGetMessageInteractor implements IGetMessageListInteractor {

    @Override
    public Observable<Collection<IMessage>> getMessagesList() {
        return Observable.create(new Observable.OnSubscribe<Collection<IMessage>>() {
            @Override
            public void call(Subscriber<? super Collection<IMessage>> subscriber) {
                TestMessageBuilder builder = new TestMessageBuilder();
                subscriber.onNext(builder.getMessageList(5));
                subscriber.onCompleted();
            }
        });

    }
}
