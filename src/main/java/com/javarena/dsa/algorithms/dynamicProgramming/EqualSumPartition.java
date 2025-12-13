package com.javarena.dsa.algorithms.dynamicProgramming;

/**
 * Equal Sum Partition (Partition Equal Subset Sum)
 *
 * <p><b>Problem Statement:</b><br>
 * Determine if array can be partitioned into two subsets with equal sum.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Reduce to subset sum problem:
 * - If total sum is odd: impossible to partition equally
 * - If even: find if subset exists with sum = totalSum / 2
 * - If one subset has sum/2, other automatically has sum/2
 * 
 * Standard 0/1 knapsack DP:
 * - dp[i][j] = can we achieve sum j using first i elements
 * - If nums[i] > j: can't include, dp[i][j] = dp[i-1][j]
 * - Else: dp[i][j] = dp[i-1][j] OR dp[i-1][j-nums[i]]
 * 
 * Answer: dp[n][target] where target = sum/2.
 *
 * <p><b>Time Complexity:</b> O(N × Sum) - N elements, Sum = total sum
 * <br><b>Space Complexity:</b> O(N × Sum) for 2D DP, O(Sum) for space-optimized
 */
public class EqualSumPartition {
    
    /**
     * Checks if equal partition possible using subset sum DP.
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        
        if (sum % 2 == 1) return false;
        
        int target = sum / 2;
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
    
    /**
     * Space-optimized version using 1D array.
     */
    public boolean canPartitionOptimized(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        
        if (sum % 2 == 1) return false;
        
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        
        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }
        
        return dp[target];
    }
}
