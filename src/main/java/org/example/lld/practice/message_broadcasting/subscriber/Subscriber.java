package org.example.lld.practice.message_broadcasting.subscriber;

import org.example.lld.practice.message_broadcasting.models.Message;
import org.example.lld.practice.message_broadcasting.models.MessageType;

import java.util.Set;

public interface Subscriber {
    void update(Message message);

    Set<MessageType> getSubscribedTypes();
}
