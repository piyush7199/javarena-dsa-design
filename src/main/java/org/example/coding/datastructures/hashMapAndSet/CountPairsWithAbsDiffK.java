package org.example.coding.datastructures.hashMapAndSet;

import java.util.HashMap;
import java.util.Map;

public class CountPairsWithAbsDiffK {
    /**
     * Problem: Count number of pairs (i, j) where |nums[i] - nums[j]| = k
     * <p>
     * Intuition:
     * - Instead of comparing every pair (brute force), we can use frequency counting.
     * - For each number `num`, the pairs that contribute to the answer must involve:
     * - (num - k) or (num + k)
     * - If we already know how many times (num - k) or (num + k) appeared before,
     * we can directly add those counts to our answer.
     * <p>
     * Approach:
     * 1. Use a frequency array freq[101] (since 1 <= nums[i] <= 100).
     * 2. For each num in nums:
     * - Add freq[num - k] to count if valid.
     * - Add freq[num + k] to count if valid.
     * - Increment freq[num].
     * 3. Return count.
     * <p>
     * Time Complexity: O(n) → single pass over nums.
     * Space Complexity: O(1) → fixed array of size 101.
     */
    public int countKDifferenceOptimal(int[] nums, int k) {
        int[] freq = new int[101]; // problem constraint: nums[i] in [1,100]
        int count = 0;

        for (int num : nums) {
            // Check pairs with (num - k)
            if (num - k >= 1) count += freq[num - k];

            // Check pairs with (num + k)
            if (num + k <= 100) count += freq[num + k];

            // Update frequency of current number
            freq[num]++;
        }

        return count;
    }

    /**
     * Alternative solution using HashMap (more general if range of nums was larger).
     * <p>
     * Intuition:
     * - Similar to frequency array, but using a HashMap
     * in case nums has larger or unknown bounds.
     * <p>
     * Approach:
     * 1. Use a map to store frequency of numbers seen so far.
     * 2. For each num in nums:
     * - Add map[num - k] (if exists) to count.
     * - Add map[num + k] (if exists) to count.
     * - Increment map[num].
     * <p>
     * Time Complexity: O(n) → single pass.
     * Space Complexity: O(n) → stores counts in HashMap.
     */
    public int countKDifferenceHashMap(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;

        for (int num : nums) {
            count += map.getOrDefault(num - k, 0);
            count += map.getOrDefault(num + k, 0);
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        return count;
    }

    /**
     * Brute force solution for clarity.
     * <p>
     * Intuition:
     * - Check every pair (i, j), count if |nums[i] - nums[j]| = k.
     * <p>
     * Approach:
     * - Double loop over array.
     * - Compare absolute difference of each pair.
     * <p>
     * Time Complexity: O(n²)
     * - Not efficient for larger input sizes.
     * Space Complexity: O(1)
     */
    public int countKDifference(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    ans++;
                }
            }
        }

        return ans;
    }

}
