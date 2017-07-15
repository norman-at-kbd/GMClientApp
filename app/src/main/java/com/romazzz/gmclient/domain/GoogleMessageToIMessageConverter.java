package com.romazzz.gmclient.domain;

import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePart;
import com.google.api.services.gmail.model.MessagePartHeader;
import com.romazzz.gmclient.mailclient.IMessage;

/**
 * Created by z01tan on 7/13/17.
 */

public class GoogleMessageToIMessageConverter {
    private static final String TAG = GoogleMessageToIMessageConverter.class.getSimpleName();
    public static IMessage convert(Message message) {
        String from = "";
        String to = "";
        String subject = "";
        String text = "";
        String snippet = message.getSnippet();
        for (MessagePartHeader header : message.getPayload().getHeaders()) {
            if (header.getName().equals("From"))
                from = header.getValue();
            if (header.getName().equals("To"))
                to = header.getValue();
            if (header.getName().equals("Subject"))
                subject = header.getValue();
        }

        for (MessagePart part : message.getPayload().getParts()) {
            if (part.getMimeType().equals("text/plain"))
                text += new String(part.getBody().decodeData());
        }

        return new com.romazzz.gmclient.mailclient.Message(from, to, subject, text, snippet);
    }
}
