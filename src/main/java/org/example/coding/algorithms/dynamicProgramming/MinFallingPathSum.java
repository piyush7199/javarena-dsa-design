package org.example.coding.algorithms.dynamicProgramming;

import java.util.Arrays;

public class MinFallingPathSum {
    /**
     * Solves the minimum falling path sum using pure recursion (brute force).
     * <p>
     * Intuition:
     * - At each cell, recursively try to move to the cell directly above,
     * diagonally left above, and diagonally right above.
     * - Return the minimum path sum reaching the top.
     * <p>
     * Time Complexity: O(3^n) — exponential due to 3 choices at each level.
     * Space Complexity: O(n) — recursion stack.
     */
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            ans = Math.min(ans, minFallingPathSum(matrix, n - 1, j, n));
        }
        return ans;
    }

    private int minFallingPathSum(int[][] matrix, int i, int j, int m) {
        if (j < 0 || j >= m) {
            return (int) 1e9;
        }
        if (i == 0) return matrix[i][j];
        int up = matrix[i][j] + minFallingPathSum(matrix, i - 1, j, m);
        int digLeft = matrix[i][j] + minFallingPathSum(matrix, i - 1, j - 1, m);
        int digRight = matrix[i][j] + minFallingPathSum(matrix, i - 1, j + 1, m);
        return Math.min(up, Math.min(digLeft, digRight));
    }

    /**
     * Solves the minimum falling path sum using recursion with memoization.
     * <p>
     * Intuition:
     * - Uses a DP table to cache results of subproblems and avoid recomputation.
     * <p>
     * <p>
     * Time Complexity: O(n * m)
     * Space Complexity: O(n * m) + O(n) for recursion stack.
     */
    public int minFallingPathSumMemorization(int[][] matrix) {
        int n = matrix.length;
        int ans = Integer.MAX_VALUE;

        int[][] dp = new int[n][n];
        for (int[] row : dp) Arrays.fill(row, -1);

        for (int j = 0; j < n; j++) {
            ans = Math.min(ans, minFallingPathSum(matrix, n - 1, j, n, dp));
        }
        return ans;
    }

    private int minFallingPathSum(int[][] matrix, int i, int j, int m, int[][] dp) {
        if (j < 0 || j >= m) {
            return (int) 1e9;
        }

        if (i == 0) return matrix[i][j];

        if (dp[i][j] != -1) return dp[i][j];

        int up = matrix[i][j] + minFallingPathSum(matrix, i - 1, j, m, dp);
        int digLeft = matrix[i][j] + minFallingPathSum(matrix, i - 1, j - 1, m, dp);
        int digRight = matrix[i][j] + minFallingPathSum(matrix, i - 1, j + 1, m, dp);
        return dp[i][j] = Math.min(up, Math.min(digLeft, digRight));
    }

    /**
     * Solves the minimum falling path sum using bottom-up tabulation.
     * <p>
     * Intuition:
     * - Build a DP table where dp[i][j] stores the minimum path sum to reach cell (i, j).
     * - Transition from the row above (up, diag-left, diag-right).
     * <p>
     * Time Complexity: O(n * m)
     * Space Complexity: O(n * m)
     */
    public int minFallingPathSumTabulation(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int ans = Integer.MAX_VALUE;

        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0) {
                    dp[i][j] = matrix[i][j];
                } else {
                    int up = matrix[i][j] + dp[i - 1][j];
                    int digLeft = matrix[i][j];
                    if (j > 0) {
                        digLeft += dp[i - 1][j - 1];
                    } else {
                        digLeft += ((int) 1e9);
                    }

                    int digRight = matrix[i][j];
                    if (j < m - 1) {
                        digRight += dp[i - 1][j + 1];
                    } else {
                        digRight += ((int) 1e9);
                    }
                    dp[i][j] = Math.min(up, Math.min(digLeft, digRight));
                }
            }
        }

        for (int j = 0; j < m; j++) {
            ans = Math.min(ans, dp[n - 1][j]);
        }
        return ans;
    }

    /**
     * Space-optimized DP version of the minimum falling path sum.
     * <p>
     * Intuition:
     * - Only the previous row is needed at each step.
     * - Use two 1D arrays (prev and curr) to reduce space.
     * <p>
     * Time Complexity: O(n * m)
     * Space Complexity: O(m)
     */
    public int minFallingPathSumSpaceOptimized(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int ans = Integer.MAX_VALUE;

        int[] prev = new int[m];

        for (int i = 0; i < n; i++) {
            int[] curr = new int[m];

            for (int j = 0; j < m; j++) {
                if (i == 0) {
                    curr[j] = matrix[i][j];
                } else {
                    int up = matrix[i][j] + prev[j];
                    int digLeft = matrix[i][j];
                    if (j > 0) {
                        digLeft += prev[j - 1];
                    } else {
                        digLeft += ((int) 1e9);
                    }

                    int digRight = matrix[i][j];
                    if (j < m - 1) {
                        digRight += prev[j + 1];
                    } else {
                        digRight += ((int) 1e9);
                    }
                    curr[j] = Math.min(up, Math.min(digLeft, digRight));
                }
            }
            prev = curr;
        }

        for (int j = 0; j < m; j++) {
            ans = Math.min(ans, prev[j]);
        }
        return ans;
    }
}
