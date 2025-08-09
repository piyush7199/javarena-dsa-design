package org.example.lld.practice.iot_event_notification_system.publishers;

import org.example.lld.practice.iot_event_notification_system.models.Event;
import org.example.lld.practice.iot_event_notification_system.clients.Client;

public interface Publisher {
    void subscribe(Client subscriber);

    void unsubscribe(Client subscriber);

    void broadcastEvent(Event event);
}
