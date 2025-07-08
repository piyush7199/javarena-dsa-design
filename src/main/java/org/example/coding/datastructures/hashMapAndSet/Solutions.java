package org.example.coding.datastructures.hashMapAndSet;

import java.util.*;

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

    class Pair {
        String s;
        int f;

        Pair(String s, int f) {
            this.s = s;
            this.f = f;
        }
    }

    /**
     * Returns the top k most frequent words, ordered by frequency (high to low),
     * and lexicographically in case of tie.
     *
     * <p>🔍 Intuition:
     * - Use a HashMap to count frequency.
     * - Use a Min Heap (PriorityQueue) to keep track of top k words.
     * - Custom comparator: lower frequency first;
     * if equal frequency, lexicographically larger string first (to pop earlier).
     * - Finally, extract elements from heap in reverse order.
     * <p>
     * ⏱ Time Complexity: O(n log k), where n is number of words.
     * 🧠 Space Complexity: O(n)
     */
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : words) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            if (a.f == b.f) {
                return b.s.compareTo(a.s);
            } else {
                return a.f - b.f;
            }
        });

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(new Pair(entry.getKey(), entry.getValue()));
            if (pq.size() > k) pq.poll();
        }

        LinkedList<String> result = new LinkedList<>();
        while (!pq.isEmpty()) {
            result.addFirst(pq.poll().s);
        }

        return result;
    }

    /**
     * Finds two numbers in the array that sum to the target and returns their indices.
     *
     * <p>🔍 Intuition:
     * - Use a HashMap to store visited numbers and their indices.
     * - For each number, check if (target - num) exists in the map.
     * - If yes, return the indices.
     * <p>
     * ⏱ Time Complexity: O(n)
     * 🧠 Space Complexity: O(n)
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans[] = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int rem = target - nums[i];
            if (map.containsKey(rem)) {
                ans[0] = map.get(rem);
                ans[1] = i;
                break;
            }
            map.put(nums[i], i);
        }
        return ans;
    }

    /**
     * Counts the number of subarrays whose XOR is equal to a given number `m`.
     * <p>
     * Intuition:
     * - Maintain a prefix XOR.
     * - Use a map to store frequency of each prefix XOR.
     * - For each prefix XOR, check if (prefix XOR ^ m) exists in map.
     * <p>
     * XOR properties used:
     * A ^ B = C ⇒ A = B ^ C ⇒ B = A ^ C
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public long subarrayXor(int arr[], int m) {
        // code here
        int xor = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(xor, 1);
        long cnt = 0;
        for (int ele : arr) {
            xor = xor ^ ele;
            int rem = xor ^ m;
            cnt += map.getOrDefault(rem, 0);
            map.put(xor, map.getOrDefault(xor, 0) + 1);
        }
        return cnt;
    }

    /**
     * 🧮 K-th Occurrence of Element
     * <p>
     * Intuition:
     * Traverse the array and track positions of x using a counter. For each query,
     * return the corresponding stored index or -1.
     * <p>
     * Time Complexity: O(n + q) where q = queries.length
     * Space Complexity: O(k) where k is the number of times x occurs
     */
    public int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int counter = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == x) {
                map.put(counter, i);
                counter++;
            }
        }
        for (int i = 0; i < queries.length; i++) {
            queries[i] = map.getOrDefault(queries[i], -1);
        }
        return queries;
    }
}
