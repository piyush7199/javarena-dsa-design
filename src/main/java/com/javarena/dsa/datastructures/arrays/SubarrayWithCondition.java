package com.javarena.dsa.datastructures.arrays;

/**
 * Count Subarrays With Median Condition
 *
 * <p><b>Problem Statement:</b><br>
 * Count subarrays of length 3 where the middle element equals the average of first and last elements.
 * Condition: 2 * (nums[i] + nums[i+2]) == nums[i+1]
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Iterate through array considering windows of size 3
 * - For indices i, i+1, i+2:
 *   - Check if 2 * (first + third) == second
 *   - This checks if middle = (first + third) / 2 (arithmetic mean)
 * - Count all such valid triplets
 * - Simple linear scan with condition check
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass with fixed window size 3
 * <br><b>Space Complexity:</b> O(1) - Only counter variable
 */
public class SubarrayWithCondition {
    /**
     * Counts subarrays where middle element is mean of endpoints.
     */
    public int countSubarrays(int[] nums) {
        int cnt = 0;
        
        for (int i = 0; i < nums.length - 2; i++) {
            int first = nums[i];
            int sec = nums[i + 1];
            int th = nums[i + 2];
            
            if (2 * (first + th) == sec) cnt++;
        }

        return cnt;
    }
}
