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
import com.romazzz.gmclient.ui.main.MainView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import rx.Single;

/**
 * Created by z01tan on 4/16/17.
 */

public class NetworkMailClient implements INetworkMailClient {
    private final static String TAG = NetworkMailClient.class.getSimpleName();

    private Gmail mService = null;
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
            MimeMessage message = createEmail(to, from,subject,content);
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
        InternetAddress tAddress = new InternetAddress(to);
        InternetAddress fAddress = new InternetAddress(from);

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
    //TODO do something about this method, maybe it should return Observable
    public List<IMessage> getList() throws IOException {
//        return messages.stream().
//                map(com.romazzz.gmclient.mailclient.Message::convert).collect(Collectors.toList());
        ArrayList<IMessage> messages = new ArrayList<>();
        messages.add(new com.romazzz.gmclient.mailclient.Message("from","to", "subject", "text"));
        return messages;
    }

    @Override
    public List<Message> getGoogleMessageList(String query) throws Exception {
        ListMessagesResponse response = mService.users().messages().
                list("me").setQ(query).execute();
        Log.d(TAG , "response size: "+response.size());
        List<Message> messages = new ArrayList<>();
//        while (response.getMessages() != null) {
        messages.addAll(response.getMessages().subList(0,5));
        return messages;
    }

    @Override
    public Message getMessageById(String messageId) throws Exception {
        return mService.users().messages().get("me",messageId)
                .setFormat("FULL").execute();
    }
}
