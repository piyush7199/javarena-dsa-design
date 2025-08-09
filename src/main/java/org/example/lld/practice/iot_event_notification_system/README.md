# Event Notification System for IoT Devices Design (Observer Pattern)

## Problem Statement

Design an **Event Notification System for IoT Devices** that allows multiple clients (e.g., Mobile Apps, Web Dashboards,
Cloud Services) to receive real-time event notifications from IoT devices (e.g., temperature sensors, smart locks,
cameras). The system must support dynamic client subscriptions, filter notifications by device type or event severity,
and ensure reliable delivery with retry mechanisms for failed notifications. The system should be extensible to support
new client types and handle high-frequency events efficiently.

### Functional Requirements

- **Event Notification:** Broadcast events from IoT devices to relevant subscribed clients.
- **Client Management:** Support dynamic subscription and unsubscription at runtime.
- **Client Types:** Support different client types (e.g., Mobile App, Web Dashboard, Cloud Service).
- **Event Filtering:** Allow clients to filter events by device type (e.g., SENSOR, LOCK) or severity (e.g., CRITICAL,
  WARNING).
- **Retry Mechanism:** Retry failed notifications up to 3 times.
- **Event Payload:** Include device ID, device type, severity, timestamp, and optional data.

### Non-Functional Requirements

- **Extensibility:** Add new client types or filters without modifying core code.
- **Loose Coupling:** Ensure the publisher is independent of client implementations.
- **High Performance:** Handle thousands of events per second with low latency.
- **Thread Safety:** Support concurrent broadcasts and subscriptions safely.
- **Reliability:** Ensure reliable delivery with retries and logging for failures.
- **Maintainability:** Follow SOLID principles, especially the Open-Closed Principle.

## Intuition

The system is ideal for the **Observer Pattern**, which manages one-to-many relationships. When an IoT device generates
an event (e.g., temperature spike), subscribed clients (e.g., mobile apps, dashboards) receive notifications if the
event matches their filters. The retry mechanism ensures reliability, simulating real-world IoT platforms like smart
home systems.

### Real-World Analogy

Think of a smart home hub broadcasting events from devices like thermostats or cameras. Mobile apps (for user alerts),
dashboards (for monitoring), and cloud services (for analytics) subscribe to specific events (e.g., critical alerts from
sensors). If a notification fails (e.g., due to network issues), the hub retries to ensure delivery.

### Why Observer Pattern?

- **Loose Coupling:** The publisher interacts with clients via an interface.
- **Dynamic Subscriptions:** Clients can join or leave at runtime.
- **Extensibility:** New client types (e.g., AI Analytics) can be added easily.
- **Filtering:** Supports efficient filtering by device type and severity.
- **Reliability:** Retries handle transient failures without affecting other clients.

## Design Approach

We use the **Observer Pattern** with the following components:

- **Client (Interface):** Defines `notifyEvent`, `getSubscribedDeviceTypes`, and `getSubscribedSeverities` methods.
- **IoTEvent (Class):** Represents an event with device ID, type, severity, timestamp, and data.
- **DeviceType (Enum):** Defines device types (e.g., SENSOR, LOCK, CAMERA).
- **Severity (Enum):** Defines event severities (e.g., CRITICAL, WARNING, INFO).
- **EventPublisher (Class):** Manages clients, broadcasts events, and handles retries.
- **Concrete Clients:** `MobileAppClient`, `WebDashboardClient`, `CloudServiceClient` process events differently.

### Class Diagram

```
EventPublisher (Subject)
  - clients: List<Client>
  - retryCounts: Map<Client, Integer>
  + subscribe(Client)
  + unsubscribe(Client)
  + broadcastEvent(IoTEvent)

Client (Interface)
  + notifyEvent(IoTEvent)
  + getSubscribedDeviceTypes(): Set<DeviceType>
  + getSubscribedSeverities(): Set<Severity>

IoTEvent
  - deviceId: String
  - deviceType: DeviceType
  - severity: Severity
  - timestamp: long
  - data: String
  + getDeviceId(): String
  + getDeviceType(): DeviceType
  + getSeverity(): Severity
  + getTimestamp(): long
  + getData(): String

DeviceType (Enum)
  - SENSOR, LOCK, CAMERA

Severity (Enum)
  - CRITICAL, WARNING, INFO

MobileAppClient
  - deviceId: String
  - subscribedDeviceTypes: Set<DeviceType>
  - subscribedSeverities: Set<Severity>
  + notifyEvent(IoTEvent)
  + getSubscribedDeviceTypes()
  + getSubscribedSeverities()

WebDashboardClient
  - dashboardId: String
  - subscribedDeviceTypes: Set<DeviceType>
  - subscribedSeverities: Set<Severity>
  + notifyEvent(IoTEvent)
  + getSubscribedDeviceTypes()
  + getSubscribedSeverities()

CloudServiceClient
  - serviceId: String
  - subscribedDeviceTypes: Set<DeviceType>
  - subscribedSeverities: Set<Severity>
  + notifyEvent(IoTEvent)
  + getSubscribedDeviceTypes()
  + getSubscribedSeverities()
```

### Design Choices

- **Observer Pattern:** Ensures loose coupling and supports dynamic subscriptions.
- **Filtering:** Uses `Set<DeviceType>` and `Set<Severity>` for efficient lookup.
- **Retry Mechanism:** Tracks retries per client with a `Map<Client, Integer>`, retrying up to 3 times.
- **Thread Safety:** Uses `synchronized` blocks in `EventPublisher` for concurrent access.
- **Error Handling:** Validates `IoTEvent` data and isolates client failures with try-catch.
- **Extensibility:** New clients implement the `Client` interface, adhering to the Open-Closed Principle.

## Implementation

The implementation is in Java and includes:

- `Client.java`: Interface for clients.
- `IoTEvent.java`: Represents an IoT event.
- `DeviceType.java`: Enum for device types.
- `Severity.java`: Enum for event severities.
- `EventPublisher.java`: Manages clients, broadcasts events, and handles retries.
- `MobileAppClient.java`: Sends push notifications.
- `WebDashboardClient.java`: Displays events on a UI.
- `CloudServiceClient.java`: Logs events for analysis.
- `Main.java`: Demonstrates the system.

## Key Interview Insights

When discussing this design in an interview:

1. **Justify Observer Pattern:**
    - Decouples publisher from clients, enabling extensibility.
    - Supports IoT use cases like smart home hubs or industrial monitoring.
2. **Retry Mechanism:**
    - Uses a `Map` to track retries per client, ensuring reliability.
    - Discuss trade-offs of retry delays vs. performance.
3. **Filtering:**
    - Uses `Set` for efficient device type and severity filtering.
    - Discuss filtering at publisher vs. client level.
4. **Thread Safety:**
    - Explain `synchronized` blocks for concurrent access.
    - Discuss alternatives like `CopyOnWriteArrayList` or `ReentrantReadWriteLock`.
5. **Scalability:**
    - For high-frequency events, consider a message queue (e.g., Kafka).
    - Use distributed systems for millions of clients.
6. **Edge Cases:**
    - Handle invalid events with validation in `IoTEvent`.
    - Isolate client failures with try-catch.
    - Manage subscription changes during broadcasts with synchronization.

## Interview Tips

- **Explain Thought Process:** Start with requirements, justify the Observer Pattern, and walk through the class
  diagram.
- **Draw Class Diagram:** Sketch the diagram, highlighting publisher-client relationships and retry logic.
- **Discuss Trade-offs:**
    - Synchronous vs. asynchronous notifications (synchronous is simpler but less scalable).
    - Retry mechanism vs. performance (retries add latency).
- **Handle Follow-ups:**
    - Async notifications? Use `ExecutorService` or message queue.
    - Persist events? Store in Redis or a database.
    - New filters? Add attributes to `IoTEvent` (e.g., location-based filtering).
- **Test Scenarios:** Test filtering, retries, concurrency, and edge cases (e.g., invalid events).

## Potential Extensions

- **Asynchronous Notifications:** Use a thread pool or message queue for non-blocking broadcasts.
- **Event Persistence:** Store events in a database for auditing.
- **Priority Notifications:** Prioritize CRITICAL events over others.
- **Client Authentication:** Validate clients before subscribing.
- **Metrics:** Track event frequency and retry success rates.

## Common Interview Questions

- **How do you handle high-frequency events?** Use a message queue or optimize data structures.
- **How do you ensure reliability?** Implement retries and logging for failures.
- **How do you scale to millions of clients?** Use a distributed pub-sub system like Kafka.
- **Can you use another pattern?** Discuss Mediator or Event Bus, with pros and cons.

## Resources for Further Learning

- **Books:**
    - *Design Patterns: Elements of Reusable Object-Oriented Software* by Gang of Four.
    - *Head First Design Patterns* for intuitive explanations.
- **Online Resources:**
    - Refactoring Guru (https://refactoring.guru/design-patterns/observer).
    - System Design Primer (https://github.com/donnemartin/system-design-primer).
- **Practice Problems:**
    - Design a Notification System, Message Broadcasting System, or Stock Price Update System to reinforce the Observer
      Pattern.

> ✨ Practice explaining this design and extending it to ace your interview!