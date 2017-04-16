package com.romazzz.gmclient.mailclient.localclient;

import com.romazzz.gmclient.mailclient.IMessage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by z01tan on 4/9/17.
 */

public class MessageStorage implements IMessageStorage {
    Map<String, IMessage> mMessages = new HashMap<>();

    @Override
    public void saveMessage(IMessage message) {
            mMessages.put(message.getID(), message);
    }

    @Override
    public void markSent(IMessage message) {

    }

    @Override
    public void delete(IMessage message) {

    }

    @Override
    public Collection<IMessage> getMessages() {
        return mMessages.values();
    }
}
