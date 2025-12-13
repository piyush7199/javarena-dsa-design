package com.javarena.dsa.algorithms.twoPointerAndSlidingWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Sum Problems Using Two Pointers
 *
 * <p><b>Problem Statement:</b><br>
 * Collection of sum-based problems solved with two-pointer technique:
 * 1. Three Sum - Find all unique triplets that sum to zero
 * 2. Four Sum - Find all unique quadruplets that sum to target
 *
 * <p><b>Intuition & Approach:</b><br>
 * General pattern for K-sum problems:
 * - Sort array first to enable two-pointer technique
 * - Fix K-2 numbers, use two pointers for remaining two
 * - Skip duplicates to ensure unique combinations
 * 
 * Three Sum strategy:
 * - Fix first number, reduce to Two Sum problem
 * - Use two pointers (left/right) on remaining sorted subarray
 * - If sum == target: record triplet, skip duplicates
 * - If sum < target: move left pointer right
 * - If sum > target: move right pointer left
 * 
 * Four Sum extends this by fixing two numbers first.
 *
 * <p><b>Time Complexity:</b> O(N²) for 3Sum, O(N³) for 4Sum
 * <br><b>Space Complexity:</b> O(1) excluding output list
 */
public class TwoPointerSum {

    /**
     * Three Sum: Find all unique triplets that sum to zero.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            // Skip duplicates for first number
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            
            // Two-pointer for remaining two numbers
            int j = i + 1;
            int k = n - 1;
            
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                    
                    // Skip duplicates for second number
                    while (j < k && nums[j] == nums[j - 1]) j++;
                    // Skip duplicates for third number
                    while (j < k && nums[k] == nums[k + 1]) k--;
                    
                } else if (sum < 0) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        
        return ans;
    }

    /**
     * Four Sum: Find all unique quadruplets that sum to target.
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            // Skip duplicates for first number
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            
            for (int j = i + 1; j < n; j++) {
                // Skip duplicates for second number
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                
                // Two-pointer for remaining two numbers
                int k = j + 1;
                int l = n - 1;
                
                while (k < l) {
                    long sum = (long) nums[i] + nums[j] + nums[k] + nums[l];
                    
                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        k++;
                        l--;
                        
                        // Skip duplicates
                        while (k < l && nums[k] == nums[k - 1]) k++;
                        while (k < l && nums[l] == nums[l + 1]) l--;
                        
                    } else if (sum < target) {
                        k++;
                    } else {
                        l--;
                    }
                }
            }
        }
        
        return ans;
    }
}
