package com.romazzz.gmclient.mailclient;

/**
 * Created by z01tan on 15/04/2017.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class MessageTest {
    final String from = "from";
    final String to = "to";
    final String subject = "subject";
    final String text = "text";
    final String from2 = "from";
    final String to2 = "to";
    final String subject2 = "subject";
    final String text2 = "text";

    @Before
    public void initMessageTest() {

    }

    @Test
    public void testMessageConstructor() {
        IMessage message = new Message(from, to, subject, text);
        assertTrue(message.getFrom().equals(from));
        assertTrue(message.getTo().equals(to));
        assertTrue(message.getSubject().equals(subject));
        assertTrue(message.getText().equals(text));
    }

    @Test
    public void testMessageEquals() {
        IMessage message1 = new Message(from, to, subject, text);
        IMessage message2 = new Message(from2, to2, subject2, text2);
        IMessage message3 = new Message(from, to, subject, text);
        assertNotEquals(message1, message2);
        assertNotEquals(message1, message3);
        assertEquals(message1, message1);
    }

    @After
    public void afterTest() {

    }
}
