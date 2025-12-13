package com.javarena.dsa.algorithms.dynamicProgramming;

/**
 * Count Square Submatrices with All Ones
 *
 * <p><b>Problem Statement:</b><br>
 * Given m×n binary matrix, count number of square submatrices with all 1s.
 *
 * <p><b>Intuition & Approach:</b><br>
 * DP with square size tracking:
 * - dp[i][j] = size of largest square with bottom-right corner at (i,j)
 * - If matrix[i][j] = 1:
 *   - Check three neighbors: left, top, top-left diagonal
 *   - dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])
 * - If matrix[i][j] = 0: dp[i][j] = 0
 * 
 * Key insight: dp[i][j] = k means there are k squares ending at (i,j)
 * - 1×1 square, 2×2 square, ..., k×k square
 * 
 * Sum all dp values = total count of squares.
 *
 * <p><b>Time Complexity:</b> O(M × N) - Visit each cell once
 * <br><b>Space Complexity:</b> O(M × N) for DP table, optimizable to O(N)
 */
public class CountSquareSubmatricesWithAllOnes {
    
    /**
     * Counts all square submatrices with all 1s.
     */
    public int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int count = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = 1 + Math.min(dp[i - 1][j], 
                                    Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                    }
                    count += dp[i][j];
                }
            }
        }
        
        return count;
    }
}
