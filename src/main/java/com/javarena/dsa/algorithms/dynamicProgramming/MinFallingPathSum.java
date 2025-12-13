package com.javarena.dsa.algorithms.dynamicProgramming;

import java.util.Arrays;

/**
 * Minimum Falling Path Sum
 *
 * <p><b>Problem Statement:</b><br>
 * Given n×n matrix of integers, find minimum sum of falling path through matrix.
 * Falling path starts at any element in first row, choosing one element from each row.
 * Can move to row below at column i-1, i, or i+1 (diagonally down-left, down, down-right).
 *
 * <p><b>Intuition & Approach:</b><br>
 * Grid DP with three choices per cell:
 * - Can come from three positions in previous row
 * - Choose path with minimum sum
 * - DP state: dp[i][j] = min falling path sum ending at cell (i,j)
 * - Recurrence: dp[i][j] = matrix[i][j] + min(dp[i-1][j-1], dp[i-1][j], dp[i-1][j+1])
 * 
 * Answer: minimum value in last row of DP table.
 * 
 * Space optimization: Only need previous row for calculation.
 *
 * <p><b>Time Complexity:</b> O(N²) - Visit each cell once with 3 comparisons
 * <br><b>Space Complexity:</b> O(N²) for 2D DP, O(N) for space-optimized
 */
public class MinFallingPathSum {
    
    /**
     * Finds minimum falling path sum using 2D DP.
     */
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];
        
        for (int j = 0; j < n; j++) {
            dp[0][j] = matrix[0][j];
        }
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int down = dp[i - 1][j];
                int downLeft = (j > 0) ? dp[i - 1][j - 1] : Integer.MAX_VALUE;
                int downRight = (j < n - 1) ? dp[i - 1][j + 1] : Integer.MAX_VALUE;
                
                dp[i][j] = matrix[i][j] + Math.min(down, Math.min(downLeft, downRight));
            }
        }
        
        int minSum = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            minSum = Math.min(minSum, dp[n - 1][j]);
        }
        
        return minSum;
    }
}
