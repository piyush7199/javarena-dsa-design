package com.javarena.dsa.datastructures.linkedList;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * LFU Cache (Least Frequently Used)
 *
 * <p><b>Problem Statement:</b><br>
 * Design and implement LFU cache supporting get and put in O(1) time.
 * When cache reaches capacity, invalidate least frequently used item.
 * If multiple items have same frequency, remove least recently used.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Triple HashMap design:
 * - keyToVal: Key → Value mapping
 * - keyToFreq: Key → Frequency count
 * - freqToLRUKeys: Frequency → LinkedHashSet (LRU order within frequency)
 * 
 * Operations:
 * - get: Retrieve value, increment frequency, update structures
 * - put: Insert/update, increment frequency, evict if needed
 * - Eviction: Remove first element from minFreq's LinkedHashSet
 * 
 * LinkedHashSet provides insertion order + O(1) operations.
 *
 * <p><b>Time Complexity:</b> O(1) for both get and put
 * <br><b>Space Complexity:</b> O(capacity) - Three maps
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
