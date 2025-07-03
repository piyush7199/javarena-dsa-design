package org.example.coding.datastructures.linkedList;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * LFUCache implements a Least Frequently Used (LFU) cache with O(1) average time complexity
 * for both get and put operations.
 * <p>
 * 🔍 Intuition:
 * - Each key has a frequency count.
 * - When a key is accessed or updated, its frequency increases.
 * - If capacity is full, the least frequently used key is removed.
 * - If multiple keys have the same frequency, the least recently used (LRU) one among them is evicted.
 * <p>
 * 📦 Internal Structure:
 * - keyToVal: stores key-value pairs
 * - keyToFreq: stores frequency count for each key
 * - freqToLRUKeys: maps frequencies to ordered sets of keys to track recency (LRU within same frequency)
 * - minFreq: tracks the minimum frequency present in the cache
 * <p>
 * ⏱ Time Complexity:
 * - get(key): O(1)
 * - put(key, value): O(1)
 * <p>
 * 🧠 Space Complexity: O(capacity)
 */
public class LFUCache {
    private final int capacity;
    private Map<Integer, Integer> keyToVal;
    private Map<Integer, Integer> keyToFreq;
    private Map<Integer, LinkedHashSet<Integer>> freqToLRUKeys;
    private int minFreq;


    public LFUCache(int capacity) {
        this.capacity = capacity;
        keyToVal = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToLRUKeys = new HashMap<>();
        minFreq = 0;
    }

    /**
     * Helper method to increase frequency of a key and move it to the new frequency group.
     */
    private void putFreq(int key, int freq) {
        keyToFreq.put(key, freq);
        freqToLRUKeys.putIfAbsent(freq, new LinkedHashSet<>());
        freqToLRUKeys.get(freq).add(key);
    }

    /**
     * Retrieves the value associated with the key if present.
     * Also updates the frequency of the key.
     */
    public int get(int key) {
        if (!keyToVal.containsKey(key)) return -1;

        int freq = keyToFreq.get(key);
        freqToLRUKeys.get(freq).remove(key);

        // If this was the last key at the current minFreq, increase minFreq
        if (minFreq == freq && freqToLRUKeys.get(freq).isEmpty()) {
            freqToLRUKeys.remove(freq);
            minFreq++;
        }

        putFreq(key, freq + 1);
        return keyToVal.get(key);
    }

    /**
     * Inserts or updates a key-value pair in the cache.
     * If the cache is full, evicts the least frequently and least recently used key.
     */
    public void put(int key, int value) {
        if (capacity == 0) return;

        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, value);
            get(key); // Reuse get() to update frequency
            return;
        }

        // Eviction logic
        if (keyToVal.size() == capacity) {
            int keyToEvict = freqToLRUKeys.get(minFreq).iterator().next(); // LRU key at minFreq
            freqToLRUKeys.get(minFreq).remove(keyToEvict);
            if (freqToLRUKeys.get(minFreq).isEmpty()) {
                freqToLRUKeys.remove(minFreq);
            }
            keyToVal.remove(keyToEvict);
            keyToFreq.remove(keyToEvict);
        }

        minFreq = 1;
        putFreq(key, minFreq);
        keyToVal.put(key, value);
    }

}
