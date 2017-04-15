package com.romazzz.gmclient.mailclient.localclient;

import com.romazzz.gmclient.mailclient.IMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by z01tan on 4/9/17.
 */

public class MessageStorage implements IMessageStorage {
    Map<Integer, IMessage> mMessages = new HashMap<>();

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
    public List<IMessage> getMessages() {
        return null;
    }
}
