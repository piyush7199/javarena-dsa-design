package org.example.lld.practice.message_broadcasting.subscriber;

import org.example.lld.practice.message_broadcasting.models.Message;
import org.example.lld.practice.message_broadcasting.models.MessageType;

import java.util.HashSet;
import java.util.Set;

public class MobileClientSubscriber implements Subscriber {

    private final String deviceId;
    private final Set<MessageType> subscribedTypes;

    public MobileClientSubscriber(String deviceId, Set<MessageType> subscribedTypes) {
        this.deviceId = deviceId;
        this.subscribedTypes = new HashSet<>(subscribedTypes);
    }

    @Override
    public void update(Message message) {
        System.out.println("Mobile App [" + deviceId + "] received push notification: " + message.getContent());
    }

    @Override
    public Set<MessageType> getSubscribedTypes() {
        return subscribedTypes;
    }
}
