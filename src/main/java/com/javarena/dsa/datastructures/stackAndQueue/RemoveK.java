package com.javarena.dsa.datastructures.stackAndQueue;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Least Number of Unique Integers after K Removals
 *
 * <p><b>Problem Statement:</b><br>
 * Given array and integer k, remove exactly k elements.
 * Return least number of unique integers remaining.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Greedy with min-heap:
 * - Count frequency of each element
 * - Remove elements with lowest frequency first
 * - Use min-heap to always pick least frequent element
 * - Remove until k elements consumed or frequency too high
 * - Remaining heap size = number of unique integers
 * 
 * Maximize removals by targeting least frequent first.
 *
 * <p><b>Time Complexity:</b> O(N log U) - N elements, U unique
 * <br><b>Space Complexity:</b> O(U) - Frequency map + heap
 */
public class RemoveK {
    /**
     * Finds minimum unique integers after k removals.
     */
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        if (k >= arr.length) return 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : arr) {
            map.put(ele, map.getOrDefault(ele, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(map.values());

        while (!pq.isEmpty() && k > 0) {
            if (pq.peek() > k) break;
            k -= pq.poll();
        }
        return pq.size();
    }
}
