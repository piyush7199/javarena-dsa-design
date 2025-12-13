package com.javarena.dsa.algorithms.dynamicProgramming;

import java.util.Arrays;

/**
 * Coin Change
 *
 * <p><b>Problem Statement:</b><br>
 * Given coins of different denominations and total amount, find minimum number of coins
 * needed to make that amount. Each coin can be used unlimited times. Return -1 if impossible.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Unbounded knapsack variant - three approaches:
 * 
 * 1. Brute Force O(2^N): Try take/not-take for each coin recursively
 * 2. Memoization O(N×Amount): Cache results of subproblems (top-down DP)
 * 3. Tabulation O(N×Amount): Build solution bottom-up using DP table
 * 
 * DP state: dp[i][j] = min coins needed using first i coins for amount j
 * - If coin[i] > j: can't use it, dp[i][j] = dp[i-1][j]
 * - Else: min of (not take: dp[i-1][j], take: 1 + dp[i][j-coin[i]])
 * 
 * Space optimization: Use 1D array since only need previous row.
 *
 * <p><b>Time Complexity:</b> O(N × Amount) for DP approaches
 * <br><b>Space Complexity:</b> O(N × Amount) for 2D DP, O(Amount) for optimized
 */
public class CoinChange {

    /**
     * Brute force recursive solution.
     */
    public int bruteCoinChange(int[] coins, int amount) {
        int n = coins.length;
        int ans = bruteHelper(coins, n - 1, amount);
        return ans >= (int) 1e9 ? -1 : ans;
    }

    private int bruteHelper(int[] coins, int ind, int amount) {
        if (amount == 0) return 0;
        if (ind < 0) return (int) 1e9;

        int notTake = bruteHelper(coins, ind - 1, amount);
        int take = (int) (1e9);
        if (coins[ind] <= amount) {
            take = 1 + bruteHelper(coins, ind, amount - coins[ind]);
        }
        return Math.min(take, notTake);
    }

    /**
     * Memoization (Top-down DP) solution.
     */
    public int memoCoinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        
        int ans = memoHelper(coins, n - 1, amount, dp);
        return ans >= (int) 1e9 ? -1 : ans;
    }

    private int memoHelper(int[] coins, int ind, int amount, int[][] dp) {
        if (amount == 0) return 0;
        if (ind < 0) return (int) 1e9;
        
        if (dp[ind][amount] != -1) return dp[ind][amount];

        int notTake = memoHelper(coins, ind - 1, amount, dp);
        int take = (int) (1e9);
        if (coins[ind] <= amount) {
            take = 1 + memoHelper(coins, ind, amount - coins[ind], dp);
        }
        
        return dp[ind][amount] = Math.min(take, notTake);
    }

    /**
     * Tabulation (Bottom-up DP) solution.
     */
    public int tabulationCoinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        for (int j = 0; j <= amount; j++) {
            if (j % coins[0] == 0) {
                dp[0][j] = j / coins[0];
            } else {
                dp[0][j] = (int) 1e9;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= amount; j++) {
                int notTake = dp[i - 1][j];
                int take = (int) 1e9;
                if (coins[i] <= j) {
                    take = 1 + dp[i][j - coins[i]];
                }
                dp[i][j] = Math.min(take, notTake);
            }
        }

        int ans = dp[n - 1][amount];
        return ans >= (int) 1e9 ? -1 : ans;
    }

    /**
     * Space-optimized tabulation using 1D array.
     */
    public int coinChange(int[] coins, int amount) {
        int[] prev = new int[amount + 1];
        
        for (int j = 0; j <= amount; j++) {
            if (j % coins[0] == 0) {
                prev[j] = j / coins[0];
            } else {
                prev[j] = (int) 1e9;
            }
        }

        for (int i = 1; i < coins.length; i++) {
            int[] curr = new int[amount + 1];
            for (int j = 0; j <= amount; j++) {
                int notTake = prev[j];
                int take = (int) 1e9;
                if (coins[i] <= j) {
                    take = 1 + curr[j - coins[i]];
                }
                curr[j] = Math.min(take, notTake);
            }
            prev = curr;
        }

        return prev[amount] >= (int) 1e9 ? -1 : prev[amount];
    }
}
