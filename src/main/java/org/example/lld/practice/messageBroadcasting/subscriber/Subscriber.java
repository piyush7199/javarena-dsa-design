package org.example.lld.practice.messageBroadcasting.subscriber;

import org.example.lld.practice.messageBroadcasting.models.Message;
import org.example.lld.practice.messageBroadcasting.models.MessageType;

import java.util.Set;

public interface Subscriber {
    void update(Message message);

    Set<MessageType> getSubscribedTypes();
}
