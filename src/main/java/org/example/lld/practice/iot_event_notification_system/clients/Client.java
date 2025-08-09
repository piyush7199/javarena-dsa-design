package org.example.lld.practice.iot_event_notification_system.clients;

import org.example.lld.practice.iot_event_notification_system.models.DeviceType;
import org.example.lld.practice.iot_event_notification_system.models.Event;
import org.example.lld.practice.iot_event_notification_system.models.Severity;

import java.util.Set;

public interface Client {
    void notifyEvent(Event event);

    Set<Severity> getSubscribedSeverities();

    Set<DeviceType> getSubscribedDeviceTypes();
}
