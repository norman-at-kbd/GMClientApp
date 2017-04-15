package com.romazzz.gmclient.mailclient;

/**
 * Created by z01tan on 15/04/2017.
 */

public class Message implements IMessage {
    private static int MESSAGE_COUNTER = 0;
    private final String mFrom;
    private final String mTo;
    private final String mSubject;
    private final String mText;
    private final int mID;
    private boolean mIsUnread;
    private boolean mIsSent;

    public Message(String from, String to, String subject, String text) {
        MESSAGE_COUNTER += 1;
        this.mID = MESSAGE_COUNTER;
        this.mFrom = from;
        this.mTo = to;
        this.mSubject = subject;
        this.mText = text;
    }

    @Override
    public int getID() {
        return mID;
    }

    @Override
    public boolean isSent() {
        return mIsSent;
    }

    @Override
    public boolean isUnread() {
        return mIsUnread;
    }

    @Override
    public String getFrom() {
        return mFrom;
    }

    @Override
    public String getTo() {
        return mTo;
    }

    @Override
    public String getSubject() {
        return mSubject;
    }

    @Override
    public String getText() {
        return mText;
    }

    @Override
    public boolean equals(Object obj) {
        Message other = (Message) obj;
        if(other == null)
            return false;

        return this.getID() == other.getID();
    }
}
