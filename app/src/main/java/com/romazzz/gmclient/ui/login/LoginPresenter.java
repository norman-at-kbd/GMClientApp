package com.romazzz.gmclient.ui.login;

import android.util.Log;

import com.romazzz.gmclient.domain.IMessageListInteractor;

import java.lang.ref.WeakReference;

/**
 * Created by z01tan on 5/16/17.
 */

public class LoginPresenter implements ILoginPresenter {
    IMessageListInteractor listInteractor;

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
}
