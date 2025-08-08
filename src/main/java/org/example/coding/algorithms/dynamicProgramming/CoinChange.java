package org.example.coding.algorithms.dynamicProgramming;

import java.util.Arrays;

public class CoinChange {

    /**
     * Solves the classic Coin Change problem using brute force recursion.
     * <p>
     * Intuition:
     * Try all combinations by recursively either taking or not taking the current coin.
     * Since we can take a coin multiple times, we call the same index again after taking it.
     * <p>
     * Approach:
     * - At each recursive call, decide whether to include the current coin or skip it.
     * - If included, subtract its value from the remaining amount and recurse.
     * - If skipped, move to the previous coin.
     * - Use a large number (1e9) to denote an invalid/unreachable state.
     * - Base case: amount == 0 ⇒ 0 coins needed; ind < 0 ⇒ invalid.
     * <p>
     * Time Complexity: O(2^n)
     * - For each coin, we branch into two choices (take or not), leading to exponential combinations.
     * <p>
     * Space Complexity: O(n)
     * - Due to the recursion stack, which at most goes n levels deep.
     */
    public int bruteCoinChange(int[] coins, int amount) {
        int n = coins.length;
        int ans = bruteHelper(coins, n - 1, amount);
        return ans >= (int) 1e9 ? -1 : ans;
    }


    /**
     * Recursive helper for brute force coin change.
     */
    public int bruteHelper(int[] coins, int ind, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (ind < 0) {
            return (int) 1e9;
        }

        int notTake = bruteHelper(coins, ind - 1, amount);
        int take = (int) (1e9);
        if (coins[ind] <= amount) {
            take = 1 + bruteHelper(coins, ind, amount - coins[ind]);
        }
        return Math.min(take, notTake);
    }

    /**
     * Solves the Coin Change problem using top-down DP with memoization.
     * <p>
     * Intuition:
     * In the brute force approach, many subproblems are recomputed.
     * With memoization, we store the results of subproblems and reuse them, significantly improving performance.
     * <p>
     * Approach:
     * - Use a 2D DP array where dp[i][amt] stores the minimum coins needed using coins[0..i] to form amount 'amt'.
     * - The recursive structure is same as the brute force, but with memoization.
     * - Base cases and recursive transitions remain unchanged.
     * <p>
     * Time Complexity: O(n * amount)
     * - Each subproblem (ind, amount) is solved only once.
     * <p>
     * Space Complexity: O(n * amount) for DP array + O(n) recursion stack space.
     */
    public int memoizationCoinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int ans = memoizationHelper(coins, n - 1, amount, dp);
        return ans >= (int) 1e9 ? -1 : ans;
    }

    /**
     * Recursive helper for memoization-based coin change.
     */
    public int memoizationHelper(int[] coins, int ind, int amount, int[][] dp) {

        if (ind == 0) {
            if (amount % coins[ind] == 0) {
                return amount / coins[ind];
            }
            return (int) 1e9;
        }

        if (dp[ind][amount] != -1) return dp[ind][amount];

        int notTake = memoizationHelper(coins, ind - 1, amount, dp);
        int take = (int) (1e9);
        if (coins[ind] <= amount) {
            take = 1 + memoizationHelper(coins, ind, amount - coins[ind], dp);
        }

        dp[ind][amount] = Math.min(take, notTake);
        return dp[ind][amount];
    }

    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        for (int i = 0; i <= amount; i++) {
            if (i % coins[0] == 0)
                dp[0][i] = i / coins[0];
            else
                dp[0][i] = (int) 1e9;
        }

        for (int ind = 1; ind < n; ind++) {
            for (int target = 0; target <= amount; target++) {
                int notTake = dp[ind - 1][target];
                int take = (int) (1e9);
                if (coins[ind] <= target) {
                    take = 1 + dp[ind][target - coins[ind]];
                }

                dp[ind][target] = Math.min(take, notTake);
            }
        }

        int ans = dp[n - 1][amount];
        return ans >= (int) 1e9 ? -1 : ans;
    }

    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return memoizationCountHelper(coins, n - 1, amount, dp);
    }

    public int memoizationCountHelper(int[] coins, int ind, int amount, int[][] dp) {

        if (ind == 0) {
            if (amount % coins[ind] == 0) {
                return 1;
            }
            return 0;
        }

        if (dp[ind][amount] != -1) return dp[ind][amount];

        int notTake = memoizationCountHelper(coins, ind - 1, amount, dp);
        int take = 0;
        if (coins[ind] <= amount) {
            take = memoizationCountHelper(coins, ind, amount - coins[ind], dp);
        }

        dp[ind][amount] = notTake + take;
        return dp[ind][amount];
    }
}
