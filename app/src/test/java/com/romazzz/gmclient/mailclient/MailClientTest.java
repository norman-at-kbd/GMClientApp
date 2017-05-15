package com.romazzz.gmclient.mailclient;

import com.romazzz.gmclient.di.component.DaggerMailClientComponent;
import com.romazzz.gmclient.mailclient.localclient.IMessageStorage;
import com.romazzz.gmclient.mailclient.network.INetworkMailClient;
import com.romazzz.gmclient.mailclient.network.NetworkMailClient;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

/**
 * Created by z01tan on 4/16/17.
 */

public class MailClientTest {
    public boolean isLoginCalled = false;
    public boolean isMailSent = false;

    class TestNetworkClient extends NetworkMailClient {

        @Override
        public void login() {
            isLoginCalled = true;
        }

        @Override
        public void send(IMessage message) {
            isMailSent = true;
        }
    }

    class TestMailClient extends MailClient {
        public TestMailClient(INetworkMailClient networkMailClient, IMessageStorage storage) {
            super(networkMailClient, storage);
        }

        @Override
        public INetworkMailClient getmNetworkMailClient() {
            return super.getmNetworkMailClient();
        }

        @Override
        public IMessageStorage getmMessageStorage() {
            return super.getmMessageStorage();
        }
    }

    @Test
    public void dependencyGraphTest() {
        TestNetworkMailClientModule mockedNetMailClientModule = mock(TestNetworkMailClientModule.class);
        when(mockedNetMailClientModule.provideNetworkMailClient()).thenReturn(new TestNetworkClient());

//        TestMailClientModule mockMailClientModule = mock(TestMailClientModule.class);
//        when(mockMailClientModule.provideMailClient(INetworkMailClient network, ))
        IMailClient mailClient= DaggerMailClientComponent.
                builder().networkMailClientModule(mockedNetMailClientModule).build().getMailClient();
        mailClient.login();
        mailClient.send(new Message("f","t","s","t"));
        assertTrue(isLoginCalled);
        assertTrue(isMailSent);
    }

    @Test
    public void java8Test() {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,0);
        list.forEach(System.out::print);
        System.out.println("");
        list.stream().filter(s->s>5).forEach(System.out::print);
    }
}
