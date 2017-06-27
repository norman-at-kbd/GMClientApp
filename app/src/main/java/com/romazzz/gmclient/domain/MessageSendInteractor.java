package com.romazzz.gmclient.domain;

import android.util.Log;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.services.gmail.model.Message;
import com.romazzz.gmclient.mailclient.network.INetworkMailClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import rx.Completable;

/**
 * Created by z01tan on 6/5/17.
 */

public class MessageSendInteractor implements IMessageSendInteractor {
    private static final String TAG = GetMessageListInteractor.class.getSimpleName();

    INetworkMailClient mNmailClient;
    @Inject
    public MessageSendInteractor(INetworkMailClient client) {
        mNmailClient = client;
    }

    private void sendMessageWithApi() throws IOException {
//        NetworkMailClientComponent component = DaggerN.builder().build();
        mNmailClient.send(null);

    }
//
//    public static void sendMessage(com.google.api.services.gmail.Gmail service,
//                                   String userId, MimeMessage email)
//            throws MessagingException, IOException {
//        Message message = createMessageWithEmail(email);
//        message = service.users().messages().send(userId, message).execute();
//
//        System.out.println("Message id: " + message.getId());
//        System.out.println(message.toPrettyString());
//    }
//
//    public static Message createMessageWithEmail(MimeMessage email)
//            throws MessagingException, IOException {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        email.writeTo(baos);
//        String encodedEmail = Base64.encodeBase64URLSafeString(baos.toByteArray());
//        Message message = new Message();
//        message.setRaw(encodedEmail);
//        return message;
//    }
//
//    public static MimeMessage createEmail(String to, String from, String subject,
//                                          String bodyText) throws MessagingException {
//        Properties props = new Properties();
//        Session session = Session.getDefaultInstance(props, null);
//
//        MimeMessage email = new MimeMessage(session);
//        InternetAddress tAddress = new InternetAddress(to);
//        InternetAddress fAddress = new InternetAddress(from);
//
//        email.setFrom(new InternetAddress(from));
//        email.addRecipient(javax.mail.Message.RecipientType.TO,
//                new InternetAddress(to));
//        email.setSubject(subject);
//        email.setText(bodyText);
//        return email;
//    }

    @Override
    public Completable getSenderCompletable() {
        return Completable.create(new Completable.CompletableOnSubscribe() {
            @Override
            public void call(Completable.CompletableSubscriber completableSubscriber) {
                try {
                    sendMessageWithApi();
                    completableSubscriber.onCompleted();
                } catch (Exception e) {
//                    MakeRequestTask.this.requestView.showOutputText("The following error occurred:\n"
//                            + mLastError.getMessage());
                    Log.d(TAG, e.toString());
                    completableSubscriber.onError(e);
                }
            }
        });
    }
}
