package com.javarena.dsa.utils;

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