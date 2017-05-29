package com.romazzz.gmclient.mailclient;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;

/**
 * Created by z01tan on 5/29/17.
 */

public interface ICredentialsProvider {
    GoogleAccountCredential getCredentials();
}
