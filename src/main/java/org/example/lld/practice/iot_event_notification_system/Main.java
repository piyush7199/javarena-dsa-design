package org.example.lld.practice.iot_event_notification_system;

import org.example.lld.practice.iot_event_notification_system.clients.Client;
import org.example.lld.practice.iot_event_notification_system.clients.CloudServiceClient;
import org.example.lld.practice.iot_event_notification_system.clients.MobileAppClient;
import org.example.lld.practice.iot_event_notification_system.clients.WebDashboardClient;
import org.example.lld.practice.iot_event_notification_system.models.DeviceType;
import org.example.lld.practice.iot_event_notification_system.models.Event;
import org.example.lld.practice.iot_event_notification_system.models.Severity;
import org.example.lld.practice.iot_event_notification_system.publishers.EventPublisher;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        EventPublisher publisher = new EventPublisher();

        Client mobileApp = new MobileAppClient(
                "device123",
                Set.of(DeviceType.SENSOR),
                Set.of(Severity.CRITICAL)
        );
        Client webDashboard = new WebDashboardClient(
                "dashboard456",
                Set.of(DeviceType.LOCK),
                Set.of(Severity.WARNING, Severity.INFO)
        );
        Client cloudService = new CloudServiceClient(
                "service789",
                Set.of(DeviceType.CAMERA),
                Set.of(Severity.CRITICAL, Severity.WARNING)
        );

        // Subscribe
        publisher.subscribe(mobileApp);
        publisher.subscribe(webDashboard);
        publisher.subscribe(cloudService);

        // Broadcast events
        publisher.broadcastEvent(new Event("sensor001", DeviceType.SENSOR, Severity.CRITICAL,
                System.currentTimeMillis(), "Temperature exceeded 50°C"));
        publisher.broadcastEvent(new Event("lock002", DeviceType.LOCK, Severity.WARNING,
                System.currentTimeMillis(), "Door unlocked"));
        publisher.broadcastEvent(new Event("camera003", DeviceType.CAMERA, Severity.INFO,
                System.currentTimeMillis(), "Motion detected"));

        Client failingMobileApp = new MobileAppClient(
                "device999",
                Set.of(DeviceType.SENSOR),
                Set.of(Severity.CRITICAL)
        ) {
            @Override
            public void notifyEvent(Event event) {
                throw new RuntimeException("Network failure");
            }
        };
        publisher.subscribe(failingMobileApp);
        publisher.broadcastEvent(new Event("sensor004", DeviceType.SENSOR, Severity.CRITICAL,
                System.currentTimeMillis(), "High humidity detected"));

        // Unsubscribe and broadcast again
        publisher.unsubscribe(webDashboard);
        publisher.broadcastEvent(new Event("lock005", DeviceType.LOCK, Severity.WARNING,
                System.currentTimeMillis(), "Door unlocked again"));

    }
}
