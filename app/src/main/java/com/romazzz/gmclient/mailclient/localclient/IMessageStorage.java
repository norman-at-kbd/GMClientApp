package com.romazzz.gmclient.mailclient.localclient;

import com.romazzz.gmclient.mailclient.IMessage;

import java.util.Collection;

/**
 * Created by z01tan on 4/9/17.
 */

public interface IMessageStorage {
    void saveMessage(IMessage message);
    void markSent(IMessage message);
    void delete(IMessage message);
    Collection<IMessage> getMessages();
}
