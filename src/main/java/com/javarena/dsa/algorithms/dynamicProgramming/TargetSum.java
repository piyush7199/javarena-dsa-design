package com.javarena.dsa.algorithms.dynamicProgramming;

import java.util.Arrays;

/**
 * Target Sum
 *
 * <p><b>Problem Statement:</b><br>
 * Given array of integers and target sum, count ways to assign + or - to each number
 * to reach target sum.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Transform to subset sum problem:
 * - Let S1 = sum of positive numbers, S2 = sum of negative numbers
 * - S1 - S2 = target
 * - S1 + S2 = total sum
 * - Solving: S1 = (total + target) / 2
 * 
 * Problem reduces to: count subsets with sum = (total + target) / 2
 * 
 * Handle edge cases:
 * - If (total + target) is odd: impossible, return 0
 * - If target > total: impossible, return 0
 * 
 * DP state: dp[i][j] = count ways to get sum j using first i elements
 *
 * <p><b>Time Complexity:</b> O(N × Sum) - N elements, Sum = (total+target)/2
 * <br><b>Space Complexity:</b> O(N × Sum) for 2D DP, optimizable to O(Sum)
 */
public class TargetSum {
    
    /**
     * Counts ways to reach target using subset sum approach.
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) sum += num;
        
        if (sum < Math.abs(target) || (sum + target) % 2 != 0) {
            return 0;
        }
        
        int subsetSum = (sum + target) / 2;
        return countSubsets(nums, subsetSum);
    }

    /**
     * Counts subsets with given sum using DP.
     */
    private int countSubsets(int[] nums, int target) {
        int n = nums.length;
        int[][] dp = new int[n + 1][target + 1];
        
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= nums[i - 1]) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[n][target];
    }
}
