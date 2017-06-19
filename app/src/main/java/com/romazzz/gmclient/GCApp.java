package com.romazzz.gmclient;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.romazzz.gmclient.di.component.AppComponent;
import com.romazzz.gmclient.di.component.DaggerAppComponent;
import com.romazzz.gmclient.di.module.AppModule;
import com.romazzz.gmclient.ui.main.IMainView;

/**
 * Created by z01tan on 5/29/17.
 */

public class GCApp extends Application {
    public static final int REQUEST_ACCOUNT_PICKER = 1000;
    public static final int REQUEST_AUTHORIZATION = 1001;
    public static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
    public static final int REQUEST_PERMISSION_GET_ACCOUNTS = 1003;

    private static GCApp APP;
    private AppComponent mAppComponent;
    private IMainView mEasyPermimssionsDep;

    @Override
    public void onCreate() {
        super.onCreate();
        if (APP == null) {
            APP = this;
        }

        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule()).build();
    }
    public static GCApp getInstance() {
        if(APP == null)
            APP = new GCApp();
        return APP;
    }

    public static Context getAppContext() {
        return getInstance();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public void setEasyPermimssionsDep(IMainView view) {
        mEasyPermimssionsDep = view;
    }

    public IMainView getEasyPermissionsDep() {
        return mEasyPermimssionsDep;
    }
}
