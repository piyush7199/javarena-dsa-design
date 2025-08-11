# Message Broadcasting System Design (Observer Pattern)

## Problem Statement

Design a Message Broadcasting System that allows multiple subscribers (e.g., Web Client, Mobile App, Email Service) to
receive messages when a publisher broadcasts an event. The system should support dynamic addition or removal of
subscribers and allow subscribers to filter messages based on their type (e.g., NEWS, CHAT, ALERT). The system must be
extensible to accommodate new subscriber types and ensure loose coupling between the publisher and subscribers.

### Functional Requirements

- **Message Broadcasting:** Publishers broadcast messages to all interested subscribers.
- **Subscriber Management:** Support dynamic subscription and unsubscription at runtime.
- **Subscriber Types:** Support different subscriber types (e.g., Web Client, Mobile App, Email Service) that process
  messages differently.
- **Message Filtering:** Allow subscribers to specify message types they want to receive.
- **Message Payload:** Support a flexible payload (string for simplicity, extensible to structured data).

### Non-Functional Requirements

- **Extensibility:** Add new subscriber types without modifying the publisher.
- **Loose Coupling:** Ensure the publisher is independent of subscriber implementations.
- **Scalability:** Handle a large number of subscribers efficiently.
- **Thread Safety:** Support concurrent broadcasting and subscription updates.
- **Maintainability:** Follow SOLID principles, especially the Open-Closed Principle.

## Intuition

The Message Broadcasting System is ideal for the **Observer Pattern**, which manages one-to-many dependencies. When a
publisher generates a message (e.g., a news update or chat message), all subscribed entities are notified, but only
those interested in the message type process it. This pattern ensures loose coupling and dynamic subscription
management.

### Real-World Analogy

Think of a radio station (publisher) broadcasting different programs (messages) like news, music, or alerts. Listeners (
subscribers) tune in to specific programs they care about. New listeners can join, and existing ones can leave without
affecting the station.

### Why Observer Pattern?

- **Loose Coupling:** The publisher only interacts with subscribers via an interface.
- **Dynamic Subscriptions:** Subscribers can join or leave at runtime.
- **Extensibility:** New subscriber types (e.g., Slack bot) can be added easily.
- **Filtering Support:** Subscribers can filter messages based on type, reducing unnecessary processing.

## Design Approach

We use the **Observer Pattern** with the following components:

- **Subscriber (Interface):** Defines the `update` method for receiving messages and `getSubscribedTypes` for filtering.
- **Message (Class):** Represents the message with content and type (using `MessageType` enum).
- **MessagePublisher (Class):** The subject that manages subscribers and broadcasts messages, checking their subscribed
  types.
- **Concrete Subscribers (WebClientSubscriber, MobileAppSubscriber, EmailServiceSubscriber):** Process messages for
  specific channels.

### Class Diagram

```
MessagePublisher (Subject)
  - subscribers: List<Subscriber>
  + subscribe(Subscriber)
  + unsubscribe(Subscriber)
  + broadcastMessage(Message)

Subscriber (Interface)
  + update(Message)
  + getSubscribedTypes(): Set<MessageType>

Message
  - content: String
  - type: MessageType
  + getContent(): String
  + getType(): MessageType

MessageType (Enum)
  - NEWS, CHAT, ALERT

WebClientSubscriber
  - clientId: String
  - subscribedTypes: Set<MessageType>
  + update(Message)
  + getSubscribedTypes()

MobileAppSubscriber
  - deviceId: String
  - subscribedTypes: Set<MessageType>
  + update(Message)
  + getSubscribedTypes()

EmailServiceSubscriber
  - emailAddress: String
  - subscribedTypes: Set<MessageType>
  + update(Message)
  + getSubscribedTypes()
```

### Design Choices

- **Observer Pattern:** Ensures loose coupling and supports dynamic subscriptions.
- **Message Filtering:** Uses a `Set<MessageType>` in subscribers to filter messages, improving efficiency.
- **Thread Safety:** Uses `synchronized` blocks in `MessagePublisher` to handle concurrent subscriptions and broadcasts.
- **Simple Payload:** Uses a `Message` class with a string and `MessageType` for simplicity, extensible to JSON or other
  formats.
- **Error Handling:** Wraps `update` calls in try-catch to prevent subscriber failures from affecting others.

## Implementation

The implementation is in Java and includes:

- `Subscriber.java`: Interface for subscribers.
- `Message.java`: Represents a message with content and type.
- `MessageType.java`: Enum for message types (NEWS, CHAT, ALERT).
- `MessagePublisher.java`: Manages subscribers and broadcasts messages.
- `WebClientSubscriber.java`: Displays messages on a webpage.
- `MobileAppSubscriber.java`: Sends push notifications.
- `EmailServiceSubscriber.java`: Sends emails.
- `Main.java`: Demonstrates the system.

### Folder Structure

```
├── README.md
├── subscriber
│    ├── Subscriber.java
│    ├── WebClientSubscriber.java
│    ├── MobileAppSubscriber.java
│    ├── EmailServiceSubscriber.java
├── publisher
│    ├── Publisher.java
│    ├── MessagePublisher.java
├── models
│    ├── Message.java
│    ├── MessageType.java
└── Main.java
```

## Key Interview Insights

When discussing this design in an interview:

1. **Justify Observer Pattern:**

    - Explain how it decouples the publisher from subscribers.
    - Highlight real-world use cases (e.g., pub-sub systems, event listeners in GUI frameworks).

2. **Extensibility:**

    - Adding a new subscriber (e.g., Slack bot) requires only a new class implementing `Subscriber`.
    - Follows the Open-Closed Principle.

3. **Scalability:**

    - For large-scale systems, use a message queue (e.g., Kafka) or async processing with a thread pool.
    - Discuss using `CopyOnWriteArrayList` for thread-safe subscriber lists.

4. **Thread Safety:**

    - Explain the use of `synchronized` blocks in `MessagePublisher`.
    - Discuss alternatives like `ConcurrentHashMap` for subscriber management.

5. **Edge Cases:**

    - Handle subscriber failures with try-catch in `broadcastMessage`.
    - Manage concurrent subscriptions using synchronized blocks.
    - Discuss filtering to prevent unnecessary notifications.

6. **Alternative Patterns:**

    - **Mediator Pattern:** For coordinated subscriber interactions.
    - **Event Bus:** For complex event-driven systems with multiple publishers.

## Interview Tips

- **Explain Thought Process:** Start with requirements, justify the Observer Pattern, and walk through the class
  diagram.
- **Draw Class Diagram:** Sketch the diagram on a whiteboard, showing publisher-subscriber relationships.
- **Discuss Trade-offs:**
    - Synchronous vs. asynchronous broadcasting (synchronous is simpler but less scalable).
    - Memory usage for large subscriber lists (use efficient data structures).
- **Handle Follow-ups:**
    - How to add async broadcasting? Use an `ExecutorService` or message queue.
    - How to persist messages? Store in a database or Redis.
    - How to handle message prioritization? Add a priority field to `Message`.
- **Test Scenarios:** Explain how you’d test filtering, concurrency, and edge cases.

## Potential Extensions

- **Asynchronous Broadcasting:** Use a thread pool or message queue for non-blocking broadcasts.
- **Message Persistence:** Store messages in a database for auditing or replay.
- **Priority Messages:** Add a priority field to `Message` for urgent alerts.
- **Subscriber Authentication:** Validate subscribers before adding them.
- **Advanced Filtering:** Support complex filters (e.g., based on message content or metadata).

## Common Interview Questions

- **How do you handle a failing subscriber?** Use try-catch in `broadcastMessage` to isolate failures.
- **How do you scale to thousands of subscribers?** Use a distributed pub-sub system like Kafka or Redis Pub/Sub.
- **How do you implement message filtering?** Use `MessageType` and `Set<MessageType>` in subscribers.
- **Can you use another pattern?** Discuss Mediator or Event Bus, with pros and cons.

## Resources for Further Learning

- **Books:**
    - *Design Patterns: Elements of Reusable Object-Oriented Software* by Gang of Four.
    - *Head First Design Patterns* for intuitive explanations.
- **Online Resources:**
    - [Refactoring Guru](https://refactoring.guru/design-patterns/observer).
    - [System Design Primer](https://github.com/donnemartin/system-design-primer).
- **Practice Problems:**
    - Design a Notification System, Stock Market Update System, or Event Listener System to reinforce the Observer
      Pattern.

> ✨ Practice explaining this design and extending it to build confidence for your interview!