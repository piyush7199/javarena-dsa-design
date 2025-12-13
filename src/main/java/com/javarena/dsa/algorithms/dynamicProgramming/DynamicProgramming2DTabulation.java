package com.javarena.dsa.algorithms.dynamicProgramming;

/**
 * Maximum Points (Ninja's Training) - Tabulation Approach
 *
 * <p><b>Problem Statement:</b><br>
 * Given N days and 3 activities per day with points, find maximum points.
 * Constraint: Cannot do same activity on consecutive days.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Bottom-up DP building solution iteratively:
 * - State: dp[day][last] = max points up to day when last activity was 'last'
 * - last = 3 means no constraint (for initialization)
 * - Build table from day 0 to N-1
 * 
 * Tabulation (bottom-up):
 * - Initialize dp[0] with base cases
 * - For each day and lastActivity:
 *   - Try all 3 activities except lastActivity
 *   - Take maximum: dp[day][last] = max(arr[day][task] + dp[day-1][task])
 * 
 * Answer: dp[N-1][3] (max points with no constraint on last day).
 * 
 * Space optimization: Only need previous day's dp values.
 *
 * <p><b>Time Complexity:</b> O(N × 4 × 3) = O(N) - N days, 4 states, 3 choices
 * <br><b>Space Complexity:</b> O(N × 4) for 2D DP, O(1) for space-optimized
 */
public class DynamicProgramming2DTabulation {

    /**
     * Finds maximum points using tabulation (bottom-up DP).
     */
    public int maximumPoints(int[][] arr) {
        int n = arr.length;
        int[][] dp = new int[n][4];

        dp[0][0] = Math.max(arr[0][1], arr[0][2]);
        dp[0][1] = Math.max(arr[0][0], arr[0][2]);
        dp[0][2] = Math.max(arr[0][0], arr[0][1]);
        dp[0][3] = Math.max(arr[0][0], Math.max(arr[0][1], arr[0][2]));

        for (int day = 1; day < n; day++) {
            for (int last = 0; last < 4; last++) {
                dp[day][last] = 0;
                for (int task = 0; task <= 2; task++) {
                    if (task != last) {
                        int activity = arr[day][task] + dp[day - 1][task];
                        dp[day][last] = Math.max(dp[day][last], activity);
                    }
                }
            }
        }

        return dp[arr.length - 1][3];
    }

    /**
     * Space-optimized version using 1D arrays.
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
                for (int task = 0; task <= 2; task++) {
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
