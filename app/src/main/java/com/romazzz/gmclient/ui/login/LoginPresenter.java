package com.romazzz.gmclient.ui.login;

/**
 * Created by z01tan on 5/16/17.
 */

public class LoginPresenter implements ILoginPresenter {
    ILoginView mView;

    @Override
    public void onAttach(ILoginView view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    @Override
    public void tryToLogin() {
        mView.showProgress();
    }

    @Override
    public void onLoginSuccess() {
        mView.onLoginSuccess();
        mView.hideProgress();
    }

    @Override
    public void onLoginError() {
        mView.hideProgress();
        mView.showLoginError();
    }
}
