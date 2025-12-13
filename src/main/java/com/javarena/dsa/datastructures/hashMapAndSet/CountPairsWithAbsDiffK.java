package com.javarena.dsa.datastructures.hashMapAndSet;

import java.util.HashMap;
import java.util.Map;

/**
 * Count Pairs With Absolute Difference K
 *
 * <p><b>Problem Statement:</b><br>
 * Count number of pairs (i, j) where i < j and |nums[i] - nums[j]| = k.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Frequency-based counting optimization:
 * - For each number num, valid pairs formed with:
 *   - (num - k) if it appeared before
 *   - (num + k) if it appeared before
 * - Use frequency array/map to track previous occurrences
 * - Add count of (num - k) and (num + k) to result
 * - Increment frequency of current number
 * 
 * Three approaches:
 * 1. Brute force O(N²): Check all pairs
 * 2. Array O(N): For bounded range [1, 100]
 * 3. HashMap O(N): For unbounded range
 *
 * <p><b>Time Complexity:</b> O(N) for optimized, O(N²) for brute force
 * <br><b>Space Complexity:</b> O(1) for array (fixed size), O(N) for HashMap
 */
public class CountPairsWithAbsDiffK {
    /**
     * Optimal approach using frequency array.
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
