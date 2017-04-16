package com.romazzz.gmclient.mailclient;

import com.romazzz.gmclient.mailclient.IMessage;
import com.romazzz.gmclient.mailclient.localclient.ILocalClient;
import com.romazzz.gmclient.mailclient.localclient.IMessageStorage;
import com.romazzz.gmclient.mailclient.network.INetworkMailClient;

import java.util.List;

/**
 * Created by z01tan on 4/9/17.
 */

public class MailClient implements IMailClient {

    private INetworkMailClient mNetworkMailClient;
    private IMessageStorage mMessageStorage;

    public MailClient(INetworkMailClient networkMailClient, IMessageStorage messageStorage) {
        this.mNetworkMailClient = networkMailClient;
        this.mMessageStorage = messageStorage;
    }

    @Override
    public void login() {

    }

    @Override
    public void send(IMessage message) {

    }

    @Override
    public List<IMessage> getList() {
        return null;
    }

    protected void addToLocalStorage(IMessage message) {

    }

    protected void sendWithNetworkClient(IMessage message) {

    }
}
