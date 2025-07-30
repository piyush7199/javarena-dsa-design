package org.example.coding.datastructures.hashMapAndSet;

import java.util.HashMap;

public class SubArraySumEqualsK {

    /**
     * Brute force approach to count the number of subarrays that sum up to a target value K.
     *
     * <p><b>Intuition:</b> Try all possible subarrays using two nested loops.
     * For each subarray, calculate the sum and compare with K.
     *
     * <p><b>Time Complexity:</b> O(n^2) – Two nested loops over the array.</p>
     * <p><b>Space Complexity:</b> O(1) – No extra space used.</p>
     */
    public int subarraySumBrute(int[] Arr, int K) {
        int ans = 0;
        int n = Arr.length;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += Arr[j];
                if (sum == K) ans++;
            }
        }
        return ans;
    }

    /**
     * Optimized approach using prefix sum and a hashmap to count subarrays with sum equal to K.
     *
     * <p><b>Intuition:</b>
     * A subarray sum from index i to j is equal to the difference between the prefix sums at j and i-1.
     * So we store the frequency of prefix sums seen so far. For each prefix sum `currSum`, if `currSum - K`
     * exists in the map, it means there is a subarray that sums to K ending at the current index.
     *
     * <p><b>Approach:</b>
     * <ul>
     *   <li>Initialize a prefix sum to 0 and a HashMap to store frequencies of prefix sums.</li>
     *   <li>Iterate through the array, updating the prefix sum.</li>
     *   <li>If (prefixSum - K) is found in the map, increment the count by the frequency.</li>
     *   <li>Update the map with the current prefix sum frequency.</li>
     * </ul>
     *
     * <p><b>Time Complexity:</b> O(n) – Single pass through the array.</p>
     * <p><b>Space Complexity:</b> O(n) – For the hashmap storing prefix sums.</p>
     */
    public int subarraySum(int[] Arr, int K) {
        // code here
        int N = Arr.length;
        long prefSum = 0;
        int c = 0;
        HashMap<Long, Integer> map = new HashMap<>();
        map.put(0L, 1);
        for (int j : Arr) {
            prefSum += j;
            long rem = prefSum - K;
            c += map.getOrDefault(rem, 0);
            map.put(prefSum, map.getOrDefault(prefSum, 0) + 1);
        }
        return c;
    }
}
