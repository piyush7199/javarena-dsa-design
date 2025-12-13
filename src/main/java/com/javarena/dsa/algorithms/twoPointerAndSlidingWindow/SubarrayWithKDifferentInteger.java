package com.javarena.dsa.algorithms.twoPointerAndSlidingWindow;

import java.util.HashMap;

/**
 * Subarrays with K Different Integers
 *
 * <p><b>Problem Statement:</b><br>
 * Given integer array nums and integer k, return number of good subarrays.
 * A good subarray contains exactly k different integers.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Transform using "at most k" technique:
 * - count(exactly k) = count(at most k) - count(at most k-1)
 * - For "at most k distinct": sliding window with HashMap
 * - Expand right, add to map
 * - When distinct count > k: shrink from left
 * - For each position: (right - left + 1) subarrays end at right
 * 
 * Key insight:
 * - Hard to count "exactly k" directly with sliding window
 * - Easy to count "at most k" with sliding window
 * - Subtract to get exact count
 * 
 * Similar pattern: Nice Subarrays, Binary Subarrays With Sum.
 *
 * <p><b>Time Complexity:</b> O(N) - Two passes through array
 * <br><b>Space Complexity:</b> O(K) - HashMap stores at most k distinct integers
 */
public class SubarrayWithKDifferentInteger {
    
    /**
     * Counts subarrays with exactly k distinct integers.
     */
    public int subarraysWithKDistinct(int[] nums, int k) {
        return countAtMost(nums, k) - countAtMost(nums, k - 1);
    }
    
    /**
     * Helper: Counts subarrays with at most k distinct integers.
     */
    private int countAtMost(int[] nums, int k) {
        if (k < 0) return 0;
        
        int count = 0;
        int left = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int right = 0; right < nums.length; right++) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            
            // Shrink window if too many distinct
            while (map.size() > k) {
                map.put(nums[left], map.get(nums[left]) - 1);
                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                left++;
            }
            
            // All subarrays from left to right are valid
            count += (right - left + 1);
        }
        
        return count;
    }
}
