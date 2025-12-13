package com.javarena.dsa.algorithms.dynamicProgramming;

import java.util.Arrays;

/**
 * Minimum Path Sum
 *
 * <p><b>Problem Statement:</b><br>
 * Given m×n grid filled with non-negative numbers, find path from top-left to bottom-right
 * that minimizes sum of numbers along path. Can only move right or down.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Grid path DP problem:
 * - At each cell, can come from top or left
 * - Choose path with minimum sum
 * - DP state: dp[i][j] = minimum path sum to reach cell (i,j)
 * - Recurrence: dp[i][j] = grid[i][j] + min(dp[i-1][j], dp[i][j-1])
 * 
 * Base cases:
 * - dp[0][0] = grid[0][0]
 * - First row: can only come from left
 * - First column: can only come from top
 * 
 * Space optimization: Use 1D array since only need previous row.
 *
 * <p><b>Time Complexity:</b> O(M × N) - Visit each cell once
 * <br><b>Space Complexity:</b> O(M × N) for 2D DP, O(N) for space-optimized
 */
public class MinPathSum {
    
    /**
     * Finds minimum path sum using 2D DP.
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        
        dp[0][0] = grid[0][0];
        
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        
        return dp[m - 1][n - 1];
    }
    
    /**
     * Space-optimized version using 1D array.
     */
    public int minPathSumOptimized(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
        
        dp[0] = grid[0][0];
        for (int j = 1; j < n; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }
        
        for (int i = 1; i < m; i++) {
            dp[0] += grid[i][0];
            for (int j = 1; j < n; j++) {
                dp[j] = grid[i][j] + Math.min(dp[j], dp[j - 1]);
            }
        }
        
        return dp[n - 1];
    }
}
