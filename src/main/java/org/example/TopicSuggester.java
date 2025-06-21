package org.example;

import java.util.*;

public class TopicSuggester {
    static List<String> algoDsTopics = Arrays.asList(
            "Two Pointers", "Binary Search", "Dynamic Programming", "Greedy",
            "Graph - BFS/DFS", "Dijkstra's Algorithm", "Bellman-Ford", "Topological Sort",
            "Disjoint Set Union", "Sliding Window", "Recursion & Backtracking",
            "Bit Manipulation", "Segment Tree", "Trie", "Stacks & Queues",
            "Heaps/Priority Queue", "Hashing", "Matrix Problems", "String Algorithms",
            "Kadane’s Algorithm", "Flood Fill", "Merge Intervals", "Prefix Sum",
            "Suffix Array", "KMP Algorithm", "Monotonic Stack", "Fenwick Tree"
    );

    static List<String> lldTopics = Arrays.asList(
            "Parking Lot", "Rate Limiter", "In-memory Cache", "LRU Cache",
            "Tic Tac Toe", "Splitwise", "Logger Rate Limiter", "Notification System",
            "Design Snake Game", "Elevator System", "Thread Pool", "Undo-Redo System"
    );

    static List<String> hldTopics = Arrays.asList(
            "URL Shortener", "YouTube", "WhatsApp", "Messenger", "Uber",
            "Distributed File System", "Twitter", "Instagram",
            "Real-time Chat System", "Netflix", "Stock Exchange", "Booking.com"
    );

    public static void main(String[] args) {
        Random rand = new Random();

        List<String> algo = getRandomTopics(algoDsTopics, 3, rand);
        String lld = getRandomTopics(lldTopics, 1, rand).get(0);
        String hld = getRandomTopics(hldTopics, 1, rand).get(0);

        System.out.println("=== Today's Practice Topics ===\n");

        System.out.println("→ Algorithms/Data Structures:");
        for (String topic : algo) {
            System.out.println(" - " + topic);
        }

        System.out.println("\n→ Low-Level Design (LLD):");
        System.out.println(" - " + lld);

        System.out.println("\n→ High-Level Design (HLD):");
        System.out.println(" - " + hld);
    }

    static List<String> getRandomTopics(List<String> topics, int count, Random rand) {
        List<String> copy = new ArrayList<>(topics);
        Collections.shuffle(copy, rand);
        return copy.subList(0, count);
    }
}
