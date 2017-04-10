package com.romazzz.gmclient.mailclient;

import java.util.List;

/**
 * Created by z01tan on 4/6/17.
 */

public interface IMailClient {
    void login();
    void send(IMessage message);
    List<IMessage> getList();
}
