package com.romazzz.gmclient;

import com.romazzz.gmclient.mailclient.IMessage;
import com.romazzz.gmclient.mailclient.localclient.IMessageStorage;
import com.romazzz.gmclient.mailclient.localclient.MessageStorage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by z01tan on 4/13/17.
 */

public class MessageStorageTest {
    IMessageStorage storage;
    class TestMessage implements IMessage {
        @Override
        public boolean isSent() {
            return false;
        }

        @Override
        public boolean isUnread() {
            return false;
        }

        @Override
        public String getFrom() {
            return null;
        }

        @Override
        public String getTo() {
            return null;
        }

        @Override
        public String getSubject() {
            return null;
        }

        @Override
        public String getText() {
            return null;
        }
    }

    @Before
    public void initMessageStorage() {
        storage = new MessageStorage();
    }

    @Test
    public void testSave() {
        int before = storage.getMessages().size();
        storage.saveMessage(new TestMessage());
        int after = storage.getMessages().size();
        assertEquals(before + 1, after);
    }

    @Test
    public void testMarkSent() {

    }

    @Test
    public void testMarkDeleted() {

    }

    @Test
    public void testGetMessages() {

    }

    @After
    public void afterTest() {

    }
}
