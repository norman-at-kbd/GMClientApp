package com.romazzz.gmclient.mailclient;

import com.romazzz.gmclient.mailclient.IMessage;
import com.romazzz.gmclient.mailclient.localclient.ILocalClient;
import com.romazzz.gmclient.mailclient.localclient.IMessageStorage;
import com.romazzz.gmclient.mailclient.network.INetworkMailClient;

import java.io.IOException;
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
        mNetworkMailClient.login();
    }

    @Override
    public void send(IMessage message) throws IOException {
        addToLocalStorage(message);
        sendWithNetworkClient(message);
    }

    @Override
    public List<IMessage> getList() {
        return null;
    }

    protected void addToLocalStorage(IMessage message) {
        mMessageStorage.saveMessage(message);
    }

    protected void sendWithNetworkClient(IMessage message) throws IOException {
        mNetworkMailClient.send(message);
    }

    protected INetworkMailClient getmNetworkMailClient() {
        return mNetworkMailClient;
    }

    protected IMessageStorage getmMessageStorage() {
        return mMessageStorage;
    }
}
