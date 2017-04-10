package com.romazzz.gmclient.mailclient.localclient;

import com.romazzz.gmclient.mailclient.IMessage;
import com.romazzz.gmclient.mailclient.network.INetworkMailClient;

import java.util.List;

/**
 * Created by z01tan on 4/9/17.
 */

public class LocalClient implements ILocalClient {

    private INetworkMailClient mNetworkMailClient;

    public LocalClient(INetworkMailClient networkMailClient) {
        this.mNetworkMailClient = networkMailClient;
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
