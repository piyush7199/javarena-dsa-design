# Real-Time Stock Price Update System Design (Observer Pattern)

## Problem Statement

Design a **Real-Time Stock Price Update System** that allows multiple subscribers (e.g., Trader Dashboards, Mobile Apps,
Analytics Services) to receive real-time stock price updates from a stock exchange. The system must support dynamic
subscription management, priority-based delivery for premium subscribers, and filtering by stock symbols or categories (
e.g., TECH, ENERGY). The system should be extensible, handle high-frequency updates, and ensure loose coupling between
the publisher and subscribers.

### Functional Requirements

- **Stock Price Updates:** Broadcast stock price updates to all relevant subscribers.
- **Subscriber Management:** Support dynamic subscription and unsubscription at runtime.
- **Subscriber Types:** Support different subscriber types (e.g., Trader Dashboard, Mobile App, Analytics Service).
- **Filtering:** Allow subscribers to filter updates by stock symbols (e.g., AAPL) or categories (e.g., TECH).
- **Priority Delivery:** Process updates for premium subscribers before regular subscribers.
- **Stock Data Payload:** Include stock symbol, price, timestamp, and category in updates.

### Non-Functional Requirements

- **Extensibility:** Add new subscriber types or filters without modifying core code.
- **Loose Coupling:** Ensure the publisher is independent of subscriber implementations.
- **High Performance:** Handle thousands of updates per second with low latency.
- **Thread Safety:** Support concurrent updates and subscriptions safely.
- **Reliability:** Ensure no subscriber misses updates unless unsubscribed, and handle failures gracefully.
- **Maintainability:** Follow SOLID principles, especially the Open-Closed Principle.

## Intuition

The system is ideal for the **Observer Pattern**, which manages one-to-many relationships. When a stock exchange
publishes a price update, subscribers (e.g., trader dashboards or mobile apps) receive it if they’re interested in the
stock or category. Priority delivery ensures premium subscribers get updates first, simulating real-world financial
systems.

### Real-World Analogy

Think of a stock exchange as a news ticker broadcasting price updates. Traders, apps, and analytics platforms (
subscribers) receive updates for stocks they follow. Premium traders get priority access to ensure faster
decision-making, like VIP clients receiving exclusive updates.

### Why Observer Pattern?

- **Loose Coupling:** The publisher interacts with subscribers via an interface.
- **Dynamic Subscriptions:** Subscribers can join or leave at runtime.
- **Extensibility:** New subscriber types (e.g., AI Trading Bot) can be added easily.
- **Priority Support:** Separate subscriber lists enable priority-based delivery.
- **Filtering:** Subscribers filter updates based on symbols or categories, reducing unnecessary processing.

## Design Approach

We use the **Observer Pattern** with the following components:

- **Subscriber (Interface):** Defines `update`, `getSubscribedSymbols`, `getSubscribedCategories`, and `isPremium`
  methods.
- **StockPriceUpdate (Class):** Represents a stock update with symbol, price, timestamp, and category.
- **Category (Enum):** Defines stock categories (e.g., TECH, ENERGY, FINANCE).
- **StockPricePublisher (Class):** Manages premium and regular subscribers, broadcasting updates with priority.
- **Concrete Subscribers:** `TraderDashboardSubscriber`, `MobileAppSubscriber`, `AnalyticsServiceSubscriber` process
  updates differently.

### Class Diagram

```
StockPricePublisher (Subject)
  - premiumSubscribers: List<Subscriber>
  - regularSubscribers: List<Subscriber>
  + subscribe(Subscriber)
  + unsubscribe(Subscriber)
  + broadcastUpdate(StockPriceUpdate)

Subscriber (Interface)
  + update(StockPriceUpdate)
  + getSubscribedSymbols(): Set<String>
  + getSubscribedCategories(): Set<Category>
  + isPremium(): boolean

StockPriceUpdate
  - symbol: String
  - price: double
  - timestamp: long
  - category: Category
  + getSymbol(): String
  + getPrice(): double
  + getTimestamp(): long
  + getCategory(): Category

Category (Enum)
  - TECH, ENERGY, FINANCE

TraderDashboardSubscriber
  - dashboardId: String
  - subscribedSymbols: Set<String>
  - subscribedCategories: Set<Category>
  - isPremium: boolean
  + update(StockPriceUpdate)
  + getSubscribedSymbols()
  + getSubscribedCategories()
  + isPremium()

MobileAppSubscriber
  - deviceId: String
  - subscribedSymbols: Set<String>
  - subscribedCategories: Set<Category>
  - isPremium: boolean
  + update(StockPriceUpdate)
  + getSubscribedSymbols()
  + getSubscribedCategories()
  + isPremium()

AnalyticsServiceSubscriber
  - serviceId: String
  - subscribedSymbols: Set<String>
  - subscribedCategories: Set<Category>
  - isPremium: boolean
  + update(StockPriceUpdate)
  + getSubscribedSymbols()
  + getSubscribedCategories()
  + isPremium()
```

### Design Choices

- **Observer Pattern:** Ensures loose coupling and supports dynamic subscriptions.
- **Priority Delivery:** Uses separate lists for premium and regular subscribers to prioritize notifications.
- **Filtering:** Uses `Set<String>` for symbols and `Set<Category>` for categories to optimize filtering.
- **Thread Safety:** Uses `synchronized` blocks in `StockPricePublisher` for concurrent access.
- **Error Handling:** Validates `StockPriceUpdate` data and wraps `update` calls in try-catch.
- **Extensibility:** New subscribers implement the `Subscriber` interface, adhering to the Open-Closed Principle.

## Implementation

The implementation is in Java and includes:

- `Subscriber.java`: Interface for subscribers.
- `StockPriceUpdate.java`: Represents a stock price update.
- `Category.java`: Enum for stock categories.
- `StockPricePublisher.java`: Manages subscribers and broadcasts updates.
- `TraderDashboardSubscriber.java`: Displays updates on a dashboard.
- `MobileAppSubscriber.java`: Sends push notifications.
- `AnalyticsServiceSubscriber.java`: Logs updates for analysis.
- `Main.java`: Demonstrates the system.

## Key Interview Insights

When discussing this design in an interview:

1. **Justify Observer Pattern:**
    - Decouples publisher from subscribers, enabling extensibility.
    - Supports real-world use cases like stock tickers or event-driven systems.
2. **Priority Delivery:**
    - Separate lists ensure premium subscribers are notified first.
    - Discuss alternatives like a priority queue for more complex prioritization.
3. **Filtering:**
    - Uses `Set` for efficient symbol and category filtering.
    - Discuss trade-offs of filtering at publisher vs. subscriber level.
4. **Scalability:**
    - For high-frequency updates, consider a message queue (e.g., Kafka) or async processing.
    - Use `CopyOnWriteArrayList` for subscriber lists in production.
5. **Thread Safety:**
    - Explain `synchronized` blocks for concurrent access.
    - Discuss alternatives like `ConcurrentHashMap` or `ReentrantReadWriteLock`.
6. **Edge Cases:**
    - Handle invalid updates with validation in `StockPriceUpdate`.
    - Use try-catch to isolate subscriber failures.
    - Manage subscription changes during broadcasts with synchronization.

## Interview Tips

- **Explain Thought Process:** Start with requirements, justify the Observer Pattern, and walk through the class
  diagram.
- **Draw Class Diagram:** Sketch the diagram, highlighting publisher-subscriber relationships and priority handling.
- **Discuss Trade-offs:**
    - Synchronous vs. asynchronous updates (synchronous is simpler but less scalable).
    - Memory usage for large subscriber lists (use efficient data structures).
- **Handle Follow-ups:**
    - Async updates? Use `ExecutorService` or message queue.
    - Persist data? Store in Redis or a database.
    - New filters? Add attributes to `StockPriceUpdate` (e.g., price thresholds).
- **Test Scenarios:** Test filtering, priority delivery, concurrency, and edge cases (e.g., invalid data).

## Potential Extensions

- **Asynchronous Updates:** Use a thread pool or message queue for non-blocking broadcasts.
- **Data Persistence:** Store updates in a database for historical analysis.
- **Advanced Filtering:** Support price thresholds or volume-based filters.
- **Subscriber Authentication:** Validate subscribers before adding.
- **Metrics:** Track update frequency and subscriber performance.

## Common Interview Questions

- **How do you handle high-frequency updates?** Use a message queue or optimize data structures.
- **How do you ensure priority delivery?** Separate lists or a priority queue.
- **How do you scale to millions of subscribers?** Use a distributed pub-sub system like Kafka.
- **Can you use another pattern?** Discuss Mediator or Event Bus, with pros and cons.

## Resources for Further Learning

- **Books:**
    - *Design Patterns: Elements of Reusable Object-Oriented Software* by Gang of Four.
    - *Head First Design Patterns* for intuitive explanations.
- **Online Resources:**
    - [Refactoring Guru](https://refactoring.guru/design-patterns/observer).
    - [System Design Primer](https://github.com/donnemartin/system-design-primer).
- **Practice Problems:**
    - Design a Notification System or Message Broadcasting System to reinforce the Observer Pattern.

> ✨ Practice explaining this design and extending it to ace your interview!