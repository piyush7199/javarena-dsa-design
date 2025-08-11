package org.example.lld.practice.message_broadcasting.publisher;

import org.example.lld.practice.message_broadcasting.models.Message;
import org.example.lld.practice.message_broadcasting.subscriber.Subscriber;

public interface Publisher {
    void subscribe(Subscriber subscriber);

    void unsubscribe(Subscriber subscriber);

    void broadcastMessage(Message message);
}
