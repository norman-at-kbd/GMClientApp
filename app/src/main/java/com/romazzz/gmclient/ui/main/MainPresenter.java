package com.romazzz.gmclient.ui.main;

import android.util.Log;

import com.romazzz.gmclient.domain.IGetMessageListInteractor;
import com.romazzz.gmclient.mailclient.IMessage;

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
//            mView.get().onLoginSuccess(); TODO if everything is ok have to  show messages
            mView.get().hideProgress();
        }
    }

    @Override
    public void onLoginError(Throwable throwable) {
        if(mView.get()!=null) {
            mView.get().hideProgress();
//            mView.get().showError(); TODO select proper error view for specific throwable
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
