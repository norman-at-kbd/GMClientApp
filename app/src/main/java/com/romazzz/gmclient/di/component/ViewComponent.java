package com.romazzz.gmclient.di.component;

import com.romazzz.gmclient.di.PerViewScope;
import com.romazzz.gmclient.di.module.ViewModule;
import com.romazzz.gmclient.ui.login.ILoginView;

import dagger.Component;

/**
 * Created by z01tan on 5/16/17.
 */
@PerViewScope
@Component(modules = {ViewModule.class})
public interface ViewComponent {
    void inject(ILoginView view);
}
