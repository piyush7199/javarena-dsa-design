package com.javarena.dsa.datastructures.arrays;

/**
 * Number of Zero-Filled Subarrays
 *
 * <p><b>Problem Statement:</b><br>
 * Count the total number of subarrays that are filled entirely with zeros.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Track length of current consecutive zero sequence
 * - When encountering zero, increment count
 * - Add count to result (each new zero adds 'count' new subarrays)
 * - Example: [0,0,0] has 6 subarrays: [0], [0], [0], [0,0], [0,0], [0,0,0]
 * - Formula: n consecutive zeros contribute n*(n+1)/2 subarrays
 * - Incremental counting: 1st zero adds 1, 2nd adds 2, 3rd adds 3, etc.
 * - Reset count to 0 when non-zero encountered
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass through array
 * <br><b>Space Complexity:</b> O(1) - Only counter variables
 */
public class NumberOfZeroFilledSubarrays {
    /**
     * Counts zero-filled subarrays.
     */
    public static long zeroFilledSubarray(int[] nums) {
        long count = 0;
        long max = 0;
        
        for (int num : nums) {
            if (num == 0) {
                count++;
                max += count;  // Add all subarrays ending at current position
            } else {
                count = 0;  // Reset on non-zero
            }
        }
        
        return max;
    }
}
