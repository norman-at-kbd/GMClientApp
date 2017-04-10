package com.romazzz.gmclient.mailclient.localclient;

/**
 * Created by z01tan on 4/9/17.
 */

public interface IMessageStorage {
    void saveMessage();
    void markSent();
    void markDeleted();
    
}
