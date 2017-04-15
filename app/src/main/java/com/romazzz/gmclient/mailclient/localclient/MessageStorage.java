package com.romazzz.gmclient.mailclient.localclient;

import com.romazzz.gmclient.mailclient.IMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by z01tan on 4/9/17.
 */

public class MessageStorage implements IMessageStorage {
    List<IMessage> mMessages = new ArrayList<>();

    @Override
    public void saveMessage(IMessage message) {
            mMessages.add(message);
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
