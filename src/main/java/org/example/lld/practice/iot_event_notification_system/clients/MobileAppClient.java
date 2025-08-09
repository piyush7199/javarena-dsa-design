package org.example.lld.practice.iot_event_notification_system.clients;

import org.example.lld.practice.iot_event_notification_system.models.DeviceType;
import org.example.lld.practice.iot_event_notification_system.models.Event;
import org.example.lld.practice.iot_event_notification_system.models.Severity;

import java.util.HashSet;
import java.util.Set;

public class MobileAppClient implements Client {
    private final String deviceId;
    private final Set<DeviceType> subscribedDeviceTypes;
    private final Set<Severity> subscribedSeverities;

    public MobileAppClient(String deviceId, Set<DeviceType> deviceTypes, Set<Severity> severities) {
        this.deviceId = deviceId;
        this.subscribedDeviceTypes = new HashSet<>(deviceTypes);
        this.subscribedSeverities = new HashSet<>(severities);
    }

    @Override
    public void notifyEvent(Event event) {
        System.out.println("Mobile App [" + deviceId + "] received: " + event.getDeviceId() +
                " [" + event.getDeviceType() + ", " + event.getSeverity() + "] - " + event.getData());
    }

    @Override
    public Set<DeviceType> getSubscribedDeviceTypes() {
        return subscribedDeviceTypes;
    }

    @Override
    public Set<Severity> getSubscribedSeverities() {
        return subscribedSeverities;
    }
}
