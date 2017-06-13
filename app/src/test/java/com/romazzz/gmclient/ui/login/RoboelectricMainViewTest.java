package com.romazzz.gmclient.ui.login;

import android.widget.Button;

import com.romazzz.gmclient.BuildConfig;
import com.romazzz.gmclient.GCApp;
import com.romazzz.gmclient.R;
import com.romazzz.gmclient.di.component.DaggerViewComponent;
import com.romazzz.gmclient.di.component.ViewComponent;
import com.romazzz.gmclient.di.module.ViewModule;
import com.romazzz.gmclient.ui.main.IMainPresenter;
import com.romazzz.gmclient.ui.main.MainPresenter;
import com.romazzz.gmclient.ui.main.MainView;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
/**
 * Created by z01tan on 21/05/2017.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class RoboelectricMainViewTest {
    private MainView MainView;
    @Mock
    private MainPresenter mockedMainPresenter;
    @Before
    public void setUp() throws Exception {
        MainView = Robolectric.buildActivity(com.romazzz.gmclient.ui.main.MainView.class).
                create().
                resume().
                get();

        ViewComponent viewComponent = DaggerViewComponent.builder()
                .viewModule(new ViewModule() {
                    @Override
                    protected IMainPresenter getMainPresenter () {
                        return mockedMainPresenter;
                    }
                })
                .appComponent(GCApp.getInstance().getAppComponent())
                .build();
        viewComponent.inject(MainView);
    }

    @Test
    public void exampleTest() throws Exception{
        Button button = (Button) MainView.findViewById(R.id.login_btn);
        String buttonText = button.getText().toString();
        assertEquals(buttonText, "Login");
    }
}
