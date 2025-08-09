package org.example.lld.practice.messageBroadcasting.subscriber;

import org.example.lld.practice.messageBroadcasting.models.Message;
import org.example.lld.practice.messageBroadcasting.models.MessageType;

import java.util.HashSet;
import java.util.Set;

public class EmailClientSubscriber implements Subscriber {
    private final String emailAddress;
    private final Set<MessageType> subscribedTypes;

    public EmailClientSubscriber(String emailAddress, Set<MessageType> subscribedTypes) {
        this.emailAddress = emailAddress;
        this.subscribedTypes = new HashSet<>(subscribedTypes);
    }

    @Override
    public void update(Message message) {
        System.out.println("Email Service sent to [" + emailAddress + "]: " + message.getContent());
    }

    @Override
    public Set<MessageType> getSubscribedTypes() {
        return subscribedTypes;
    }
}
