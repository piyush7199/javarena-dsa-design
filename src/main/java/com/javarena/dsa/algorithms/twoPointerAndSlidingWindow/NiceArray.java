package com.javarena.dsa.algorithms.twoPointerAndSlidingWindow;

/**
 * Count Number of Nice Subarrays
 *
 * <p><b>Problem Statement:</b><br>
 * Given array nums and integer k, return number of nice subarrays.
 * A subarray is nice if it contains exactly k odd numbers.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Transform problem using "at most k" technique:
 * - count(exactly k) = count(at most k) - count(at most k-1)
 * - For "at most k odd numbers": sliding window
 * - Expand right, count odd numbers
 * - When odd count > k: shrink from left
 * - For each position: (right - left + 1) subarrays end at right
 * 
 * Key insight:
 * - Treat odd numbers as 1, even as 0
 * - Problem becomes "subarrays with sum exactly k"
 * - Use prefix sum sliding window technique
 * 
 * Similar pattern: Binary Subarrays With Sum.
 *
 * <p><b>Time Complexity:</b> O(N) - Two passes through array
 * <br><b>Space Complexity:</b> O(1) - Constant extra space
 */
public class NiceArray {
    
    /**
     * Counts subarrays with exactly k odd numbers.
     */
    public int numberOfSubarrays(int[] nums, int k) {
        return countAtMost(nums, k) - countAtMost(nums, k - 1);
    }
    
    /**
     * Helper: Counts subarrays with at most k odd numbers.
     */
    private int countAtMost(int[] nums, int k) {
        if (k < 0) return 0;
        
        int count = 0;
        int left = 0;
        int oddCount = 0;
        
        for (int right = 0; right < nums.length; right++) {
            // Count if odd
            oddCount += (nums[right] % 2);
            
            // Shrink window if too many odds
            while (oddCount > k) {
                oddCount -= (nums[left] % 2);
                left++;
            }
            
            // All subarrays from left to right are valid
            count += (right - left + 1);
        }
        
        return count;
    }
}
