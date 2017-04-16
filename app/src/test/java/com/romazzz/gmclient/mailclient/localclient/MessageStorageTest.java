package com.romazzz.gmclient.mailclient.localclient;

import com.romazzz.gmclient.mailclient.IMessage;
import com.romazzz.gmclient.mailclient.Message;
import com.romazzz.gmclient.mailclient.localclient.IMessageStorage;
import com.romazzz.gmclient.mailclient.localclient.MessageStorage;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by z01tan on 4/13/17.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MessageStorageTest {
    final String from = "from";
    final String to = "to";
    final String subject = "subject";
    final String text = "text";
    final String from2 = "from";
    final String to2 = "to";
    final String subject2 = "subject";
    final String text2 = "text";
    IMessage message1;
    IMessage message2;
    IMessage message3;

    @Before
    public void initMessageStorage() {
        message1 = new Message(from,to,subject,text);
        message2 = new Message(from2, to2, subject2, text2);
        message3 = new Message(from, to, subject, text);
    }

    @Test
    public void testSave() {
        IMessageStorage storage = new MessageStorage();
        int before = storage.getMessages().size();
        storage.saveMessage(message1);
        int after = storage.getMessages().size();
        assertEquals(before + 1, after);
        assertTrue(storage.getMessages().contains(message1));
        //check that same message won' be saved twice
        storage.saveMessage(message1);
        assertEquals(storage.getMessages().size(), after);
    }

    @Test
    public void testDelete() {
        IMessageStorage storage = new MessageStorage();
        storage.saveMessage(message1);
        storage.saveMessage(message2);
        storage.delete(message1);
        assertTrue(!storage.getMessages().contains(message1));
    }

    @Test
    public void testGetMessages() {

    }

    @After
    public void afterTest() {

    }
}
