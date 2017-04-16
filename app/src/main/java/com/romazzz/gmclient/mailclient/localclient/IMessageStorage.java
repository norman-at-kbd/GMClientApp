package com.romazzz.gmclient.mailclient.localclient;

import com.romazzz.gmclient.mailclient.IMessage;

import java.util.Collection;

/**
 * Created by z01tan on 4/9/17.
 */

public interface IMessageStorage {
    void saveMessage(IMessage message);
    IMessage getMessage(String id);
    void delete(IMessage message);
    Collection<IMessage> getMessages();
}
