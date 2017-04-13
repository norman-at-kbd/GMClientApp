package com.romazzz.gmclient;

import com.romazzz.gmclient.mailclient.localclient.IMessageStorage;
import com.romazzz.gmclient.mailclient.localclient.MessageStorage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by z01tan on 4/13/17.
 */

public class MessageStorageTest {
    IMessageStorage storage;
    @Before
    void initMessageStorage() {
        storage = new MessageStorage();
    }

    @Test
    void testSave() {

    }

    @Test
    void testMarkSent() {

    }

    @Test
    void testMarkDeleted() {

    }

    @Test
    void testGetMessages() {

    }

    @After
    void afterTest() {

    }
}
