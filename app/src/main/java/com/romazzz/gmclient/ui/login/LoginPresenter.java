package com.romazzz.gmclient.ui.login;

import android.util.Log;

import com.romazzz.gmclient.domain.IGetMessageListInteractor;
import com.romazzz.gmclient.mailclient.IMessage;

import java.lang.ref.WeakReference;
import java.util.Collection;

import rx.Observer;

/**
 * Created by z01tan on 5/16/17.
 */

public class LoginPresenter implements ILoginPresenter {
    IGetMessageListInteractor listInteractor;

    WeakReference<ILoginView> mView;

    @Override
    public void onAttach(ILoginView view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    @Override
    public void tryToLogin() {
        Log.d("LoginPresenter", "TRY TO LOGIN");
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
