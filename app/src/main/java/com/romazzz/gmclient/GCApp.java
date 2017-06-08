package com.romazzz.gmclient;

import android.app.Application;
import android.content.Context;

/**
 * Created by z01tan on 5/29/17.
 */

public class GCApp extends Application {
    public static final int REQUEST_ACCOUNT_PICKER = 1000;
    public static final int REQUEST_AUTHORIZATION = 1001;
    public static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
    public static final int REQUEST_PERMISSION_GET_ACCOUNTS = 1003;

    private static GCApp APP;

    @Override
    public void onCreate() {
        super.onCreate();
        if (APP == null) {
            APP = this;
        }
    }
    public static GCApp getInstance() {
        if(APP == null)
            APP = new GCApp();
        return APP;
    }

    public static Context getAppContext() {
        return getInstance();
    }
}
