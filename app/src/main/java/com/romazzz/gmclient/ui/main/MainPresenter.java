package com.romazzz.gmclient.ui.main;

import android.content.Intent;

import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.romazzz.gmclient.GCApp;
import com.romazzz.gmclient.domain.IGetMessageListInteractor;
import com.romazzz.gmclient.mailclient.IMessage;
import com.romazzz.gmclient.mailclient.gapi.IGApiHelper;

import java.lang.ref.WeakReference;
import java.util.Collection;

import pub.devrel.easypermissions.AfterPermissionGranted;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by z01tan on 5/16/17.
 */

public class MainPresenter implements IMainPresenter {
    private final String TAG = MainPresenter.class.getSimpleName();

    WeakReference<IMainView> mView;
    IGetMessageListInteractor getMessageListInteractor;
    IGApiHelper mGApiHelper;

    public MainPresenter(IGetMessageListInteractor interactor,
                         IGApiHelper gApiHelper) {
        getMessageListInteractor = interactor;
        mGApiHelper = gApiHelper;
    }

    @Override
    public void onAttach(IMainView view) {
        mView = new WeakReference<>(view);
        GCApp.getInstance().setEasyPermimssionsDep(mView);
    }

    @Override
    public void onDetach() {
        mView = null;
        GCApp.getInstance().setEasyPermimssionsDep(null);
    }

    @Override
    public void onRequestMessagesError(Throwable throwable) {
        if (throwable instanceof GooglePlayServicesAvailabilityIOException) {
            onGoogleServicesAvalibilityError((GooglePlayServicesAvailabilityIOException) throwable);
        } else if (throwable instanceof UserRecoverableAuthIOException) {
            onUserRevoverableAuthIOException((UserRecoverableAuthIOException) throwable);
        } else {
            //TODO show error notification on user screen
            if (mView.get() != null) {
                mView.get().hideProgress();
                mView.get().showError(throwable.getMessage());
            }
        }
    }

    private void onGoogleServicesAvalibilityError(GooglePlayServicesAvailabilityIOException throwable) {
        if (mView.get() != null)
            mView.get().showGooglePlayServicesAvailabilityErrorDialog(throwable.getConnectionStatusCode());
    }

    private void onUserRevoverableAuthIOException(UserRecoverableAuthIOException throwable) {
        if (mView.get() != null)
            mView.get().startActivityForResult(throwable.getIntent(), GCApp.REQUEST_AUTHORIZATION);
    }

    @Override
    public void onRequestMessagesSuccess(Collection<IMessage> messages) {
        if (mView.get() != null) {
            mView.get().hideProgress();
            mView.get().showMessages(messages);
        }
    }

    @Override
    public void requestMessages() {
        if (!mGApiHelper.isGooglePlayServicesAvailable()) {
            mGApiHelper.acquireGooglePlayServices();
        } else if (mGApiHelper.getCredentials().getSelectedAccountName() == null) {
            chooseAccount();
        } else {
            getMessageListInteractor.getMessagesList().
                    subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread()).
                    subscribe(new GetMessageObserver());
        }
    }

    @AfterPermissionGranted(GCApp.REQUEST_PERMISSION_GET_ACCOUNTS)
    public void chooseAccount() {
        mGApiHelper.chooseAccount(this::onAccountChoosed,this::pickUpAccount,mView.get());
    }

    private void onAccountChoosed() {
        requestMessages();
    }

    private void pickUpAccount() {
        mView.get().startActivityForResult(
                mGApiHelper.getCredentials().newChooseAccountIntent(),
                GCApp.REQUEST_ACCOUNT_PICKER);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mGApiHelper.onPermissionRequestResult(this::requestMessages, requestCode, resultCode, data);
    }

    class GetMessageObserver implements Observer<Collection<IMessage>> {
        @Override
        public void onCompleted() {
            if (MainPresenter.this.mView.get() != null)
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
