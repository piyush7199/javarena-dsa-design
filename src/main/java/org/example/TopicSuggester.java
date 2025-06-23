package org.example;

import java.util.*;

public class TopicSuggester {
    static List<String> dataStructures = Arrays.asList(
            "Arrays", "Linked List", "Stacks", "Queues", "HashMap", "HashSet",
            "Heap", "Graph Representation", "Binary Tree", "BST",
            "Trie", "Segment Tree", "Fenwick Tree", "Union-Find", "Deque",
            "Circular Queue", "Skip List"
    );

    static List<String> algorithms = Arrays.asList(
            "Binary Search", "Two Pointers", "Sliding Window", "Recursion",
            "Backtracking", "Greedy", "Dynamic Programming", "Topological Sort",
            "BFS", "DFS", "Dijkstra's Algorithm", "Bellman-Ford", "Kruskal’s MST",
            "Prim’s Algorithm", "Kadane’s Algorithm", "KMP Algorithm", "Bit Manipulation",
            "Prefix Sum", "Merge Intervals", "Flood Fill", "Fast Exponentiation"
    );

    static Map<String, String> lldDesignPatterns = Map.ofEntries(
            Map.entry("Factory Pattern", "Design a Notification Service with Email/SMS/Push channels"),
            Map.entry("Singleton Pattern", "Design a Configuration Manager or Logger"),
            Map.entry("Strategy Pattern", "Design a Payment Gateway with multiple payment modes"),
            Map.entry("Observer Pattern", "Design a Publish-Subscribe Notification System"),
            Map.entry("Decorator Pattern", "Design a Text Editor with pluggable features"),
            Map.entry("Builder Pattern", "Design an Object Construction for a Complex User Profile"),
            Map.entry("Adapter Pattern", "Design a Media Player supporting multiple formats"),
            Map.entry("Prototype Pattern", "Design a Cloneable Document Editor"),
            Map.entry("Command Pattern", "Design an Undo/Redo System"),
            Map.entry("State Pattern", "Design a Vending Machine or Traffic Light System")
    );

    static List<String> hldTopics = Arrays.asList(
            "URL Shortener", "YouTube", "WhatsApp", "Instagram", "Netflix",
            "Twitter", "Uber", "Zomato", "Distributed File System",
            "Design Amazon", "Search Autocomplete", "Payment Gateway", "Real-Time Chat App"
    );

    public static void main(String[] args) {
        Random rand = new Random();

        List<String> dsSuggestions = getRandomTopics(dataStructures, 2, rand);
        List<String> algoSuggestions = getRandomTopics(algorithms, 2, rand);
        Map.Entry<String, String> lldSuggestion = getRandomMapEntry(lldDesignPatterns, rand);
        String hld = getRandomTopics(hldTopics, 1, rand).get(0);

        System.out.println("=== 📚 Today's Practice Topics ===\n");

        System.out.println("→ 🧱 Data Structures:");
        for (String topic : dsSuggestions) {
            System.out.println(" - " + topic);
        }

        System.out.println("\n→ ⚙️ Algorithms:");
        for (String topic : algoSuggestions) {
            System.out.println(" - " + topic);
        }

        System.out.println("\n→ 🧩 Low-Level Design (LLD):");
        System.out.println(" - Pattern: " + lldSuggestion.getKey());
        System.out.println(" - Problem: " + lldSuggestion.getValue());

        System.out.println("\n→ 🏗 High-Level Design (HLD):");
        System.out.println(" - " + hld);
    }

    static List<String> getRandomTopics(List<String> topics, int count, Random rand) {
        List<String> copy = new ArrayList<>(topics);
        Collections.shuffle(copy, rand);
        return copy.subList(0, Math.min(count, copy.size()));
    }

    static Map.Entry<String, String> getRandomMapEntry(Map<String, String> map, Random rand) {
        List<Map.Entry<String, String>> entries = new ArrayList<>(map.entrySet());
        return entries.get(rand.nextInt(entries.size()));
    }
}