package org.example.coding.algorithms.dynamicProgramming;

import java.util.Arrays;

public class MinPathSum {
    /**
     * Finds the minimum path sum in a grid using brute-force recursion.
     * <p>
     * Intuition:
     * - Explore all possible paths recursively (right and down).
     * - Return the minimum path sum that reaches the top-left corner.
     * <p>
     * Time Complexity: O(2^(n+m)) - Exponential due to overlapping subproblems.
     * Space Complexity: O(n + m) - Stack space for recursion.
     */
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        return minPathSum(grid, n - 1, m - 1);
    }

    private int minPathSum(int[][] grid, int i, int j) {
        if (i == 0 && j == 0) {
            return grid[i][j];
        }
        if (i < 0 || j < 0) {
            return (int) 1e9;
        }

        int left = grid[i][j] + minPathSum(grid, i - 1, j);
        int up = grid[i][j] + minPathSum(grid, i, j - 1);
        return Math.min(up, left);
    }

    /**
     * Finds the minimum path sum using recursion with memoization (top-down DP).
     * <p>
     * Intuition:
     * - Same logic as brute-force but caches results of overlapping subproblems
     * to avoid recomputation.
     * <p>
     * Time Complexity: O(n * m)
     * Space Complexity: O(n * m) + O(n + m) (DP table + recursion stack).
     */
    public int minPathSumMemorization(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        for (int[] row : dp) Arrays.fill(row, -1);

        return minPathSum(grid, n - 1, m - 1, dp);
    }

    private int minPathSum(int[][] grid, int i, int j, int[][] dp) {
        if (i == 0 && j == 0) {
            return grid[i][j];
        }
        if (i < 0 || j < 0) {
            return (int) 1e9;
        }

        if (dp[i][j] != -1) return dp[i][j];

        int left = grid[i][j] + minPathSum(grid, i - 1, j, dp);
        int up = grid[i][j] + minPathSum(grid, i, j - 1, dp);
        return dp[i][j] = Math.min(up, left);
    }

    /**
     * Finds the minimum path sum using tabulation (bottom-up DP).
     * <p>
     * Intuition:
     * - Build a 2D DP table where dp[i][j] represents minimum sum to reach (i, j).
     * - Transition from left and top cells, adding current grid value.
     * <p>
     * Time Complexity: O(n * m)
     * Space Complexity: O(n * m) for the dp table.
     */
    public int minPathSumTabulation(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                } else {
                    int up = grid[i][j];
                    if (i > 0)
                        up += dp[i - 1][j];
                    else
                        up += (int) Math.pow(10, 9);

                    int left = grid[i][j];
                    if (j > 0)
                        left += dp[i][j - 1];
                    else
                        left += (int) Math.pow(10, 9); // Add a large value if out of bounds in the left direction

                    // Store the minimum of the two possible paths
                    dp[i][j] = Math.min(up, left);
                }
            }
        }

        return dp[n - 1][m - 1];
    }

    /**
     * Optimized version of tabulation using 1D arrays to reduce space complexity.
     * <p>
     * Intuition:
     * - Since only the previous row is needed, store just one row at a time.
     * - Reduces space from O(n*m) to O(m).
     * <p>
     * Time Complexity: O(n * m)
     * Space Complexity: O(m)
     */
    public int minPathSumSpaceOptimized(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[] prev = new int[m];

        for (int i = 0; i < n; i++) {
            int[] curr = new int[m];
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    curr[j] = grid[i][j];
                } else {
                    int up = grid[i][j];
                    if (i > 0)
                        up += prev[j]; // Add the value from above if it's not out of bounds
                    else
                        up += (int) Math.pow(10, 9); // Add a large value if out of bounds in the up direction

                    int left = grid[i][j];
                    if (j > 0)
                        left += curr[j - 1]; // Add the value from the left if it's not out of bounds
                    else
                        left += (int) Math.pow(10, 9); // Add a large value if out of bounds in the left direction

                    // Store the minimum of the two possible paths
                    curr[j] = Math.min(up, left);
                }
            }
            prev = curr;
        }

        return prev[m - 1];
    }
}
