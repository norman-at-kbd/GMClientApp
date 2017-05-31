package com.romazzz.gmclient.ui.main;

import com.romazzz.gmclient.domain.IGetMessageListInteractor;
import com.romazzz.gmclient.mailclient.IMessage;

import java.lang.ref.WeakReference;
import java.util.Collection;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by z01tan on 5/16/17.
 */

public class MainPresenter implements IMainPresenter {
    WeakReference<IMainView> mView;

    @Inject
    IGetMessageListInteractor getMessageListInteractor;

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
        getMessageListInteractor.getMessagesList().
                observeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new GetMessageObserver());
    }

    class GetMessageObserver implements Observer<Collection<IMessage>> {
        @Override
        public void onCompleted() {
            if(MainPresenter.this.mView.get()!=null)
                MainPresenter.this.mView.get().hideProgress();
        }

        @Override
        public void onError(Throwable e) {
            MainPresenter.this.onRequestMessagesError(e);
        }

        @Override
        public void onNext(Collection<IMessage> iMessages) {
            MainPresenter.this.onRequestMessagesSuccess(iMessages);
        }
    }
}
