package com.romazzz.gmclient.di.module;

import com.romazzz.gmclient.di.PerViewScope;
import com.romazzz.gmclient.ui.main.IMainPresenter;
import com.romazzz.gmclient.ui.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z01tan on 5/16/17.
 */

@Module
public class ViewModule {
    @Provides
    @PerViewScope
    protected IMainPresenter getMainPresenter() {
        return new MainPresenter();
    }
}
