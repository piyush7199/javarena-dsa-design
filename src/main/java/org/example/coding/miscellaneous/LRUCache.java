package org.example.coding.miscellaneous;

import java.util.HashMap;
import java.util.Map;

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

public class LRUCache {

    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        this.head.next = tail;
        this.tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        delete(node);
        insert(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            delete(map.get(key));
        }

        Node node = new Node(value, key);
        insert(node);
        map.put(key, node);
        if (map.size() > capacity) {
            Node prev = tail.prev;
            tail.prev = prev.prev;
            prev.prev.next = tail;
            map.remove(prev.key);
        }
    }

    private void insert(Node node) {
        Node headNext = head.next;
        head.next = node;
        node.next = headNext;
        headNext.prev = node;
        node.prev = head;
    }

    private void delete(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = node.next;
        next.prev = node.prev;
    }
}
