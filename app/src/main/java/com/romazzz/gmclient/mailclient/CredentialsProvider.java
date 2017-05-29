package com.romazzz.gmclient.mailclient;

import android.content.Context;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.gmail.GmailScopes;

import java.util.Arrays;

import javax.inject.Inject;

/**
 * Created by z01tan on 5/29/17.
 */

public class CredentialsProvider implements ICredentialsProvider {

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
}
