package com.romazzz.gmclient.mailclient.network;

import com.google.api.services.gmail.model.Message;
import com.romazzz.gmclient.mailclient.ICommonMailClient;

import java.util.List;

/**
 * Created by z01tan on 4/6/17.
 */

public interface INetworkMailClient extends ICommonMailClient{
    void delete(String messageID);
    List<Message> getGoogleMessageList(String query) throws Exception;
    Message getMessageById(String messageId) throws Exception;
    List<Message> getGoogleMessageListWithLabels(List<String> labelIds) throws Exception;
}
