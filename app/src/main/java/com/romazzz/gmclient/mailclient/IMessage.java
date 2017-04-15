package com.romazzz.gmclient.mailclient;

/**
 * Created by z01tan on 4/9/17.
 */

public interface IMessage {
    boolean isSent();
    boolean isUnread();
    String getFrom();
    String getTo();
    String getSubject();
    String getText();
}
