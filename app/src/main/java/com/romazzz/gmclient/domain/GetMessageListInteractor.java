package com.romazzz.gmclient.domain;

import android.util.Base64;
import android.util.Log;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.services.gmail.model.Message;
import com.romazzz.gmclient.mailclient.IMessage;
import com.romazzz.gmclient.mailclient.network.INetworkMailClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Single;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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
//
//        return Observable.create(subscriber ->  {
//                try {
//                    getMessages();
//                    subscriber.onNext(mNMailClient.getList());
//                } catch (Exception e) {
//                    subscriber.onError(e);
//                }
//                subscriber.onCompleted();
//        });
        ArrayList<IMessage> messages = new ArrayList<>();
        return getMessagesIds().
                flatMap(message -> getMessageByObservable(message.getId())).
                reduce(messages, (iMessages, message) -> {
                    iMessages.add(GoogleMessageToIMessageConverter.convert(message));
                    return iMessages;
                });
    }

    private void getMessages() throws Exception{
        getMessagesIds().
                flatMap(message -> getMessageByObservable(message.getId())).
                subscribeOn(Schedulers.io()).
                subscribe(message -> Log.d("TEST_TAG", message.getThreadId()));
    }

    private Observable<Message> getMessagesIds() {
        return Observable.create(subscriber -> {
            try {
                List<Message> messages = mNMailClient.getGoogleMessageList("");
                Log.d("GetMEssagesList","messages size : " +messages.size() );
                messages.forEach( message -> subscriber.onNext(message));
            } catch (Exception e) {
                subscriber.onError(e);
            }
            subscriber.onCompleted();
        });
    }

    private Single<Message> getMessage(String messageId) {
        return Single.create(singleSubscriber -> {
            try {
                Message message = mNMailClient.getMessageById(messageId);
                singleSubscriber.onSuccess(message);
            } catch (Exception e) {
                singleSubscriber.onError(e);
            }
        });
    }

    private Observable<Message> getMessageByObservable(String messageId) {
        return Observable.create(singleSubscriber -> {
            try {
                Message message = mNMailClient.getMessageById(messageId);
                singleSubscriber.onNext(message);
            } catch (Exception e) {
                singleSubscriber.onError(e);
            }
            singleSubscriber.onCompleted();
        });
    }
}
