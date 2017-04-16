package com.romazzz.gmclient.mailclient.network;

import com.romazzz.gmclient.mailclient.ICommonMailClient;
import com.romazzz.gmclient.mailclient.IMessage;

/**
 * Created by z01tan on 4/6/17.
 */

public interface INetworkMailClient extends ICommonMailClient{
    void delete(String messageID);
}
