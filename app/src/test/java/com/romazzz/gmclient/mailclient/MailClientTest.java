package com.romazzz.gmclient.mailclient;

import com.romazzz.gmclient.depinjection.component.DaggerMailClientComponent;

import org.junit.Test;

/**
 * Created by z01tan on 4/16/17.
 */

public class MailClientTest {

    @Test
    public void dependencyGraphTest() {
        IMailClient mailClient= DaggerMailClientComponent.
                builder().build().getMailClient();

    }
}
