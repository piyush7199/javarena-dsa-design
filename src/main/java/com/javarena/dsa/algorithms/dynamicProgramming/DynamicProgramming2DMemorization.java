package com.javarena.dsa.algorithms.dynamicProgramming;

import java.util.Arrays;

/**
 * Maximum Points (Ninja's Training) - Memoization Approach
 *
 * <p><b>Problem Statement:</b><br>
 * Given N days and 3 activities per day with points, find maximum points.
 * Constraint: Cannot do same activity on consecutive days.
 *
 * <p><b>Intuition & Approach:</b><br>
 * DP with activity constraint:
 * - State: dp[day][lastActivity] = max points from day onwards
 * - lastActivity tracks what was done yesterday (to avoid repetition)
 * - For each day, try all 3 activities except lastActivity
 * - Choose activity giving maximum points
 * 
 * Memoization (top-down):
 * - Start from last day, recurse backwards
 * - Cache results to avoid recomputation
 * - Base case: day 0, return max of activities ≠ lastActivity
 * 
 * Recurrence: dp[i][k] = max(arr[i][j] + dp[i-1][j]) for all j ≠ k
 *
 * <p><b>Time Complexity:</b> O(N × 4 × 3) = O(N) - N days, 4 states, 3 choices
 * <br><b>Space Complexity:</b> O(N × 4) for memoization table + O(N) recursion stack
 */
public class DynamicProgramming2DMemorization {

    /**
     * Finds maximum points using memoization.
     */
    public int maximumPoints(int[][] arr) {
        int[][] dp = new int[arr.length][4];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return maximumPointsHelper(arr, arr.length - 1, 3, dp);
    }

    /**
     * Recursive helper with memoization.
     */
    private int maximumPointsHelper(int[][] arr, int i, int k, int[][] dp) {
        if (i == 0) {
            int maxi = 0;
            for (int j = 0; j < 3; j++) {
                if (k != j) {
                    maxi = Math.max(maxi, arr[i][j]);
                }
            }
            return maxi;
        }
        
        if (dp[i][k] != -1) return dp[i][k];
        
        int ans = 0;
        for (int j = 0; j < 3; j++) {
            if (k != j) {
                ans = Math.max(ans, arr[i][j] + maximumPointsHelper(arr, i - 1, j, dp));
            }
        }
        
        return dp[i][k] = ans;
    }

    /**
     * Space-optimized memoization version.
     */
    public int maximumPointsSpaceOptimized(int[][] arr) {
        int n = arr.length;
        int[] prev = new int[4];
        
        prev[0] = Math.max(arr[0][1], arr[0][2]);
        prev[1] = Math.max(arr[0][0], arr[0][2]);
        prev[2] = Math.max(arr[0][0], arr[0][1]);
        prev[3] = Math.max(arr[0][0], Math.max(arr[0][1], arr[0][2]));
        
        for (int day = 1; day < n; day++) {
            int[] curr = new int[4];
            for (int last = 0; last < 4; last++) {
                for (int task = 0; task < 3; task++) {
                    if (task != last) {
                        curr[last] = Math.max(curr[last], arr[day][task] + prev[task]);
                    }
                }
            }
            prev = curr;
        }
        
        return prev[3];
    }
}
