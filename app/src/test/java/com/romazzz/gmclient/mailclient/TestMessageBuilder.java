package com.romazzz.gmclient.mailclient;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by z01tan on 6/2/17.
 */

public class TestMessageBuilder {

    static class MessageBuilder {
        static private int COUNT;
        boolean isSent = false;
        boolean isUnread = false;
        String id = "defaultId";
        String from = "defaultFrom";
        String to = "defaultTo";
        String subject = "defaultSubject";
        String text = "defaultText";
        String snippet = "defaultSnippet";

        public MessageBuilder() {
            COUNT = 0;
        }
        public void isSent(boolean issent) {
            this.isSent = issent;
        }

        public void isUnread(boolean isunread) {
            this.isUnread = isunread;
        }

        public void id(String id) {
            this.id = id;
        }

        public void from(String from) {
            this.from = from;
        }

        public void to(String to) {
            this.to = to;
        }

        public void subject(String subject) {
            this.subject = subject;
        }

        public void text(String text) {
            this.text = text;
        }

        public IMessage build() {
            COUNT++;
            return new IMessage() {
                @Override
                public boolean isSent() {
                    return isSent;
                }

                @Override
                public boolean isUnread() {
                    return isUnread;
                }

                @Override
                public String getID() {
                    return id + "_" + COUNT;
                }

                @Override
                public String getFrom() {
                    return from;
                }

                @Override
                public String getTo() {
                    return to;
                }

                @Override
                public String getSubject() {
                    return subject;
                }

                @Override
                public String getText() {
                    return text;
                }

                @Override
                public String getSnippet() {
                    return snippet;
                }
            };
        }
    }

    public IMessage getMessage() {
        return new MessageBuilder().build();
    }

    public Collection<IMessage> getMessageList(int size) {
        MessageBuilder builder = new MessageBuilder();
        ArrayList<IMessage> messages = new ArrayList<>();
        for(int i=0; i<size; i++)
            messages.add(builder.build());
        return messages;
    }
}
