package com.romazzz.gmclient.mailclient.gapi;

import android.support.annotation.NonNull;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;

import javax.inject.Inject;

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
