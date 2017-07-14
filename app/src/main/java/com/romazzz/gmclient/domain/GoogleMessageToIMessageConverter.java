package com.romazzz.gmclient.domain;

import android.util.Base64;
import android.util.Log;

import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePart;
import com.google.api.services.gmail.model.MessagePartHeader;
import com.romazzz.gmclient.mailclient.IMessage;

import java.nio.charset.Charset;

/**
 * Created by z01tan on 7/13/17.
 */

public class GoogleMessageToIMessageConverter {
    public static IMessage convert(Message message) {
        String from = "";
        String to = "";
        String subject = "";
        String text = "";
        String snippet = message.getSnippet();
        try {
            Log.d("MESSAGES", "msg: " + message.toPrettyString());
            for (MessagePartHeader header : message.getPayload().getHeaders()) {
                if (header.getName().equals("From"))
                    from = header.getValue();
                if (header.getName().equals("To"))
                    to = header.getValue();
                if (header.getName().equals("Subject"))
                    subject = header.getValue();
            }
//            if (message.getPayload().getBody().getSize() != 0)
//                Log.d("MESSAGES", "body " + new String(Base64.decode(message.getPayload().getBody().getData(), Base64.DEFAULT), Charset.forName("UTF-8")));
//            Log.d("MESSAGES", "payload headers: " + message.getPayload().getHeaders().toString());
//            Log.d("MESSAGE", "message " + message.getSnippet());
            for (MessagePart part : message.getPayload().getParts()) {
                if (part.getMimeType().equals("text/plain"))
                    text += new String(part.getBody().decodeData());
//                    Log.d("PART_HEADERS", "" + part.getHeaders());
//                Log.d("PART", "part: " + new String(part.getBody().decodeData()));
            }
        } catch (Exception e) {
            Log.d("message: ", "Exception " + e.toString());
            Log.d("MESSAGES", "body " + message.getPayload().getBody().getData());
        }
        return new com.romazzz.gmclient.mailclient.Message(from, to, subject, text, snippet);
    }
}
