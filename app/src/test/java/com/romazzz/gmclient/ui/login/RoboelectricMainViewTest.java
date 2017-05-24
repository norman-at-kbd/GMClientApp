package com.romazzz.gmclient.ui.login;

import android.widget.Button;

import com.romazzz.gmclient.BuildConfig;
import com.romazzz.gmclient.R;
import com.romazzz.gmclient.ui.main.MainView;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
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

    @Before
    public void setUp() throws Exception {
        MainView = Robolectric.buildActivity(com.romazzz.gmclient.ui.main.MainView.class).
                create().
                resume().
                get();
    }

    @Test
    public void exampleTest() throws Exception{
        Button button = (Button) MainView.findViewById(R.id.login_btn);
        String buttonText = button.getText().toString();
        assertEquals(buttonText, "Login");
    }
}
