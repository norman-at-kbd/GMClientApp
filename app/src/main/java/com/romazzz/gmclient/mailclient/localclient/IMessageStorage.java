package com.romazzz.gmclient.mailclient.localclient;

import com.romazzz.gmclient.mailclient.IMessage;

import java.util.List;

/**
 * Created by z01tan on 4/9/17.
 */

public interface IMessageStorage {
    void saveMessage();
    void markSent(IMessage message);
    void markDeleted(IMessage message);
    List<IMessage> getMessages();
}
