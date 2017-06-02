package com.romazzz.gmclient.domain;

import com.romazzz.gmclient.mailclient.IMessage;

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
                List<IMessage> messages = new ArrayList<>();
                messages.add(new IMessage() {
                    @Override
                    public boolean isSent() {
                        return false;
                    }

                    @Override
                    public boolean isUnread() {
                        return false;
                    }

                    @Override
                    public String getID() {
                        return null;
                    }

                    @Override
                    public String getFrom() {
                        return null;
                    }

                    @Override
                    public String getTo() {
                        return null;
                    }

                    @Override
                    public String getSubject() {
                        return null;
                    }

                    @Override
                    public String getText() {
                        return null;
                    }
                });
            }
        });

    }
}
