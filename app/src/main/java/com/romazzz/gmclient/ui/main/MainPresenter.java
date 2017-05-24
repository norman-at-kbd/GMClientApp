package com.romazzz.gmclient.ui.main;

import android.util.Log;

import com.romazzz.gmclient.domain.IGetMessageListInteractor;
import com.romazzz.gmclient.mailclient.IMessage;
import com.romazzz.gmclient.ui.main.IMainPresenter;
import com.romazzz.gmclient.ui.main.IMainView;

import java.lang.ref.WeakReference;
import java.util.Collection;

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
    public void tryToLogin() {
        Log.d("MainPresenter", "TRY TO LOGIN");
        if(mView.get()!=null)
            mView.get().showProgress();
    }

    @Override
    public void onLoginSuccess() {
        if(mView.get()!=null) {
            mView.get().onLoginSuccess();
            mView.get().hideProgress();
        }
    }

    @Override
    public void onLoginError() {
        if(mView.get()!=null) {
            mView.get().hideProgress();
            mView.get().showLoginError();
        }
    }

    class GetMessageObserver implements Observer<Collection<IMessage>> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Collection<IMessage> iMessages) {

        }
    }
}
