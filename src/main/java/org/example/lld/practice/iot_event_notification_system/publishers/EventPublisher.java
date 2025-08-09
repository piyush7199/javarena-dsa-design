package org.example.lld.practice.iot_event_notification_system.publishers;

import org.example.lld.practice.iot_event_notification_system.clients.Client;
import org.example.lld.practice.iot_event_notification_system.models.DeviceType;
import org.example.lld.practice.iot_event_notification_system.models.Event;
import org.example.lld.practice.iot_event_notification_system.models.Severity;

import java.util.*;

public class EventPublisher implements Publisher {
    private final List<Client> clients = new ArrayList<>();
    private final Map<Client, Integer> retryCounts = new HashMap<>();
    private static final int MAX_RETRIES = 3;

    // Add a client
    public void subscribe(Client client) {
        synchronized (clients) {
            clients.add(client);
            retryCounts.put(client, 0);
        }
    }

    // Remove a client
    public void unsubscribe(Client client) {
        synchronized (clients) {
            clients.remove(client);
            retryCounts.remove(client);
        }
    }

    // Broadcast an IoT event to interested clients with retries
    public void broadcastEvent(Event event) {
        synchronized (clients) {
            System.out.println("Broadcasting event from " + event.getDeviceId() +
                    " (Type: " + event.getDeviceType() + ", Severity: " + event.getSeverity() + ")");
            for (Client client : clients) {
                Set<DeviceType> deviceTypes = client.getSubscribedDeviceTypes();
                Set<Severity> severities = client.getSubscribedSeverities();
                if (deviceTypes.contains(event.getDeviceType()) || severities.contains(event.getSeverity())) {
                    notifyClientWithRetry(client, event);
                }
            }
        }
    }

    private void notifyClientWithRetry(Client client, Event event) {
        int retries = retryCounts.getOrDefault(client, 0);
        while (retries <= MAX_RETRIES) {
            try {
                client.notifyEvent(event);
                retryCounts.put(client, 0); // Reset retries on success
                break;
            } catch (Exception e) {
                retries++;
                retryCounts.put(client, retries);
                if (retries > MAX_RETRIES) {
                    System.err.println("Failed to notify client after " + MAX_RETRIES + " retries: " + e.getMessage());
                    break;
                }
                System.out.println("Retrying notification for client (Attempt " + retries + "): " + e.getMessage());
                try {
                    Thread.sleep(100); // Simulate delay between retries
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
