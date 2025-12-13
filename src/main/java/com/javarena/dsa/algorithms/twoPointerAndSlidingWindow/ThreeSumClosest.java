package com.javarena.dsa.algorithms.twoPointerAndSlidingWindow;

import java.util.Arrays;

/**
 * 3Sum Closest
 *
 * <p><b>Problem Statement:</b><br>
 * Given an integer array nums and target, find three integers whose sum is closest to target.
 * Return the sum of the three integers. Assume each input has exactly one solution.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Similar to 3Sum but instead of finding exact target, find closest sum:
 * - Sort array to enable two-pointer technique
 * - Fix first number, use two pointers for remaining two
 * - Track minimum difference from target
 * - For each triplet sum:
 *   - If sum == target: return immediately (closest possible)
 *   - If |target - sum| < minDiff: update result
 *   - If sum > target: move right pointer left (decrease sum)
 *   - If sum < target: move left pointer right (increase sum)
 * 
 * Key insight: Sorted array allows efficient pointer movement towards target.
 *
 * <p><b>Time Complexity:</b> O(N²) - O(N log N) sort + O(N²) two-pointer loop
 * <br><b>Space Complexity:</b> O(1) - Constant extra space
 */
public class ThreeSumClosest {
    
    /**
     * Finds sum of three integers closest to target.
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = 0;
        int minDiff = Integer.MAX_VALUE;
        int n = nums.length;
        
        for (int i = 0; i < n - 1; i++) {
            // Skip duplicates
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            
            int j = i + 1;
            int k = n - 1;
            
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                
                // Exact match - return immediately
                if (sum == target) return target;
                
                // Update if closer
                if (Math.abs(target - sum) < minDiff) {
                    ans = sum;
                    minDiff = Math.abs(target - sum);
                }
                
                // Move pointers based on sum vs target
                if (sum > target) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        
        return ans;
    }
}
