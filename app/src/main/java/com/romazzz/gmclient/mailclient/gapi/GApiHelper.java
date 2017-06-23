package com.romazzz.gmclient.mailclient.gapi;

import android.accounts.AccountManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.romazzz.gmclient.GCApp;

import javax.inject.Inject;

import static android.app.Activity.RESULT_OK;

/**
 * Created by z01tan on 6/23/17.
 */

public class GApiHelper implements IGApiHelper {
    ICredentialsProvider mCredentialsProvider;
    IPermissions mPermissions;
    IGApiAvalability mGApiAvalibility;

    @Inject
    public GApiHelper(ICredentialsProvider cred, IPermissions perm, IGApiAvalability avail){
        mCredentialsProvider = cred;
        mPermissions = perm;
        mGApiAvalibility = avail;
    }

    @Override
    public void onPermissionRequestResult(OnPermissionRequestSuccess requestSuccess,
                                          int requestCode,
                                          int resultCode,
                                          Intent data) {
        switch (requestCode) {
            case GCApp.REQUEST_GOOGLE_PLAY_SERVICES:
                if (resultCode != RESULT_OK) {
//                    mView.get().showOutputText(
//                            "This app requires Google Play Services. Please install " +
//                                    "Google Play Services on your device and relaunch this app.");
                    //TODO make some mechanism to show warnings and errors in mainview
                    Log.d("MainPresenterTag", "REQUEST_GOOGLE_PLAY_SERVICES RESULT != OK");
                } else {
                    requestSuccess.onSuccess();
                }
                break;
            case GCApp.REQUEST_ACCOUNT_PICKER:
                if (resultCode == RESULT_OK && data != null &&
                        data.getExtras() != null) {
                    String accountName =
                            data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                    if (accountName != null) {
                        setAccountName(accountName);
                        requestSuccess.onSuccess();
                    }
                }
                break;
            case GCApp.REQUEST_AUTHORIZATION:
                if (resultCode == RESULT_OK) {
                    requestSuccess.onSuccess();
                }
                break;
        }
    }

    @Override
    public void acquireGooglePlayServices() {
        mGApiAvalibility.acquireGooglePlayServices();
    }

    @Override
    public void chooseAccount(@NonNull onAccountChosen accc, @NonNull pickUpAccount pick, @NonNull Object obj) {
        mPermissions.chooseAccount(accc, pick, obj);
    }

    @Override
    public boolean isGooglePlayServicesAvailable() {
        return mGApiAvalibility.isGooglePlayServicesAvailable();
    }

    @Override
    public GoogleAccountCredential getCredentials() {
        return mCredentialsProvider.getCredentials();
    }

    @Override
    public String getAccountName() {
        return mCredentialsProvider.getAccountName();
    }

    @Override
    public void setAccountName(String name) {
        mCredentialsProvider.setAccountName(name);
    }
}
