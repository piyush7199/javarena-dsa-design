# Consistency Models

## Definition

Consistency models define the rules governing how data is read and written in databases or distributed systems. They
specify guarantees about the visibility, ordering, and freshness of data updates, balancing data accuracy (consistency)
with performance (latency) and availability.

---

## Why It Matters

Choosing the right consistency model is critical for meeting application requirements. For example, financial systems
demand strong consistency to prevent errors, while social media feeds can use eventual consistency for better
scalability. In system design interviews, consistency models test your ability to navigate trade-offs in distributed
systems.

---

## Key Concepts

### 1. Strong Consistency

- **Definition**: All reads reflect the most recent successful write across all nodes.
- **How It Works**: Ensures every read sees the latest write, requiring coordination between nodes (e.g., via
  synchronous replication).
- **Examples**:
    - Relational databases like MySQL or PostgreSQL with synchronous replication.
    - Google Spanner, leveraging TrueTime for globally consistent reads.
- **Use Cases**:
    - Financial systems (e.g., bank account balances).
    - Inventory management (e.g., e-commerce stock levels).
- **Trade-offs**:
    - **Pros**: Guarantees data accuracy, simplifies application logic.
    - **Cons**: Higher latency due to node coordination, reduced availability during network partitions.

### 2. Eventual Consistency

- **Definition**: Reads may return stale data temporarily, but all updates eventually propagate to all nodes.
- **How It Works**: Nodes serve data without waiting for full replication, prioritizing performance.
- **Examples**:
    - NoSQL databases like Amazon DynamoDB (eventual consistency mode) or Apache Cassandra.
    - DNS systems, where domain updates propagate over time.
- **Use Cases**:
    - Social media feeds (e.g., Twitter timelines).
    - Non-critical analytics (e.g., video view counts).
- **Trade-offs**:
    - **Pros**: Lower latency, higher availability, and scalability.
    - **Cons**: Potential stale data, requiring conflict resolution (e.g., last-write-wins).

### 3. Causal Consistency

- **Definition**: Guarantees that causally related operations (e.g., a write followed by a read) are seen in the correct
  order, but unrelated operations may vary across nodes.
- **How It Works**: Tracks dependencies (e.g., via vector clocks) to maintain logical order without global
  synchronization.
- **Examples**:
    - Facebook’s news feed, ensuring comments appear after their parent post.
    - Riak with causal context metadata.
- **Use Cases**:
    - Chat applications (messages in order).
    - Collaborative editing tools (e.g., Google Docs).
- **Trade-offs**:
    - **Pros**: Balances consistency and performance for dependent operations.
    - **Cons**: Complex to implement due to dependency tracking.

### 4. CAP Theorem

- **Definition**: In distributed systems, you can guarantee only two of three properties:
    - **Consistency (C)**: All nodes see the same data.
    - **Availability (A)**: Every request gets a response.
    - **Partition Tolerance (P)**: System operates despite network partitions.
- **Implications**:
    - **CP Systems**: Prioritize Consistency and Partition Tolerance (e.g., MongoDB in strong consistency mode, HBase).
        - Example: Sacrifices availability during partitions for data accuracy.
    - **AP Systems**: Prioritize Availability and Partition Tolerance (e.g., DynamoDB, Cassandra).
        - Example: Returns stale data during partitions to stay available.
    - **CA Systems**: Prioritize Consistency and Availability (e.g., single-node SQL databases).
        - Example: Not partition-tolerant, rare in distributed systems.
- **Note**: Network partitions are inevitable, so most systems are AP or CP.

### 5. Isolation Levels (Related Concept)

- **Definition**: Isolation levels define how transactions in a database are isolated from each other, controlling
  visibility of uncommitted changes. They are critical for transaction consistency in relational databases.
- **Relation to Consistency Models**: Isolation levels (e.g., Read Committed, Serializable) affect data consistency
  within a single database, while consistency models apply to distributed systems.
- **Details**: See [Isolation Levels](isolation_levels.md) for in-depth coverage.

---

## Real-World Examples

- **Amazon DynamoDB**: Offers eventual consistency for high-throughput e-commerce (e.g., cart updates) and strong
  consistency for payments.
- **MongoDB**: Configurable consistency; defaults to AP but supports CP for critical operations.
- **Google Spanner**: Uses TrueTime for strong consistency, ideal for financial transactions.
- **Twitter**: Employs eventual consistency for timelines, prioritizing low latency.

---

## Trade-offs

- **Strong vs. Eventual Consistency**:
    - Strong: Ensures accuracy but increases latency and reduces availability.
    - Eventual: Boosts performance and availability but risks stale data.
- **Causal Consistency**:
    - Middle ground but adds complexity with dependency tracking.
- **CAP Theorem Choices**:
    - CP: Best for data-critical systems (e.g., banking).
    - AP: Suited for high-availability systems (e.g., social media).
- **Tunable Consistency**:
    - Databases like DynamoDB and Cassandra allow per-operation consistency levels for flexibility.

---

## Important Notes

- **Application Needs**:
    - Strong consistency for critical data (e.g., account balances).
    - Eventual consistency for scalable, non-critical data (e.g., post likes).
- **Conflict Resolution**:
    - Eventual consistency requires mechanisms like last-write-wins or vector clocks.
    - Example: DynamoDB uses timestamps for conflict resolution.
- **Network Partitions**:
    - Design assuming partitions occur; choose CP or AP based on priorities.
- **Hybrid Models**:
    - Use tunable consistency (e.g., Cassandra’s quorum reads) for workload-specific needs.
- **Testing**:
    - Simulate partitions or failures to verify consistency behavior.

---

## Interview Guidance

### Common Interview Questions

1. **Explain strong vs. eventual consistency**:
    - Strong: MySQL with synchronous replication for banking.
    - Eventual: DynamoDB for social feeds.
    - Discuss trade-offs: latency vs. availability.
2. **How does CAP theorem apply to [System X]?**:
    - Example: Twitter is AP, prioritizing availability for tweets during partitions.
3. **Design a system with specific consistency requirements**:
    - Example: Payment system (strong consistency) vs. news feed (eventual consistency).
4. **What happens during a network partition in [Database X]?**:
    - Example: Cassandra (AP) may return stale data; MongoDB (CP) may reject writes.

### Tips for Interviews

- **Clarify Requirements**: Ask if the system prioritizes accuracy or availability.
- **Use CAP Framework**: Justify CP or AP choices with use cases.
- **Draw Diagrams**: Show data flow (client → API → replicas) to illustrate consistency.
- **Discuss Trade-offs**: Highlight pros/cons of your consistency choice.
- **Handle Follow-ups**: Be ready for questions like “What if a node fails?” or “How do you handle conflicts?”
- **Real-World Examples**: Reference systems like Netflix (Cassandra, AP) or PayPal (SQL, CP).

---

## Sample Interview Problem: Design a Notification System’s Database

### Problem Statement

Design a database for a notification system (e.g., WhatsApp, Gmail) serving 100M users, with 1B notifications/day,
requiring low-latency delivery and eventual consistency for non-critical notifications.

### Approach

- **Functional Requirements**:
    - Store and deliver notifications (e.g., message alerts).
    - Allow users to mark notifications as read.
- **Non-Functional Requirements**:
    - Scalability: Handle 1B notifications/day (~12K QPS).
    - Low latency: Deliver notifications in <1s.
    - Eventual consistency: Slight delays in read status are acceptable.
- **Capacity Estimation**:
    - Storage: 1B notifications × 1KB/notification = 1TB/day.
    - Reads: 100M users × 10 reads/day = 1B reads/day (~12K QPS).
- **Database Choice**:
    - NoSQL (e.g., Cassandra) for scalability and eventual consistency.
    - Cache (e.g., Redis) for low-latency reads of recent notifications.

### Solution

- **Schema**:
    - Table: `notifications`
        - `user_id` (partition key, to shard by user).
        - `notification_id` (clustering key, unique per notification).
        - `content` (e.g., “New message from Alice”).
        - `status` (e.g., unread/read).
        - `created_at` (timestamp for sorting).
- **Consistency Model**:
    - Eventual consistency for notification delivery and status updates.
    - Use Cassandra’s tunable consistency (e.g., write with ONE, read with QUORUM).
- **Replication**:
    - Replicate data across 3 nodes per data center for fault tolerance.
    - Reads go to any replica, accepting potential staleness.
- **Caching**:
    - Cache recent notifications in Redis (`user_id → [notification_id, content]`).
    - Cache TTL: 24 hours for active notifications.
- **Trade-offs:**
    - Eventual consistency: Fast writes but users may see stale read statuses briefly.
    - Cassandra: Scales well but complex to manage compared to SQL.

- **Scalability:**
    - Add more Cassandra nodes to handle increased QPS.
    - Shard by user_id to distribute load evenly.
- **Architecture Diagram**:
  ```mermaid
  graph TD
    A[Client] --> B[Load Balancer]
    B --> C[API Server]
    C --> D[Redis Cache]
    C --> E[Cassandra Cluster]
    E --> F[Node 1]
    E --> G[Node 2]
    E --> H[Node 3]

---

## Resources

- Designing Data-Intensive Applications by Martin Kleppmann
- [System Design Primer: Consistency patterns](https://github.com/donnemartin/system-design-primer?tab=readme-ov-file#consistency-patterns)
- AWS DynamoDB Consistency Models