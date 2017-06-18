package com.romazzz.gmclient.mailclient;

/**
 * Created by z01tan on 6/17/17.
 */

public interface IGApiAvalability {
    void acquireGooglePlayServices();
    boolean isGooglePlayServicesAvailable();
}
