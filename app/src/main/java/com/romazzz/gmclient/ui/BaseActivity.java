package com.romazzz.gmclient.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.romazzz.gmclient.GCApp;
import com.romazzz.gmclient.di.component.AppComponent;
import com.romazzz.gmclient.di.component.DaggerViewComponent;
import com.romazzz.gmclient.di.component.ViewComponent;
import com.romazzz.gmclient.di.module.ViewModule;
import com.romazzz.gmclient.ui.main.MainPresenter;

/**
 * Created by z01tan on 5/16/17.
 */

public class BaseActivity extends AppCompatActivity {

    protected ViewComponent getViewComponent() {
        return DaggerViewComponent.builder()
                .viewModule(new ViewModule()).
                        appComponent(GCApp.getInstance().getAppComponent()).build();
    }
}
