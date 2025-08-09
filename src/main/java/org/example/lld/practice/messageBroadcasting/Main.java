package org.example.lld.practice.messageBroadcasting;

import org.example.lld.practice.messageBroadcasting.models.Message;
import org.example.lld.practice.messageBroadcasting.models.MessageType;
import org.example.lld.practice.messageBroadcasting.publisher.MessagePublisher;
import org.example.lld.practice.messageBroadcasting.subscriber.EmailClientSubscriber;
import org.example.lld.practice.messageBroadcasting.subscriber.MobileClientSubscriber;
import org.example.lld.practice.messageBroadcasting.subscriber.Subscriber;
import org.example.lld.practice.messageBroadcasting.subscriber.WebClientSubscriber;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        MessagePublisher publisher = new MessagePublisher();

        Subscriber webClient = new WebClientSubscriber("web123", Set.of(MessageType.NEWS));
        Subscriber mobileApp = new MobileClientSubscriber("device456", Set.of(MessageType.CHAT));
        Subscriber emailService = new EmailClientSubscriber("user@example.com", Set.of(MessageType.ALERT));

        // Broadcast messages
        publisher.broadcastMessage(new Message("Breaking news update!", MessageType.NEWS));
        publisher.broadcastMessage(new Message("New chat message!", MessageType.CHAT));
        publisher.broadcastMessage(new Message("System alert!", MessageType.ALERT));

        // Unsubscribe mobile app and broadcast again
        publisher.unsubscribe(mobileApp);
        publisher.broadcastMessage(new Message("Another chat message!", MessageType.CHAT));
    }
}
