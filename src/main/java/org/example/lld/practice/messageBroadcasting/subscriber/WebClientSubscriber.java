package org.example.lld.practice.messageBroadcasting.subscriber;

import org.example.lld.practice.messageBroadcasting.models.Message;
import org.example.lld.practice.messageBroadcasting.models.MessageType;

import java.util.HashSet;
import java.util.Set;

public class WebClientSubscriber implements Subscriber {

    private final String clientId;
    private final Set<MessageType> subscribedTypes;

    public WebClientSubscriber(String clientId, Set<MessageType> subscribedTypes) {
        this.clientId = clientId;
        this.subscribedTypes = new HashSet<>(subscribedTypes);
    }

    @Override
    public void update(Message message) {
        System.out.println("Web Client [" + clientId + "] received: " + message.getContent());
    }

    @Override
    public Set<MessageType> getSubscribedTypes() {
        return subscribedTypes;
    }
}
