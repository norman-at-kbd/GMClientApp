package com.romazzz.gmclient.ui.main;

import android.Manifest;
import android.accounts.AccountManager;
import android.content.Intent;
import android.util.Log;

import com.romazzz.gmclient.GCApp;
import com.romazzz.gmclient.domain.IGetMessageListInteractor;
import com.romazzz.gmclient.mailclient.ICredentialsProvider;
import com.romazzz.gmclient.mailclient.IMessage;

import java.lang.ref.WeakReference;
import java.util.Collection;

import javax.inject.Inject;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;

/**
 * Created by z01tan on 5/16/17.
 */

public class MainPresenter implements IMainPresenter {
    WeakReference<IMainView> mView;

    @Inject
    IGetMessageListInteractor getMessageListInteractor;

    @Inject
    ICredentialsProvider mCredentialsProvider;

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

    @AfterPermissionGranted(GCApp.REQUEST_PERMISSION_GET_ACCOUNTS)
    public void chooseAccount() {
        if (EasyPermissions.hasPermissions(
                GCApp.getAppContext(), Manifest.permission.GET_ACCOUNTS)) {
            String accountName = mCredentialsProvider.getAccountName();
            if (accountName != null) {
                mCredentialsProvider.getCredentials().setSelectedAccountName(accountName);
                requestMessages();
            } else {
                // Start a dialog from which the user can choose an account
                mView.get().startActivityForResult(
                        mCredentialsProvider.getCredentials().newChooseAccountIntent(),
                        GCApp.REQUEST_ACCOUNT_PICKER);
            }
        } else {
            // Request the GET_ACCOUNTS permission via a user dialog
            requestPermissions();
        }
    }

    private void requestPermissions() {
        EasyPermissions.requestPermissions(
                mView.get(),
                "This app needs to access your Google account (via Contacts).",
                GCApp.REQUEST_PERMISSION_GET_ACCOUNTS,
                Manifest.permission.GET_ACCOUNTS);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case GCApp.REQUEST_GOOGLE_PLAY_SERVICES:
                if (resultCode != RESULT_OK) {
//                    mView.get().showOutputText(
//                            "This app requires Google Play Services. Please install " +
//                                    "Google Play Services on your device and relaunch this app.");
                    //TODO make some mechanism to show warnings and errors in mainview
                    Log.d("MainPresenterTag", "REQUEST_GOOGLE_PLAY_SERVICES RESULT != OK");
                } else {
                    requestMessages();
                }
                break;
            case GCApp.REQUEST_ACCOUNT_PICKER:
                if (resultCode == RESULT_OK && data != null &&
                        data.getExtras() != null) {
                    String accountName =
                            data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                    if (accountName != null) {
                        mCredentialsProvider.setAccountName(accountName);
                        requestMessages();
                    }
                }
                break;
            case GCApp.REQUEST_AUTHORIZATION:
                if (resultCode == RESULT_OK) {
                    requestMessages();
                }
                break;
        }
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
