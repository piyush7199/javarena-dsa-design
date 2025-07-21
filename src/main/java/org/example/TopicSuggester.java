package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class TopicSuggester {
    private static final String SUGGESTIONS_FILE_PREFIX = "TopicSuggestions_";
    private static final String HISTORY_FILE = "topic_history.txt";

    static class Topic {
        String name;
        String difficulty;
        String category;

        Topic(String name, String difficulty, String category) {
            this.name = name;
            this.difficulty = difficulty;
            this.category = category;
        }
    }

    private static final List<Topic> topics = new ArrayList<>();

    static {
        // DSA Topics
        topics.add(new Topic("Arrays (Dynamic, Static, Multi-dimensional, Sparse Arrays)", "Beginner", "dsa"));
        topics.add(new Topic("Linked Lists (Singly, Doubly, Circular, Skip Lists, XOR Linked Lists)", "Beginner", "dsa"));
        topics.add(new Topic("Stacks (Array, Linked List based, Min/Max Stack)", "Beginner", "dsa"));
        topics.add(new Topic("Queues (Array, Linked List based, Circular, Deque, Priority Queue, Monotonic Queue)", "Beginner", "dsa"));
        topics.add(new Topic("Trees (Binary Tree, Binary Search Tree (BST), AVL Trees, Red-Black Trees)", "Intermediate", "dsa"));
        topics.add(new Topic("Trees (B-Trees, B+ Trees, Trie (Prefix Tree))", "Advanced", "dsa"));
        topics.add(new Topic("Trees (Segment Trees, Fenwick Trees/BIT, Suffix Trees, Suffix Arrays, K-D Trees, Treaps)", "Advanced", "dsa"));
        topics.add(new Topic("Heaps (Min-Heap, Max-Heap)", "Beginner", "dsa"));
        topics.add(new Topic("Hash Tables (Hashing functions, Collision Resolution: Chaining, Open Addressing, Load Factor)", "Intermediate", "dsa"));
        topics.add(new Topic("Hash Tables (Cuckoo Hashing, Universal Hashing)", "Advanced", "dsa"));
        topics.add(new Topic("Graphs (Adjacency Matrix/List, Directed/Undirected, Weighted/Unweighted, Spanning Trees)", "Intermediate", "dsa"));
        topics.add(new Topic("Graphs (Bipartite Graphs, Eulerian/Hamiltonian Paths)", "Advanced", "dsa"));
        topics.add(new Topic("Disjoint Set Union (DSU) with Path Compression & Union by Rank/Size)", "Intermediate", "dsa"));
        topics.add(new Topic("Advanced Data Structures (Bloom Filters, LRU Cache, LFU Cache)", "Intermediate", "dsa"));
        topics.add(new Topic("Advanced Data Structures (Fibonacci Heap, Splay Tree)", "Advanced", "dsa"));
        topics.add(new Topic("Amortized Analysis (Concept)", "Advanced", "dsa"));
        topics.add(new Topic("Mathematics (Prefix Sum, Sieve of Eratosthenes, Fast Exponentiation, GCD with Extended Euclidean)", "Intermediate", "dsa"));
        topics.add(new Topic("Matrix Algorithms (Transpose, Rotate, Prefix Matrix, Binary Search in Matrix)", "Beginner", "dsa"));
        topics.add(new Topic("Interval Trees", "Advanced", "dsa"));
        topics.add(new Topic("Persistent Data Structures (Persistent Segment Trees, Persistent Trie)", "Advanced", "dsa"));
        topics.add(new Topic("Rolling Hash (Polynomial Hashing for Strings)", "Intermediate", "dsa"));

        // Algorithms Topics
        topics.add(new Topic("Meet in the Middle (Subset sum variations)", "Advanced", "algorithms"));
        topics.add(new Topic("Heavy-Light Decomposition", "Advanced", "algorithms"));
        topics.add(new Topic("Sorting Algorithms (Merge Sort, Quick Sort, Heap Sort)", "Beginner", "algorithms"));
        topics.add(new Topic("Sorting Algorithms (Counting Sort, Radix Sort, Bucket Sort, External Sorting)", "Intermediate", "algorithms"));
        topics.add(new Topic("Searching Algorithms (Binary Search, Ternary Search, Interpolation Search, Exponential Search)", "Beginner", "algorithms"));
        topics.add(new Topic("Recursion & Backtracking (N-Queens, Sudoku Solver, Permutations, Combinations, Subset Sum)", "Intermediate", "algorithms"));
        topics.add(new Topic("Dynamic Programming (Memoization, Tabulation, Space Optimization, Common problems like Knapsack, Longest Common Subsequence, Edit Distance, Coin Change)", "Intermediate", "algorithms"));
        topics.add(new Topic("Dynamic Programming (Matrix Chain Multiplication, Advanced DP problems)", "Advanced", "algorithms"));
        topics.add(new Topic("Greedy Algorithms (Activity Selection, Huffman Coding)", "Beginner", "algorithms"));
        topics.add(new Topic("Graph Traversal (BFS, DFS, Cycle Detection, Connected Components)", "Beginner", "algorithms"));
        topics.add(new Topic("Graph Traversal (Strong/Weakly Connected Components)", "Intermediate", "algorithms"));
        topics.add(new Topic("Shortest Path Algorithms (Dijkstra's, Bellman-Ford, Floyd-Warshall)", "Intermediate", "algorithms"));
        topics.add(new Topic("Shortest Path Algorithms (SPFA, A* Search)", "Advanced", "algorithms"));
        topics.add(new Topic("Minimum Spanning Tree (Prim's, Kruskal's)", "Intermediate", "algorithms"));
        topics.add(new Topic("Topological Sort (Kahn's Algorithm, DFS-based)", "Intermediate", "algorithms"));
        topics.add(new Topic("String Algorithms (KMP, Rabin-Karp, Z-algorithm)", "Intermediate", "algorithms"));
        topics.add(new Topic("String Algorithms (Manacher's Algorithm, Boyer-Moore)", "Advanced", "algorithms"));
        topics.add(new Topic("Bit Manipulation (Common tricks, powers of 2, XOR properties, Bitmasks)", "Beginner", "algorithms"));
        topics.add(new Topic("Divide and Conquer (Master Theorem, Karatsuba Algorithm, Strassen's Algorithm)", "Intermediate", "algorithms"));
        topics.add(new Topic("Network Flow (Max Flow Min Cut Theorem, Ford-Fulkerson, Edmonds-Karp)", "Advanced", "algorithms"));
        topics.add(new Topic("Two Pointers, Sliding Window", "Beginner", "algorithms"));
        topics.add(new Topic("Geometric Algorithms (Convex Hull, Line Intersection)", "Advanced", "algorithms"));
        topics.add(new Topic("Computational Geometry Basics", "Advanced", "algorithms"));
        topics.add(new Topic("Number Theory (Primes, GCD, LCM, Modular Arithmetic, Fermat's Little Theorem)", "Intermediate", "algorithms"));
        topics.add(new Topic("Game Theory (Nim Game, Grundy Numbers)", "Advanced", "algorithms"));
        topics.add(new Topic("Monotonic Stack/Queue Applications", "Intermediate", "algorithms"));
        topics.add(new Topic("Line Sweep Algorithms", "Advanced", "algorithms"));
        topics.add(new Topic("Union-Find with Rollback", "Advanced", "algorithms"));

        // LLD Topics
        topics.add(new Topic("Builder Pattern with Fluent Interfaces", "Intermediate", "lld"));
        topics.add(new Topic("Validation Frameworks (JSR-303, Hibernate Validator)", "Intermediate", "lld"));
        topics.add(new Topic("API Rate Limiting Implementation (Token Bucket, Leaky Bucket)", "Advanced", "lld"));
        topics.add(new Topic("SOLID Principles (Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, Dependency Inversion)", "Beginner", "lld"));
        topics.add(new Topic("Design Patterns (Creational: Singleton, Factory Method, Abstract Factory, Builder, Prototype)", "Intermediate", "lld"));
        topics.add(new Topic("Design Patterns (Structural: Adapter, Decorator, Facade, Proxy, Bridge, Composite, Flyweight)", "Intermediate", "lld"));
        topics.add(new Topic("Design Patterns (Behavioral: Observer, Strategy, Command, State, Template Method, Iterator, Mediator, Chain of Responsibility, Memento, Visitor, Interpreter)", "Advanced", "lld"));
        topics.add(new Topic("UML Diagrams (Class Diagrams, Sequence Diagrams)", "Beginner", "lld"));
        topics.add(new Topic("UML Diagrams (Component Diagrams, Activity Diagrams, State Machine Diagrams)", "Intermediate", "lld"));
        topics.add(new Topic("Concurrency Patterns (Producer-Consumer, Reader-Writer, Thread Pool)", "Intermediate", "lld"));
        topics.add(new Topic("Concurrency Patterns (Fork-Join Framework, Phaser, StampedLock, Latch, Barrier, Semaphore, Atomic Operations)", "Advanced", "lld"));
        topics.add(new Topic("Error Handling Strategies (Custom Exceptions, try-with-resources, Exception Propagation)", "Beginner", "lld"));
        topics.add(new Topic("Resilience Patterns (Circuit Breaker, Retry, Fallback)", "Intermediate", "lld"));
        topics.add(new Topic("API Design Principles (RESTful principles, Idempotency, API Versioning)", "Beginner", "lld"));
        topics.add(new Topic("API Design Principles (HATEOAS, API Gateway patterns)", "Intermediate", "lld"));
        topics.add(new Topic("Code Quality & Refactoring (Clean Code principles, Code Smells, Refactoring Techniques)", "Beginner", "lld"));
        topics.add(new Topic("Static Analysis Tools (basics)", "Intermediate", "lld"));
        topics.add(new Topic("Testing Principles (Unit Testing, Integration Testing, Mocking, Stubbing, Testable Code)", "Beginner", "lld"));
        topics.add(new Topic("Testing Principles (Test Driven Development (TDD), Behavior Driven Development (BDD))", "Intermediate", "lld"));
        topics.add(new Topic("Object-Oriented Design Case Studies (e.g., Vending Machine, Parking Lot, Tic-Tac-Toe)", "Beginner", "lld"));
        topics.add(new Topic("Object-Oriented Design Case Studies (e.g., Elevator System, Online Chess Game, ATM)", "Intermediate", "lld"));
        topics.add(new Topic("Object-Oriented Design Case Studies (e.g., Distributed Logger, Rate Limiter, URL Shortener)", "Advanced", "lld"));
        topics.add(new Topic("GRASP Principles (Creator, Information Expert, Low Coupling, High Cohesion)", "Intermediate", "lld"));
        topics.add(new Topic("GRASP Principles (Controller, Polymorphism, Pure Fabrication, Indirection, Protected Variations)", "Advanced", "lld"));
        topics.add(new Topic("Domain-Driven Design (DDD) Basics (Entities, Value Objects, Aggregates, Bounded Contexts)", "Intermediate", "lld"));
        topics.add(new Topic("Domain-Driven Design (DDD) (Domain Events, Repositories, Services)", "Advanced", "lld"));
        topics.add(new Topic("Event-Driven Architecture (LLD perspective: Events, Event Handlers, Message Bus/Broker)", "Intermediate", "lld"));
        topics.add(new Topic("Event Sourcing & CQRS (LLD perspective)", "Advanced", "lld"));
        topics.add(new Topic("Dependency Injection Frameworks (e.g., Spring IoC Container, Guice)", "Beginner", "lld"));
        topics.add(new Topic("Aspect-Oriented Programming (AOP) concepts", "Intermediate", "lld"));
        topics.add(new Topic("JVM Internals (Class Loading, Memory Management, JIT Compilation basics)", "Intermediate", "lld"));
        topics.add(new Topic("Garbage Collection (Types, Tuning basics)", "Intermediate", "lld"));
        topics.add(new Topic("Concurrency Primitives (Locks, Semaphores, Mutexes, Condition Variables)", "Intermediate", "lld"));
        topics.add(new Topic("Object Pooling Pattern", "Intermediate", "lld"));
        topics.add(new Topic("Command Query Separation (CQS)", "Intermediate", "lld"));
        topics.add(new Topic("Repository Pattern", "Intermediate", "lld"));
        topics.add(new Topic("Unit of Work Pattern", "Advanced", "lld"));

        // HLD Topics
        topics.add(new Topic("Data Modeling (ER Modeling, Star vs. Snowflake Schema)", "Intermediate", "hld"));
        topics.add(new Topic("Job Scheduling Systems (Quartz, CRON, Event-based Triggers)", "Intermediate", "hld"));
        topics.add(new Topic("Geo-based Systems (Geohashing, Radius Search, Spatial Indexing)", "Advanced", "hld"));
        topics.add(new Topic("Scalability (Vertical vs. Horizontal Scaling, Load Balancing Algorithms - Round Robin, Least Connections, IP Hash)", "Beginner", "hld"));
        topics.add(new Topic("Scalability (Weighted Round Robin, Consistent Hashing)", "Intermediate", "hld"));
        topics.add(new Topic("Caching Strategies (CDN, Application-level, Database-level, Cache Invalidation, Cache Aside, Write Through, Write Back)", "Intermediate", "hld"));
        topics.add(new Topic("Database Sharding/Partitioning (Range, Hash, Directory-based)", "Intermediate", "hld"));
        topics.add(new Topic("Replication (Master-Slave, Multi-Master, Leader-Follower; Read Replicas, Data Locality)", "Beginner", "hld"));
        topics.add(new Topic("Availability & Reliability (Redundancy, Fault Tolerance, Disaster Recovery (RTO/RPO), N-tier Architecture)", "Intermediate", "hld"));
        topics.add(new Topic("Availability & Reliability (Microservices vs. Monolith, Serverless, Health Checks, Self-healing Systems)", "Intermediate", "hld"));
        topics.add(new Topic("Resilience Patterns (Circuit Breakers, Bulkheads, Retries, Fallbacks)", "Intermediate", "hld"));
        topics.add(new Topic("Consistency Models (ACID, BASE, Strong Consistency, Eventual Consistency)", "Beginner", "hld"));
        topics.add(new Topic("Consistency Models (Causal Consistency, Read-Your-Writes Consistency, Linearizability, Sequential Consistency)", "Advanced", "hld"));
        topics.add(new Topic("Data Storage (Relational vs. NoSQL Databases - Document, Key-Value, Column-Family, Graph)", "Beginner", "hld"));
        topics.add(new Topic("Data Storage (Database Indexing, Denormalization, Data Warehousing basics, Data Lakes, ETL/ELT, OLAP/OLTP)", "Intermediate", "hld"));
        topics.add(new Topic("Messaging Systems (Message Queues - SQS, RabbitMQ, ActiveMQ; Pub/Sub - SNS, Kafka, Google Pub/Sub)", "Intermediate", "hld"));
        topics.add(new Topic("Messaging Systems (Message Brokers, Idempotent Consumers, Dead Letter Queues, Message Ordering, Retries)", "Advanced", "hld"));
        topics.add(new Topic("Security (Authentication - OAuth2, OpenID Connect, JWT, SAML)", "Intermediate", "hld"));
        topics.add(new Topic("Security (Authorization - RBAC, ABAC; HTTPS/TLS, Encryption at Rest/In Transit, Rate Limiting, WAFs, DDoS Protection, Security Headers, OWASP Top 10)", "Advanced", "hld"));
        topics.add(new Topic("Monitoring & Logging (Centralized Logging - ELK Stack, Splunk; Metrics - Prometheus, Grafana; Alerting)", "Intermediate", "hld"));
        topics.add(new Topic("Monitoring & Logging (Distributed Tracing - Jaeger, Zipkin, OpenTelemetry; APM tools)", "Advanced", "hld"));
        topics.add(new Topic("Networking Basics (DNS, TCP/IP, HTTP/HTTPS, Proxies, Reverse Proxies, Firewalls)", "Beginner", "hld"));
        topics.add(new Topic("Networking Basics (VPNs, CIDR, Subnetting)", "Intermediate", "hld"));
        topics.add(new Topic("Common System Designs (URL Shortener, Chat System, Notification Service, News Feed)", "Intermediate", "hld"));
        topics.add(new Topic("Common System Designs (Payment Gateway, Distributed Cache, Ride-Sharing App, E-commerce Platform)", "Advanced", "hld"));
        topics.add(new Topic("Common System Designs (Recommendation Engine, Search Engine, Social Media Feed, Online Gaming Platform, Video Streaming Service, Analytics Platform)", "Advanced", "hld"));
        topics.add(new Topic("Distributed Consensus (Paxos, Raft - basic understanding, Zookeeper, etcd, Consul)", "Advanced", "hld"));
        topics.add(new Topic("API Gateway (Purpose, features - Authentication, Throttling, Routing, Transformation, Caching, Logging, Monitoring)", "Intermediate", "hld"));
        topics.add(new Topic("Service Discovery (Client-side vs. Server-side, Eureka, Consul, ZooKeeper, Kubernetes Service Discovery)", "Intermediate", "hld"));
        topics.add(new Topic("CDN (Content Delivery Network, Edge Caching, Cache Invalidation)", "Beginner", "hld"));
        topics.add(new Topic("Containerization & Orchestration (Docker, Kubernetes - Pods, Deployments, Services, Ingress)", "Intermediate", "hld"));
        topics.add(new Topic("Containerization & Orchestration (Helm Charts, StatefulSets)", "Advanced", "hld"));
        topics.add(new Topic("CI/CD Pipelines (Build, Test, Deploy Automation, Jenkins, GitLab CI, GitHub Actions, ArgoCD, Spinnaker)", "Intermediate", "hld"));
        topics.add(new Topic("Cloud Computing Concepts (IaaS, PaaS, SaaS; AWS/GCP/Azure core services - Compute, Storage, Databases, Serverless)", "Beginner", "hld"));
        topics.add(new Topic("Cloud Computing Concepts (Advanced Cloud Services, Cost Optimization, Multi-Cloud Strategies)", "Intermediate", "hld"));
        topics.add(new Topic("Event Sourcing & CQRS (Command Query Responsibility Segregation)", "Advanced", "hld"));
        topics.add(new Topic("GraphQL vs. REST (Comparison, Use Cases, N+1 problem)", "Intermediate", "hld"));
        topics.add(new Topic("System Reliability Engineering (SRE) Principles (SLAs, SLOs, SLIs, Error Budgets, Post-mortems, Toil)", "Advanced", "hld"));
        topics.add(new Topic("Data Streaming (Kafka Streams, Flink, Spark Streaming)", "Advanced", "hld"));
        topics.add(new Topic("Search Technologies (Elasticsearch, Solr, Lucene)", "Intermediate", "hld"));
        topics.add(new Topic("API Gateways & Service Mesh (Istio, Linkerd)", "Advanced", "hld"));
        topics.add(new Topic("Distributed Transactions (2PC, Saga Pattern, TCC)", "Advanced", "hld"));
        topics.add(new Topic("Feature Flags/Toggle Systems", "Intermediate", "hld"));
        topics.add(new Topic("A/B Testing Infrastructure", "Intermediate", "hld"));
        topics.add(new Topic("Time Series Databases (InfluxDB, Prometheus)", "Intermediate", "hld"));
        topics.add(new Topic("Distributed Locking Mechanisms (Redis, Zookeeper)", "Advanced", "hld"));
        topics.add(new Topic("Event-Driven Microservices (Design and Implementation)", "Advanced", "hld"));
        topics.add(new Topic("Data Consistency in Microservices (Eventual Consistency Patterns)", "Advanced", "hld"));

        // Backend Tech Topics
        topics.add(new Topic("Thread Dumps & Heap Dumps (Analyzing with VisualVM, MAT)", "Advanced", "backendTech"));
        topics.add(new Topic("Spring Retry, Resilience4j Advanced Configs", "Intermediate", "backendTech"));
        topics.add(new Topic("Java Flight Recorder, JFR Event Streaming", "Advanced", "backendTech"));
        topics.add(new Topic("gRPC (Unary, Streaming, Load Balancing, Interceptors)", "Advanced", "backendTech"));
        topics.add(new Topic("Java Core (JVM Internals, Class Loading, Memory Model, Concurrency Utilities)", "Intermediate", "backendTech"));
        topics.add(new Topic("Java Core (JVM Tuning, Advanced Concurrency Primitives)", "Advanced", "backendTech"));
        topics.add(new Topic("Spring Framework (IoC, DI, AOP, Bean Scopes, Lifecycle)", "Beginner", "backendTech"));
        topics.add(new Topic("Spring Boot (Auto-configuration, Starters, Actuator, Profiles, Externalized Config)", "Beginner", "backendTech"));
        topics.add(new Topic("Spring Data JPA/Hibernate (Entities, Repositories, Transactions, Lazy/Eager Loading, N+1 Problem, Caching)", "Intermediate", "backendTech"));
        topics.add(new Topic("Spring Web (MVC/REST Controllers, Request/Response Handling, DTOs, Validation)", "Beginner", "backendTech"));
        topics.add(new Topic("Spring Security (Authentication, Authorization, Filters, JWT, OAuth2)", "Intermediate", "backendTech"));
        topics.add(new Topic("Spring Cloud (Service Discovery, Config Server, API Gateway, Circuit Breaker, Load Balancer)", "Advanced", "backendTech"));
        topics.add(new Topic("Reactive Programming (Reactor/WebFlux, RxJava - concepts and benefits)", "Advanced", "backendTech"));
        topics.add(new Topic("Database Technologies (SQL vs NoSQL, ACID vs BASE, Indexing, Normalization, Transactions)", "Beginner", "backendTech"));
        topics.add(new Topic("Database Technologies (Database Connection Pooling, ORM Performance Tuning, Database Migrations - Flyway/Liquibase)", "Intermediate", "backendTech"));
        topics.add(new Topic("Messaging Systems (Kafka, RabbitMQ, SQS, SNS - Producers, Consumers, Topics, Queues)", "Intermediate", "backendTech"));
        topics.add(new Topic("Messaging Systems (Kafka Streams, Schema Registry, Idempotent Consumers, Exactly-once Semantics, Consumer Groups)", "Advanced", "backendTech"));
        topics.add(new Topic("Containerization (Docker - Dockerfile, Images, Containers, Volumes, Networks)", "Beginner", "backendTech"));
        topics.add(new Topic("Container Orchestration (Kubernetes - Pods, Deployments, Services, Ingress, StatefulSets, Helm)", "Intermediate", "backendTech"));
        topics.add(new Topic("Cloud Platforms (AWS/GCP/Azure - core compute, storage, database, serverless services)", "Beginner", "backendTech"));
        topics.add(new Topic("Cloud Platforms (Cloud Networking, Security Groups, IAM, Cost Optimization in Cloud)", "Intermediate", "backendTech"));
        topics.add(new Topic("CI/CD (Version Control - Git; Build Tools - Maven/Gradle; CI/CD Pipelines - Jenkins, GitLab CI, GitHub Actions)", "Beginner", "backendTech"));
        topics.add(new Topic("Observability (Logging - Log4j/Logback; Metrics - Micrometer, Prometheus; Tracing - OpenTelemetry, Zipkin; Alerting)", "Intermediate", "backendTech"));
        topics.add(new Topic("Distributed Systems Concepts (Consensus, Leader Election, Distributed Locks, Idempotency)", "Advanced", "backendTech"));
        topics.add(new Topic("API Design (RESTful best practices, GraphQL, gRPC)", "Intermediate", "backendTech"));
        topics.add(new Topic("Performance Testing & Tuning (JMeter, LoadRunner, Profiling, Benchmarking)", "Intermediate", "backendTech"));
        topics.add(new Topic("Security Best Practices (OWASP Top 10, Secure Coding, Vulnerability Scanning)", "Intermediate", "backendTech"));
        topics.add(new Topic("Microservices Architecture (Patterns, Communication, Data Management, Testing)", "Intermediate", "backendTech"));
        topics.add(new Topic("Serverless Computing (Lambda/Cloud Functions, FaaS)", "Intermediate", "backendTech"));
        topics.add(new Topic("Gateway API (Spring Cloud Gateway, Zuul)", "Intermediate", "backendTech"));
        topics.add(new Topic("Service Mesh (Istio, Linkerd - basic concepts)", "Advanced", "backendTech"));
        topics.add(new Topic("Database Performance Optimization (Query Optimization, Indexing Strategies)", "Intermediate", "backendTech"));
        topics.add(new Topic("Distributed Caching (Redis Cluster, Memcached)", "Advanced", "backendTech"));
        topics.add(new Topic("Asynchronous Processing (Spring Async, CompletableFuture)", "Intermediate", "backendTech"));
        topics.add(new Topic("Event-Driven Architecture with Kafka (Event Design, Consumer Groups)", "Advanced", "backendTech"));
    }

    public static void generateDailySuggestions(String suggestionsFile) {
        LocalDate today = LocalDate.now();
        Random random = new Random(today.getDayOfYear());
        List<Topic> suggestions = new ArrayList<>();
        String[] difficulties = {"Beginner", "Intermediate", "Advanced"};
        Map<String, Integer> difficultyCount = new HashMap<>();
        for (String diff : difficulties) difficultyCount.put(diff, 0);

        while (suggestions.size() < 5 && !topics.isEmpty()) {
            Topic topic = topics.get(random.nextInt(topics.size()));
            if (difficultyCount.get(topic.difficulty) < 2) {
                suggestions.add(topic);
                difficultyCount.put(topic.difficulty, difficultyCount.get(topic.difficulty) + 1);
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(suggestionsFile))) {
            writer.write("Daily Topic Suggestions for " + today + ":\n");
            for (int i = 0; i < suggestions.size(); i++) {
                Topic topic = suggestions.get(i);
                writer.write((i + 1) + ". [" + topic.difficulty + "] [" + topic.category + "] " + topic.name + "\n");
            }
            writer.write("\nPractice these topics and update your progress in topic_history.txt!");
        } catch (IOException e) {
            System.out.println("Error writing suggestions: " + e.getMessage());
        }
    }

    public static void updateHistory(String topicName, String status, String notes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HISTORY_FILE, true))) {
            writer.write(LocalDate.now() + " | " + topicName + " | " + status + " | " + notes + "\n");
        } catch (IOException e) {
            System.out.println("Error updating history: " + e.getMessage());
        }
    }

    /*
    public static void commitToGit(String... files) {
        try {
            ProcessBuilder checkGit = new ProcessBuilder("git", "--version");
            Process gitCheckProcess = checkGit.start();
            if (gitCheckProcess.waitFor() != 0) {
                System.out.println("Git is not installed or not found in PATH. Skipping commit.");
                return;
            }
            File gitDir = new File(".git");
            if (!gitDir.exists() || !gitDir.isDirectory()) {
                System.out.println("Not a Git repository. Initialize with 'git init' first. Skipping commit.");
                return;
            }
            List<String> addCommand = new ArrayList<>();
            addCommand.add("git");
            addCommand.add("add");
            addCommand.addAll(Arrays.asList(files));
            ProcessBuilder gitAdd = new ProcessBuilder(addCommand);
            Process addProcess = gitAdd.start();
            if (addProcess.waitFor() != 0) {
                System.out.println("Failed to stage files with 'git add'. Skipping commit.");
                return;
            }
            String commitMessage = "Updated topics and README for " + LocalDate.now();
            ProcessBuilder gitCommit = new ProcessBuilder("git", "commit", "-m", commitMessage);
            Process commitProcess = gitCommit.start();
            if (commitProcess.waitFor() == 0) {
                System.out.println("Successfully committed files to Git: " + String.join(", ", files));
            } else {
                System.out.println("Failed to commit files. Check Git status for issues.");
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error during Git commit: " + e.getMessage());
        }
    }
     */

    public static void main(String[] args) {
        String suggestionsFile = SUGGESTIONS_FILE_PREFIX + LocalDate.now() + ".txt";
        generateDailySuggestions(suggestionsFile);
        updateHistory("Dynamic Programming", "Started", "Implemented Knapsack and LCS");
        // commitToGit(suggestionsFile, HISTORY_FILE, "README-DynamicProgramming.md", "src/org/example/coding/algorithms/DynamicProgramming.java", "src/org/example/coding/TopicSuggester.java");
    }
}