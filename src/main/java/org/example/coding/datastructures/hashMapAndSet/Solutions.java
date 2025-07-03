package org.example.coding.datastructures.hashMapAndSet;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solutions {

    /**
     * Finds the top k most frequent elements in an integer array.
     *
     * <p>🔍 Intuition:
     * - Count the frequency of each element using a HashMap.
     * - Use a Min Heap (PriorityQueue) to keep track of the top k frequent elements.
     * - If the heap size exceeds k, remove the least frequent element.
     * - Finally, extract the k elements from the heap.
     *
     * <p> Why Min Heap?
     * - So we can efficiently evict the least frequent element when the heap exceeds size k.
     * <p>
     * Time Complexity:
     * - O(n log k), where n is the number of elements in nums.
     * - O(n) for frequency counting.
     * - O(n log k) for pushing into the heap.
     * <p>
     * 🧠 Space Complexity: O(n)
     * - For the frequency map and the heap (worst case all unique).
     */
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int it : nums) {
            map.put(it, map.getOrDefault(it, 0) + 1);
        }

        // Min Heap based on frequency
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
                (a, b) -> a.getValue() - b.getValue()
        );

        for (Map.Entry<Integer, Integer> it : map.entrySet()) {
            pq.offer(it);
            if (pq.size() > k) pq.poll(); // Maintain heap of size k
        }

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = pq.poll().getKey();
        }

        return ans;
    }

}
