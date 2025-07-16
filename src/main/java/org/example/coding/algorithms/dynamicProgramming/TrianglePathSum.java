package org.example.coding.algorithms.dynamicProgramming;

import java.util.Arrays;
import java.util.List;

public class TrianglePathSum {

    /**
     * Solves the triangle minimum path sum using recursion.
     * <p>
     * Intuition:
     * - Starting from the top, recursively explore both downward paths (i+1, j) and (i+1, j+1).
     * - Return the minimum path sum to the bottom.
     * <p>
     * Time Complexity: O(2^n) — due to exploring two choices at each level.
     * Space Complexity: O(n) — recursion stack depth.
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        return minimumTotal(triangle, 0, 0, n);
    }

    private int minimumTotal(List<List<Integer>> triangle, int i, int j, int n) {
        if (i == n - 1) {
            return triangle.get(i).get(j);
        }

        int next = minimumTotal(triangle, i + 1, j, n);
        int ele = minimumTotal(triangle, i + 1, j + 1, n);
        return triangle.get(i).get(j) + Math.min(next, ele);
    }

    /**
     * Top-down DP with memoization to solve the triangle minimum path sum.
     * <p>
     * Intuition:
     * - Use a 2D dp array to cache the results of subproblems.
     * - Avoid redundant recursive calls for overlapping subproblems.
     * <p>
     * Time Complexity: O(n^2)
     * Space Complexity: O(n^2) for dp + O(n) recursion stack
     */
    public int minimumTotalMemorization(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return minimumTotal(triangle, 0, 0, n, dp);
    }

    private int minimumTotal(List<List<Integer>> triangle, int i, int j, int n, int[][] dp) {
        if (i == n - 1) {
            return triangle.get(i).get(j);
        }
        if (dp[i][j] != -1) return dp[i][j];

        int next = minimumTotal(triangle, i + 1, j, n, dp);
        int ele = minimumTotal(triangle, i + 1, j + 1, n, dp);
        return dp[i][j] = triangle.get(i).get(j) + Math.min(next, ele);
    }

    /**
     * Bottom-up DP approach to solve the triangle minimum path sum.
     * <p>
     * Intuition:
     * - Start from the last row and build the solution upwards.
     * - At each cell, store the minimum sum to reach the bottom.
     * <p>
     * <p>
     * Time Complexity: O(n^2)
     * Space Complexity: O(n^2)
     */
    public int minimumTotalTabulation(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = triangle.get(n - 1).get(j);
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i + 1][j], dp[i + 1][j + 1]);
            }
        }

        return dp[0][0];
    }

    /**
     * Space optimized DP approach for the triangle minimum path sum.
     * <p>
     * Intuition:
     * - At each row, we only need the results of the row below.
     * - Use 1D arrays to store previous and current row results.
     *
     * @param triangle 2D triangle list.
     * @return Minimum path sum from top to bottom.
     * <p>
     * Time Complexity: O(n^2)
     * Space Complexity: O(n)
     */
    public int minimumTotalSpaceOptimized(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] next = new int[n];
        for (int j = 0; j < n; j++) {
            next[j] = triangle.get(n - 1).get(j);
        }

        for (int i = n - 2; i >= 0; i--) {
            int[] cur = new int[n];
            for (int j = i; j >= 0; j--) {
                cur[j] = triangle.get(i).get(j) + Math.min(next[j], next[j + 1]);
            }
            next = cur;
        }

        return next[0];
    }
}
