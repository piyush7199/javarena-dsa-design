# Database Design and Databases

## Definition

Database design is the process of structuring and organizing data storage to meet an application's functional and
non-functional requirements. It involves selecting the appropriate database type (e.g., SQL, NoSQL), designing schemas,
and ensuring scalability, performance, reliability, and security.

---

## Why It Matters

Databases power most modern applications, from e-commerce to social media. Effective database design ensures:

- **Low Latency**: Fast data retrieval for user queries.
- **High Availability**: Minimal downtime, even during failures.
- **Scalability**: Ability to handle growing data and traffic.
- **Reliability**: Consistent and accurate data access.

In system design interviews, database design tests your ability to make informed decisions about trade-offs like
consistency vs. performance or scalability vs. complexity.

---

## Key Concepts

### 1. Types of Databases

- **Relational Databases (SQL)**:
    - Structured schemas with tables, rows, and columns.
    - Support ACID transactions (Atomicity, Consistency, Isolation, Durability).
    - Examples: MySQL, PostgreSQL, Oracle.
    - Use Cases: Financial systems, ERP, or apps with complex queries.
- **NoSQL Databases**:
    - Handle unstructured/semi-structured data and scale horizontally.
    - Types:
        - **Key-Value**: Redis, DynamoDB (e.g., caching, session storage).
        - **Document**: MongoDB, CouchDB (e.g., content management).
        - **Column-Family**: Cassandra, HBase (e.g., analytics).
        - **Graph**: Neo4j, ArangoDB (e.g., social networks, recommendation systems).
    - Use Cases: Real-time analytics, social media, IoT.
- **In-Memory Databases**:
    - Store data in RAM for ultra-low latency.
    - Examples: Redis, Memcached.
    - Use Cases: Caching, real-time leaderboards, session stores.
- **Time-Series Databases**:
    - Optimized for time-stamped data.
    - Examples: InfluxDB, TimescaleDB.
    - Use Cases: IoT, monitoring, analytics.

### 2. Schema Design

- **Normalization** (SQL):
    - Reduces redundancy and ensures data consistency via normal forms (1NF, 2NF, 3NF).
    - Example: Splitting a customer-order table into separate `Customers` and `Orders` tables.
    - Trade-off: Complex queries may require multiple joins, increasing latency.
- **Denormalization** (NoSQL or SQL):
    - Combines data to optimize read performance.
    - Example: Embedding order details in a customer document in MongoDB.
    - Trade-off: Higher storage and update complexity.
- **Indexes**:
    - Data structures (e.g., B+ trees, hash tables) that speed up query performance.
    - **Types of Indexes**:
        - **Primary Index**: Built on the primary key, ensures unique identification.
        - **Secondary Index**: Built on non-key columns for faster searches (e.g., index on `email` in a `Users` table).
        - **Composite Index**: Covers multiple columns (e.g., index on `user_id` and `order_date`).
        - **Clustered Index**: Determines physical order of data (e.g., in SQL Server, only one per table).
        - **Non-Clustered Index**: Separate structure pointing to data (e.g., multiple allowed in MySQL).
        - **Full-Text Index**: Optimizes text searches (e.g., in PostgreSQL for search engines).
        - **Bitmap Index**: Efficient for low-cardinality columns (e.g., gender).
    - **Trade-offs**:
        - Pros: Faster reads for specific queries.
        - Cons: Increased storage, slower writes due to index updates.
    - **Best Practices**:
        - Index frequently queried columns (e.g., `WHERE`, `JOIN`, `ORDER BY`).
        - Avoid over-indexing to minimize write overhead.
        - Monitor index usage to drop unused indexes.

### 3. Scalability Techniques

- **Sharding**:
    - Splits data across multiple servers using a shard key (e.g., `user_id` or geographic region).
    - Example: Instagram shards user data by `user_id` to distribute load.
    - Benefits: Scales storage and query throughput.
    - Challenges: Complex cross-shard queries, uneven shard distribution.
- **Replication**:
    - Copies data to multiple servers for redundancy and performance.
    - **Types of Replication**:
        - **Master-Slave Replication**:
            - One master handles writes; slaves handle reads.
            - Example: MySQL with a single write master and multiple read replicas.
            - Benefit: Scales read-heavy workloads.
            - Challenge: Slave lag (eventual consistency for reads).
        - **Master-Master Replication**:
            - Multiple servers handle both reads and writes.
            - Example: MariaDB Galera for distributed systems.
            - Benefit: High availability for writes.
            - Challenge: Conflict resolution for concurrent writes.
        - **Asynchronous Replication**:
            - Writes propagate to replicas with a delay.
            - Example: MongoDB replica sets.
            - Benefit: Low write latency.
            - Challenge: Temporary inconsistencies.
        - **Synchronous Replication**:
            - Writes must commit on all replicas before acknowledgment.
            - Example: PostgreSQL with synchronous_commit=on.
            - Benefit: Strong consistency.
            - Challenge: Higher write latency.
    - **Best Practices**:
        - Use replication for read-heavy systems (e.g., social media feeds).
        - Combine with sharding for write-heavy systems (e.g., e-commerce).
- **Partitioning**:
    - Divides a table into smaller chunks based on a partition key (e.g., date ranges).
    - Example: Partitioning a `Logs` table by `year`.
    - Benefits: Faster queries on large tables, easier data archiving.
    - Challenges: Requires careful partition key selection.

### 4. Consistency Models

- **Strong Consistency**:
    - All reads reflect the latest write.
    - Example: SQL databases like PostgreSQL.
    - Trade-off: Higher latency in distributed systems.
- **Eventual Consistency**:
    - Reads may return stale data but converge over time.
    - Example: DynamoDB for high-throughput systems.
    - Trade-off: Faster writes, potential staleness.
- **CAP Theorem**:
    - Choose two: Consistency, Availability, Partition Tolerance.
    - Example: Cassandra (AP) prioritizes availability; Spanner (CP) prioritizes consistency.

### 5. Real-World Examples

- **Amazon DynamoDB**: NoSQL database with sharding and eventual consistency for e-commerce.
- **PostgreSQL at Uber**: Handles structured trip data with replication for availability.
- **Redis at Twitter**: In-memory caching for timelines, reducing database load.
- **Cassandra at Netflix**: Column-family database for streaming analytics with high scalability.

---

## Trade-offs

- **SQL vs. NoSQL**:
    - SQL: Strong consistency, complex queries; less flexible for schema changes.
    - NoSQL: Horizontal scaling, flexible schemas; weaker consistency.
- **Sharding vs. Replication**:
    - Sharding: Scales writes but complicates queries.
    - Replication: Scales reads but increases write overhead.
- **Indexes**:
    - Pros: Faster reads for targeted queries.
    - Cons: Slower writes, higher storage.

---

## Important Notes

- **Database Selection**:
    - SQL: Structured data, transactions (e.g., banking).
    - NoSQL: Unstructured data, high scalability (e.g., social media).
    - In-Memory: Low-latency caching (e.g., leaderboards).
- **Capacity Estimation**:
    - Storage: Estimate data size (e.g., 1M users × 1KB = 1GB).
    - Throughput: Estimate QPS (e.g., 10K read QPS, 1K write QPS).
- **Avoid Over-Engineering**:
    - Start simple (e.g., single SQL database) and scale as needed (e.g., add sharding).
- **Consistency vs. Performance**:
    - Strong consistency for critical systems (e.g., payments).
    - Eventual consistency for high-throughput systems (e.g., feeds).
- **Backup and Recovery**:
    - Implement regular backups and point-in-time recovery.
- **Security**:
    - Encrypt data at rest and in transit.
    - Use role-based access control (RBAC) and row-level security.

---

## Sample HLD Questions

1. **Design a database for a ride-sharing app**:
    - Requirements: Store user profiles, trip details, and payment transactions; handle 10K QPS.
    - Approach: Use PostgreSQL for structured data (users, trips), Redis for caching trip status, and sharding by
      `region_id`.
    - Solution: `In Progress`.
2. **Design a scalable social media feed**:
    - Requirements: Store posts, comments, and likes; support 100K read QPS.
    - Approach: Use Cassandra for high write throughput, Redis for caching feeds, and eventual consistency.
    - Solution: `In Progress`.

---

## Resources

- Books: *Designing Data-Intensive Applications* by Martin Kleppmann.