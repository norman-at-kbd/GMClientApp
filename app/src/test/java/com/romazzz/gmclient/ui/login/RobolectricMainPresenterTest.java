package com.romazzz.gmclient.ui.login;

import android.content.Intent;
import android.widget.Button;

import com.google.android.gms.auth.GooglePlayServicesAvailabilityException;
import com.google.android.gms.auth.MockServicesAvalibilityException;
import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.MockPlayServicesAvalabilityIOException;
import com.romazzz.gmclient.BuildConfig;
import com.romazzz.gmclient.GCApp;
import com.romazzz.gmclient.R;
import com.romazzz.gmclient.di.component.DaggerViewComponent;
import com.romazzz.gmclient.di.component.ViewComponent;
import com.romazzz.gmclient.di.module.ViewModule;
import com.romazzz.gmclient.domain.IGetMessageListInteractor;
import com.romazzz.gmclient.mailclient.IMessage;
import com.romazzz.gmclient.mailclient.TestMessageBuilder;
import com.romazzz.gmclient.mailclient.gapi.ICredentialsProvider;
import com.romazzz.gmclient.mailclient.gapi.IGApiHelper;
import com.romazzz.gmclient.ui.main.IMainPresenter;
import com.romazzz.gmclient.ui.main.IMainView;
import com.romazzz.gmclient.ui.main.MainPresenter;
import com.romazzz.gmclient.ui.main.MainView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Collection;

import rx.Observable;
import rx.Subscriber;
import rx.android.plugins.RxAndroidTestPlugins;
import rx.plugins.RxJavaTestPlugins;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by z01tan on 21/05/2017.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class RobolectricMainPresenterTest {
    @Mock
    IMainView mockMainView;
    @Mock
    IGApiHelper mockGapiHelper;
    @Mock
    IGetMessageListInteractor mockGetMessageListInteractor;

    MainPresenter MainPresenter;

    @Before
    public void setUp() throws Exception {
        RxJavaTestPlugins.setImmediateScheduler();
        RxAndroidTestPlugins.setImmediateScheduler();
        mockMainView = mock(IMainView.class);
        mockGapiHelper = mock(IGApiHelper.class);
        mockGetMessageListInteractor = mock(IGetMessageListInteractor.class);
        MainPresenter = new MainPresenter(mockGetMessageListInteractor, mockGapiHelper);
        MainPresenter.onAttach(mockMainView);
    }

    @Test
    public void presenterGetMessageSubscriptionTest() {
        Collection<IMessage> testMessages = new TestMessageBuilder().getMessageList(3);
        Observable<Collection<IMessage>> mockObservable =
                Observable.create((Subscriber<? super Collection<IMessage>> subscriber) ->
                {
                    subscriber.onNext(testMessages);
                    subscriber.onCompleted();
                });
        when(mockGapiHelper.isGooglePlayServicesAvailable()).thenReturn(true);
        when(mockGapiHelper.getAccountName()).thenReturn("testAcc");
        when(mockGetMessageListInteractor.getMessagesList()).thenReturn(mockObservable);
        MainPresenter.requestMessages();
        verify(mockMainView).showMessages(testMessages);
    }


    @Test
    public void presenterGetMessageErrorTest() {
        int CONNECTION_CODE = 66;
        MockPlayServicesAvalabilityIOException exception =
                new MockPlayServicesAvalabilityIOException(
                        new MockServicesAvalibilityException(CONNECTION_CODE, "test", null));
        Observable<Collection<IMessage>> mockObservable =
                Observable.create((Subscriber<? super Collection<IMessage>> subscriber) ->
                {
                    subscriber.onError(exception);
                });
        when(mockGapiHelper.isGooglePlayServicesAvailable()).thenReturn(true);
        when(mockGapiHelper.getAccountName()).thenReturn("testAcc");
        when(mockGetMessageListInteractor.getMessagesList()).thenReturn(mockObservable);
        MainPresenter.requestMessages();
        verify(mockMainView).showGooglePlayServicesAvailabilityErrorDialog(CONNECTION_CODE);
    }

    @After
    public void tearDown() {
        RxJavaTestPlugins.resetJavaTestPlugins();
        RxAndroidTestPlugins.resetAndroidTestPlugins();
    }
}
