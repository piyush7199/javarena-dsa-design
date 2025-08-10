package org.example.coding.algorithms.dynamicProgramming;

import java.util.Arrays;

public class LongestCommonSubsequence {

    /**
     * Finds the Longest Common Subsequence (LCS) between two strings using a brute-force recursive approach.
     *
     * <p><b>Intuition:</b>
     * The LCS problem asks for the longest sequence that appears in both strings in the same order (not necessarily contiguous).
     * The brute-force approach tries all possible subsequences by recursively comparing characters from the end of both strings.
     * If the characters match, we include them and move diagonally in both strings.
     * If they don't match, we explore two possibilities:
     * 1. Skip the last character of the first string
     * 2. Skip the last character of the second string
     * We take the maximum length from these two possibilities.
     *
     * <p><b>Approach:</b>
     * - Use recursion with indices starting from the end of both strings.
     * - If one index becomes zero, there is no subsequence left, return 0.
     * - If characters match: 1 + recursive call on both indices reduced by one.
     * - Else: take the max of moving one index back in either string.
     *
     * <p><b>Time Complexity:</b> O(2^(N+M))
     * <br>Reason: At each step, we branch into two recursive calls until we exhaust one string.
     *
     * <p><b>Space Complexity:</b> O(N+M)
     * <br>Reason: The recursion stack can go as deep as N+M in the worst case.
     */
    public int bruteLongestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        return bruteHelper(text1, text2, n, m);
    }

    private int bruteHelper(String text1, String text2, int ind1, int ind2) {
        if (ind1 <= 0 || ind2 <= 0) return 0;

        if (text1.charAt(ind1 - 1) == text2.charAt(ind2 - 1)) {
            return 1 + bruteHelper(text1, text2, ind1 - 1, ind2 - 1);
        }
        int take1 = bruteHelper(text1, text2, ind1 - 1, ind2);
        int take2 = bruteHelper(text1, text2, ind1, ind2 - 1);
        return Math.max(take1, take2);
    }

    /**
     * Finds the Longest Common Subsequence (LCS) between two strings using top-down dynamic programming (memoization).
     *
     * <p><b>Intuition:</b>
     * The brute-force solution recalculates the same subproblems multiple times.
     * By storing already-computed results in a memoization table (dp array),
     * we avoid redundant computations and reduce exponential complexity to polynomial.
     *
     * <p><b>Approach:</b>
     * - Create a 2D dp array of size (n+1) x (m+1) initialized to -1.
     * - Recursively solve subproblems as in the brute force approach.
     * - Before computing a subproblem, check if it's already stored in dp; if yes, return it.
     * - Fill dp with computed results for reuse.
     *
     * <p><b>Time Complexity:</b> O(N*M)
     * <br>Reason: Each subproblem (n, m) is computed at most once.
     *
     * <p><b>Space Complexity:</b> O(N*M + N+M)
     * <br>Reason: O(N*M) for the dp array, plus O(N+M) recursion stack space.
     */
    public int memoLongestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return memoHelper(text1, text2, n, m, dp);
    }

    private int memoHelper(String text1, String text2, int ind1, int ind2, int[][] dp) {
        if (ind1 <= 0 || ind2 <= 0) return 0;
        if (dp[ind1][ind2] != -1) return dp[ind1][ind2];
        if (text1.charAt(ind1 - 1) == text2.charAt(ind2 - 1)) {
            return dp[ind1][ind2] = 1 + memoHelper(text1, text2, ind1 - 1, ind2 - 1, dp);
        }
        int take1 = memoHelper(text1, text2, ind1 - 1, ind2, dp);
        int take2 = memoHelper(text1, text2, ind1, ind2 - 1, dp);
        return dp[ind1][ind2] = Math.max(take1, take2);
    }

    /**
     * Finds the Longest Common Subsequence (LCS) between two strings using bottom-up dynamic programming (tabulation).
     *
     * <p><b>Intuition:</b>
     * Instead of solving subproblems recursively, we can solve them iteratively from the smallest subproblems up.
     * Each dp[i][j] represents the length of the LCS for the first i characters of text1 and the first j characters of text2.
     *
     * <p><b>Approach:</b>
     * - Create a 2D dp array of size (n+1) x (m+1), initialized to 0.
     * - Iterate over all indices:
     * - If characters match: dp[i][j] = 1 + dp[i-1][j-1]
     * - Else: dp[i][j] = max(dp[i-1][j], dp[i][j-1])
     * - The bottom-right cell dp[n][m] contains the answer.
     *
     * <p><b>Time Complexity:</b> O(N*M)
     * <br>Reason: We fill an N x M table once.
     *
     * <p><b>Space Complexity:</b> O(N*M)
     * <br>Reason: The dp table stores N*M subproblem results.
     * Can be optimized to O(min(N, M)) space using rolling arrays.
     */
    public int tabularLongestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int ind1 = 1; ind1 <= n; ind1++) {
            for (int ind2 = 1; ind2 <= m; ind2++) {
                if (text1.charAt(ind1 - 1) == text2.charAt(ind2 - 1)) {
                    dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
                } else {
                    dp[ind1][ind2] = Math.max(dp[ind1 - 1][ind2], dp[ind1][ind2 - 1]);
                }
            }
        }
        return dp[n][m];
    }
}
