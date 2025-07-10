# Isolation Levels

## Definition

Isolation levels define how transactions in a database are isolated from each other, controlling the visibility of
uncommitted changes. They are a key property of ACID transactions in relational databases (e.g., MySQL, PostgreSQL),
ensuring data consistency during concurrent operations.

## Why It Matters

Isolation levels balance transaction consistency with performance. Higher isolation levels prevent data anomalies but
increase locking and overhead, while lower levels improve concurrency but risk inconsistencies. In system design
interviews, isolation levels test your understanding of transactional behavior and trade-offs.

## Key Concepts

### 1. Types of Isolation Levels

Isolation levels, as defined by the SQL standard, range from least to most strict:

- **Read Uncommitted**:
    - **Definition**: Transactions can read uncommitted changes from other transactions.
    - **How It Works**: Allows "`dirty reads`", where a transaction sees uncommitted data that may be rolled back.
    - **Examples**: Rarely used; supported in MySQL, SQL Server.
    - **Use Cases**: Non-critical reporting where speed is prioritized.
    - **Issues**: High risk of inconsistent data.
    - **Trade-offs**:
        - **Pros**: Minimal locking, high concurrency.
        - **Cons**: Risk of reading incorrect data.

- **Read Committed**:
    - **Definition**: Transactions only read committed data, but non-repeatable reads and phantom reads are possible.
    - **How It Works**: Ensures no dirty reads, but data may change between reads within a transaction.
    - **Examples**: Default in PostgreSQL, SQL Server, Oracle.
    - **Use Cases**: General-purpose applications (e.g., user profile updates).
    - **Issues**:
        - **Non-repeatable reads**: Data read twice in a transaction may differ.
        - **Phantom reads**: New rows may appear during a transaction.
    - **Trade-offs**:
        - **Pros**: Balances consistency and performance.
        - **Cons**: Inconsistent reads within a transaction.

- **Repeatable Read**:
    - **Definition**: Ensures consistent reads within a transaction, but phantom reads are possible.
    - **How It Works**: Locks read data to prevent updates by other transactions.
    - **Examples**: Default in MySQL (InnoDB); supported in PostgreSQL.
    - **Use Cases**: Applications needing stable reads (e.g., financial reports).
    - **Issues**: Phantom reads (new rows inserted by other transactions).
    - **Trade-offs**:
        - **Pros**: Stronger consistency than Read Committed.
        - **Cons**: Increased locking, potential deadlocks.

- **Serializable**:
    - **Definition**: Transactions execute as if run sequentially, preventing all anomalies.
    - **How It Works**: Uses strict locking or multiversion concurrency control (MVCC) to ensure complete isolation.
    - **Examples**: Supported in PostgreSQL, MySQL, Oracle.
    - **Use Cases**: Critical systems (e.g., banking, inventory control).
    - **Issues**: Lowest concurrency due to heavy locking.
    - **Trade-offs**:
        - **Pros**: Maximum consistency, no anomalies.
        - **Cons**: High latency, risk of deadlocks, reduced throughput.

### 2. Common Anomalies

- **Dirty Reads**: Reading uncommitted data that may be rolled back.
- **Non-repeatable Reads**: Data changes between reads within a transaction.
- **Phantom Reads**: New rows appear during a transaction due to inserts by other transactions.
- **Lost Updates**: Concurrent transactions overwrite each other’s changes.

### 3. Implementation Techniques

- **Locking**: Uses shared (read) or exclusive (write) locks to enforce isolation.
    - Example: MySQL’s Repeatable Read uses row-level locks.
- **Multiversion Concurrency Control (MVCC)**:
    - Creates data snapshots for transactions to read consistent versions.
    - Example: PostgreSQL uses MVCC for Read Committed and Repeatable Read.
- **Trade-offs**:
    - Locking: Simple but risks deadlocks and contention.
    - MVCC: Improves concurrency but increases storage for versioned data.

## Real-World Examples

- **PostgreSQL at Uber**: Uses Repeatable Read for consistent trip data reads, with MVCC to avoid locking contention.
- **MySQL at Airbnb**: Employs Read Committed for booking updates, balancing performance and consistency.
- **Oracle at Banks**: Uses Serializable for financial transactions to prevent anomalies.

## Trade-offs

- **Higher vs. Lower Isolation**:
    - Higher (Serializable): Maximum consistency but lower concurrency and higher latency.
    - Lower (Read Uncommitted): High performance but risks anomalies.
- **Locking vs. MVCC**:
    - Locking: Simpler but prone to deadlocks.
    - MVCC: Better concurrency but higher storage overhead.

## Important Notes

- **Choose the Right Level**:
    - Use Serializable for critical systems (e.g., payments).
    - Use Read Committed for general-purpose apps (e.g., e-commerce).
- **Database Defaults**:
    - Check defaults (e.g., PostgreSQL: Read Committed, MySQL: Repeatable Read).
- **Performance Tuning**:
    - Monitor lock contention and deadlocks for high isolation levels.
    - Use MVCC for read-heavy workloads.
- **Testing**:
    - Simulate concurrent transactions to verify isolation behavior.
- **Interview Tip**:
    - Clarify if the system requires strict consistency or can tolerate anomalies.

## Interview Guidance

### Common Interview Questions

1. **Explain isolation levels with examples**:
    - Read Committed: E-commerce order updates.
    - Serializable: Bank transfers.
    - Discuss anomalies and trade-offs.
2. **How do isolation levels differ from consistency models?**:
    - Isolation levels: Transactional consistency in a single database.
    - Consistency models: Data visibility across distributed nodes.
3. **Design a system with specific isolation requirements**:
    - Example: Bank transfer system (Serializable) vs. analytics dashboard (Read Committed).
4. **What happens if two transactions update the same row?**:
    - Explain locking or MVCC behavior based on isolation level.

### Tips for Interviews

- **Clarify Requirements**: Ask if the system needs anomaly-free transactions or high concurrency.
- **Use Examples**: Reference real-world systems (e.g., Uber’s use of PostgreSQL MVCC).
- **Draw Diagrams**: Show transaction timelines or lock interactions.
- **Discuss Trade-offs**: Highlight performance vs. consistency trade-offs.
- **Handle Follow-ups**: Be ready for questions like “How do you avoid deadlocks?” or “What’s MVCC’s impact?”

## Sample Interview Problem: Design a Banking System’s Database

- **Problem Statement**: Design a database for a banking system handling 1M transactions/day, requiring strict
  consistency to prevent overdrafts.
- **Approach**:
    - **Functional Requirements**: Store accounts, transactions; support transfers.
    - **Non-Functional Requirements**:
        - Consistency: No overdrafts or lost updates.
        - Scalability: Handle 1K QPS.
    - **Capacity Estimation**:
        - Storage: 1M transactions × 500B = 500MB/day.
        - Throughput: 1K QPS for reads/writes.
    - **Database Choice**: PostgreSQL with Serializable isolation for strict consistency.
- **Solution**:
    - **Schema**:
        - Table: `accounts`
            - `account_id` (PK), `balance`.
        - Table: `transactions`
            - `transaction_id` (PK), `from_account`, `to_account`, `amount`, `timestamp`.
    - **Isolation Level**: Serializable with MVCC to prevent anomalies.
    - **Replication**: Master-slave with synchronous replication for strong consistency.
    - **Indexes**: On `account_id` for fast balance checks.

- **Trade-offs:**
    - Serializable: Prevents overdrafts but may retry transactions on conflicts.
    - MVCC: Improves concurrency but increases storage.

- **Scalability:** Add read replicas for read-heavy queries; shard by `account_id` for scale.

- **Architecture Diagram**:
  ```mermaid
  graph TD
      A[Client] --> B[API Server]
      B --> C[PostgreSQL Master]
      C --> D[PostgreSQL Slave]

## Resources

- Designing Data-Intensive Applications by Martin Kleppmann
- PostgreSQL Documentation: Transaction Isolation
- MySQL Documentation: InnoDB Transaction Model
- YouTube: System Design Interview - Transaction Isolation