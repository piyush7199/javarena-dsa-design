package org.example.coding.algorithms;

import java.util.Arrays;

public class DynamicProgramming2DMemorization {

    public int maximumPoints(int[][] arr) {
        // code here
        int[][] dp = new int[arr.length][4];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return maximumPointsHelper(arr, arr.length - 1, 3, dp);
    }

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

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return uniquePathsHelper(m - 1, n - 1, dp);
    }

    private int uniquePathsHelper(int m, int n, int[][] dp) {
        if (m == 0 && n == 0) return 1;
        if (m < 0 || n < 0) return 0;
        if (dp[m][n] != -1) return dp[m][n];
        int left = uniquePathsHelper(m, n - 1, dp);
        int up = uniquePathsHelper(m - 1, n, dp);
        return dp[m][n] = left + up;
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[n - 1][m - 1] == 1) return 0;
        int[][] dp = new int[n][m];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return uniquePathsWithObstacles(n - 1, m - 1, obstacleGrid, dp);
    }

    private int uniquePathsWithObstacles(int i, int j, int[][] grid, int[][] dp) {
        if (i == 0 && j == 0) return 1;
        if (i < 0 || j < 0) return 0;
        if (grid[i][j] == 1) return 0;
        if (dp[i][j] != -1) return dp[i][j];
        int up = uniquePathsWithObstacles(i - 1, j, grid, dp);
        int left = uniquePathsWithObstacles(i, j - 1, grid, dp);
        return dp[i][j] = up + left;
    }
}
