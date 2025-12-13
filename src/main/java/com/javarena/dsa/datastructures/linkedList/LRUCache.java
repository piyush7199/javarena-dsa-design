package com.javarena.dsa.datastructures.linkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * Node class used in the Doubly Linked List for the LRU Cache.
 */
class Node {
    Node next;
    Node prev;
    int val;
    int key;

    public Node(int val, int key) {
        this.next = null;
        this.prev = null;
        this.val = val;
        this.key = key;
    }
}

/**
 * LRU Cache (Least Recently Used)
 *
 * <p><b>Problem Statement:</b><br>
 * Design and implement LRU cache supporting get and put in O(1) time.
 * When cache reaches capacity, invalidate least recently used item.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Doubly Linked List + HashMap:
 * - Doubly Linked List: Maintains usage order (most recent at head, LRU at tail)
 * - HashMap: Provides O(1) access to nodes by key
 * 
 * Operations:
 * - get: Access node, move to front (most recently used)
 * - put: Insert/update node at front, evict tail if capacity exceeded
 * - Eviction: Remove tail node (LRU)
 * 
 * Dummy head/tail nodes simplify edge cases.
 *
 * <p><b>Time Complexity:</b> O(1) for both get and put
 * <br><b>Space Complexity:</b> O(capacity) - HashMap + DLL nodes
 */
public class LRUCache {

    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head, tail;

    /**
     * Constructor to initialize LRU cache with a fixed capacity.
     */
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(0, 0); // Dummy head
        this.tail = new Node(0, 0); // Dummy tail
        this.head.next = tail;
        this.tail.prev = head;
    }

    /**
     * Retrieves the value for the given key if it exists in the cache.
     * Moves the accessed node to the front as most recently used.
     */
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        delete(node);
        insert(node);
        return node.val;
    }

    /**
     * Inserts or updates a key-value pair in the cache.
     * Moves the updated node to the front. If capacity is exceeded, evicts LRU.
     */
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            delete(map.get(key)); // Remove existing
        }

        Node node = new Node(value, key);
        insert(node);
        map.put(key, node);

        if (map.size() > capacity) {
            Node lru = tail.prev; // Least Recently Used
            delete(lru);
            map.remove(lru.key);
        }
    }

    /**
     * Inserts a node right after the head (MRU position).
     */
    private void insert(Node node) {
        Node headNext = head.next;
        head.next = node;
        node.next = headNext;
        headNext.prev = node;
        node.prev = head;
    }

    /**
     * Deletes a node from the linked list.
     */
    private void delete(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }
}
