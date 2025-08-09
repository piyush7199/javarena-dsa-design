package org.example.lld.practice.messageBroadcasting.publisher;

import org.example.lld.practice.messageBroadcasting.models.Message;
import org.example.lld.practice.messageBroadcasting.models.MessageType;
import org.example.lld.practice.messageBroadcasting.subscriber.Subscriber;

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
