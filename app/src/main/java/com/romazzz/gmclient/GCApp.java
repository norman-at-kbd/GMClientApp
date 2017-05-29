package com.romazzz.gmclient;

import android.app.Application;
import android.content.Context;

/**
 * Created by z01tan on 5/29/17.
 */

public class GCApp extends Application {
    private static GCApp APP;
    private GCApp() {}

    public GCApp getInstance() {
        if(APP == null)
            APP = new GCApp();
        return APP;
    }

    public Context getAppContext() {
        return getInstance();
    }
}