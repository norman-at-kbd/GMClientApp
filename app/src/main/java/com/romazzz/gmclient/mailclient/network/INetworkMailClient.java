package com.romazzz.gmclient.mailclient.network;

import com.romazzz.gmclient.mailclient.IMessage;

/**
 * Created by z01tan on 4/6/17.
 */

public interface INetworkMailClient {
    void send(IMessage message);
    void delete(String messageID);
}
