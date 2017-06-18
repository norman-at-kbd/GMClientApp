package com.romazzz.gmclient.mailclient;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.romazzz.gmclient.GCApp;
import com.romazzz.gmclient.ui.main.IMainView;

import java.lang.ref.WeakReference;

/**
 * Created by z01tan on 6/18/17.
 */

public class GApiAvalibility implements IGApiAvalability {
    WeakReference<IMainView> mView;

    public void setView(IMainView view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void acquireGooglePlayServices() {
        GoogleApiAvailability apiAvailability =
                GoogleApiAvailability.getInstance();
        final int connectionStatusCode =
                apiAvailability.isGooglePlayServicesAvailable(GCApp.getAppContext());
        if (apiAvailability.isUserResolvableError(connectionStatusCode)) {
            mView.get().showGooglePlayServicesAvailabilityErrorDialog(connectionStatusCode);
        }
    }

    @Override
    public boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability apiAvailability =
                GoogleApiAvailability.getInstance();
        final int connectionStatusCode =
                apiAvailability.isGooglePlayServicesAvailable(GCApp.getAppContext());
        return connectionStatusCode == ConnectionResult.SUCCESS;
    }
}
