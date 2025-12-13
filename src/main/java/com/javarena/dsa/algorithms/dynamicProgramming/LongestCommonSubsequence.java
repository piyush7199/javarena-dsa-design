package com.javarena.dsa.algorithms.dynamicProgramming;

import java.util.Arrays;

/**
 * Longest Common Subsequence (LCS)
 *
 * <p><b>Problem Statement:</b><br>
 * Find length of longest subsequence common to both strings.
 * Subsequence maintains relative order but need not be contiguous.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Character matching with recursion/DP:
 * 
 * - If characters match: include both, LCS = 1 + LCS(i-1, j-1)
 * - If don't match: try skipping from either string
 *   - Skip from first: LCS(i-1, j)
 *   - Skip from second: LCS(i, j-1)
 *   - Take maximum
 * 
 * Three approaches:
 * 1. Brute force O(2^(N+M)): Try all possibilities recursively
 * 2. Memoization O(N×M): Cache subproblem results
 * 3. Tabulation O(N×M): Build DP table bottom-up
 * 
 * DP state: dp[i][j] = LCS of first i chars of text1 and first j chars of text2
 *
 * <p><b>Time Complexity:</b> O(N × M) for DP approaches
 * <br><b>Space Complexity:</b> O(N × M) for 2D DP, optimizable to O(min(N,M))
 */
public class LongestCommonSubsequence {

    /**
     * Brute force recursive approach.
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
        
        return Math.max(
            bruteHelper(text1, text2, ind1 - 1, ind2),
            bruteHelper(text1, text2, ind1, ind2 - 1)
        );
    }

    /**
     * Memoization approach.
     */
    public int memoLongestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        
        return memoHelper(text1, text2, n, m, dp);
    }

    private int memoHelper(String text1, String text2, int ind1, int ind2, int[][] dp) {
        if (ind1 <= 0 || ind2 <= 0) return 0;
        if (dp[ind1][ind2] != -1) return dp[ind1][ind2];

        if (text1.charAt(ind1 - 1) == text2.charAt(ind2 - 1)) {
            return dp[ind1][ind2] = 1 + memoHelper(text1, text2, ind1 - 1, ind2 - 1, dp);
        }
        
        return dp[ind1][ind2] = Math.max(
            memoHelper(text1, text2, ind1 - 1, ind2, dp),
            memoHelper(text1, text2, ind1, ind2 - 1, dp)
        );
    }

    /**
     * Tabulation approach - most efficient.
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n][m];
    }
}
