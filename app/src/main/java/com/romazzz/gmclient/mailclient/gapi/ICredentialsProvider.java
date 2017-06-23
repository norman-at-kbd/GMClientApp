package com.romazzz.gmclient.mailclient.gapi;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;

/**
 * Created by z01tan on 5/29/17.
 */

public interface ICredentialsProvider {
    GoogleAccountCredential getCredentials();
    String getAccountName();
    void setAccountName(String name);
}
