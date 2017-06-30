package com.romazzz.gmclient.mailclient;

import java.io.IOException;
import java.util.List;

/**
 * Created by z01tan on 4/16/17.
 */

public interface ICommonMailClient {
    void login();
    void send(IMessage message) throws IOException;
    List<IMessage> getList() throws IOException;
}
