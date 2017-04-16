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
    public synchronized void saveMessage(IMessage message) {
            mMessages.put(message.getID(), message);
    }

    @Override
    public synchronized IMessage getMessage(String id) {
        return mMessages.get(id);
    }

    @Override
    public synchronized void delete(IMessage message) {
        mMessages.remove(message.getID());
    }

    @Override
    public synchronized Collection<IMessage> getMessages() {
        return mMessages.values();
    }
}
