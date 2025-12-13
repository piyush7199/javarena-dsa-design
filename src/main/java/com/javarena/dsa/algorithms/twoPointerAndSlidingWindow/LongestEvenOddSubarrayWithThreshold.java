package com.javarena.dsa.algorithms.twoPointerAndSlidingWindow;

/**
 * Longest Even Odd Subarray With Threshold
 *
 * <p><b>Problem Statement:</b><br>
 * Given integer array nums and integer threshold, return length of longest subarray where:
 * - First element is even and ≤ threshold
 * - Elements alternate between even and odd
 * - All elements ≤ threshold
 *
 * <p><b>Intuition & Approach:</b><br>
 * Two-pointer approach finding valid starting points:
 * - Start with valid first element (even and ≤ threshold)
 * - Extend while maintaining alternating even-odd pattern and threshold
 * - Use bitwise AND (&1) to check even/odd efficiently
 * - Track maximum length found
 * 
 * Strategy:
 * 1. Skip invalid starting positions (odd or > threshold)
 * 2. From valid start, extend while alternating and within threshold
 * 3. Update max length
 * 4. Move to next potential start position
 * 
 * Optimization: Skip to end of valid segment instead of incrementing by 1.
 *
 * <p><b>Time Complexity:</b> O(N) - Each element visited at most twice
 * <br><b>Space Complexity:</b> O(1) - Constant extra space
 */
public class LongestEvenOddSubarrayWithThreshold {
    
    /**
     * Finds longest alternating even-odd subarray within threshold.
     */
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int maxLen = 0;
        int i = 0;
        int n = nums.length;
        
        while (i < n) {
            // Skip if invalid starting position (odd or > threshold)
            if ((nums[i] & 1) == 1 || nums[i] > threshold) {
                i++;
                continue;
            }
            
            // Extend while alternating and within threshold
            int j = i + 1;
            while (j < n && 
                   (nums[j] & 1) != (nums[j - 1] & 1) && 
                   nums[j] <= threshold) {
                j++;
            }
            
            maxLen = Math.max(maxLen, j - i);
            i = j;
        }
        
        return maxLen;
    }
}
