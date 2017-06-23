package com.romazzz.gmclient.mailclient.gapi;

import android.Manifest;

import com.romazzz.gmclient.GCApp;

import javax.inject.Inject;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by z01tan on 16/06/2017.
 */

public class Permissions implements IPermissions {
    ICredentialsProvider mCredentialsProvider;
    Object mObject;
    /**
     * @param cred credentials provider interface
     * @param object Activity or Fragment object needed for easypermissions
     */

    @Inject
    public Permissions(ICredentialsProvider cred, Object object) {
        mCredentialsProvider = cred;
        mObject = object;
    }

    /**
     * @param choosen callback which called if account is set
     * @param pick callback which has to be called to choose account
     */

    @Override
    public void chooseAccount(onAccountChosen choosen, pickUpAccount pick) {
        if (EasyPermissions.hasPermissions(
                GCApp.getAppContext(), Manifest.permission.GET_ACCOUNTS)) {
            String accountName = mCredentialsProvider.getAccountName();
            if (accountName != null) {
                mCredentialsProvider.getCredentials().setSelectedAccountName(accountName); //TODO it has to be already set, doesn't it??
                choosen.onAccChosen();
            } else {
                // Start a dialog from which the user can choose an account
//                mView.get().startActivityForResult(
//                        mCredentialsProvider.getCredentials().newChooseAccountIntent(),
//                        GCApp.REQUEST_ACCOUNT_PICKER);
                pick.pickUpAccount();

            }
        } else {
            // Request the GET_ACCOUNTS permission via a user dialog
            requestPermissions();
        }
    }

    /**
     * request access permissions for application
     */

    @Override
    public void  requestPermissions() {
        EasyPermissions.requestPermissions(
                mObject,
                "This app needs to access your Google account (via Contacts).",
                GCApp.REQUEST_PERMISSION_GET_ACCOUNTS,
                Manifest.permission.GET_ACCOUNTS);
    }
}
