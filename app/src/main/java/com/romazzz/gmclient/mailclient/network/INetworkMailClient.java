package com.romazzz.gmclient.mailclient.network;

import com.google.api.services.gmail.model.Message;
import com.romazzz.gmclient.mailclient.ICommonMailClient;
import com.romazzz.gmclient.mailclient.IMessage;

import java.io.IOException;
import java.util.List;

/**
 * Created by z01tan on 4/6/17.
 */

public interface INetworkMailClient extends ICommonMailClient{
    void delete(String messageID);
    List<Message> getGoogleMessageList() throws IOException;
    Message getMessageById(String messageId) throws IOException;
}
