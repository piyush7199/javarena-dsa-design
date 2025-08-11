package org.example.lld.practice.message_broadcasting.publisher;

import org.example.lld.practice.message_broadcasting.models.Message;
import org.example.lld.practice.message_broadcasting.models.MessageType;
import org.example.lld.practice.message_broadcasting.subscriber.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MessagePublisher implements Publisher {

    private final List<Subscriber> subscriberList;

    public MessagePublisher() {
        subscriberList = new ArrayList<>();
    }

    @Override
    public void subscribe(Subscriber subscriber) {
        subscriberList.add(subscriber);
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        subscriberList.remove(subscriber);
    }

    @Override
    public void broadcastMessage(Message message) {
        synchronized (subscriberList) {
            System.out.println("Broadcasting message: " + message.getContent() + " (Type: " + message.getType() + ")");
            for (Subscriber subscriber : subscriberList) {
                Set<MessageType> subscribedTypes = subscriber.getSubscribedTypes();
                if (subscribedTypes.contains(message.getType())) {
                    try {
                        subscriber.update(message);
                    } catch (Exception e) {
                        System.err.println("Error notifying subscriber: " + e.getMessage());
                    }
                }
            }
        }

    }
}
