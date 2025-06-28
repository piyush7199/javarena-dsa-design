package org.example;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class TopicSuggester {
    // Expanded Data Structures with difficulty levels
    static List<Topic> dataStructures = Arrays.asList(
            new Topic("Arrays", "Easy"),
            new Topic("Linked List", "Easy"),
            new Topic("Stacks", "Medium"),
            new Topic("Queues", "Medium"),
            new Topic("HashMap", "Medium"),
            new Topic("HashSet", "Easy"),
            new Topic("Heap", "Medium"),
            new Topic("Graph Representation", "Hard"),
            new Topic("Binary Tree", "Medium"),
            new Topic("Binary Search Tree", "Medium"),
            new Topic("Trie", "Hard"),
            new Topic("Segment Tree", "Hard"),
            new Topic("Fenwick Tree", "Hard"),
            new Topic("Union-Find", "Medium"),
            new Topic("Deque", "Medium"),
            new Topic("Circular Queue", "Medium"),
            new Topic("Skip List", "Hard"),
            new Topic("AVL Tree", "Hard"),
            new Topic("Red-Black Tree", "Hard"),
            new Topic("B-Tree", "Hard"),
            new Topic("Matrix", "Medium"),
            new Topic("Priority Queue", "Medium"),
            new Topic("Bloom Filter", "Hard"),
            new Topic("LRU Cache", "Medium")
    );

    // Expanded Algorithms with difficulty levels
    static List<Topic> algorithms = Arrays.asList(
            new Topic("Binary Search", "Easy"),
            new Topic("Two Pointers", "Medium"),
            new Topic("Sliding Window", "Medium"),
            new Topic("Recursion", "Medium"),
            new Topic("Backtracking", "Hard"),
            new Topic("Greedy", "Medium"),
            new Topic("Dynamic Programming", "Hard"),
            new Topic("Topological Sort", "Medium"),
            new Topic("Breadth-First Search (BFS)", "Medium"),
            new Topic("Depth-First Search (DFS)", "Medium"),
            new Topic("Dijkstra's Algorithm", "Hard"),
            new Topic("Bellman-Ford", "Hard"),
            new Topic("Kruskal’s MST", "Medium"),
            new Topic("Prim’s Algorithm", "Medium"),
            new Topic("Kadane’s Algorithm", "Medium"),
            new Topic("KMP Algorithm", "Hard"),
            new Topic("Bit Manipulation", "Medium"),
            new Topic("Prefix Sum", "Medium"),
            new Topic("Merge Intervals", "Medium"),
            new Topic("Flood Fill", "Medium"),
            new Topic("Fast Exponentiation", "Medium"),
            new Topic("Rabin-Karp", "Hard"),
            new Topic("Union-Find Algorithms", "Medium"),
            new Topic("Monotonic Stack", "Hard"),
            new Topic("Rolling Hash", "Hard"),
            new Topic("Reservoir Sampling", "Hard"),
            new Topic("A* Search", "Hard"),
            new Topic("Manacher’s Algorithm", "Hard"),
            new Topic("Sieve of Eratosthenes", "Medium"),
            new Topic("Game Theory", "Hard")
    );

    // Expanded LLD Design Patterns with questions
    static Map<String, String> lldDesignPatterns = Map.ofEntries(
            Map.entry("Factory Pattern", "Design a Notification Service with Email/SMS/Push channels"),
            Map.entry("Singleton Pattern", "Design a thread-safe Configuration Manager or Logger"),
            Map.entry("Strategy Pattern", "Design a Payment Gateway with multiple payment modes"),
            Map.entry("Observer Pattern", "Design a Publish-Subscribe Notification System"),
            Map.entry("Decorator Pattern", "Design a Text Editor with pluggable features"),
            Map.entry("Builder Pattern", "Design an Object Construction for a Complex User Profile"),
            Map.entry("Adapter Pattern", "Design a Media Player supporting multiple formats"),
            Map.entry("Prototype Pattern", "Design a Cloneable Document Editor"),
            Map.entry("Command Pattern", "Design an Undo/Redo System for a Text Editor"),
            Map.entry("State Pattern", "Design a Vending Machine or Traffic Light System"),
            Map.entry("Chain of Responsibility", "Design a Request Processing Pipeline"),
            Map.entry("Facade Pattern", "Design a simplified interface for a Banking System"),
            Map.entry("Template Method", "Design a generic Report Generator"),
            Map.entry("Mediator Pattern", "Design a Chat Room with user interactions")
    );

    // Expanded HLD topics (as strings, no difficulty)
    static List<String> hldTopics = Arrays.asList(
            "URL Shortener", "YouTube", "WhatsApp", "Instagram", "Netflix",
            "Twitter", "Uber", "Zomato", "Distributed File System", "Design Amazon",
            "Search Autocomplete", "Payment Gateway", "Real-Time Chat App",
            "Distributed Key-Value Store", "News Feed System", "Rate Limiter",
            "Design Google Docs", "Distributed Messaging Queue", "Web Crawler",
            "Online Code Compiler", "Stock Trading Platform", "Recommendation System"
    );

    // Topic class to store name and difficulty
    static class Topic {
        String name;
        String difficulty;

        Topic(String name, String difficulty) {
            this.name = name;
            this.difficulty = difficulty;
        }
    }

    // History to avoid recent topics
    static Set<String> recentDsTopics = new HashSet<>();
    static Set<String> recentAlgoTopics = new HashSet<>();
    private static final String HISTORY_FILE = "topic_history.txt";
    private static final int HISTORY_DAYS = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        // Load history from file
        loadHistory();

        // Get user input for DS/Algo counts
//        System.out.print("Enter number of Data Structure topics (1-5, default 2): ");
//        int dsCount = getValidInput(scanner, 1, 5, 2);
//        System.out.print("Enter number of Algorithm topics (1-5, default 2): ");
//        int algoCount = getValidInput(scanner, 1, 5, 2);
        int dsCount = 2;
        int algoCount = 2;

        // Get random suggestions
        List<Topic> dsSuggestions = getRandomTopics(dataStructures, dsCount, rand, recentDsTopics);
        List<Topic> algoSuggestions = getRandomTopics(algorithms, algoCount, rand, recentAlgoTopics);
        Map.Entry<String, String> lldSuggestion = getRandomMapEntry(lldDesignPatterns, rand);
        // Fixed: Use getRandomStringTopics for hldTopics (List<String>)
        String hld = getRandomStringTopics(hldTopics, 1, rand, new HashSet<>()).get(0);

        // Get complementary algorithms
        Map<String, List<String>> complementaryAlgos = getComplementaryAlgorithms(algoSuggestions, rand);

        // Update history
        updateHistory(dsSuggestions, algoSuggestions);

        // Display and save suggestions
        String output = formatOutput(dsSuggestions, algoSuggestions, complementaryAlgos, lldSuggestion, hld);
        System.out.println(output);
        saveToFile(output);

        // Motivational message
        System.out.println("\n💪 Keep pushing forward! You're building the skills to ace FAANG interviews!");
    }

    // Get valid user input
    static int getValidInput(Scanner scanner, int min, int max, int defaultValue) {
        try {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) return defaultValue;
            int value = Integer.parseInt(input);
            if (value >= min && value <= max) return value;
        } catch (NumberFormatException ignored) {
        }
        System.out.printf("Invalid input. Using default value: %d%n", defaultValue);
        return defaultValue;
    }

    // Get random topics (for Topic objects) avoiding recent ones
    static List<Topic> getRandomTopics(List<Topic> topics, int count, Random rand, Set<String> recent) {
        if (topics.isEmpty()) return new ArrayList<>();
        List<Topic> available = new ArrayList<>();
        for (Topic topic : topics) {
            if (!recent.contains(topic.name)) {
                available.add(topic);
            }
        }
        Collections.shuffle(available, rand);
        return available.subList(0, Math.min(count, available.size()));
    }

    // New method: Get random topics for String lists (for HLD)
    static List<String> getRandomStringTopics(List<String> topics, int count, Random rand, Set<String> recent) {
        if (topics.isEmpty()) return new ArrayList<>();
        List<String> available = new ArrayList<>();
        for (String topic : topics) {
            if (!recent.contains(topic)) {
                available.add(topic);
            }
        }
        Collections.shuffle(available, rand);
        return available.subList(0, Math.min(count, available.size()));
    }

    // Get random map entry with edge-case handling
    static Map.Entry<String, String> getRandomMapEntry(Map<String, String> map, Random rand) {
        if (map.isEmpty()) {
            throw new IllegalStateException("LLD design patterns map is empty");
        }
        List<Map.Entry<String, String>> entries = new ArrayList<>(map.entrySet());
        return entries.get(rand.nextInt(entries.size()));
    }

    // Get complementary algorithms
    static Map<String, List<String>> getComplementaryAlgorithms(List<Topic> selectedAlgos, Random rand) {
        Map<String, List<String>> result = new LinkedHashMap<>();
        List<String> allAlgoNames = algorithms.stream().map(t -> t.name).toList();

        for (Topic algo : selectedAlgos) {
            List<String> available = new ArrayList<>(allAlgoNames);
            available.remove(algo.name); // Remove the selected algo
            Collections.shuffle(available, rand);
            result.put(algo.name, available.subList(0, Math.min(2, available.size())));
        }
        return result;
    }

    // Format output
    static String formatOutput(List<Topic> dsTopics, List<Topic> algoTopics,
                               Map<String, List<String>> complementaryAlgos,
                               Map.Entry<String, String> lld, String hld) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== 📚 Today's Practice Topics (").append(LocalDate.now()).append(") ===\n\n");

        sb.append("→ 🧱 Data Structures:\n");
        for (Topic topic : dsTopics) {
            sb.append(" - ").append(topic.name).append(" (").append(topic.difficulty).append(")\n");
        }

        sb.append("\n→ ⚙️ Algorithms:\n");
        for (Topic topic : algoTopics) {
            sb.append(" - ").append(topic.name).append(" (").append(topic.difficulty).append(")\n");
            List<String> complements = complementaryAlgos.get(topic.name);
            if (!complements.isEmpty()) {
                sb.append("   ↳ Also consider: ").append(String.join(", ", complements)).append("\n");
            }
        }

        sb.append("\n→ 🧩 Low-Level Design (LLD):\n");
        sb.append(" - Pattern: ").append(lld.getKey()).append("\n");
        sb.append(" - Problem: ").append(lld.getValue()).append("\n");

        sb.append("\n→ 🏗 High-Level Design (HLD):\n");
        sb.append(" - ").append(hld).append("\n");

        return sb.toString();
    }

    // Save output to file
    static void saveToFile(String output) {
        try (FileWriter writer = new FileWriter("TopicSuggestions_" + LocalDate.now() + ".txt")) {
            writer.write(output);
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    // Load history from file
    static void loadHistory() {
        try (BufferedReader reader = new BufferedReader(new FileReader(HISTORY_FILE))) {
            String line;
            LocalDate today = LocalDate.now();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 3) continue; // Skip malformed lines
                LocalDate date = LocalDate.parse(parts[0]);
                if (today.minusDays(HISTORY_DAYS).isBefore(date)) {
                    if (parts[1].equals("DS")) {
                        recentDsTopics.add(parts[2]);
                    } else if (parts[1].equals("ALGO")) {
                        recentAlgoTopics.add(parts[2]);
                    }
                }
            }
        } catch (FileNotFoundException ignored) {
        } catch (IOException e) {
            System.out.println("Error loading history: " + e.getMessage());
        }
    }

    // Update history
    static void updateHistory(List<Topic> dsTopics, List<Topic> algoTopics) {
        try (FileWriter writer = new FileWriter(HISTORY_FILE, true)) {
            String date = LocalDate.now().toString();
            for (Topic ds : dsTopics) {
                writer.write(date + ",DS," + ds.name + "\n");
                recentDsTopics.add(ds.name);
            }
            for (Topic algo : algoTopics) {
                writer.write(date + ",ALGO," + algo.name + "\n");
                recentAlgoTopics.add(algo.name);
            }
        } catch (IOException e) {
            System.out.println("Error updating history: " + e.getMessage());
        }
    }
}