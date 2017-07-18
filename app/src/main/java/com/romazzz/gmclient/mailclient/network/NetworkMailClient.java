package com.romazzz.gmclient.mailclient.network;

import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import com.romazzz.gmclient.mailclient.IMessage;
import com.romazzz.gmclient.mailclient.gapi.ICredentialsProvider;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by z01tan on 4/16/17.
 */

public class NetworkMailClient implements INetworkMailClient {
    private final static String TAG = NetworkMailClient.class.getSimpleName();

    private Gmail mService = null;
    private String mUserID = "me";
    ICredentialsProvider mCredentialsProvider;

    @Inject
    public NetworkMailClient(ICredentialsProvider credProv) {
        mCredentialsProvider = credProv;
        initService(mCredentialsProvider.getCredentials());
    }

    private void initService(GoogleAccountCredential credential) {
        HttpTransport transport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        mService = new com.google.api.services.gmail.Gmail.Builder(
                transport, jsonFactory, credential)
                .setApplicationName("Gmail API Android Quickstart")
                .build();
    }

    private void sendMessageWithApi() throws IOException {
        String user = "me";
        String from = "zaytcev.roman@gmail.com";
        String to = "zaytcev.roman@gmail.com";
        String subject = "test";
        String content = "gmail api test success !!!";

        try {
            MimeMessage message = createEmail(to, from, subject, content);
            sendMessage(mService, user, message);
        } catch (MessagingException e) {
            Log.d("SEND MESSAGE", "EXCEPTION " + e.toString());
        }
    }

    private static void sendMessage(com.google.api.services.gmail.Gmail service,
                                    String userId, MimeMessage email)
            throws MessagingException, IOException {
        Message message = createMessageWithEmail(email);
        message = service.users().messages().send(userId, message).execute();

        System.out.println("Message id: " + message.getId());
        System.out.println(message.toPrettyString());
    }

    private static Message createMessageWithEmail(MimeMessage email)
            throws MessagingException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        email.writeTo(baos);
        String encodedEmail = Base64.encodeBase64URLSafeString(baos.toByteArray());
        Message message = new Message();
        message.setRaw(encodedEmail);
        return message;
    }

    private static MimeMessage createEmail(String to, String from, String subject,
                                           String bodyText) throws MessagingException {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(from));
        email.addRecipient(javax.mail.Message.RecipientType.TO,
                new InternetAddress(to));
        email.setSubject(subject);
        email.setText(bodyText);
        return email;
    }

    @Override
    public void send(IMessage message) throws IOException {
        sendMessageWithApi();
    }

    @Override
    public void delete(String messageID) {

    }

    @Override
    public void login() {

    }

    @Override
    public List<IMessage> getList() throws IOException {
        ArrayList<IMessage> messages = new ArrayList<>();
        messages.add(new com.romazzz.gmclient.mailclient.Message("from", "to", "subject", "text"));
        return messages;
    }

    @Override
    public List<Message> getGoogleMessageList(String query) throws Exception {
        ListMessagesResponse response = mService.users().messages().
                list(mUserID).setQ(query).execute();
        List<Message> messages = new ArrayList<>();
        while (response.getMessages() != null) {
            messages.addAll(response.getMessages());
            if (response.getNextPageToken() != null) {
                String pageToken = response.getNextPageToken();
                response = mService.users().messages().list(mUserID).setQ(query)
                        .setPageToken(pageToken).execute();
            } else {
                break;
            }
        }
        return messages;
    }

    @Override
    public List<Message> getGoogleMessageListWithLabels(List<String> labelIds) throws IOException {
        ListMessagesResponse response = mService.users().messages().list(mUserID)
                .setLabelIds(labelIds).execute();

        List<Message> messages = new ArrayList<>();
        while (response.getMessages() != null) {
            messages.addAll(response.getMessages());
            if (response.getNextPageToken() != null) {
                String pageToken = response.getNextPageToken();
                response = mService.users().messages().list(mUserID).setLabelIds(labelIds)
                        .setPageToken(pageToken).execute();
            } else {
                break;
            }
        }
        return messages;
    }

    @Override
    public Message getMessageById(String messageId) throws Exception {
        return mService.users().messages().get("me", messageId)
                .setFormat("FULL").execute();
    }
}
