package org.example.lld.practice.messageBroadcasting.publisher;

import org.example.lld.practice.messageBroadcasting.models.Message;
import org.example.lld.practice.messageBroadcasting.subscriber.Subscriber;

public interface Publisher {
    void subscribe(Subscriber subscriber);

    void unsubscribe(Subscriber subscriber);

    void broadcastMessage(Message message);
}
