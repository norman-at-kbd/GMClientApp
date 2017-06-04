package com.romazzz.gmclient.mailclient;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.gmail.GmailScopes;
import com.romazzz.gmclient.GCApp;

import java.util.Arrays;

import javax.inject.Inject;

/**
 * Created by z01tan on 5/29/17.
 */

public class CredentialsProvider implements ICredentialsProvider {
    public static final String PREF_ACCOUNT_NAME = "accountName";
    private static final String GMAIL_API_TEST_SHAREDS = "GMAIL_API_TEST_SHAREDS";
    Context mContext;
    GoogleAccountCredential mCredential;
    private static final String[] SCOPES = GmailScopes.all()
            .toArray(new String[GmailScopes.all().size()]);

    @Inject
    public CredentialsProvider(Context context) {
        mContext = context;
    }

    @Override
    public GoogleAccountCredential getCredentials() {
        if(mCredential == null)
            mCredential = GoogleAccountCredential.usingOAuth2(mContext,
                    Arrays.asList(SCOPES)).setBackOff(new ExponentialBackOff());
        return mCredential;
    }

    @Override
    public String getAccountName() {
        return mContext.getSharedPreferences(GMAIL_API_TEST_SHAREDS, Context.MODE_PRIVATE)
                .getString(PREF_ACCOUNT_NAME,null);
    }

    @Override
    public void setAccountName(String name) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(GMAIL_API_TEST_SHAREDS,
                Context.MODE_PRIVATE).edit();
        editor.putString(PREF_ACCOUNT_NAME, name);
        editor.apply();
    }
}
