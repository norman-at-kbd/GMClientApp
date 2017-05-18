package com.romazzz.gmclient.di.module;

import com.romazzz.gmclient.di.PerViewScope;
import com.romazzz.gmclient.ui.login.ILoginPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z01tan on 5/16/17.
 */

@Module
public class ViewModule {
    @Provides
    @PerViewScope
    protected ILoginPresenter getLoginPresenter() {
        return null;
    }
}
