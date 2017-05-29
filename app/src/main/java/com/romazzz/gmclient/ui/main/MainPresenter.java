package com.romazzz.gmclient.ui.main;

import android.util.Log;

import com.romazzz.gmclient.domain.IGetMessageListInteractor;
import com.romazzz.gmclient.mailclient.CredentialsProvider;
import com.romazzz.gmclient.mailclient.ICredentialsProvider;
import com.romazzz.gmclient.mailclient.IMessage;

import java.lang.ref.WeakReference;
import java.util.Collection;

import javax.inject.Inject;

import rx.Observer;

/**
 * Created by z01tan on 5/16/17.
 */

public class MainPresenter implements IMainPresenter {
    IGetMessageListInteractor listInteractor;

    WeakReference<IMainView> mView;

    @Override
    public void onAttach(IMainView view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    @Override
    public void onRequestMessagesError(Throwable throwable) {
        if(mView.get()!=null) {
            mView.get().hideProgress();
            mView.get().showLoginError();
        }
    }

    @Override
    public void onRequestMessagesSuccess(Collection<IMessage> messages) {
        if(mView.get()!=null) {
            mView.get().hideProgress();
            mView.get().showMessages(messages);
        }
    }

    @Override
    public void requestMessages() {

    }

    class GetMessageObserver implements Observer<Collection<IMessage>> {
        @Override
        public void onCompleted() {
            if(MainPresenter.this.mView.get()!=null)
                MainPresenter.this.mView.get().hideProgress();
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Collection<IMessage> iMessages) {
            if(MainPresenter.this.mView.get()!=null)
                MainPresenter.this.mView.get().showMessages(iMessages);
        }
    }
}
