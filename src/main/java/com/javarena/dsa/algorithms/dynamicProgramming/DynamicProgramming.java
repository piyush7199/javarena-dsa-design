package com.javarena.dsa.algorithms.dynamicProgramming;

import java.util.Arrays;

/**
 * 0/1 Knapsack and Partition Problems
 *
 * <p><b>Problem Statement:</b><br>
 * Collection of classic DP problems:
 * 1. Partition Equal Subset Sum - can array be partitioned into two equal sum subsets
 * 2. 0/1 Knapsack - maximize value with weight constraint
 * 3. Subset Sum - check if subset exists with given sum
 *
 * <p><b>Intuition & Approach:</b><br>
 * Core pattern: Include/Exclude decision for each element
 * 
 * For Partition:
 * - If sum is odd: impossible
 * - Find subset with sum = totalSum/2
 * 
 * DP state: dp[i][j] = can we achieve sum j using first i elements
 * - Include: dp[i-1][j-nums[i]] if nums[i] ≤ j
 * - Exclude: dp[i-1][j]
 * - Result: dp[i][j] = Include OR Exclude
 * 
 * Space optimization: Use 1D array, iterate backwards.
 *
 * <p><b>Time Complexity:</b> O(N × Sum) - N elements, Sum = target sum
 * <br><b>Space Complexity:</b> O(N × Sum) for 2D DP, O(Sum) for 1D
 */
public class DynamicProgramming {
    
    /**
     * Partition Equal Subset Sum using 2D DP.
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int ele : nums) {
            sum += ele;
        }
        if (sum % 2 == 1) return false;
        
        int n = nums.length;
        int target = sum / 2;
        boolean[][] dp = new boolean[nums.length + 1][target + 1];
        
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= target; j++) {
                if (nums[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        
        return dp[n][target];
    }
    
    /**
     * 0/1 Knapsack - maximize value with weight constraint.
     */
    public int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], 
                                       values[i - 1] + dp[i - 1][w - weights[i - 1]]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        
        return dp[n][capacity];
    }
    
    /**
     * Subset Sum - check if subset exists with given sum.
     */
    public boolean subsetSum(int[] nums, int target) {
        int n = nums.length;
        boolean[][] dp = new boolean[n + 1][target + 1];
        
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                if (nums[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        
        return dp[n][target];
    }
}
